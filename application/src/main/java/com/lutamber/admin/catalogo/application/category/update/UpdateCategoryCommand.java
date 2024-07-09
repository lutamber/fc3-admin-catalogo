package com.lutamber.admin.catalogo.application.category.update;

import com.lutamber.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryCommand(
        CategoryID categoryId,
        String name,
        String description,
        boolean isActive
) {

    public static UpdateCategoryCommand create(
            final CategoryID anCategoryId,
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new UpdateCategoryCommand(anCategoryId, aName, aDescription, isActive);
    }

}
