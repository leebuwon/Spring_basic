package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private int sum;

    public HomeController() {
        this.sum = 0;
    }

    @GetMapping("/home/main")
    @ResponseBody // 아래 메서드를 싱행한 후 그 리턴값을 응답으로 준다.
    public String showMain1(){
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public String showIncrease(){
        sum++;
        return "응답 : " + sum;
    }
}
