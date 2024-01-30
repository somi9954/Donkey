package org.project.donkey.commons.exceptions;

public class BadRequestException {
    public BadRequestException() {
        super(Utils.getMessage("BadRequest", "error"));
    }
}