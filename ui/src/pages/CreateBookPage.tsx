import { Link } from "react-router-dom";
import React from "react";
import { useState } from "react";
import axios from "axios";
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { BooksPage } from "./BooksPage";

import { NavBar } from "./NavBar";

type Book = {
  id: number,
  isbn: string,
  title: string,
  author: string,
  categories: string,
  description: string,
}


export const CreateBookPage = () => {
  const defaultBook: Book = {
    id: 0,
    isbn: '',
    title: '',
    author: '',
    categories: '',
    description: '',
  };
  const [book, setBook] = useState<Book>(defaultBook);

  const handleClick = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await axios.post("/api/books", book)
    window.location.href = `/`
  }

  return (
    <>
      <NavBar pageName="Create books" />

      {/*<p className="text-red-500 text-xs italic">Please choose a password.</p>*/}
      <br />
      <div className="flex flex-col items-center justify-center">
        <form className="w-full max-w-lg" onSubmit={handleClick}>
          <div className="flex flex-wrap -mx-3 mb-6">
            <div className="w-full md:w-1/2 px-3">
              <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
                Isbn
              </label>
              <input placeholder="Isbn" value={book.isbn}
                onChange={(event) =>
                  setBook({ ...book, isbn: event.target.value })
                }
                className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" />
            </div>

            <div className="w-full md:w-1/2 px-3">
              <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
                Title
              </label>
              <input placeholder="title" value={book.title}
                onChange={(event) =>
                  setBook({ ...book, title: event.target.value })
                }
                className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" />
            </div>
          </div>
          <div className="flex flex-wrap -mx-3 mb-6">
            <div className="w-full md:w-1/2 px-3">
              <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
                Author
              </label>
              <input value={book.author}
                onChange={(event) =>
                  setBook({ ...book, author: event.target.value })
                }
                className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" placeholder="author" />
            </div>

            <div className="w-full md:w-1/2 px-3">
              <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
                Categories
              </label>
              <input value={book.categories}
                onChange={(event) =>
                  setBook({ ...book, categories: event.target.value })
                }
                className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" placeholder="categories" />
            </div>
          </div>
          <div className="flex flex-wrap -mx-3 mb-6">
            <div className="w-full px-3">
              <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-password">
                Description
              </label>
              <textarea value={book.description}
                onChange={(event) => {
                  setBook({ ...book, description: event.target.value })
                }
                }
                className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-password" placeholder="create a description.." />
            </div>
          </div>
          <button type="submit"
            className=" bg-blue-600 w-full mt-4 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-500 dark:focus:ring-blue-800">
            Add Book
          </button>
        </form>
      </div>
    </>
  )
}