package drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.tanks.CELL_SIZE
import com.example.tanks.R
import enums.Material
import models.Coordinate
import models.Element

class ElementsDrawers(val picture:FrameLayout) {
    var currentMaterial = Material.EMPTY
    private fun ElementsOnPicture() = mutableListOf<Element>()

    private fun drawView(coordinate: Coordinate){
        val view = ImageView(picture.context)
        val layoutParams = FrameLayout.LayoutParams(CELL_SIZE, CELL_SIZE)
        when (currentMaterial){
            Material.EMPTY -> {

            }
            Material.BRICK -> view.setImageResource(R.drawable.kirpiz)
            Material.CONCRETE -> view.setImageResource(R.drawable.obsidian)
            Material.GRASS -> view.setImageResource(R.drawable.grass)


        }
        layoutParams.topMargin = coordinate.top
        layoutParams.leftMargin = coordinate.left
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        picture.addView(view)
        ElementsOnPicture().add(Element(viewId, currentMaterial, coordinate))
    }
}