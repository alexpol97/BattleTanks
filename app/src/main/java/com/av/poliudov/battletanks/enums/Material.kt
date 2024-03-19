package com.av.poliudov.battletanks.enums

enum class Material(val tankCanGoThrought: Boolean) {
    EMPTY(true),
    BRICK(false),
    CONCRETE(false),
    GRASS(true),
}