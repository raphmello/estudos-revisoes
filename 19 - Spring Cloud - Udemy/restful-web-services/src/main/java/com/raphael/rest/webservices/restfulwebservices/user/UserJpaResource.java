package com.raphael.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

    @Autowired
    UserDaoService service;

    @Autowired
    UserRepository repository;

    @GetMapping(path = "/users")
    public List<User> retreiveAllUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retreiveUser(@PathVariable int id) {
        Optional<User> userOptional = repository.findById(id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFoundException("id-"+id);
        }

        WebMvcLinkBuilder links = linkTo(methodOn(this.getClass()).retreiveAllUsers());
        EntityModel<User> model = EntityModel.of(user);
        model.add(links.withRel("all-users"));

        return model;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User savedUser = repository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}
