package org.project.donkey.api.main;

import lombok.RequiredArgsConstructor;
import org.project.donkey.commons.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final Utils utils;

    @GetMapping("/")
    public String index() {

        return utils.tpl("main/index");
    }
}
