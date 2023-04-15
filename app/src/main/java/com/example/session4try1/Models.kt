package com.example.session4try1

data class ModelError(
    val error: String
)
data class ModelTask(
    val id: Int,
    val idCourse: Int,
    val title: String,
    val description: String,
    val isComplete: Boolean,
    val datetime: String,
    val duration: Int
)