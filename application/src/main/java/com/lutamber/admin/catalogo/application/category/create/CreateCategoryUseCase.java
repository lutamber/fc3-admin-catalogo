package com.lutamber.admin.catalogo.application.category.create;

import com.lutamber.admin.catalogo.application.UseCase;
import com.lutamber.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {



}
