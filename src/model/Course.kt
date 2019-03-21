package com.minh.model

data class Course(val id:Int,
                  val title:String,
                  val level:Int,
                  val isActive:Boolean) {

    companion object {
        fun newInstance(id:Int, title:String, level: Int, isActive: Boolean) = Course(id, title, level, isActive)
    }
}