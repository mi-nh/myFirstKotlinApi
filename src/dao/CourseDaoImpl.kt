package com.minh.dao

import com.minh.model.Course


class CourseDaoImpl : CourseDao {

    val courses = listOf(
        Course(1, "How to troll a Troll", 5, true),
        Course(2, "Kotlin for troll", 1, true),
        Course(3, "cours numero 3", 3, false)
    )

    override fun findAll(): List<Course> {
        return courses
    }

    override fun findCourseById(id: Int): Course? {
        return courses.filter { elem -> elem.id.equals(id) }.firstOrNull()
    }

    override fun findCourseByLevelMax(): Course? {
        return courses.filter { elem -> elem.isActive }
            .maxBy { elem -> elem.level }
    }

    companion object {
        val instance = CourseDaoImpl()
    }

}