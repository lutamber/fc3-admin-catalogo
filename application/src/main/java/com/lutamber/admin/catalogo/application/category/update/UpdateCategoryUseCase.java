package com.lutamber.admin.catalogo.application.category.update;

import com.lutamber.admin.catalogo.application.UseCase;
import com.lutamber.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
    extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
