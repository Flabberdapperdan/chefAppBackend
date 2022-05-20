package rest;

import controller.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeEndpoint {

    @Autowired
    RecipeService service;

    @GetMapping("first")
    public void firsGet() {
        System.out.println("the firs het is here");
    }
}
