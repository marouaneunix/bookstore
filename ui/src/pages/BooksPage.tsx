import React, {useEffect, useState} from "react";
import axios from "axios";
import PageHero from "../layout/PageHero";
import BooksList from "../components/BooksList";

type Book = {
    id: number;
    name: string;
    author: string;
    isbn: string;
    category: string;
    description: string;
}

export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);


    useEffect(() => {
        const fetchBooks = async () => {
            const response = await axios("/api/v1/books")
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();
    },[])

    return (
        <>
            <PageHero title="Books"/>
            <BooksList books={books} />
        </>
    )
}