package com.github.mcculloh213.groupie

import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

open class TypedGroupieListManager<T : Item> : GroupieListManager<T> {
    override val adapter = GroupAdapter<GroupieViewHolder>()
    override val header: Item?
        get() = _header
    private var _header: Item? = null
    override val data: List<T>
        get() = _data
    private val _data = mutableListOf<T>()

    override operator fun get(index: Int): T? = data.getOrNull(index)
    protected val sectionLookup: MutableMap<String, Group> = HashMap()

    override fun setHeader(section: String, header: Item) = lookupSection(section).run {
        setHeader(header)
        _header = header
    }

    override fun setBody(section: String, items: List<T>) = lookupSection(section).run {
        _data.run {
            clear()
            addAll(items)
        }
        update(items)
    }

    override fun getSectionPosition(section: String) =
        adapter.getAdapterPosition(lookupSection(section))

    override fun getItemPosition(index: Int): Int = adapter.getAdapterPosition(_data[index])
    private fun lookupSection(section: String): Section =
        sectionLookup[section] as? Section ?: Section().also {
            adapter.add(it)
            sectionLookup[section] = it
        }
}