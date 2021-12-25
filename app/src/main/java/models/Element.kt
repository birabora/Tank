package models

import enums.Material

data class Element (
    val view:Int,
    val material:Material,
    val coordinate: Coordinate
){}


