import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import { SearchPage } from "./SearchPage";

type Book = {
    id: number;
    name: string;
    author:string;
    categories:string;
    isbn:string;
    description:string;
}

export const BooksPage = () => {
    
    const [books, setBooks] = useState<Array<Book>>([]);
    const [search,setSearch]=useState("");
    const {id}=useParams();
    const {name}=useParams();

    const fetchBooks = async () => {
        const response = await axios("/api/books")
        console.log(response.data);
        setBooks(response.data)
    }
    useEffect(() => {
        fetchBooks();
    },[])
    const deleteBoookById = async (id:any) => {
        console.log(id);
        await axios.delete(`/api/books/${id}`)
       fetchBooks();
    }
    const onSearch = async (search: any) => {
        const response = await axios.get("/api/books/titlesAndCategories", {
            params: {
                name: search.name,
                categories: search.categories
            }
        })
        setBooks(response.data)
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
                        <div className="flex items-center">
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
    
            <SearchPage onSearch={onSearch} />

            {
                
                <div>
                <div className="relative w-full flex flex-col shadow-lg mb-6 mt-4"></div>
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
                           
                            <td className="text-md px-6 py-3"><a href="/description" className="row-link">{book.isbn}</a></td>
                            <td className="text-md px-6 py-3"><a href="/description" className="row-link">{book.author}</a></td>
                            <td className="text-md px-6 py-3"><a href="/description"className="row-link">{book.name}</a></td>
                            <td className="text-md px-6 py-3"><a href="/description" className="row-link">{book.categories}</a></td>
                            <td className="text-md px-6 py-3"> 
                             
                            <button 
                            onClick={()=>deleteBoookById(book.id)}
                            className="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900" > Remove </button> </td>
                        </tr>
                        )
                    }
                    </tbody>
                </table>
                </div>
            }
        </>
    )
}