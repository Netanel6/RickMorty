package com.netanel.rickmorty.gernericRecyclerView

import android.view.ViewGroup

/**
 * Pass an instance of this factory implementation to the GenericRecyclerViewAdapter constructor
 * when you need to have a more complex viewHolder than the basic GenericViewHolder.
 */
interface ViewHolderFactory {

    /**
     * Creates a subclass of the GenericViewHolder from given parent viewGroup and viewType.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new subclass of the GenericViewHolder that holds a View of the given view type.
     */
    fun createViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericRecyclerViewAdapter.GenericViewHolder?
}