package com.example.prueba.controller;

import com.example.prueba.entity.User;
import com.example.prueba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    private ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id" ) Long id){

        Optional<User> optionalUser = userService.findById(id);// esto se hace por si no se encuentra el registro en la db

        if(!optionalUser.isPresent()){ // si no hay un obj presente en optionaUser...
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalUser);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@PathVariable(value = "id") Long id,@RequestBody User user){

        Optional<User> optionalUser = userService.findById(id);// evita que de un null

        if(!optionalUser.isPresent()){ // si no hay un obj presente en optionaUser...
            return ResponseEntity.notFound().build();// con el build se dice que se "construye la respuesta"
        }

// para evitar hacer tdo esto se puede hacer un BeanUtils.copyProperties(user,optionalUser);
//      BeanUtils.copyProperties(user,optionalUser); pero como no se quiere actualizar el id no se usa este

        optionalUser.get().setName(user.getName());
        optionalUser.get().setEmail(user.getEmail());
        optionalUser.get().setEnabled(user.getEnabled());
        optionalUser.get().setSurname(user.getSurname());


        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(optionalUser.get()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){

        Optional<User> optionalUser = userService.findById(id);

        if(!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }

         userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<User> usersAll(){
        List<User> users =
                StreamSupport.stream(userService.findAll().spliterator(),false)
                        .collect(Collectors.toList());

//              (List<User>) userService.findAll();
        return users;
    }


}

