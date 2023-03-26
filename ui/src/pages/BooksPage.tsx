import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import './BooksPage.css'
import Popup from "reactjs-popup";
import 'reactjs-popup/dist/index.css';

export interface Book{
    id:number,
    isbn:string,
    name:string,
    author:string,
    category:string
    description:string
}
export const BooksPage = () => {

    const [books, setBooks] = useState<Array<Book>>([]);
    const [name,setName]=useState('');
    const [categories,setCategories]=useState('');
    const {id:number}=useParams()

    useEffect(() => {
        fetchBooks();
    },[])
    const fetchBooks = async () => {
        const response = await axios("/api/books")
        console.log(response.data);
        setBooks(response.data)
    }
   const deleteRecord=async(id:number)=>{
        let result=confirm("want to delete?")
       if(result)
       {
           await axios.delete(`api/books/${id}`)
               .then(()=>{alert("data has deleted")})
       }

       fetchBooks()
   }
   const getSearchedData=async(name:string,categories:string)=>{
        const response=await axios.get(`api/books/search`,{
            params:{
                name:name,
                categories:categories
            }
        })
        console.log(response.data);
        setBooks(response.data)
   }
   const postData=async (e:any)=>{
        e.preventDefault();
       getSearchedData(name,categories)
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
            <div className="search-container">
                <input type="text" placeholder="Search by name..." value={name} onChange={(event)=>setName(event.target.value)}/>
                <input type="text" placeholder="Search by category..." value={categories} onChange={(event)=>setCategories(event.target.value)}/>
                <button className='button1' onClick={postData}>Search</button>
            </div>

            <div >
                <h1 className="text-center">List Books</h1>
                <table className="table-sub-heading-color" >
                    <thead>
                    <tr>
                        <th>Author</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>ISBN</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>
                    {
                        books.map(book=><tr key={book.id}>
                            <td>{book.author}</td>
                            <td>{book.name}</td>
                            <td>{book.category}</td>
                            <td>{book.isbn}</td>
                            <td>
                                <button className="button button2" onClick={()=>
                                {
                                    deleteRecord(book.id);
                                    console.log(book.id);
                                }}>Delete</button>

                                <div>
                                    <Popup trigger=
                                               { <button className="button button1" >Description</button>}
                                           modal nested position="right center">
                                        <h2>Description:</h2>
                                        <div>{book.description}</div>
                                    </Popup>
                                </div>
                            </td>
                        </tr>)
                    }
                    </tbody>
                </table>
            </div>
        </>
    )
}