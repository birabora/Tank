package com.example.tanks

import android.app.Activity
import  android.content.Context
import android.view.View
import android.widget.FrameLayout

class GibtDrawble (private val context: Context){
private val allLines = mutableListOf<View>()

    fun removeGrid (){
        val picture = (context as Activity).findViewById<FrameLayout>(R.id.picture)
        allLines.forEach {
            picture.removeView(it)
        }

    }

    fun drawGrid() {
        val picture = (context as Activity).findViewById<FrameLayout>(R.id.picture)
        drawHorizontalLines(picture)
        drawVerticalLines(picture)

    }


    private fun drawHorizontalLines(picture: FrameLayout) {
        var topMargins = 0
        while (topMargins <= picture.layoutParams.height) {
            val horizontalLine = View(context)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1)
            topMargins += CELL_SIZE
            layoutParams.topMargin = topMargins
            horizontalLine.layoutParams = layoutParams
            horizontalLine.setBackgroundColor(context.resources.getColor(android.R.color.white))
                    allLines.add(horizontalLine)
            picture.addView(horizontalLine)
        }
    }

    private fun drawVerticalLines(picture: FrameLayout) {
        var leftMargins = 0
        while (leftMargins <= picture.layoutParams.width) {
            val verticaLine = View(context)
            val layoutParams = FrameLayout.LayoutParams(1, FrameLayout.LayoutParams.MATCH_PARENT)
            leftMargins += CELL_SIZE
            layoutParams.leftMargin = leftMargins
            verticaLine.layoutParams = layoutParams
            verticaLine.setBackgroundColor(context.resources.getColor(android.R.color.white))
            allLines.add(verticaLine)
            picture.addView(verticaLine)
        }
    }
}