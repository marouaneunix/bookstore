import axios from "axios";
import React, { useState } from "react";
import {Link} from "react-router-dom";
import { Fragment } from "react";
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import { NavBar } from "../components/NavBar";



type Book={
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


      const onAddBook = async(event: React.FormEvent<HTMLFormElement>)=>{
        event.preventDefault();
        await axios.post("/books",book)
        window.location.href=`/`
      }

      
      
    return (
        <>
        <NavBar pageName="create Book"/>
        
        <h1 className="text-center text-2xl font-semibold mt-10">Add New  Book</h1>

        <form   className="max-w-xl m-auto py-10 mt-10 px-12 border"
                    onSubmit={onAddBook}>
            <div className="mb-2">
                <label htmlFor="Isbn" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Isbn</label>
                <input type="text" id="isbn"
                      className="border-solid border-gray-300 border py-2 px-4 w-full rounded text-gray-700"
                      placeholder="Isbn" value={book.isbn}
                      onChange={(event) =>
                        setBook({ ...book, isbn: event.target.value })
                      }
                      required/>
            </div>
            <div className="mb-2">
                <label htmlFor="title" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Title</label>
                <input type="text" id="title"
                      className="border-solid border-gray-300 border py-2 px-4 w-full rounded text-gray-700"
                      placeholder="title" value={book.title}
                      onChange={(event) =>
                        setBook({ ...book, title: event.target.value })
                      }
                      required/>
            </div>
            <div className="mb-2">
                <label htmlFor="author"
                      className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Author</label>
                <input type="text" id="author" 
                    className="border-solid border-gray-300 border py-2 px-4 w-full rounded text-gray-700"
                      value={book.author}
                      onChange={(event) =>
                        setBook({ ...book, author: event.target.value })
                      }
                      required/>
            </div>
            <div className="mb-2">
                <label htmlFor="categories"
                      className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Categories</label>
                <input type="text" id="categories"
                      className="border-solid border-gray-300 border py-2 px-4 w-full rounded text-gray-700"
                      value={book.categories}
                      onChange={(event) =>
                        setBook({ ...book, categories: event.target.value })
                      }
                      required/>
            </div>
            <div className="mb-2">
                <label htmlFor="description"
                      className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Description</label>
                <textarea id="description" placeholder="Write a description (at least 100 caracteres)..."
                      className="border-solid border-gray-300 border py-2 px-4 w-full rounded text-gray-700"
                      value={book.description}
                      onChange={(event) =>{
                        setBook({ ...book, description: event.target.value })}
                      }
                      required/>
            </div>
            <button type="submit" 
                  className="mt-4 w-full bg-green-400 hover:bg-green-600 text-green-100 border py-3 px-6 font-semibold text-md rounded">
                Add Book 
            </button>
    </form>


 
        </>
    )  
}



