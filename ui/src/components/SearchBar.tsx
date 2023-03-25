import React, {useEffect, useState} from "react";
import axios from "axios";

export const SearchBar = (props:any)=>{

    const [title, setName] = useState("");
    const [category, setCategory] = useState("");

    useEffect(() => {

        setTimeout(()=>fetchBooksByNameAndCategory(),1000)

    },[title,category])

    const fetchBooks = async () => {
        const response = await axios.get("/books/")
        props.onSearch(response.data)
    }

    const fetchBooksByNameAndCategory= async ()=>{
        if(title ==="" && category==="" ) {
            await fetchBooks();
        }else{
            const response = await axios.get(
                `/books/search?name=${title}&categories=${category}`
            );
            props.onSearch(response.data)

        }

    }


    return(<div className="flex justify-between">
        <br/>
        <label htmlFor="default-search"
               className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search by title</label>
        <div className="relative w-1/2  mr-4">
            <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400" fill="none"
                     stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </div>
            <input type="search" id="default-search"
                   value={title}
                   onChange={(event)=>setName(event.target.value)}
                   className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                   placeholder="Search by title" required/>



        </div>

        <label htmlFor="default-search"
               className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search by category</label>
        <div className="relative w-1/2">
            <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                <svg aria-hidden="true" className="w-5 h-5 text-gray-500 dark:text-gray-400" fill="none"
                     stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </div>
            <input type="search" id="default-search"
                   value={category}
                   onChange={(event)=>{setCategory(event.target.value)}}
                   className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                   placeholder="Search by category" required/>





        </div>

    </div>)
}