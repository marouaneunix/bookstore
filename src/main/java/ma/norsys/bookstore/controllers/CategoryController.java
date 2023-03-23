package ma.norsys.bookstore.controllers;


import ma.norsys.bookstore.models.Category;
import ma.norsys.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories= categoryRepository.findAll();
        return ResponseEntity.ok().body(categories);
    }


}