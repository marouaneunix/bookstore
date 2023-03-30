package com.example.evaluation_livre.services;


import ma.norsys.bookstore.exceptions.PostArgumentNotValidException;
import ma.norsys.bookstore.models.Book;

import java.util.List;
import java.util.Set;

public interface IBookService {

    Book getById(int id);

    List<Book> getAll();

    Book addBook(Book Book) throws PostArgumentNotValidException;

    boolean deleteBook(int id);

    void updateBook(Book book);
    Set<Book> getByTitleOrCategoriesOrBoth(String title, String categories, String author);
}
