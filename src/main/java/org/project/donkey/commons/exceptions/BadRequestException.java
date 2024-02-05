package org.project.donkey.commons.exceptions;

import org.project.donkey.commons.Utils;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super(Utils.getMessage("BadRequest", "error"));
    }
}