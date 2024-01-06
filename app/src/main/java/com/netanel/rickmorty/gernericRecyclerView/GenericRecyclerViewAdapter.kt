package com.netanel.rickmorty.gernericRecyclerView

import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.netanel.rickmorty.BR
import com.netanel.rickmorty.gernericRecyclerView.GenericRecyclerViewAdapter.GenericViewHolder

/**
 * A callback that is executed when a viewHolder is clicked.
 * Takes in the Model that was bound to the ViewDataBinding object and the ViewHolder itself.
 */
typealias ViewHolderClickCallback = (Model, GenericViewHolder) -> Unit

/**
 * Default constructor for the GenericRecyclerViewAdapter.
 *
 * @param models The list of models to load into the adapter. See [Model] for more info.
 * @param listener Use this to pass in an object that implements to each of your Model's
 *                 interfaces to interact with them properly.
 * @param viewHolderFactory Optional [ViewHolderFactory] implementation.
 * @param onViewHolderClicked Optional [ViewHolderClickCallback] implementation.
 */
class GenericRecyclerViewAdapter(
    models: List<Model> = arrayListOf(),
    private val listener: Any? = null,
    private val viewHolderFactory: ViewHolderFactory? = null,
    private val onViewHolderClicked: ViewHolderClickCallback? = null
) : RecyclerView.Adapter<GenericViewHolder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Model>() {

        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean =
            oldItem.areItemsTheSame(newItem)

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean =
            oldItem.areContentsTheSame(newItem)
    })

    init {
        updateData(models)
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): GenericViewHolder {

        // If we get a custom viewHolder from the ViewHolder factory,
        // return it, else, create a basic GenericViewHolder
        viewHolderFactory?.createViewHolder(parent, layoutId)?.let { viewHolder ->
            viewHolder.setListener(listener)
            viewHolder.setClickListener(onViewHolderClicked)
            return viewHolder
        }

        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        ) ?: throw InflateException(
            "Couldn't inflate binding layout for layout ${
                parent.context.resources.getResourceName(
                    layoutId
                ) ?: "id $layoutId"
            }, make sure the id is for a binding layout."
        )

        return GenericViewHolder(viewDataBinding, listener, onViewHolderClicked)
    }

    override fun onViewRecycled(holder: GenericViewHolder) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    override fun getItemViewType(position: Int) = differ.currentList[position].getLayoutId()

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    /**
     * Updated the recycler view with a new list of Models.
     *
     * @param list The list of models to load into the adapter.
     * @param callback Optional callback that is executed when the List is committed,
     *                 if it is committed.
     */
    fun updateData(list: List<Model>, callback: (() -> Unit)? = null) {
        differ.submitList(list) {
            callback?.invoke()
        }
    }

    /**
     * A Basic ViewHolder that binds data and some listener to a data binding class.
     *
     * Subclass this GenericViewHolder if you need more complex logic like custom animations,
     * gestures, user inputs or any other type of behavior that is not implemented by
     * the basic GenericViewHolder.
     *
     * Note: When creating the xml file that represents the data binding class, you must add a
     * a variable called 'model' (and optionally a variable named 'listener'),
     * which will later be bound with the Model and Listener implementations passed from the
     * onBindViewHolder method.
     *
     * @param binding A data binding class for the layout used by the viewHolder, it will be bound
     *        with the Model and Listener right after instantiation.
     * @param listener Optional interface that list the methods used to interact with this ViewHolder.
     * @param onViewHolderClicked Optional [ViewHolderClickCallback] implementation.
     */
    open class GenericViewHolder(
        protected open val binding: ViewDataBinding,
        protected var listener: Any? = null,
        private var onViewHolderClicked: ViewHolderClickCallback? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * This method is used for binding the xml with the Model and the optional Listener
         * implementations.
         *
         * Override this method when extra initiation logic is needed for your custom viewHolder.
         */
        open fun bind(model: Model) {
            binding.setVariable(BR.model, model)

            try {
                listener?.let { binding.setVariable(BR.listener, it) }
            } catch (exception: ClassCastException) {
                exception.localizedMessage?.let { Log.d(this.javaClass.simpleName, it) }
            }

            onViewHolderClicked?.let { callback ->
                itemView.setOnClickListener { callback.invoke(model, this) }
            }

            binding.executePendingBindings()
        }

        /**
         * This method is called whenever the view in the GenericViewHolder is recycled.
         * It unbinds data passed to the data binding class used by this viewHolder.
         * Override this method when you need to clear more custom data used by the viewHolder.
         */
        open fun onViewRecycled() {
            binding.unbind()
        }

        internal fun setClickListener(onViewHolderClicked: ViewHolderClickCallback?) {
            this.onViewHolderClicked = onViewHolderClicked
        }

        internal fun setListener(listener: Any?) {
            this.listener = listener
        }
    }
}
