import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

type Book = {
    id: number;
    title: string;
    author: string;
    yearofpub: string;
    isbn: string;
    descreption: string;
    category: string;
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);
    const [selectedIndex,setSelectedIndex] = useState(null)

    const [title, setName] = useState("");
    const [category, setCategory] = useState("");



    useEffect(() => {
        fetchBooks();
    },[])

    useEffect(() => {
        fetchBooksByNameAndCategory();
    },[title,category])






    const fetchBooksByNameAndCategory= async ()=>{
        if(title ==="" && category==="" ) {
            fetchBooks();
        }else{
            const response = await axios.get(
                `/books/search?name=${title}&categories=${category}`
            );
            setBooks(response.data);

        }

    }

    const fetchBooks = async () => {
        const response = await axios.get("/books/")
        setBooks(response.data)
        console.log(books)
    }
    const handleDeleteBook = async(id:any)=>{
            await axios.delete(`/books/${id}`)
        fetchBooks();
    }

    const handleRowClick = (index:any)=>{
        if(selectedIndex!=null && index===selectedIndex){
            console.log("ikchem")
            setSelectedIndex(null)
        }
        else {const rowIndex = index
            console.log(rowIndex)
            setSelectedIndex(rowIndex);}

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


<div className="flex justify-between">
    <label htmlFor="default-search"
           className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search by title</label>
    <div className="relative w-1/2  mr-4">
        <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400" fill="none"
                 stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
            </svg>
        </div>
        <input type="search" id="default-search"
               value={title}
               onChange={(event)=>setName(event.target.value)}
               className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
               placeholder="Search by title" required/>


        <div className="absolute z-10 bg-white border divide-y w-1/2 hidden" id="item-list">
            <ol bg-white>
                {books.map((book, index) => (
                    <li className="p-2 border-b border-gray-400" key={index}>{book.title}</li>
                ))}
            </ol>
        </div>


    </div>

    <label htmlFor="default-search"
           className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search by category</label>
    <div className="relative w-1/2">
        <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400" fill="none"
                 stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
            </svg>
        </div>
        <input type="search" id="default-search"
               value={category}
               onChange={(event)=>{setCategory(event.target.value)}}
               className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
               placeholder="Search by category" required/>


        <div className="absolute z-10 bg-white border divide-y w-1/2 hidden" id="item-list">
            <ol bg-white>
                {books.map((book, index) => (
                    <li className="p-2 border-b border-gray-400" key={index}>{book.title}</li>
                ))}
            </ol>
        </div>


    </div>

</div>


<br/>

            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400 overflow-x-auto max-h-48">
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

                        {books.map((book,index)=>{
                            return(
                                <>
                                <tr key={book.id} className={`bg-white border-b dark:bg-gray-900 dark:border-gray-700 shadow hover:bg-gray-400 ${selectedIndex===book.id? "bg-gray-400":""} `}>
                                    <th onClick={()=>handleRowClick(book.id)}  scope="row"
                                        className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {index+1}
                                    </th>
                                    <th onClick={()=>handleRowClick(book.id)}  scope="row"
                                        className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {book.title}
                                    </th>
                                    <td onClick={()=>handleRowClick(book.id)}  className="px-6 py-4">
                                        {book.author}
                                    </td>
                                    <td onClick={()=>handleRowClick(book.id)}  className="px-6 py-4">
                                        {book.category}
                                    </td>
                                    <td onClick={()=>handleRowClick(book.id)}  className="px-6 py-4">
                                        {book.yearofpub}
                                    </td>
                                    <td  className="px-6 py-4">
                                        <a href="#" onClick={()=>handleDeleteBook(book.id)}
                                           className="font-medium text-blue-600 dark:text-blue-500 hover:underline">Remove</a>
                                    </td>
                                </tr>
                                <tr  className={`border-b border-gray-200 hover:bg-gray-100 dark:border-gray-600 transition-colors ${selectedIndex!=book.id? "hidden":""}`}>
                                    <td colSpan={7}  className="py-2 px-4 font-medium text-center " ><span>Descreption : {book.descreption}
                                        </span></td></tr>
                                </>

                            )
                        })

                        }

                    </tbody>
                </table>
            </div>
            <br/>

        </>
    )
}