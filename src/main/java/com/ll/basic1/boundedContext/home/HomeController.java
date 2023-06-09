package com.ll.basic1.boundedContext.home;

import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private int sum;

    private final List<Person> personList;

    // 필드주입
    @Autowired
    private MemberService memberService;

    public HomeController() {
        personList = new ArrayList<>();
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

    @GetMapping("/home/plus")
    @ResponseBody
    // @RequestParam 의 의미
    // int a는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값을 더한다.
    // /plus?a=100&&b=200
    public int showPlus(@RequestParam(defaultValue = "100") int a,
                        @RequestParam int b){
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name,
                            @RequestParam int age){
        Person p = new Person(name, age);
        System.out.println(p);

        personList.add(p);

        return "%d번 사람이 추가되었습니다.".formatted(p.getId());

    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople(){
        return personList;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam int id){
        boolean removed = personList.removeIf(person -> person.getId() == id);

        if (removed == false){
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        return "%d번 사람이 삭제되었습니다..".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam int age){
        // 이 stream 외우도록하자.
        Person found = personList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (found == null){
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        found.setName(name);
        found.setAge(age);

        return "%d번 사람이 수정되었습니다..".formatted(id);
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int countInCookie = 0;

        if (req.getCookies() != null){
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie ->  cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }

    @GetMapping("/home/user1")
    @ResponseBody
    public Member showUser1() {
        return memberService.findByUsername("user1");
    }
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Person{
    private static int lastId;
    private int id;
    private String name;
    private int age;

    static {
        lastId = 0;
    }

    Person(String name, int age){
        this(++lastId, name, age);
    }
}
