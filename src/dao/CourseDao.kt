package com.minh.dao

import com.minh.model.Course

interface CourseDao {
    fun findAll(): List<Course>
    fun findCourseById(id:Int):Course?
    fun findCourseByLevelMax():Course?
}