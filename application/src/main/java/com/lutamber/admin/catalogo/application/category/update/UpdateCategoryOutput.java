package com.lutamber.admin.catalogo.application.category.update;

import com.lutamber.admin.catalogo.domain.category.Category;
import com.lutamber.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(
        CategoryID id
) {

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }

}
