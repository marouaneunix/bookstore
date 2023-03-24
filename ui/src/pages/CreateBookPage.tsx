import {Link, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import './CreateBookPage.css'
import axios from "axios";


export const CreateBookPage = () => {
    let navigate=useNavigate();
    const [name,setName]=useState('');
    const [author,setAuthor]=useState('');
    const [isbn,setIsbn]=useState('');
    const [category,setCategory]=useState('');
    const [description,setDescription]=useState('');
    const addBook=async (e:any)=>{
        e.preventDefault();
        const book={isbn,name,author,category,description}
        console.log(book.isbn)
        if(book.isbn!==null&&book.name!==null){
            console.log(book.isbn)
            await axios.post("/api/books",book)
            console.log(book.isbn)
            navigate("/")
        }else{
            alert("isbn and name are required")
        }
    }
    return (
        <>
            <nav className="flex" aria-label="Breadcrumb">
                <ol className="inline-flex items-center space-x-1 md:space-x-3">
                    <li className="inline-flex items-center">
                        <Link to="/"
                              className="inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600 dark:text-gray-400 dark:hover:text-white">
                            <svg aria-hidden="true" className="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path>
                            </svg>
                            Home
                        </Link>
                    </li>
                    <li aria-current="page">
                        <div className="flex items-center">
                            <svg aria-hidden="true" className="w-6 h-6 text-gray-400" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                <path fillRule="evenodd"
                                      d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                      clipRule="evenodd"></path>
                            </svg>
                            <span
                                className="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Create books</span>
                        </div>
                    </li>
                </ol>
            </nav>

            <form className="book-form">

                    <label htmlFor="isbn">ISBN:</label>
                    <input type="text" id="isbn" name="isbn" placeholder="Enter book isbn" value={isbn} onChange={(event)=>
                        setIsbn(event.target.value)
                    } required/>

                    <label htmlFor="title">Name:</label>
                    <input type="text" id="title" name="title" placeholder="Enter book title" value={name} onChange={(event)=>setName(event.target.value)} required/>

                    <label htmlFor="author">Author:</label>
                    <input type="text" id="author" name="author" placeholder="Enter book author name" value={author} onChange={(event)=>setAuthor(event.target.value)}/>

                    <label htmlFor="category">Category:</label>
                    <input type="text" id="category" name="category" placeholder="Enter book category" value={category} onChange={(event)=>setCategory(event.target.value)}/>


                    <label htmlFor="description">Description:</label>
                    <textarea id="description" name="description" placeholder="Enter book description" value={description} onChange={(event)=>setDescription(event.target.value)}></textarea>

                    <button type="submit" onClick={addBook}>Save Book</button>
            </form>

        </>
    )
}