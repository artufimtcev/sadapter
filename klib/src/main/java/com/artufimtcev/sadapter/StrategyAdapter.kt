package com.artufimtcev.sadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*


abstract class StrategyAdapter<VH : RecyclerView.ViewHolder>() : RecyclerView.Adapter<VH>() {

    private val strategiesTypes: MutableList<Class<out StrategyItem<out VH>>> = ArrayList()
    private val items: MutableList<StrategyItemDecorator> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        // The cast should always work since one strategy can't return and accept different holders
        val strategy: StrategyItem<VH> = items[position].decoratedItem
        strategy.onBindViewHolder(holder)
    }


    final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH? {
        parent ?: return null
        return get(strategiesTypes[viewType])!!.createViewHolder(parent)
    }


    override fun getItemViewType(position: Int): Int {
        return strategiesTypes.indexOf(items[position].decoratedItem.javaClass)
    }


    operator fun get(position: Int): StrategyItem<out VH> {
        return items[position].decoratedItem
    }


    operator fun <SI : StrategyItem<out VH>> get(type: Class<SI>): SI? {
        for (item in items) {
            if (item.decoratedItem.javaClass === type) {
                return item.decoratedItem as SI
            }
        }

        return null
    }


    fun add(item: StrategyItem<out VH>) = this.add(itemCount, item)


    fun add(index: Int, item: StrategyItem<out VH>) {
        addItem(index, decorate(item))
        notifyItemInserted(index);
    }


    fun addSorted(item: StrategyItem<out VH>) {
        val decoratedItem = decorate(item)
        addItem(decoratedItem)
        Collections.sort(items)
        notifyItemInserted(findById(decoratedItem.id))
    }


    fun addAll(itemsList: List<StrategyItem<out VH>>) {
        val rangeStart = items.size
        addAllItems(itemsList.map { decorate(it) })
        notifyItemRangeInserted(rangeStart, itemsList.size)
    }


    fun addAllSorted(itemsList: List<StrategyItem<out VH>>) {
        val decorators = itemsList.map { decorate(it) }
        addAllItems(itemsList.map { decorate(it) })
        for (decorator in decorators) {
            notifyItemInserted(findById(decorator.id))
        }
    }


    fun remove(position: Int): StrategyItem<out VH> {
        val removedItem = items.removeAt(position)
        notifyItemRemoved(position)
        return removedItem.decoratedItem
    }


    fun replaceAt(index: Int, item: StrategyItem<out VH>) {
        items[index] = decorate(item)
        notifyItemChanged(index)
    }


    fun replace(item: StrategyItem<out VH>) {
        replaceSpecificType(item, item.javaClass)
    }


    fun replaceAll(itemList: List<StrategyItem<out VH>>) {
        val removedViewTypes = HashSet<Class<out StrategyItem<out VH>>>()
        for (strategy in itemList) {
            if (!removedViewTypes.contains(strategy.javaClass)) {
                removeItemsWithType(strategy.javaClass)
                removedViewTypes.add(strategy.javaClass)
            }
        }
        addAll(itemList)
    }


    fun replaceSpecificType(item: StrategyItem<out VH>, type: Class<out StrategyItem<out VH>>) {
        removeItemsWithType(type)
        add(item)
    }


    fun replaceSpecificType(itemList: List<StrategyItem<out VH>>, type: Class<out StrategyItem<out VH>>) {
        removeItemsWithType(type)
        addAll(itemList)
    }


    fun remove(item: StrategyItem<out VH>): Boolean {
        val index = indexOf(item)
        if (index < 0) return false
        items.removeAt(index)
        notifyItemRemoved(index)
        return true
    }


    fun removeItemsWithType(type: Class<out StrategyItem<out VH>>) {
        var i = 0
        while (i < items.size) {
            val item = items[i]
            if (item.decoratedItem.javaClass == type) {
                remove(i)
            } else {
                i++
            }
        }
    }


    fun indexOf(strategy: StrategyItem<out VH>): Int {
        items.forEachIndexed { i, item ->
            if (item.decoratedItem.javaClass == strategy.javaClass) return i
        }

        return -1;
    }


    fun indexOf(type: Class<out StrategyItem<out VH>>): Int {
        items.forEachIndexed { i, item ->
            if (item.decoratedItem.javaClass == type) return i
        }

        return -1;
    }


    fun isEmpty(): Boolean = items.isEmpty()


    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }


    private fun decorate(item: StrategyItem<out VH>): StrategyItemDecorator {
        var id: String = UUID.randomUUID().toString();
        return StrategyItemDecorator(id, item as StrategyItem<VH>)
    }


    private fun findById(id: String): Int {
        items.forEachIndexed { i, itemDecorator ->
            if (itemDecorator.id == id) return i
        }
        return -1
    }


    private inner class StrategyItemDecorator(val id: String, val decoratedItem: StrategyItem<VH>) :
            Comparable<StrategyItemDecorator> {

        override fun compareTo(other: StrategyItemDecorator): Int {
            return if (decoratedItem is ComparableStrategyItem && other.decoratedItem is ComparableStrategyItem) {
                decoratedItem.compareTo(other.decoratedItem)
            } else 0
        }
    }


    private fun addItem(item: StrategyItemDecorator) {
        this.addItem(itemCount, item)
    }


    private fun addItem(index: Int, item: StrategyItemDecorator) {
        if (!strategiesTypes.contains(item.decoratedItem.javaClass)) {
            strategiesTypes.add(item.decoratedItem.javaClass)
        }

        this.items.add(index, item)
    }


    private fun addAllItems(items: List<StrategyItemDecorator>) {
        items.forEach {
            if (!strategiesTypes.contains(it.decoratedItem.javaClass)) {
                strategiesTypes.add(it.decoratedItem.javaClass)
            }
        }

        this.items.addAll(items)
    }
}
