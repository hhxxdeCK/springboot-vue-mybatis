package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.result.Result;

import org.springframework.web.util.HtmlUtils;

@RestController("loginController")
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User u = userService.get(username, requestUser.getPassword());
        if (null == u) {
            System.out.println("error");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
