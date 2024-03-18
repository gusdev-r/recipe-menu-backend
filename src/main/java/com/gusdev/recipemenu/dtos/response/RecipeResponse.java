package com.gusdev.recipemenu.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record RecipeResponse (Long id, String title, String image, String description,
                              boolean vegetarian, List<Long> likes, LocalDateTime createdAt){
}
