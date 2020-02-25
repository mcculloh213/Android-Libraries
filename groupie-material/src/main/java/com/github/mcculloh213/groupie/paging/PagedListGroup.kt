package com.github.mcculloh213.groupie.paging


import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.kotlinandroidextensions.Item

class PagedListGroup<T : Item> : Group, GroupDataObserver {
    private var parentObserver: GroupDataObserver? = null
    private lateinit var placeholder: Item
    private val listUpdateCallback: ListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            parentObserver?.onItemRangeInserted(this@PagedListGroup, position, count)
        }
        override fun onRemoved(position: Int, count: Int) {
            parentObserver?.onItemRangeRemoved(this@PagedListGroup, position, count)
        }
        override fun onMoved(fromPosition: Int, toPosition: Int) {
            parentObserver?.onItemMoved(this@PagedListGroup, fromPosition, toPosition)
        }
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            parentObserver?.onItemRangeChanged(this@PagedListGroup, position, count)
        }
    }
    private val differ: AsyncPagedListDiffer<T> = AsyncPagedListDiffer(
        listUpdateCallback,
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                newItem.isSameAs(oldItem)
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                newItem.equals(oldItem)
        }).build()
    )
    fun setPlaceholder(item: Item) {
        placeholder = item
    }
    fun submitList(pagedList: PagedList<T>) {
        differ.submitList(pagedList)
    }
    override fun getItemCount(): Int = differ.itemCount
    override fun getItem(position: Int): com.xwray.groupie.Item<*> {
        return differ.getItem(position)?.apply {
            registerGroupDataObserver(this@PagedListGroup)
        } ?: placeholder
    }
    override fun getPosition(item: com.xwray.groupie.Item<*>): Int {
        return differ.currentList?.indexOf(item) ?: -1
    }
    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = groupDataObserver
    }
    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = null
    }
    override fun onChanged(group: Group) {
        parentObserver?.onChanged(this)
    }
    override fun onItemInserted(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemInserted(this, index)
        }
    }
    override fun onItemChanged(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemChanged(this, index)
        }
    }
    override fun onItemChanged(group: Group, position: Int, payload: Any?) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemChanged(this, index, payload)
        }
    }
    override fun onItemRemoved(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRemoved(this, index)
        }
    }
    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRangeChanged(this, index, positionStart, itemCount)
        }
    }
    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int, payload: Any?) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRangeChanged(this, index, itemCount, payload)
        }
    }
    override fun onItemRangeInserted(group: Group, positionStart: Int, itemCount: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRangeInserted(this, index, itemCount)
        }
    }
    override fun onItemRangeRemoved(group: Group, positionStart: Int, itemCount: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRangeRemoved(this, index, itemCount)
        }
    }
    override fun onItemMoved(group: Group, fromPosition: Int, toPosition: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver?.onItemRangeChanged(this, index, toPosition)
        }
    }
    private fun getItemPosition(group: Group): Int {
        return differ.currentList?.indexOf(group) ?: -1
    }
}