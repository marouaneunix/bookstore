import {Link, useNavigate} from "react-router-dom";
import React, { useState } from "react";
import axios from "axios";
import './CreateBookPage.css'
import { NavComponent } from "./NavComponent";


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
        if(book.isbn!==null){
            await axios.post("/api/books",book)
            navigate("/")
        }   
    }
    return (
        <>
           <NavComponent/>
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