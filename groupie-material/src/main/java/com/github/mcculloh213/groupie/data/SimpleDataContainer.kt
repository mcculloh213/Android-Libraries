package com.github.mcculloh213.groupie.data

class SimpleDataContainer<T>(private val data: T) : DataContainer<T> {
    override fun getValue(): T = data
}