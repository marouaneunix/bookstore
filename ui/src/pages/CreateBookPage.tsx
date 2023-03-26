import {Link, Navigate, useNavigate} from "react-router-dom";
import axios from "axios";
import React, {useEffect, useState} from "react";

type Book = {
    id: number;
    name: string;
    auteur: string;
    category: string;
    isbn: string;
    description: string;

}

export const CreateBookPage = () => {

    const navigate = useNavigate();
    const [name,setName] = useState('')
    const [auteur,setAuteur] = useState('')
    const [category,setCategory] = useState('')
    const [isbn,setIsbn] = useState('')
    const [description,setDescription] = useState('')

    const handleChangeName=(name:string)=>{
        setName(name)
    }
    const handleChangeAuteur = (auteur:string) => {
        setAuteur(auteur)
    }
    const handleChangeCategorie = (category:string) => {
        setCategory(category)
    }
    const handleChangeISBN = (isbn:string) => {
        setIsbn(isbn)
    }
    const handleChangeDescription = (description:string) => {
        setDescription(description)
    }

    const saveBook = async () => {
        const book ={name,isbn,description,auteur,category}
        console.log(book)
        try {
            const response = await fetch("http://localhost:8080/api/v1/books/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(
                    book
                )
            }).then();



            const data = await response.json();

            console.log("Book added:", data);
            navigate("/");
        } catch (error) {
            console.error("Error adding book:", error);
        }
    }
    return (
        <>
            <nav className="flex" aria-label="Breadcrumb">
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
                                className="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Create books</span>
                        </div>
                    </li>
                </ol>
            </nav>
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md offset-md-3 offset-md-3">
                            <h3 className="text-center h2">Add Book</h3>
                            <div className="card-body">

                                <div className="form-group">
                                    <label>id</label>
                                    <input placeholder="id Book" name="id"
                                           className="form-control"></input>
                                    <label>name</label>
                                    <input placeholder="name Book" name="name"
                                           className="form-control" onChange={(event)=> {handleChangeName(event.target.value)} }></input>
                                    <label>auteur</label>
                                    <input placeholder="auteur of Book" name="auteur"
                                           className="form-control" onChange={(event)=> {handleChangeAuteur(event.target.value)} }></input>
                                    <label>category</label>
                                    <input placeholder="category of Book" name="category"
                                           className="form-control" onChange={(event)=> {handleChangeCategorie(event.target.value)} }></input>
                                    <label>ISBN</label>
                                    <input placeholder="ISBN of Book" name="ISBN"
                                           className="form-control" onChange={(event)=> {handleChangeISBN(event.target.value)} }></input>
                                    <label>Description</label>
                                    <input placeholder="Short Description about the Book" name="Description"
                                           className="form-control" onChange={(event)=> {handleChangeDescription(event.target.value)} }></input>
                                </div>

                                <button className="btn btn-info" onClick={saveBook }>Save Book</button>
                                <Link to="/" className="btn btn-warning " style={{marginLeft:"10px"}}>
                                    Cancel Book
                                </Link>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

