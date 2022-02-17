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
import drawers.ElementsDrawer
import drawers.GridDrawer
import drawers.TankDrawer
import enums.Derection
import enums.Material
import kotlinx.android.synthetic.main.activity_main.*

const val CELL_SIZE = 50
const val VERTICAL_CELL_AMOUNT = 38
const val HORIZONTAL_CELL_AMOUNT = 25
const val VERTICAL_MAX_SIZE = CELL_SIZE * VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE = CELL_SIZE * HORIZONTAL_CELL_AMOUNT

class MainActivity : AppCompatActivity() {
    private var editMode = false


    private val GridDrawer by lazy {
        GridDrawer(picture)
    }
    private val ElementsDrawer by lazy {
        ElementsDrawer(picture)
    }
    private val TankDrawer by lazy {
        TankDrawer(picture)
    }
//    private val BulletDrawer by lazy {
//        BulletDrawer(picture)
//    }


    var step = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        picture.layoutParams = FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)
        editor_clea.setOnClickListener { ElementsDrawer.currentMaterial = Material.EMPTY }
        editor_bricks.setOnClickListener { ElementsDrawer.currentMaterial = Material.BRICK }
        editor_concrete.setOnClickListener { ElementsDrawer.currentMaterial = Material.CONCRETE }
        editor_grass.setOnClickListener { ElementsDrawer.currentMaterial = Material.GRASS }
        picture.setOnTouchListener { v, motionEvent ->
            ElementsDrawer.onTouchPicture(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                switcheditMode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun switcheditMode() {
        if (editMode) {
            GridDrawer.removeGrid()
            material_picture.visibility = GONE

        } else {
        }
        GridDrawer.drawGrid()
        material_picture.visibility = VISIBLE
        editMode = !editMode
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_DPAD_UP -> TankDrawer.move(myTanks, Derection.UP, ElementsDrawer.elementsOnPicture)
            KEYCODE_DPAD_DOWN -> TankDrawer.move(myTanks, Derection.DOWN, ElementsDrawer.elementsOnPicture)
            KEYCODE_DPAD_RIGHT -> TankDrawer.move(myTanks, Derection.RIGHT, ElementsDrawer.elementsOnPicture)
            KEYCODE_DPAD_LEFT -> TankDrawer.move(myTanks, Derection.LEFT, ElementsDrawer.elementsOnPicture)
        }
        return super.onKeyDown(keyCode, event)
    }
}


