package drawers

import android.view.View
import android.widget.FrameLayout
import com.example.tanks.CELL_SIZE
import com.example.tanks.HORIZONTAL_MAX_SIZE
import com.example.tanks.VERTICAL_MAX_SIZE
import enums.Direction
import models.Coordinate
import models.Element
import utils.checkViewCanMoveThroughBorder



class TankDrawer(private val picture: FrameLayout) {
    var currentDirection = Direction.DOWN
    fun move(myTanks: View, direction: Direction, elementsOnPicture: List<Element>) {
        val layoutParams = myTanks.layoutParams as FrameLayout.LayoutParams
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.topMargin)
        currentDirection = direction
        myTanks.rotation = direction.rotation
        when (direction) {
            Direction.UP -> {
                myTanks.rotation = 0f
                (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
            }
            Direction.DOWN -> {
                myTanks.rotation = 180f
                (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
            }
            Direction.RIGHT -> {
                myTanks.rotation = 90f
                (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
            }
            Direction.LEFT -> {
                myTanks.rotation = 270f
                (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
            }
        }
        val nextCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin) //save after change
        if (myTanks.checkViewCanMoveThroughBorder(nextCoordinate, myTanks)
            && checkTankCanMoveThroughMaterial(nextCoordinate, elementsOnPicture)
        ) {
            picture.removeView(myTanks)
            picture.addView(myTanks, 0)
        } else {
            (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.top
            (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.left
        }
    }

    private fun checkTankCanMoveThroughMaterial(
        coordinate: Coordinate,
        elementsOnPicture: List<Element>
    ): Boolean {
        getTankCoordinates(coordinate).forEach {
            val element = getElementByCoordinates(it, elementsOnPicture)
            if (element != null && !element.material.tankCanGoThrough) {
                return false
            }
        }
        return true
    }

    private fun getElementByCoordinates(coordinate: Coordinate, elementsOnPicture: List<Element>) =
        elementsOnPicture.firstOrNull { it.coordinate == coordinate }

    private fun checkTankCanMoveThroughBorder(coordinate: Coordinate, myTanks: View): Boolean {
        if (coordinate.top >= 0
            && coordinate.top + myTanks.height < HORIZONTAL_MAX_SIZE
            && coordinate.left >= 0
            && coordinate.left + myTanks.width < VERTICAL_MAX_SIZE
        ) {
            return true
        }
        return false
    }

    private fun getTankCoordinates(topLeftCoordinate: Coordinate): List<Coordinate> {
        val coordinateList = mutableListOf<Coordinate>()
        coordinateList.add(topLeftCoordinate)
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left)) //bottom_left
        coordinateList.add(Coordinate(topLeftCoordinate.top, topLeftCoordinate.left + CELL_SIZE)) //top_right
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left + CELL_SIZE)) //bottom_right
        return coordinateList
    }
}
