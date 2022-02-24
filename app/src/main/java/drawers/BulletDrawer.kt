package drawers

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.tanks.CELL_SIZE
import com.example.tanks.R
import enums.Direction
import models.Coordinate
import utils.checkViewCanMoveThroughBorder

private const val BULLET_WIDTH = 15
private const val BULLET_HEIGHT = 25

class BulletDrawer(private val picture: FrameLayout) {

    fun makeBulletMove(myTank: View, currentDirection: Direction){
        Thread(Runnable {
            val bullet = createBullet(myTank, currentDirection)
            while (bullet.checkViewCanMoveThroughBorder(Coordinate(bullet.top,bullet.left),myTank)){
                when(currentDirection){
                    Direction.UP -> (bullet.layoutParams as FrameLayout.LayoutParams).topMargin -= BULLET_HEIGHT
                    Direction.DOWN -> (bullet.layoutParams as FrameLayout.LayoutParams).topMargin += BULLET_HEIGHT
                    Direction.LEFT -> (bullet.layoutParams as FrameLayout.LayoutParams).leftMargin -= BULLET_HEIGHT
                    Direction.RIGHT -> (bullet.layoutParams as FrameLayout.LayoutParams).leftMargin += BULLET_HEIGHT
                }
                Thread.sleep(30)
                (picture.context as Activity).runOnUiThread {
                    picture.removeView(bullet)
                    picture.addView(bullet)
                }
            }
            (picture.context as Activity).runOnUiThread {
                picture.removeView(bullet)
            }
        }).start()
    }

    private fun createBullet(myTank: View, currentDirection: Direction) :ImageView{
        return ImageView(picture.context)
            .apply {
                this.setImageResource(R.drawable.bullet)
                this.layoutParams = FrameLayout.LayoutParams(BULLET_WIDTH, BULLET_HEIGHT)
                val bulletCoordinate = getBulletCoordinates(this,myTank,currentDirection)
                (this.layoutParams as FrameLayout.LayoutParams).topMargin = bulletCoordinate.top
                (this.layoutParams as FrameLayout.LayoutParams).leftMargin = bulletCoordinate.left
                this.rotation = currentDirection.rotation
            }
    }
    private fun getBulletCoordinates(
        bullet:ImageView,
        myTank: View,
        currentDirection: Direction
    ):Coordinate{
        val tankLeftTopCoordinate = Coordinate(myTank.top, myTank.left)
        return when (currentDirection) {
            Direction.UP ->
                Coordinate(
                    top = tankLeftTopCoordinate.top - bullet.layoutParams.height,
                    left = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.left, bullet.layoutParams.width))
            Direction.DOWN ->
                Coordinate(
                    top = tankLeftTopCoordinate.top - bullet.layoutParams.height,
                    left = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.left, bullet.layoutParams.width ))

            Direction.LEFT ->
                Coordinate(
                    top = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.top, bullet.layoutParams.height ),
                    left = tankLeftTopCoordinate.left - bullet.layoutParams.width)

            Direction.RIGHT ->
                Coordinate(
                    top = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.top, bullet.layoutParams.height ),
                    left = tankLeftTopCoordinate.left + myTank.layoutParams.width)

        }
    }

    private  fun getDistanceToMiddleft0fTank(startCoordinate: Int, bulletSize:Int):Int{
        return startCoordinate + (CELL_SIZE - bulletSize / 2)
    }
}