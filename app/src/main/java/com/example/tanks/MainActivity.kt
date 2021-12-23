package com.example.tanks


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var step = 100
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
               myTanks.rotation =0f
               (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin  += -step
            }
               Derection.DOWN -> {
                  myTanks.rotation =180f
                   (myTanks.layoutParams as FrameLayout.LayoutParams).topMargin  += step
               }
              Derection.RIGHT -> {
                  myTanks.rotation =90f
                  (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin  += step
              }
                  Derection.LEFT -> {
                      myTanks.rotation =270f
                      (myTanks.layoutParams as FrameLayout.LayoutParams).leftMargin  -= step
               }

       }
           picture.removeView(myTanks)
           picture.addView(myTanks)
       }
   }
