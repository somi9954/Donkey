package org.project.donkey.models.comment;

import org.project.donkey.commons.Utils;
import org.project.donkey.commons.exceptions.BadRequestException;

public class CommentNotFoundException extends BadRequestException {

    public CommentNotFoundException() {
        super(Utils.getMessage("NotFound.comment", "error"));
    }
}
