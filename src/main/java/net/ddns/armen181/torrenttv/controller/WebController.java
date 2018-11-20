package net.ddns.armen181.torrenttv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebController {
    @RequestMapping("/log")
    public String welcome(Map<String, Object> model) {
        return "login.html";
    }
}
