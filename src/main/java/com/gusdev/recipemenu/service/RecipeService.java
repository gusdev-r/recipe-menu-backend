package com.gusdev.recipemenu.service;

import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.respository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {
    public Recipe likeRecipe(Long id, User user);
    public Recipe createRecipe(Recipe recipe, User user);
    public void delete(Long id);
    public Recipe update(Recipe recipe, Long id);
    public Recipe findById(Long id);
    public List<Recipe> findAll();
}
