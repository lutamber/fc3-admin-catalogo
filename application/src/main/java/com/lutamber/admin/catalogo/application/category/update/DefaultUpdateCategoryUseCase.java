package com.lutamber.admin.catalogo.application.category.update;

import com.lutamber.admin.catalogo.domain.category.CategoryGateway;
import com.lutamber.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {

        final var aCategoryId = aCommand.categoryId();
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = this.categoryGateway.findById(aCategoryId);

        return null;
    }
}
