package org.project.exception;

public class ProjectNotFoundException extends Exception{
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
