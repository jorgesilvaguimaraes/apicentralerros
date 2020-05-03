package br.com.apicentralerros.api.controller;

import br.com.apicentralerros.domain.entity.User;
import br.com.apicentralerros.domain.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> lista(){
        return  this.userService.findAll() ;
    }

}
