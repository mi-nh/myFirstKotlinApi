package com.minh.extensions

import com.minh.model.Course
import com.minh.model.ErrorMessage
import java.lang.Exception

fun Course.log() = println("id: ${this.id} title: ${this.title} level:${this.level} isActive:${this.isActive}")

fun ErrorMessage.log() = println("status: ${this.status}  message:${this.message}")

fun Exception.log() = println("Erreur dans OpenClassRooms api: ${this.toString()}")