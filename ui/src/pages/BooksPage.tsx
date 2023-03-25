import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Popup from "reactjs-popup";
import 'reactjs-popup/dist/index.css'

type Book = {
    id: number;
    name: string;
    isbn: string;
    author: string;
    category: string;
    description: string
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);
    const {id} = useParams()
    const [name,setName] = useState('')
    const [category,setCategory] = useState('')
    useEffect(() => {
        fetchBooks();
    },[])
    const fetchBooks = async () => {
        const response = await axios("/api/books")
        console.log(response.data);
        setBooks(response.data)
    }
    const handleDelete = async (id:number) =>{
        await axios.delete(`/api/books/${id}`)
        fetchBooks();
    }
    
    const handleSearch = async (name:string,category:string) =>{
        const response = await axios.get(`/api/books/search`,{
            params : {
                name :name,
                category:category
            }
        })
        console.log(response.data)
        setBooks(response.data)
    }
    const postData = async (e:any)=>{
        e.preventDefault();
        handleSearch(name,category);

    }
    return (
        <>
            <nav className="flex pt-5 pb-10" aria-label="Breadcrumb">
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
                        <div className="flex items-center button">
                            <svg aria-hidden="true" className="w-6 h-6 text-gray-400" fill="currentColor"
                                 viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                <path fillRule="evenodd"
                                      d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                      clipRule="evenodd"></path>
                            </svg>
                            <span
                                className="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Books</span>
                        </div>
                    </li>
                </ol>
            </nav>

            <form >   
                <label className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
                <div className="relative">
                    <input 
                        type="text" 
                        value={name}
                        onChange={(e)=>setName(e.target.value)}
                        id="default-search" 
                        className="form-control input-sm p-4 pl-40 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
                        placeholder="Search Book By Name..." 
                        required
                     />
                      <input 
                        type="text" 
                        value={category}
                        onChange={(e)=>setCategory(e.target.value)}
                        id="default-search" 
                        className="form-control input-sm p-4 pl-40 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
                        placeholder="Search Book By Category..." 
                        required
                     />
                           
                    <button onClick={postData} type="submit" className="text-white absolute right-2 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Search</button>
                    </div>
            </form>
            <div>
                <div className="relative w-full flex flex-col shadow-lg mb-6 mt-4">
                <table className="table-auto">
                    <thead>
                        <tr className="border border-solid border-l-0 ">
                            <th className="text-md px-6 py-3">ISBN</th>
                            <th className="text-md px-6 py-3">Author</th>
                            <th className="text-md px-6 py-3">Name</th>
                            <th className="text-md px-6 py-3">Category</th>
                            <th className="text-md px-6 py-3">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            books.map(book => 

                        <tr key={book.id}>
                            <td className="text-md px-6 py-3">{book.isbn}</td>
                            <td className="text-md px-6 py-3">{book.author}</td>
                            <td className="text-md px-6 py-3">{book.name}</td>
                            <td className="text-md px-6 py-3">{book.category}</td> 
                            <td className="text-md px-6 py-3"> 
                                <button onClick={()=>{handleDelete(book.id)}} type="button" className="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900">Delete</button>
                                <div>
                                    <Popup trigger={<button className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-2 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                                                    Description
                                    </button>}
                                    modal nested position="right center">
                                        <h1>Description</h1>
                                        <div className="details-modal">{book.description}</div>
                                    </Popup>
                                </div>
                            </td>
                        </tr>)
                        }
                    </tbody>
                </table>
                </div>
            </div>
            {/* </Link> */}
           
            
           
        </>
    )
}