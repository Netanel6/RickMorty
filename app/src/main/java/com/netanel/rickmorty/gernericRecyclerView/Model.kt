package com.netanel.rickmorty.gernericRecyclerView

/**
 * All items in the recyclerView will be represented by this interface.
 * This will allow each viewHolder being created to get it's layout resource id and
 * compare it with other viewHolders for better performance.
 */
interface Model {

    /**
     * Gets the resource id from the model class.
     *
     * @return A layout resource id.
     */
    fun getViewType(): Int

    /**
     * Called to check whether two objects represent the same item.
     * <p>
     * For example, if your items have unique ids, this method should check their id equality.
     * <p>
     * Note: {@code null} items in the list are assumed to be the same as another {@code null}
     * item and are assumed to not be the same as a non-{@code null} item. This callback will
     * not be invoked for either of those cases.
     *
     * @param other The item to check equality to.
     * @return True if the two items represent the same object or false if they are different.
     */
    fun isEqualTo(other: Model?): Boolean = this == other

    /**
     * Called to check whether two items have the same data.
     * <p>
     * This information is used to detect if the contents of an item have changed.
     * <p>
     * This method to check equality instead of {@link Object#equals(Object)} so that you can
     * change its behavior depending on your UI.
     * <p>
     * For example, if you are using DiffUtil with a
     * {@link RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * <p>
     * This method is called only if {@link #areItemsTheSame(T, T)} returns {@code true} for
     * these items.
     * <p>
     * Note: Two {@code null} items are assumed to represent the same contents. This callback
     * will not be invoked for this case.
     *
     * @param other The item to check equality to.
     * @return True if the contents of the items are the same or false if they are different.
     */
    fun isEqualContentTo(other: Model?): Boolean = this == other
}