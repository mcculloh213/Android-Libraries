package com.github.mcculloh213.groupie.material

import androidx.annotation.LayoutRes
import com.xwray.groupie.kotlinandroidextensions.Item

abstract class MaterialListItem(
    @LayoutRes private val layoutResId: Int
) : Item() {
    override fun getLayout(): Int = layoutResId
}