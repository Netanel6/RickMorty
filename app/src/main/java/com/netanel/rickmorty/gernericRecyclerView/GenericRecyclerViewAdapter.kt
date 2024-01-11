package com.netanel.rickmorty.gernericRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.netanel.rickmorty.BR
import com.netanel.rickmorty.gernericRecyclerView.GenericRecyclerViewAdapter.GenericViewHolder

typealias ViewHolderClickCallback = (Model?, GenericViewHolder) -> Unit

class GenericRecyclerViewAdapter(
    items: List<Model> = arrayListOf(),
    private val listener: Any? = null,
    private val viewHolderFactory: ViewHolderFactory? = null,
    private val onViewHolderClicked: ViewHolderClickCallback? = null
) : RecyclerView.Adapter<GenericViewHolder>() {
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Model>() {

        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean =
            oldItem.isEqualTo(newItem)

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean =
            oldItem.isEqualContentTo(newItem)
    })

    init {
        updateData(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val genericViewHolder = viewHolderFactory?.createViewHolder(parent, viewType)
            ?: GenericViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
            )
        genericViewHolder.setListener(listener)
        return genericViewHolder
    }

    override fun onViewRecycled(holder: GenericViewHolder) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    override fun getItemViewType(position: Int) = differ.currentList[position].getViewType()

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) =
        holder.bind(differ.currentList[position], onViewHolderClicked)

    override fun getItemCount(): Int = differ.currentList.size

    fun updateData(list: List<Model>, callback: (() -> Unit)? = null) {
        differ.submitList(list) {
            callback?.invoke()
        }
    }

    open class GenericViewHolder(
        protected open val binding: ViewDataBinding,
        protected var listener: Any? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(
            model: Model?,
            onViewHolderClicked: ViewHolderClickCallback? = null
        ) {
            model?.let {
                binding.setVariable(BR.model, it)
            }

            try {
                listener?.let { binding.setVariable(BR.listener, it) }
            } catch (exception: ClassCastException) {
                exception.localizedMessage?.let { Log.d(javaClass.simpleName, it) }
            }

            onViewHolderClicked?.let { callback ->
                itemView.setOnClickListener { callback.invoke(model, this) }
            }

            binding.executePendingBindings()
        }

        open fun onViewRecycled() {
            binding.unbind()
        }

        internal fun setListener(listener: Any?) {
            this.listener = listener
        }
    }
}
