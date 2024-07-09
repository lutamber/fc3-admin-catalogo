package com.lutamber.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    List<Error> getErrors();

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error getFirstError() {
        if(getErrors() != null && !getErrors().isEmpty())
            return getErrors().getFirst();
        else
            return null;
    }

    public interface Validation {
        void validate();
    }

}
