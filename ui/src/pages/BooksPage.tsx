import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import Table from "./Table"
import Search from "./Search";

type Book = {
    id: number;
    description: string;
    isbn: string;
    categories: string;
    title: string;
    auteur:string
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);

    const fetchBooks = async () => {
        const response = await axios("/api/v1/books")
        console.log(response.data);
        setBooks(response.data)
    }
    

    const hundleClickDelete = async (id : any) =>{

            const response = await axios.delete(`/api/v1/books/${id}`)
            fetchBooks();
    }
    const handleSearch = async (title?: string, category?: string) =>{

            if(title && category){
                const response= await axios.get(`/api/v1/books/search?title=${title}&categorie=${category}`);
                setBooks(response.data);
            }
            else if(title){
                const response= await axios.get(`/api/v1/books/search?title=${title}`);
                setBooks(response.data);
            }
            else if(category){
                const response= await axios.get(`/api/v1/books/search?categorie=${category}`);
                setBooks(response.data);
            }else {
               fetchBooks();
            }
            




    }

        

       

          
          
          

    useEffect(() => {
        
            fetchBooks();
        },[])


    

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

            <Search hundleSearch={handleSearch}  />

            <Table books={books} hundleClickDelete={hundleClickDelete}/>
        </>
    )
}