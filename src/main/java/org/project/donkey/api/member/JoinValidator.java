package org.project.donkey.api.member;

import lombok.RequiredArgsConstructor;
import org.project.donkey.commons.validators.MobileValidator;
import org.project.donkey.commons.validators.PasswordValidator;
import org.project.donkey.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원 가입 추가 유효성 검사
 *
 */
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, PasswordValidator, MobileValidator {

    private final MemberRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(org.koreait.controllers.members.RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        org.koreait.controllers.members.RequestJoin form = (org.koreait.controllers.members.RequestJoin)target;

        /**
         * 1. 아이디 중복 여부 체크
         * 2. 비밀번호 복잡성 체크
         * 3. 비밀번호 및 비밀번호 확인 일치 여부
         * 4. 휴대전화번호 형식 체크
         */

        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();

        // 1. 아이디 중복 여부 체크
        if (email != null && !email.isBlank() && repository.exists(email)) {
            errors.rejectValue("email", "Duplicated");
        }

        // 2. 비밀번호 복잡성 체크
        if (password != null && !password.isBlank() && (!alphaCheck(password, false) || !numberCheck(password) || !specialCharsCheck(password))) {
            errors.rejectValue("password", "Complexity");
        }

        // 3. 비밀번호 및 비밀번호 확인 일치 여부
        if (password != null && !password.isBlank() && confirmPassword != null && !confirmPassword.isBlank() && !password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "Mismatch");
        }

        // 4. 휴대전화번호 형식 체크
        if (mobile != null && !mobile.isBlank() && !mobileNumCheck(mobile)) {
            errors.rejectValue("mobile", "Mobile");
        }
    }
}