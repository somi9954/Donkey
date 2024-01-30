package org.project.donkey.models.board.config;

import org.apache.coyote.BadRequestException;

import org.project.donkey.commons.Utils;

public class BoardNotFoundException extends BadRequestException {
    public BoardNotFoundException() {
        super(Utils.getMessage("NotFound.board", "error"));
    }
}
