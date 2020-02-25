package com.github.mcculloh213.groupie.paging

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.mcculloh213.groupie.TypedGroupieListManager
import com.xwray.groupie.kotlinandroidextensions.Item

class PagedGroupieListManager<T : Item>(
    factory: DataSource.Factory<Int, T>,
    config: PagedList.Config
) : TypedGroupieListManager<T>() {
    val pages = LivePagedListBuilder(factory, config).build()
    private val pager = PagedListGroup<T>()
    init {
        adapter.add(pager)
    }
    fun setBody(page: PagedList<T>) {
        pager.submitList(page)
    }
    companion object {
        private const val SECTION_PAGE: String = "page"
    }
}