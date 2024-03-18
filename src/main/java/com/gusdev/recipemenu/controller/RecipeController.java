package com.gusdev.recipemenu.controller;

import com.gusdev.recipemenu.mapper.RecipeMapper;
import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.dtos.response.RecipeResponse;
import com.gusdev.recipemenu.service.RecipeService;
import com.gusdev.recipemenu.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gusdev.recipemenu.utils.Utility.LOGGER;
@RestController
@RequestMapping({"v1/recipes/recipe", "v1/recipes/recipe/"})
public class RecipeController {

    private RecipeService recipeService;
    private UserService userService;
    private RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, UserService userService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeMapper = recipeMapper;
    }

    public RecipeController() {
    }

    @GetMapping(path = "/all")
    public List<RecipeResponse> getAllRecipe() {
        return recipeMapper.toRecipeList(recipeService.findAll());
    }

    @PostMapping(name = "/user/{userId}")
    public RecipeResponse createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        LOGGER.info("Starting the creation of a new Recipe - RecipeController");
        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return recipeMapper.toRecipe(createdRecipe);
    }
    @PutMapping(path = "/update/{userId}")
    public RecipeResponse updateRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) {
        LOGGER.info("Starting the update of the Recipe - RecipeController");
        return recipeMapper.toRecipe(recipeService.update(recipe, userId));
    }
    @PutMapping(path = "/{id}/like/")
    public RecipeResponse likeRecipe(@PathVariable Long id, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        LOGGER.info("Liking the recipe from user by param jwt");
        return recipeMapper.toRecipe(recipeService.likeRecipe(id, user));
    }
    @DeleteMapping(path = "/delete/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) {
        LOGGER.info("Starting the delete of the recipe by param id: '{}' - RecipeController", recipeId);
        recipeService.delete(recipeId);
        return "Recipe was deleted successfully";
    }

}
