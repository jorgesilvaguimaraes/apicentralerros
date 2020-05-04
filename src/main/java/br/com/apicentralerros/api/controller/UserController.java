package br.com.apicentralerros.api.controller;

import br.com.apicentralerros.api.model.UserInputModel;
import br.com.apicentralerros.api.model.input.EventLogInputModel;
import br.com.apicentralerros.api.model.output.EventOutPutModel;
import br.com.apicentralerros.domain.entity.Event;
import br.com.apicentralerros.domain.entity.User;
import br.com.apicentralerros.domain.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;

   @Autowired
   private ModelMapper modelMapper;

    @GetMapping
    public List<User> lista(){
        return  this.userService.findAll() ;
    }

    @PostMapping
    public ResponseEntity<User> store(@Valid @RequestBody UserInputModel userInputModel){
        User user = modelMapper.map(userInputModel, User.class);
        return new ResponseEntity<User>(this.userService.store(user), HttpStatus.CREATED) ;
    }

}
