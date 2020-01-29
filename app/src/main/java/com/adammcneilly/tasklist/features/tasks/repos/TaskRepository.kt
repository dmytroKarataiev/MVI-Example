package com.adammcneilly.tasklist.features.tasks.repos

import com.adammcneilly.tasklist.data.Task

interface TaskRepository {
    fun getTasks(): List<Task>
}