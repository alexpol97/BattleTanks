package com.av.poliudov.battletanks.models

import com.av.poliudov.battletanks.enums.Material

data class Element(
    val viewId: Int,
    val material: Material,
    val coordinate: Coordinate
)
