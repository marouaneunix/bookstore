import React, {useEffect, useState} from "react";
import {BookCriteria, SearchBooksForm} from "../components/SearchBooksForm";
import {deleteBookById, fetchBooks, fetchBooksByCriteria} from "../services/bookApiService";
import {Breadcrumbs} from "../components/Breadcrumbs";
import {BookList} from "../components/BookList";

export type Book = {
    id: number;
    name: string;
    categories: string
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);

    useEffect(() => {
        fetchBooks()
            .then(data => {setBooks(data)})
            .catch(error => {
                //TODO Display the error message and delete the console.error()
                console.error(error)
            })
    }, [])

    const handleSearchBooks = (bookCriteria: BookCriteria) => {
        fetchBooksByCriteria(bookCriteria)
            .then(data => {
                setBooks(data)
            })
            .catch(error => console.error(error))
    }

    const handleBookDelete = (booksId: number) => {
        deleteBookById(booksId)
            .then(() => {
                fetchBooks()
                    .then(data => {setBooks(data)})
                    .catch(error => {
                        //TODO Display the error message and delete the console.error()
                        console.error(error);
                    })
            }).catch(error => {
            //TODO Display the error message and delete the console.error()
            console.error(error);
        })
    }

    return (
        <>
            <Breadcrumbs currentPage="Books"/>
            <SearchBooksForm onSearch={handleSearchBooks}/>
            <BookList books={books} onBookDelete={handleBookDelete}/>
        </>
    )
}

