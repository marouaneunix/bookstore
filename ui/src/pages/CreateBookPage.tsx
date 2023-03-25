import {Link, useNavigate} from "react-router-dom";
import React, { useState } from "react";
import axios from "axios";
import './CreateBookPage.css'


export const CreateBookPage = () => {
    let navigate = useNavigate()
    const [name,setName] = useState('')
    const [isbn,setISBN] = useState('')
    const [category,setCategory] = useState('')
    const [author,setAuthor] = useState('')
    const [description,setDescription] = useState('')

    const saveBook = async (e:any) => {
        e.preventDefault();
        const book = {isbn,name,author,category,description}
        if(book.isbn=="" || book.name=="")
        {
            alert("isbn and name are required")
            return false;
        }
        
        if(book.isbn!==null){
            await axios.post("/api/books",book)
            navigate("/")
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
                            <h2
                                className="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Create books</h2>
                        </div>
                    </li>
                </ol>
            </nav>
            <div className="mt-6">
                <form>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">ISBN</label>
                        <input 
                            type="text" 
                            id="isbn" 
                            value={isbn}
                            onChange={(e)=>setISBN(e.target.value)}
                            className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light" 
                            placeholder="1111-111" 
                            required
                            // pattern="^[0-9]$"
                        />
                        <span>{"isbn shouldn't be empty"}</span>
                    </div>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                        <input 
                            type="text" 
                            id="name" 
                            value={name}
                            onChange={(e)=>setName(e.target.value)}
                            className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light" 
                            required
                        />
                        <span>{"name shouldn't be empty"}</span>
                    </div>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Author</label>
                        <input 
                            type="text" 
                            id="author" 
                            value={author}
                            onChange={(e)=>setAuthor(e.target.value)}
                            className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light" 
                            required
                        />
                    </div>
                    <div className="mb-6">
                        <label  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Category</label>
                        <input 
                            type="text" 
                            id="category"
                            value={category}
                            onChange={(e)=>setCategory(e.target.value)} 
                            className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
                            required
                        />
                            <span>{"category shouldn't be empty"}</span>
                    </div>
                    <div className="mb-6">
                        <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description</label>
                        <textarea 
                            id="message" 
                            rows={4} 
                            value={description}
                            onChange={(e)=>setDescription(e.target.value)}
                            className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
                            placeholder="Leave a description ..."></textarea>
                    </div>
                    <button 
                        type="submit" 
                        onClick={(e)=>saveBook(e)}
                        className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Add Book</button>
                </form>
            </div>
        </>
    )
}