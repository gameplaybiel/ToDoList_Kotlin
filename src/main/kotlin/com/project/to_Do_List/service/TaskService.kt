package com.project.to_Do_List.service

import com.project.to_Do_List.model.Task
import com.project.to_Do_List.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val repository: TaskRepository) {

    fun findAll() = repository.findAll()

    fun findById(id: Long) = repository.findById(id).orElse(null)

    fun save(task: Task): Task = repository.save(task)

    fun update(id: Long, updatedTask: Task): Task? {
        val existingTask = repository.findById(id).orElse(null) ?: return null
        val toSaved = existingTask.copy(
            title = updatedTask.title,
            description = updatedTask.description,
            isCompleted =  updatedTask.isCompleted
        )
        return repository.save(toSaved)
    }

    fun delete(id: Long): Boolean {
        return if(repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else false
    }
}