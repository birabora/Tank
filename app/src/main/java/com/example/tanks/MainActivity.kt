package com.example.tanks


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.widget.FrameLayout
import android.widget.ImageView




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KEYCODE_DPAD_UP -> move(Derection.UP)
             KEYCODE_DPAD_DOWN -> move(Derection.DOWN)
            KEYCODE_DPAD_RIGHT -> move(Derection.RIGHT)
            KEYCODE_DPAD_LEFT -> move(Derection.LEFT)
        }
        return super.onKeyDown(keyCode, event)
    }
       private fun move(derection: Derection) {
        when (derection) {
           Derection.UP-> {
               findViewById<ImageView>(R.id.myTanks).rotation =0f
               (findViewById<ImageView>(R.id.myTanks).layoutParams as FrameLayout.LayoutParams).topMargin  += -50
            }
               Derection.DOWN -> {
                   findViewById<ImageView>(R.id.myTanks).rotation =180f
                   (findViewById<ImageView>(R.id.myTanks).layoutParams as FrameLayout.LayoutParams).topMargin  += 50
               }
              Derection.RIGHT -> {findViewById<ImageView>(R.id.myTanks).rotation =90f
                  (findViewById<ImageView>(R.id.myTanks).layoutParams as FrameLayout.LayoutParams).topMargin  += 50
              }
                  Derection.LEFT -> {
                      findViewById<ImageView>(R.id.myTanks).rotation =270f
                      (findViewById<ImageView>(R.id.myTanks).layoutParams as FrameLayout.LayoutParams).topMargin  -= 50
               }

       }
           R.id.picture.removeView.myTanks
           R.id.picture.addView.myTanks
       }
   }
