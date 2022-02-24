package utils

import android.view.View
import com.example.tanks.HORIZONTAL_MAX_SIZE
import com.example.tanks.VERTICAL_MAX_SIZE
import models.Coordinate

fun View.checkViewCanMoveThroughBorder(coordinate: Coordinate, myTanks: View): Boolean {
        if (coordinate.top >= 0
            && coordinate.top + this.height < HORIZONTAL_MAX_SIZE
            && coordinate.left >= 0
            && coordinate.left + this.width < VERTICAL_MAX_SIZE
        ) {
            return true
        }
        return false
    }
