package com.ll.basic1.controller.member.controller;

import com.ll.basic1.base.rq.RsData;
import com.ll.basic1.controller.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// springboot 20강, 21강 강의 다시보기
@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData startLogin(@RequestParam String username,
                        @RequestParam String password){
        // v1
//        Map<String, Object> rsData = new LinkedHashMap<>() {{
//            put("resultCode", "S-1");
//            put("msg", "%s 님 환영합니다.".formatted(username))
//        }};

        if (username == null || username.trim().length() == 0){
            return RsData.of("F-3", "username을 입력해주세요");
        }

        if (password == null || password.trim().length() == 0){
            return RsData.of("F-4", "password를 입력해주세요");
        }

        return memberService.tryLogin(username, password);
    }

}
