package com.yashoid.instacropper.sample

/**
 * Created by jason on 10/6/17.
 */

open class Photo(
        val url: String,
        val width: Int,
        val height: Int,
        val x1: Float,
        val y1: Float,
        val x2: Float,
        val y2: Float,
        var selected: Boolean = false
)
