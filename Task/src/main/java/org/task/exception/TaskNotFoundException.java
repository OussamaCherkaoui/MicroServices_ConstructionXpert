package org.task.exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException() {
        super("Task not found");
    }
}
