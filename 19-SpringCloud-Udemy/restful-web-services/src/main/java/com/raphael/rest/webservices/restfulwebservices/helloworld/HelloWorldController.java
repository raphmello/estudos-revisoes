package com.raphael.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(name);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale
            ) {
        return messageSource.getMessage("good.morning.message", null,"Default Message",
//                locale);
                LocaleContextHolder.getLocale());
    }
}
