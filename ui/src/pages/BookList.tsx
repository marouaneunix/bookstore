import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

export const BookList = ({books,handleDeleteButton,handleShowPopUp}) => {

    // const [books, setBooks] = useState([]);


    // useEffect(() => {
    //     const fetchBooks = async () => {
    //         const response = await axios("http://localhost:8090/books")
    //         console.log(response.data);
    //         setBooks(response.data)
    //     }
    //     fetchBooks();
    // },[])

    
    // const handleDeleteButton=(idBook:any)=>{


               
    //     axios.delete(`http://localhost:8090/books/${idBook}`)
    //     .then(response => {
    //         const fetchBooks = async () => {
    //             const response = await axios("http://localhost:8090/books")
    //             console.log(response.data);
    //             setBooks(response.data)
    //         } 
    //         fetchBooks();
         
    //     })
    //     .catch(error => console.log(error));

    // }
    return (

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">

        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
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
                        Isbn
                    </th>
                    <th scope="col" className="px-6 py-3">
                        <span className="sr-only">Edit</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            {
                 books.map(book =>  <tr  className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                 <th  onClick={event=>handleShowPopUp(book.name,book.description)} scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    {book.name}
                 </th>
                 <td onClick={event=>handleShowPopUp(book.name,book.description)} className="px-6 py-4">
                 {book.author}
                 </td>
                 <td onClick={event=>handleShowPopUp(book.name,book.description)} className="px-6 py-4">
                 {book.category}
                 </td>
                 <td onClick={event=>handleShowPopUp(book.name,book.description)} className="px-6 py-4">
                 {book.isbn}
                 </td>
                 <td className="px-6 py-4 text-right">
                 <button type="button" onClick={event=>handleDeleteButton(book.idBook)} className="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900">Delete</button>
                 </td>
             </tr>)
               
               
                 }
    
            </tbody>
        </table>
    </div>


    )

}