package org.project.donkey.api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.donkey.commons.CommonProcess;
import org.project.donkey.commons.Utils;
import org.project.donkey.models.member.MemberSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess {

    private final Utils utils;
    private final MemberSaveService saveService;

    @GetMapping("/join")
    public String join(@ModelAttribute org.project.donkey.api.member.RequestJoin form, Model model) {
        commonProcess(model, Utils.getMessage("회원가입", "common"));
        
        return utils.tpl("member/join");
    }

    @PostMapping("/join")
    public String joinPs(@Valid org.project.donkey.api.member.RequestJoin form, Errors errors, Model model) {
        commonProcess(model, Utils.getMessage("회원가입", "common"));

        saveService.join(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "redirectURL", required = false) String redirectURL, Model model) {
        commonProcess(model, Utils.getMessage("로그인", "common"));
        
        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }

}
