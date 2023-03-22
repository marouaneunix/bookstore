import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

type Book = {
    id: number;
    title: string;
    author: string;
    year: string;
    isbn: string;
    descreption: string;
    category: string;
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);
    const [descreption,setDescreption]=useState("")


    useEffect(() => {

        fetchBooks();
    },[])

    const fetchBooks = async () => {
        const response = await axios.get("/books/")
        console.log(response.data);
        setBooks(response.data)
    }
    const handleDeleteBook = async(id:any)=>{
            await axios.delete(`/books/${id}`)
        fetchBooks();
    }

    let desc
    let descre
    const handleRowClick = (id:any)=>{

        console.log("hhk")
        desc=books[id].descreption
        setDescreption(desc)
        descre=<tr>{desc}</tr>

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


            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" className="px-6 py-3">
                            #
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Book name
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Author
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Category
                        </th>
                        <th scope="col" className="px-6 py-3">
                            year
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Action
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                        {books.map((book)=>{
                            return(
                                <tr key={book.id} onClick={()=>handleRowClick(book.id)} className="bg-white border-b dark:bg-gray-900 dark:border-gray-700 shadow">
                                    <th scope="row"
                                        className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {book.id}
                                    </th>
                                    <th scope="row"
                                        className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {book.title}
                                    </th>
                                    <td className="px-6 py-4">
                                        {book.author}
                                    </td>
                                    <td className="px-6 py-4">
                                        {book.category}
                                    </td>
                                    <td className="px-6 py-4">
                                        {book.year}
                                    </td>
                                    <td className="px-6 py-4">
                                        <a href="#" onClick={()=>handleDeleteBook(book.id)}
                                           className="font-medium text-blue-600 dark:text-blue-500 hover:underline">Remove</a>
                                    </td>
                                </tr>

                            )
                        })

                        }

                    </tbody>
                </table>
            </div>

        </>
    )
}