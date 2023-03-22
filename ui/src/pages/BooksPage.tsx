import React, {useEffect, useState} from "react";
import axios from "axios";
import PageHero from "../layout/PageHero";

type Book = {
    id: number;
    name: string;
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

            {
                books.map(book => <h3>{book.name}</h3>)
            }
        </>
    )
}