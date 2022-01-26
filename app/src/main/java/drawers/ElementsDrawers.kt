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

class ElementsDrawers(val picture:FrameLayout) {
    var currentMaterial = Material.EMPTY
    private fun elementsOnPicture() = mutableListOf<Element>()

    fun onTouchPicture (x: Float, y:Float){
        val topMargin = y.toInt() - (y.toInt()% CELL_SIZE)
        val leftMargin = x.toInt() - (x.toInt()% CELL_SIZE)
        val coordinate = Coordinate(topMargin, leftMargin)
        if (currentMaterial==Material.EMPTY){
            eraseView(coordinate)
        }else{
            drawOrReplaseView(coordinate)
        }
    }

    private  fun drawOrReplaseView(coordinate: Coordinate){
        val  viewOnCoordinate = getElementByCoordinates(coordinate)
        if (viewOnCoordinate==null){
            drawView(coordinate)
            return
        }
        if (viewOnCoordinate.material!==currentMaterial){
            replaceView(coordinate)
        }
    }

    private  fun replaceView(coordinate: Coordinate){
        eraseView(coordinate)
        drawView(coordinate)
    }

    private fun getElementByCoordinates(coordinate: Coordinate)=
        elementsOnPicture().firstOrNull { it.coordinate==coordinate }


    private  fun eraseView(coordinate: Coordinate){
        val elementOnCoordinate = getElementByCoordinates(coordinate)
        if (elementOnCoordinate!=null){
            val  erasingView = picture.findViewById<View>(elementOnCoordinate.viewId)
            picture.removeView(erasingView)
            elementsOnPicture().remove(elementOnCoordinate)
    }

         fun drawView(coordinate: Coordinate){
        val view = ImageView(picture.context)
        val layoutParams = FrameLayout.LayoutParams(CELL_SIZE, CELL_SIZE)
        when (currentMaterial){
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
        elementsOnPicture().add(Element(viewId, currentMaterial, coordinate))
    }

     fun move(myTanks:View , derection: Derection) {
         val layoutParams = myTanks.layoutParams as FrameLayout.LayoutParams
         when (derection) {
             Derection.UP -> {
                 myTanks.rotation = 0f

                 if (layoutParams.topMargin > 0) {

                     (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
                 }
             }
             Derection.DOWN -> {
                 myTanks.rotation = 180f
                 if (layoutParams.topMargin + myTanks.height < HORIZONTAL_MAX_SIZE) {
                     (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                 }
             }
             Derection.RIGHT -> {
                 myTanks.rotation = 90f
                 if (layoutParams.leftMargin + myTanks.width < VERTICAL_MAX_SIZE) {
                     (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
                 }
             }
             Derection.LEFT -> {
                 myTanks.rotation = 270f
                 if (layoutParams.leftMargin > 0) {
                     (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
                 }
             }

         }
         picture.removeView(myTanks)
         picture.addView(myTanks)
     }

    private fun checkTankCanMoveThroughMaterial(coordinate: Coordinate):Boolean{
        getTankCoordinates(coordinate).forEach{
            val element = getElementByCoordinates(coordinate)
        }


    }

    private fun checkTankCanMoveThroughBorder(coordinate: Coordinate, myTanks: View):Boolean{
        if (coordinate.top >= 0
            &&coordinate.top + myTanks.height < HORIZONTAL_MAX_SIZE
            &&coordinate.left >= 0
            &&coordinate.left + myTanks.width < VERTICAL_MAX_SIZE) {
            return true
        }
        return false
    }

    private  fun  getTankCoordinates (topLeftCoordinate: Coordinate):List<Coordinate>{
        val coordinateList = mutableListOf<Coordinate>()
        coordinateList.add(topLeftCoordinate)
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left)) //bottom_left
        coordinateList.add(Coordinate(topLeftCoordinate.top , topLeftCoordinate.left + CELL_SIZE )) //top_right
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left+ CELL_SIZE)) //bottom_right
        return coordinateList
    }
}


