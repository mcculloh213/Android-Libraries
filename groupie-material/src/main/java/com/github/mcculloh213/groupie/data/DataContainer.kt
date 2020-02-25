package com.github.mcculloh213.groupie.data

interface DataContainer<T> {
    fun getValue(): T
}