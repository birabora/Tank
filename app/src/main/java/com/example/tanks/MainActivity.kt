package com.example.tanks


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import drawers.ElementsDrawers
import drawers.GridDrawble
import enums.Derection
import enums.Material
import kotlinx.android.synthetic.main.activity_main.*

const val CELL_SIZE = 50
const val VERTICAL_CELL_AMOUNT = 38
const val HORIZONTAL_CELL_AMOUNT = 25
const val VERTICAL_MAX_SIZE =  CELL_SIZE * VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE = CELL_SIZE * HORIZONTAL_CELL_AMOUNT
class MainActivity : AppCompatActivity() {
    private var editMode = false


      private val GridDrawble by lazy {
          GridDrawble(picture)
      }
private val elementsDrawers by lazy {
    ElementsDrawers(picture)
}



    var step = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            picture.layoutParams = FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)
        editor_clea.setOnClickListener{elementsDrawers.currentMaterial = Material.EMPTY}
        editor_bricks.setOnClickListener{elementsDrawers.currentMaterial = Material.BRICK}
        editor_concrete.setOnClickListener{elementsDrawers.currentMaterial = Material.CONCRETE}
        editor_grass.setOnClickListener{elementsDrawers.currentMaterial = Material.GRASS}
        picture.setOnTouchListener { v, motionEvent ->
            elementsDrawers.onTouchPicture( motionEvent.x,motionEvent.y)
            return@setOnTouchListener true
        }
}


override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

         override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when(item.itemId){
         R.id.menu_settings -> {
             switcheditMode()
             return true
         }
         else -> super.onOptionsItemSelected(item)
         }

    }
    private fun switcheditMode () {
        if (editMode){
            GridDrawble.removeGrid()
            material_picture.visibility = GONE

        } else{ }
            GridDrawble.drawGrid()
            material_picture.visibility = VISIBLE
        editMode = !editMode
    }




    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KEYCODE_DPAD_UP -> elementsDrawers.move(myTanks, Derection.UP)
             KEYCODE_DPAD_DOWN ->elementsDrawers.move(myTanks, Derection.DOWN)
            KEYCODE_DPAD_RIGHT -> elementsDrawers.move(myTanks, Derection.RIGHT)
            KEYCODE_DPAD_LEFT ->elementsDrawers.move(myTanks, Derection.LEFT)
        }
        return super.onKeyDown(keyCode, event)
    }

   }


