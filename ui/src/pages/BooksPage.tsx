import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import SearchNameBar from "./SearchNameBar";
import SearchCategory from "./SearchCategoryBar"
import { BookList } from "./BookList";
import PopUp from "./PopUp";
import '../App.css'

// type Book = {
//     id: number;
//     name: string;
// }
//<Array<Book>>
export const BooksPage = () => {

    const [books, setBooks] = useState([]);
    const [showPopup, setShowPopup] = useState(false);
 

    const handleClosePopUp=()=>{
        setShowPopup(false)
    }


    useEffect(() => {
        const fetchBooks = async () => {
            const response = await axios("http://localhost:8090/books")
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();
    },[])

    
    const handleDeleteButton=(idBook:any)=>{


               
        axios.delete(`http://localhost:8090/books/${idBook}`)
        .then(response => {
            const fetchBooks = async () => {
                const response = await axios("http://localhost:8090/books")
                console.log(response.data);
                setBooks(response.data)
            } 
            fetchBooks();
         
        })
        .catch(error => console.log(error));

    }
    const [name,setName]=useState()
    const [description,setDescription]=useState()
    const [nameSeaech, setNameSeaech] = useState("");
    const [categorySearch, setCategorySearch] = useState("");


    const handleShowPopUp =(name,description)=>{

            setName(name)
            setDescription(description)
        setShowPopup(true)
    }


const handleSearchName =(nameSeaech:any)=>{

    setNameSeaech(nameSeaech)

    console.log(nameSeaech)

    if(nameSeaech=="" ){
        const fetchBooks = async () => {
            const response = await axios("http://localhost:8090/books")
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();

    }
        else {
        const fetchBooks = async () => {
            const response = await axios(`http://localhost:8090/books/search/name/${nameSeaech}`)
            console.log(response.data);
            setBooks(response.data)
        } 
        fetchBooks();}



}
const handleSearchCategory =(categorySearch:any)=>{

    setCategorySearch(categorySearch)
    console.log(categorySearch)

    if(categorySearch=="" ){
        const fetchBooks = async () => {
            const response = await axios("http://localhost:8090/books")
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();
    
    }
    else {
    const fetchBooks = async () => {
        const response = await axios(`http://localhost:8090/books/search/categories/${categorySearch}`)
        console.log(response.data);
        setBooks(response.data)
    } 
    fetchBooks();}
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
            <SearchNameBar handleSearchName={handleSearchName} />
            <SearchCategory handleSearchCategory={handleSearchCategory} />
            <BookList handleShowPopUp={handleShowPopUp} books={books} handleDeleteButton={handleDeleteButton}/>
            <PopUp  handleClosePopUp={handleClosePopUp} showPopup={showPopup} name={name} description={description}/>

            





         


         
        </>
    )
}