package br.com.apicentralerros.domain.service.interfaces;

import br.com.apicentralerros.domain.entity.Event;
import br.com.apicentralerros.domain.entity.User;

import java.util.List;

public interface UserServiceInterface extends ServiceInterface<User>{
    List<User> findAll();

    User store(User user);

}
