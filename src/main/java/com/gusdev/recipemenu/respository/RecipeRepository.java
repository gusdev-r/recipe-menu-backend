package com.gusdev.recipemenu.respository;

import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
