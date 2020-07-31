package com.quark.porent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户社区页面索引
 * @author zhangds
 * @date 2020/7/29 10:32
 */
@Controller
public class PageController {

    @GetMapping(value = {"/index", ""})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/label")
    public String labelhome() {
        return "label/home";
    }

    @RequestMapping("/chat")
    public String chathome(){
        return "chat/home";
    }
}
