package com.raphael.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retreiveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retreiveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) throw new UserNotFoundException("id-"+id);

        WebMvcLinkBuilder links = linkTo(methodOn(this.getClass()).retreiveAllUsers());
        EntityModel<User> model = EntityModel.of(user);
        model.add(links.withRel("all-users"));

        return model;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User savedUser = service.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        User deleteUser = service.deleteUser(id);

        if (deleteUser == null) throw new UserNotFoundException("id-"+id);

        return ResponseEntity.noContent().build();
    }
}
