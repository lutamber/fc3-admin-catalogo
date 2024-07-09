package com.lutamber.admin.catalogo.application.category.create;

import com.lutamber.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.argThat;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallCreateCategoryUseCase_thenReturnCategoryID() {

        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria de Filmes";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                && Objects.equals(expectedDescription, aCategory.getDescription())
                && Objects.equals(expectedIsActive, aCategory.isActive())
                && Objects.nonNull(aCategory.getId())
                && Objects.nonNull(aCategory.getCreatedAt())
                && Objects.nonNull(aCategory.getUpdatedAt())
                && Objects.isNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    public void givenACommandWithInvalidName_whenCallCreateCategoryUseCase_thenThrowsDomainException() {

        final var expectedName = "Fi ";
        final var expectedDescription = "Categoria de Filmes";
        final var expectedIsActive = true;
        final var expectedMessage = "name must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(notification.getFirstError().message(), expectedMessage);
        Assertions.assertEquals(notification.getErrors().size(), expectedErrorCount);
    }

    @Test
    public void givenAValidInactiveCategoryCommand_whenCallCreateCategoryUseCase_thenReturnInactiveCategoryID() {

        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria de Filmes";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                && Objects.equals(expectedDescription, aCategory.getDescription())
                && Objects.equals(expectedIsActive, aCategory.isActive())
                && Objects.nonNull(aCategory.getId())
                && Objects.nonNull(aCategory.getCreatedAt())
                && Objects.nonNull(aCategory.getUpdatedAt())
                && Objects.nonNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    public void givenAValidCommand_whenCallCreateCategoryUseCase_thenGatewayThrowsUnexpectedException() {

        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria de Filmes";
        final var expectedIsActive = true;
        final var expectedExceptionMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenThrow(new IllegalStateException(expectedExceptionMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(notification.getFirstError().message(), expectedExceptionMessage);
        Assertions.assertEquals(notification.getErrors().size(), expectedErrorCount);

        Mockito.verify(categoryGateway, Mockito.times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                && Objects.equals(expectedDescription, aCategory.getDescription())
                && Objects.equals(expectedIsActive, aCategory.isActive())
                && Objects.nonNull(aCategory.getId())
                && Objects.nonNull(aCategory.getCreatedAt())
                && Objects.nonNull(aCategory.getUpdatedAt())
                && Objects.isNull(aCategory.getDeletedAt())
        ));
    }
}
