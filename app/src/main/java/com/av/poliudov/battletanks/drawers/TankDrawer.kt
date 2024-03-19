package com.av.poliudov.battletanks.drawers

import android.view.View
import android.widget.FrameLayout
import com.av.poliudov.battletanks.CELL_SIZE
import com.av.poliudov.battletanks.binding
import com.av.poliudov.battletanks.enums.Direction
import com.av.poliudov.battletanks.models.Coordinate
import com.av.poliudov.battletanks.models.Element
import com.av.poliudov.battletanks.utils.checkViewCanMoveThroughtBorder
import com.av.poliudov.battletanks.utils.getElementByCoordinates

class TankDrawer(val container: FrameLayout) {
    var currentDirection = Direction.UP
    fun move(myTank: View, direction: Direction, elementsOnContainer: List<Element>) {
        val layoutParams = myTank.layoutParams as FrameLayout.LayoutParams
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        currentDirection = direction
        myTank.rotation = direction.rotation
        when (direction) {
            Direction.UP -> {
                myTank.rotation = 0f
                (myTank.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
            }

            Direction.DOWN -> {
                myTank.rotation = 180f
                (myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
            }

            Direction.LEFT -> {
                myTank.rotation = 270f
                (myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
            }

            Direction.RIGHT -> {
                myTank.rotation = 90f
                (myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
            }
        }

        val nextCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        if (myTank.checkViewCanMoveThroughtBorder(
                nextCoordinate
            ) && checkTankCanMoveThroughtMaterial(nextCoordinate, elementsOnContainer)
        ) {
            binding.container.removeView(myTank)
            binding.container.addView(myTank)
        } else {
            (myTank.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.top
            (myTank.layoutParams as FrameLayout.LayoutParams).leftMargin = currentCoordinate.left
        }
    }

    private fun checkTankCanMoveThroughtMaterial(
        coordinate: Coordinate,
        elementsOnContainer: List<Element>
    ): Boolean {
        getTankCoordinates(coordinate).forEach {
            val element = getElementByCoordinates(it, elementsOnContainer)
            if (element != null && !element.material.tankCanGoThrought) {
                return false
            }
        }
        return true
    }

    private fun getTankCoordinates(topLeftCoordinate: Coordinate): List<Coordinate> {
        val coordinateList = mutableListOf<Coordinate>()
        coordinateList.add(topLeftCoordinate)
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left))
        coordinateList.add(Coordinate(topLeftCoordinate.top, topLeftCoordinate.left + CELL_SIZE))
        coordinateList.add(
            Coordinate(
                topLeftCoordinate.top + CELL_SIZE,
                topLeftCoordinate.left + CELL_SIZE
            )
        )
        return coordinateList
    }
}