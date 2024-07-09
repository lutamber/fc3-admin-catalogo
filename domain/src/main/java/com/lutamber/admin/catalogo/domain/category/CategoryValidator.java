package com.lutamber.admin.catalogo.domain.category;

import com.lutamber.admin.catalogo.domain.validation.Error;
import com.lutamber.admin.catalogo.domain.validation.ValidationHandler;
import com.lutamber.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = category.getName();
        if(name == null)
        {
            this.validationHandler().append(new Error("name cannot be null"));
            return;
        }
        if(name.isBlank())
        {
            this.validationHandler().append(new Error("name cannot be empty"));
            return;
        }

        final int nameLength = name.trim().length();
        if(nameLength > NAME_MAX_LENGTH || nameLength < NAME_MIN_LENGTH)
            this.validationHandler().append(new Error("name must be between 3 and 255 characters"));
    }
}
