package drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.tanks.CELL_SIZE
import com.example.tanks.R
import enums.Direction
import models.Coordinate

private const val BULLET_WIDTH = 15
private const val BULLET_HEIGHT = 25

class BulletDrawer(private val picture: FrameLayout) {
    fun drawBullet(myTank: View, currentDirection: Direction){
        val bullet = ImageView(picture.context)
            .apply {
                this.setImageResource(R.drawable.bullet)
                this.layoutParams = FrameLayout.LayoutParams(BULLET_WIDTH, BULLET_HEIGHT)
                val bulletCoordinate = getBulletCoordinates(this,myTank,currentDirection)
                (this.layoutParams as FrameLayout.LayoutParams).topMargin = bulletCoordinate.top
                (this.layoutParams as FrameLayout.LayoutParams).leftMargin = bulletCoordinate.left
                this.rotation = currentDirection.rotation
            }
        picture.addView(bullet)
    }
    private fun getBulletCoordinates(
        bullet:ImageView,
        myTank: View,
        currentDirection: Direction
    ):Coordinate{
        val tankLeftTopCoordinate = Coordinate(myTank.top, myTank.left)
        return when (currentDirection) {
            Direction.UP ->
                return Coordinate(
                    top = tankLeftTopCoordinate.top - bullet.layoutParams.height,
                    left = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.left, bullet.layoutParams.width))
            Direction.DOWN ->
                return Coordinate(
                    top = tankLeftTopCoordinate.top - bullet.layoutParams.height,
                    left = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.left, bullet.layoutParams.width ))

            Direction.LEFT ->
                return Coordinate(
                    top = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.top, bullet.layoutParams.height ),
                    left = tankLeftTopCoordinate.left - bullet.layoutParams.width)

            Direction.RIGHT ->
                return Coordinate(
                    top = getDistanceToMiddleft0fTank(tankLeftTopCoordinate.top, bullet.layoutParams.height ),
                    left = tankLeftTopCoordinate.left + myTank.layoutParams.width)

        }
    }

    private  fun getDistanceToMiddleft0fTank(startCoordinate: Int, bulletSize:Int):Int{
        return startCoordinate + (CELL_SIZE - bulletSize / 2)
    }
}