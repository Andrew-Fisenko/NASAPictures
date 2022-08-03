package com.example.nasapictures.view.navigation

const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_HEADER = 3

data class Data(
    val name: String = "Text",
    val someDescription: String? = "Description",
    val type: Int = TYPE_MARS
)