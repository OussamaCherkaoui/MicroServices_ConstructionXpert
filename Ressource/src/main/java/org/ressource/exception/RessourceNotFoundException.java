package org.ressource.exception;

public class RessourceNotFoundException extends Exception{
    public RessourceNotFoundException() {
        super("Ressource not found");
    }
}
