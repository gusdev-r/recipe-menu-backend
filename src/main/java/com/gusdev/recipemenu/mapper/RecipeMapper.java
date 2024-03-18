package com.gusdev.recipemenu.mapper;

import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.dtos.response.RecipeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecipeMapper {
    RecipeResponse toRecipe(Recipe recipe);
    List<RecipeResponse> toRecipeList(List<Recipe> recipeList);
}
