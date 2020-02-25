package com.github.mcculloh213.groupie.material

import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import com.github.mcculloh213.groupie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_single_line_large.*

open class MaterialLargeSingleLineListItem(
    private val text: String,
    @DrawableRes private val startIconRes: Int? = null,
    @DrawableRes private val endIconRes: Int? = null
) : MaterialListItem(R.layout.item_single_line_large) {
    @CallSuper
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_text.text = text
            startIconRes?.let { res ->
                item_start_icon.apply {
                    setImageResource(res)
                    visibility = View.VISIBLE
                }
            }
            endIconRes?.let { res ->
                item_end_icon.apply {
                    setImageResource(res)
                    visibility = View.VISIBLE
                }
            }
        }
    }
}