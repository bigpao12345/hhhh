package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mappertest {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/testmysql")
    public String testmysql(){

        List<User> userList = userMapper.findall();
        System.out.println(userList.toString());
        return "11";
    }
}
