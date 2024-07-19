package com.transactional.systemactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    // Instanciamos ahora el Servicio de UserService, esto nos brinda una mayor capa de ocultamiento
    @Autowired
    private UserService userService;

    // Definimos metodos (CRUD )
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    // Definimos ahora pues un metodo post que recibe un cuerpo ( USER )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Definimos ahora un metodo que abstrae la información del usuario en especifico
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

}
