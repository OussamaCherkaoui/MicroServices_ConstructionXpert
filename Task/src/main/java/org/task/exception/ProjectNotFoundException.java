package org.task.exception;

public class ProjectNotFoundException extends Exception{
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
