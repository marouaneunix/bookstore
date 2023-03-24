import React, { useEffect, useState } from "react";
import axios from "axios";
import { TableHead } from "../components.tsx/TableHead";
import { TableBody } from "../components.tsx/TableBody";
import { SubNav } from "../components.tsx/subNav";
import { BookSearch } from "../components.tsx/BookSearch";
import { useParams } from "react-router-dom";

export interface Book {
    id: number;
    name: string;
    author: string;
    categorie: string;
    isbn: string;
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);

    const { id } = useParams();

    const fetchBooks = async () => {
        const response = await axios("/books")
        console.log(response.data);
        setBooks(response.data)
    }

    useEffect(() => {
        fetchBooks();
    }, [])

    const handelDeleteBook = async (book: Book) => {
        await axios.delete(`/books/${book.id}`);
        fetchBooks();

    }
    const handelSearch = async (searchTerm: string, catSearchTerm: string) => {
        const response = await axios.get('/books/search', {
            params: {
                name: searchTerm,
                categories: catSearchTerm
            }
        })
        console.log(response.data);
        setBooks(response.data)
    }


    return (
        <>
            <SubNav />
            <BookSearch onSearchBook={handelSearch} />
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <TableHead />
                    <tbody>
                        {
                            books.map((book) => {

                                return <>
                                    
                                        <TableBody
                                            key={book.id}
                                            book={book}
                                            onDeleteBook={handelDeleteBook}
                                        />
                                    
                                </>
                            })
                        }
                    </tbody>
                </table>
            </div>

        </>
    )
}