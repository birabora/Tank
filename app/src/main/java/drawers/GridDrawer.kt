package drawers

import android.view.View
import android.widget.FrameLayout
import com.example.tanks.CELL_SIZE
import com.example.tanks.MainActivity

class GridDrawer(private val picture: FrameLayout){
    private val allLines = mutableListOf<View>()
    fun removeGrid (){

        allLines.forEach {
            picture.removeView(it)
        }

    }

    fun drawGrid(){
        drawHorizontalLines()
        drawVerticalLines()
    }

    private fun drawHorizontalLines() {
        var topMargins = 0
        while (topMargins <= picture.layoutParams.height) {
            val horizontalLine = View(picture.context)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1)
            topMargins += CELL_SIZE
            layoutParams.topMargin = topMargins
            horizontalLine.layoutParams = layoutParams
            horizontalLine.setBackgroundColor(picture.context.resources.getColor(android.R.color.white))
                    allLines.add(horizontalLine)
            picture.addView(horizontalLine)
        }
    }

    private fun drawVerticalLines() {
        var leftMargins = 0
        while (leftMargins <= picture.layoutParams.width) {
            val verticaLine = View(picture.context)
            val layoutParams = FrameLayout.LayoutParams(1, FrameLayout.LayoutParams.MATCH_PARENT)
            leftMargins += CELL_SIZE
            layoutParams.leftMargin = leftMargins
            verticaLine.layoutParams = layoutParams
            verticaLine.setBackgroundColor(picture.context.resources.getColor(android.R.color.white))
            allLines.add(verticaLine)
            picture.addView(verticaLine)
        }
    }


}