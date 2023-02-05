package com.hackaton.project.exceptions.common;

import javax.swing.text.html.parser.Entity;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
