package com.project.to_Do_List.controller

import com.project.to_Do_List.model.Task
import com.project.to_Do_List.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/task")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTask() = ResponseEntity.ok(taskService.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(task)
    }

    @PostMapping
    fun create(@RequestBody task: Task) = ResponseEntity.ok(taskService.save(task))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updatedTask: Task): ResponseEntity<Task> {
        val task = taskService.update(id, updatedTask) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(task)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if(taskService.delete(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}