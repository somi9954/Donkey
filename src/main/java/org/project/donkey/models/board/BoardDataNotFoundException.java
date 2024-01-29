package org.project.donkey.models.board;


import org.apache.coyote.BadRequestException;
import org.project.donkey.commons.Utils;

public class BoardDataNotFoundException extends BadRequestException {
    public BoardDataNotFoundException() {
        super(Utils.getMessage("NotFound.boardData", "error"));
    }
}
