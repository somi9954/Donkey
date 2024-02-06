package org.project.donkey.api.comments;

import lombok.RequiredArgsConstructor;
import org.project.donkey.commons.validators.PasswordValidator;
import org.project.donkey.configs.jwt.CustomJwtFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CommentFormValidator implements Validator, PasswordValidator {

    private final CustomJwtFilter jwtFilter;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CommentForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 로그인한 회원은 검증 X
        if (jwtFilter.isUserLoggedIn()) {
            return;
        }

        CommentForm form = (CommentForm)target;

        // 비회원 - 비회원 비밀번호 필수
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "guestPw", "NotBlank");

        // 비밀번호 복잡성 - 알파벳 + 숫자
        String guestPw = form.getGuestPw();
        if (StringUtils.hasText(guestPw)
                && (!alphaCheck(guestPw, true) || !numberCheck(guestPw))) {
            errors.rejectValue("guestPw", "Complexity");
        }
    }
}
