import React, {useEffect, useState} from "react";
import axios from "axios";
import {SearchBar} from "../components/SearchBar";
import {NavBar} from "../components/NavBar";

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


    useEffect(() => {
        fetchBooks();
    },[])


    const fetchBooks = async () => {
     await axios.get("/books/").then(response=>setBooks(response.data));
    }
    const handleDeleteBook = async (id:any)=>{
            await axios.delete(`/books/${id}`)
            await fetchBooks();
    }

    const handleRowClick = (index:any)=>{
        if(selectedIndex!=null && index===selectedIndex){
            setSelectedIndex(null)
        }
        else {
            setSelectedIndex(index);}
    }
    const handleSearch=(book:any)=>{
        setBooks(book)
    }

    return (
        <>

            <NavBar/>

            <SearchBar onSearch={handleSearch} books={books}/>
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


                        {books.map((book,index)=>{
                            return(
                                <tbody key={book.id}>
                                <tr className={`bg-white border-b dark:bg-gray-900 dark:border-gray-700 shadow hover:bg-gray-400 ${selectedIndex===book.id? "bg-gray-400":""} `}>
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
                                <tr className={`border-b border-gray-200 hover:bg-gray-100 dark:border-gray-600 transition-colors ${selectedIndex!=book.id? "hidden":""}`}>
                                    <td colSpan={7}  className="py-2 px-4 font-medium text-center " ><span>Descreption : {book.descreption}
                                        </span></td>
                                </tr>
                                </tbody>
                            )
                        })
                        }
                </table>
            </div>
            <br/>

        </>
    )
}