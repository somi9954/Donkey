package org.project.donkey.models.board;


import org.apache.coyote.BadRequestException;
import org.project.donkey.commons.Utils;
import org.springframework.http.HttpStatus;

public class RequiredPasswordCheckException extends BadRequestException {
    public RequiredPasswordCheckException() {
        super(Utils.getMessage("Required.guestPw.check", "validation"));
    }
}
