package com.github.mcculloh213.groupie

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

interface GroupieListManager<T : Item> {
    val adapter: GroupAdapter<GroupieViewHolder>
    val header: Item?
    val data: List<T>
    operator fun get(index: Int): T?
    fun setHeader(section: String, header: Item)
    fun setBody(section: String, items: List<T>)
    fun getSectionPosition(section: String): Int
    fun getItemPosition(index: Int): Int
}