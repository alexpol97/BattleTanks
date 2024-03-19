package com.av.poliudov.battletanks.utils

import android.view.View
import com.av.poliudov.battletanks.binding
import com.av.poliudov.battletanks.models.Coordinate

fun View.checkViewCanMoveThroughtBorder(coordinate: Coordinate): Boolean {
    return coordinate.top >= 0 &&
            coordinate.top + this.height <= binding.container.height &&
            coordinate.left >= 0 &&
            coordinate.left + this.width <= binding.container.width
}