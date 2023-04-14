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
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseHandler.responseBuilder("category created",
                HttpStatus.CREATED,categoryService.saveCategory(categoryRequest));
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<Object> updateCategory(@RequestBody @Valid CategoryRequest categoryRequest,
                                                 @PathVariable Long categoryId){
        return ResponseHandler.responseBuilder("Category updated",
                HttpStatus.OK,categoryService.updateCategory(categoryRequest,categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok().body(categoryService.getCategory(categoryId));
    }

}
