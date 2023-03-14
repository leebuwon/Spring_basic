package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    private int sum;

    private final List<Person> personList;

    public HomeController(List<Person> personList) {
        this.personList = personList;
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
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Person{
    private static int lastId;
    private final int id;
    private final String name;
    private final int age;

    static {
        lastId = 0;
    }

    Person(String name, int age){
        this(++lastId, name, age);
    }
}
