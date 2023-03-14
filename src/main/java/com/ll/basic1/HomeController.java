package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home/main")
    @ResponseBody // 아래 메서드를 싱행한 후 그 리턴값을 응답으로 준다.
    public String showMain(){
        return "안녕하세요";
    }
}
