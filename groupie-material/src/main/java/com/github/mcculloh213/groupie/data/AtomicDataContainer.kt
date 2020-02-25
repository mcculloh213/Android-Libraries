package com.github.mcculloh213.groupie.data

import java.util.concurrent.atomic.AtomicReference

class AtomicDataContainer<T>(data: T) : DataContainer<T> {
    private val reference: AtomicReference<T> = AtomicReference(data)
    override fun getValue(): T = reference.get()
}