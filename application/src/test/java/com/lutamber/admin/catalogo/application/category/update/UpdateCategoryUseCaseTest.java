package com.lutamber.admin.catalogo.application.category.update;

import com.lutamber.admin.catalogo.domain.category.Category;
import com.lutamber.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallUpdateCategoryUseCase_thenReturnCategoryId() {

        final var expectedCategoryName = "Category Name";
        final var expectedCategoryDescription = "Category Description";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Cool Category", "Cool Description", true);

        final var aCategoryId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.create(aCategoryId, expectedCategoryName, expectedCategoryDescription, expectedIsActive);

        Mockito.when(categoryGateway.findById(Mockito.eq(aCategoryId))).thenReturn(Optional.of(aCategory));
        Mockito.when(categoryGateway.update(Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var aOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(aOutput);
        Assertions.assertNotNull(aOutput.id());
        Assertions.assertEquals(aOutput.id(), aCategoryId);

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(Mockito.eq(aCategoryId));
        Mockito.verify(categoryGateway, Mockito.times(1)).update(Mockito.argThat(
            aUpdatedCategory ->
                Objects.equals(aUpdatedCategory.getId(), aCategoryId)
                && Objects.equals(aUpdatedCategory.getName(), expectedCategoryName)
                && Objects.equals(aUpdatedCategory.getDescription(), expectedCategoryDescription)
                && Objects.equals(aUpdatedCategory.isActive(), expectedIsActive)
                && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                && Objects.nonNull(aUpdatedCategory.getUpdatedAt())
                && Objects.isNull(aUpdatedCategory.getDeletedAt())
        ));

    }


}
