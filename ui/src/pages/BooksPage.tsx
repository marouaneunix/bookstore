import React, {useEffect, useState} from "react";
import axios from "axios";
import { BookLine } from "../components/BookLine";
import { NavBar } from "../components/NavBar";

type Book = {
    id: number;
    title: string;
    isbn:string, 
    description:string, 
    categories: string
}

type SearchInputs = {
    title: string, 
    categories: string
}

export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);

    const [searchInputs, setSearchInputs] = useState<SearchInputs>(
        {
            title:'',
            categories :''
        }
    );

    


    const fetchBooks = async () => {
        const response = await axios("/books")
        setBooks(response.data)
    }

    useEffect(() => {
        fetchBooks();
    },[])

    const  handleDeleteBook =async (id:number)=>{
         await axios.delete("/books/"+id)
         fetchBooks()
    }

    const search = async()=>{
        const response = await axios("/books/filter?title="+searchInputs.title+"&categories="+searchInputs.categories)
        setBooks(response.data)
    }

    return (
        <>
            <NavBar pageName="books"/>
         {
             <div className="flex items-center">
             <div className="flex space-x-1">
                 <input
                     type="text"
                     className="block w-full px-4 py-2 text-purple-700 bg-white border rounded-full focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                     placeholder="Search By Title..."
                     onChange={(event)=>{setSearchInputs({...searchInputs,title: event.target.value})}}
                 />
                  <input
                     type="text"
                     className="block w-full px-4 py-2 text-purple-700 bg-white border rounded-full focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                     placeholder="Search By Categories..."
                     onChange={(event)=>{setSearchInputs({...searchInputs, categories:event.target.value
                    })}}
                 />
                 <button className="px-4 text-white bg-purple-600 rounded-full
                   onClick "
                   onClick={search}
                   >
                     <svg
                         xmlns="http://www.w3.org/2000/svg"
                         className="w-5 h-5"
                         fill="none"
                         viewBox="0 0 24 24"
                         stroke="currentColor"
                         strokeWidth={2}
                     >
                         <path
                             strokeLinecap="round"
                             strokeLinejoin="round"
                             d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                             
                         />
                     </svg>
                 </button>
             </div>
         </div>
         
         }
<br></br>
            {
                <div className="flex flex-col">
                <div className="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                    <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                        <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        id
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Title
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Author
                                    </th>
            
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        isbn
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Categories
                                    </th>
                                    <th scope="col" className="relative px-6 py-3">
                                        <span className="sr-only">Edit</span>
                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {books.map((book, index) =>
                                <BookLine book ={book}
                                  key={index}
                                  onDeleteBook={handleDeleteBook}/>)}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            }    
        </>
    )
}