package com.bapspatil.elon.util

import androidx.recyclerview.widget.DiffUtil

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Utils for `RecyclerView` to check if there are some  difference between 2 lists, containing in the RecyclerView, to improve
 * the `RecyclerView` performance, as it won't rebuild the View from scratch
 */
class DiffCallback : DiffUtil.Callback() {

    private var oldList: List<Any> = emptyList()
    private var newList: List<Any> = emptyList()

    fun compareLists(oldList: List<Any>, newList: List<Any>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    /**
     * Simplified for now, would probably want to compare against an ID.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        !(oldItemPosition >= oldList.size || newItemPosition >= newList.size)
                && oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        !(oldItemPosition >= oldList.size || newItemPosition >= newList.size)
                && oldList[oldItemPosition] == newList[newItemPosition]
}