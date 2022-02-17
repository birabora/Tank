package drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.tanks.CELL_SIZE
import com.example.tanks.HORIZONTAL_MAX_SIZE
import com.example.tanks.R
import com.example.tanks.VERTICAL_MAX_SIZE
import enums.Derection
import enums.Material
import kotlinx.android.synthetic.main.activity_main.*
import models.Coordinate
import models.Element

class ElementsDrawers(val picture: FrameLayout) {
    var currentMaterial = Material.EMPTY
    val elementsOnPicture = mutableListOf<Element>()

    fun onTouchPicture(x: Float, y: Float) {
        val topMargin = y.toInt() - (y.toInt() % CELL_SIZE)
        val leftMargin = x.toInt() - (x.toInt() % CELL_SIZE)
        val coordinate = Coordinate(topMargin, leftMargin)
        if (currentMaterial == Material.EMPTY) {
            eraseView(coordinate)
        } else {
            drawOrReplaseView(coordinate)
        }
    }

    private fun drawOrReplaseView(coordinate: Coordinate) {
        val viewOnCoordinate = getElementByCoordinates(coordinate)
        if (viewOnCoordinate == null) {
            drawView(coordinate)
            return
        }
        if (viewOnCoordinate.material != currentMaterial) {
            replaceView(coordinate)
        }
    }

    private fun replaceView(coordinate: Coordinate) {
        eraseView(coordinate)
        drawView(coordinate)
    }

    private fun getElementByCoordinates(coordinate: Coordinate) =
        elementsOnPicture.firstOrNull { it.coordinate == coordinate }


    private fun eraseView(coordinate: Coordinate) {
        val elementOnCoordinate = getElementByCoordinates(coordinate)
        if (elementOnCoordinate != null) {
            val erasingView = picture.findViewById<View>(elementOnCoordinate.viewId)
            picture.removeView(erasingView)
            elementsOnPicture.remove(elementOnCoordinate)
        }
    }

    private fun drawView(coordinate: Coordinate) {
        val view = ImageView(picture.context)
        val layoutParams = FrameLayout.LayoutParams(CELL_SIZE, CELL_SIZE)
        when (currentMaterial) {
            Material.EMPTY -> {

            }
            Material.BRICK -> view.setImageResource(R.drawable.kirpizr)
            Material.CONCRETE -> view.setImageResource(R.drawable.obsidianr)
            Material.GRASS -> view.setImageResource(R.drawable.grass)


        }
        layoutParams.topMargin = coordinate.top
        layoutParams.leftMargin = coordinate.left
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        picture.addView(view)
        elementsOnPicture.add(Element(viewId, currentMaterial, coordinate))
    }
}


