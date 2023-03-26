import React, { useEffect, useState } from "react";
import axios from "axios";
import { TableHeadItem } from "./TableHeadItem";
import { TableRow } from "./TableRow";
import { Search } from "./Search";
import { NavBar } from "./NavBar";


type Book = {
    author: string,
    categories: string,
    description: string,
    id: number,
    isbn: string,
    title: string
}

export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);

    let column = [
        { heading: 'Isbn', value: 'isbn' },
        { heading: 'Title', value: 'title' },
        { heading: 'Author', value: 'author' },
        { heading: 'Categories', value: 'categories' },
        { heading: 'Action', value: 'id' },
    ]

    const fetchBooks = async () => {
        const response = await axios("/api/books")
        setBooks(response.data)
    }

    useEffect(() => { fetchBooks() }, [])

    const onDelete = async (key: any) => {
        await axios.delete("/api/books/" + key)
        fetchBooks();
    }
    const onSearch = async (search: any) => {
        const response = await axios.get("/api/books/titlesAndCategories", {
            params: {
                title: search.title,
                categories: search.categories
            }
        })
        setBooks(response.data)
    }

    return (
        <>
            <NavBar pageName="books" />
            <Search onSearch={onSearch} />

            <br />

            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            {column.map((item, index) => <TableHeadItem item={item} key={index} />)}
                        </tr>
                    </thead>
                    <tbody>
                        {books.map((item, index) => <TableRow item={item} column={column} deleteBook={onDelete} key={index} />)}
                    </tbody>

                </table>
            </div>

        </>
    )
}

