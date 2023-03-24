import React, {useEffect, useState} from "react";
import axios from "axios";
import PageHero from "../layout/PageHero";
import BooksList from "../components/BooksList";
import {VscListFilter} from 'react-icons/all'

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
    const [searchQuery, setSearchQuery] = useState('');
    const [searchCategory, setSearchCategory] = useState('');


    useEffect(() => {
        const fetchBooks = async () => {
            const formData = new FormData();
            formData.append("name", searchQuery);
            formData.append("category", searchCategory);
            const response = await axios.post("/api/v1/books/list", formData);
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();
    },[searchCategory, searchQuery]);

    const filterBooksByName = books.filter((book) => {
        const nameMatch = book.name.toLowerCase().includes(searchQuery.toLowerCase());
        const categoryMatch = book.category.toLowerCase().includes(searchCategory.toLowerCase());
        return nameMatch && categoryMatch;
    });

    const deleteBookHandler = (filteredBooks) => {
        setBooks(filteredBooks)
    };


    return (
        <>
            <PageHero title="Books"/>
            <div className="flex items-center w-[80%] mx-auto space-x-4 lg:w-[50%] my-8 shadow-2xl px-10 py-6 rounded-xl">
                <span className="text-xl font-bold mr-6">
                    <VscListFilter />
                </span>
                <input
                    name="name"
                    type="text"
                    value={searchQuery}
                    className="form-input rounded bg-gray-100"
                    placeholder="Search by name"
                    onChange={(event) => setSearchQuery(event.target.value)}
                />
                <select
                    name="category"
                    id="category"
                    className="form-select rounded bg-gray-100"
                    onChange={(event) => setSearchCategory(event.target.value) }
                >
                    <option value=""></option>
                    <option>Classics</option>
                    <option>Crime</option>
                    <option>Fantasy</option>
                    <option>Humour</option>
                    <option>Horror</option>
                    <option>Fiction</option>
                    <option>Mystery</option>
                    <option>Poetry</option>
                    <option>Romance</option>
                    <option>War</option>
                </select>
            </div>
            <BooksList books={filterBooksByName} onDeleteBook={deleteBookHandler} />
        </>
    )
}