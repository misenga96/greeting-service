package pt.misenga.microservicos.greeting.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.misenga.microservicos.greeting.service.config.GreetingConfiguration;
import pt.misenga.microservicos.greeting.service.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {
        if (name.isEmpty()) {
            name = configuration.getDefaultValue();
        }
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}