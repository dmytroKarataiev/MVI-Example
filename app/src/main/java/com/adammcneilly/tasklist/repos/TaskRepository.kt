package com.adammcneilly.tasklist.repos

import com.adammcneilly.tasklist.data.Task

interface TaskRepository {
    fun getTasks(): List<Task>
}