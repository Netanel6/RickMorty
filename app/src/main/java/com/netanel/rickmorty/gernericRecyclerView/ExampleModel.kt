package com.netanel.rickmorty.gernericRecyclerView

import com.netanel.rickmorty.R

/**
 * This is an example data class that implement Model interface.
 * It will represent a simple view holder that will hold a single textView that will get it's
 * text from this class after it will be bind to the view by the generic recyclerView adapter.
 * NOTE - the variable holding this model in your xml file will have
 *        to be named 'model' in order for the binding to work.
 */
internal data class ExampleModel(
    val text: String
) : Model {

    /**
     * To interact with viewHolders, we can define a listener interface that will later on be
     * injected to the view using data binding.
     * NOTE - the variable holding this listener in your xml file will have
     *        to be named 'listener' in order for the binding to work.
     */
    interface Listener {
        fun onTextClicked(model: ExampleModel)
    }

    override fun getViewType(): Int = R.layout.item_example_text
}