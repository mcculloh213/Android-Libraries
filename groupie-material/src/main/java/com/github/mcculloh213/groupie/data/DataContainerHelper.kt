@file:JvmName("DataContainerHelper")
package com.github.mcculloh213.groupie.data

fun <T> atomicData(data: T): DataContainer<T> = AtomicDataContainer(data)
fun <T> simpleData(data: T): DataContainer<T> = SimpleDataContainer(data)