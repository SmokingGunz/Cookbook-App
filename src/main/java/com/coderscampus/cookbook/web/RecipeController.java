package com.coderscampus.cookbook.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.cookbook.domain.Recipe;
import com.coderscampus.cookbook.service.FileService;

@RestController
public class RecipeController {

    @Autowired
    private FileService fileService;

    @GetMapping("/all-recipes")
    public List<Recipe> getFullCollection() throws IOException{
        return fileService.allRecipes();

    }

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFreeCollection() {
        return fileService.getGlutenFreeRecipes();

    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganCollection() {
        return fileService.getVeganRecipes();

    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFreeCollection() {
        return fileService.getVeganAndGlutenFreeRecipes();

    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianCollection() {
        return fileService.getVegetarianRecipes();

    }

}