package com.school.union.poetry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BackStageController
 *
 * @author houxin
 * @date 2019/4/30
 */
@Controller
@RequestMapping("/backstage")
public class BackStageController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
