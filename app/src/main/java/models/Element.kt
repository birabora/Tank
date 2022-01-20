package models

import enums.Material

data class Element (
    val viewId:Int,
    val material:Material,
    val coordinate: Coordinate
){}


