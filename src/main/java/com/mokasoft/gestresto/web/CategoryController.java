package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.CategoryRequest;
import com.mokasoft.gestresto.dtos.CategoryResponse;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseHandler.responseBuilder("nouvelle catégorie crée avec succès",
                HttpStatus.CREATED,categoryService.saveCategory(categoryRequest));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
