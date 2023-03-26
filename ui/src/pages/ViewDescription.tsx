import axios from "axios";
import React, {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";


export const ViewDescription = () => {

    const [book, setBook] = useState({
        name: "",
        author:"",
        categories:"",
        isbn:"",
        description:""
    });
    const {id}=useParams();
    const loadBook=async () =>{
        const result=await axios.get(`/api/books/${id}`);
        console.log(result.data);
        setBook(result.data);
    }
    useEffect(() => {
        loadBook();
    },[])
    
    return (
    <>

    <div>
       <div> <label htmlFor="description" className="block text-sm font-medium text-gray-900 dark:text-white">Description for the book {book.name}</label></div>
       <div>{book.description}</div>
    </div>
    
    </>
        

        
         
    )



}



