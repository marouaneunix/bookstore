import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate, useParams} from "react-router-dom";
import Popup from "reactjs-popup";
import 'reactjs-popup/dist/index.css'
import { NavComponent } from "./NavComponent";

type Book = {
    id: number;
    name: string;
    isbn: string;
    author: string;
    category: string;
    description: string
}
export const BooksPage = () => {
    let navigate = useNavigate();
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
           <NavComponent/>
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
                           
                    <button onClick={postData} type="submit" className="text-white absolute center bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Search</button>
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
                                        <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
                                            <div className="flex items-start justify-between p-4 border-b rounded-t dark:border-gray-600">
                                                <h1 className="text-xl font-semibold text-gray-900 dark:text-white">Description</h1>
                                            </div>
                                            <div className="p-6 space-y-6">
                                                <p className="text-base leading-relaxed text-gray-500 dark:text-gray-400">{book.description}</p>
                                            </div>
                                        </div>
                                    </Popup>
                                </div>
                            </td>
                        </tr>)
                        }
                    </tbody>
                </table>
                </div>
            </div>
        </>
    )
}