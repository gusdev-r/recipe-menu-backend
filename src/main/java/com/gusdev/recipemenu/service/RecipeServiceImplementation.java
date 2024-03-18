package com.gusdev.recipemenu.service;

import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.infra.exception.RecipeNotFoundException;
import com.gusdev.recipemenu.infra.exception.enums.ErrorCode;
import com.gusdev.recipemenu.respository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static com.gusdev.recipemenu.utils.Utility.LOGGER;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private final RecipeRepository recipeRepository;
    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe likeRecipe(Long id, User user) {
        var recipe = findById(id);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe newRecipe= new Recipe();
        newRecipe.setUser(user);
        newRecipe.setTitle(recipe.getImage());
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setId(recipe.getId());
        newRecipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(newRecipe);
    }

    @Override
    public void delete(Long id) {
        recipeRepository.delete(findById(id));
    }

    @Override
    public Recipe update(Recipe recipe, Long id) {
        var recipeToUpdate = findById(id);
        if (recipe.getTitle() != null) {
            LOGGER.info("Updating the title - RecipeServiceImplementation");
            recipeToUpdate.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            LOGGER.info("Updating the image - RecipeServiceImplementation");
            recipeToUpdate.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            LOGGER.info("Updating the description - RecipeServiceImplementation");
            recipeToUpdate.setImage(recipe.getImage());
        }
        return recipeRepository.save(recipeToUpdate);
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isEmpty()) {
            LOGGER.info("This recipe is empty - RecipeServiceImplementation");
            throw new RecipeNotFoundException(ErrorCode.WA0001.getCode(), ErrorCode.WA0001.getMessage());
        }
        return recipeOptional.get();
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
