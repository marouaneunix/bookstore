import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import axios from "axios";
import {NavBar} from "../components/NavBar";


export const CreateBookPage = () => {



    const [data,setData]=useState({
        title: "",
        author: "",
        yearofpub: "",
        isbn: "",
        descreption: "",
        category: "",
    });
    const navigate = useNavigate();
    const handleInputChange =(event:any)=>{
        const { name, value } = event.target;
        setData(prevState => ({
            ...prevState,
            [name]: value
        }));
    }
    const addBook = async ()=>{
       await axios.post("books/",data)
    }
    const onSubmit = (event:any)=>{
        event.preventDefault();
        addBook().then(() =>navigate('/') );
    }
    return (
        <>
          <NavBar/>
            <br/>
            <form onSubmit={onSubmit}>
                <div className="relative z-0 w-full mb-6 group">
                    <input type="text" name="title" id="title"
                           onChange={handleInputChange}
                           value={data.title}
                           className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                           placeholder=" " required/>
                    <label htmlFor="title"
                           className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                        Book Name</label>
                </div>
                <div className="relative z-0 w-full mb-6 group">
                    <input type="text" name="isbn" id="isbn"
                           onChange={handleInputChange}
                           value={data.isbn}
                           className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                           placeholder=" " required/>
                    <label htmlFor="isbn"
                           className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform
                           -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600
                           peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0
                           peer-focus:scale-75 peer-focus:-translate-y-6">Isbn</label>
                </div>
                <div className="relative z-0 w-full mb-6 group">
                    <input type="text" name="author" id="author"
                           onChange={handleInputChange}
                           value={data.author}
                           className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2
                           border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500
                           focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                           placeholder=" " required/>
                    <label htmlFor="author"
                           className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Confirm
                        Author</label>
                </div>
                <div className="grid md:grid-cols-2 md:gap-6">
                    <div className="relative z-0 w-full mb-6 group">
                        <input type="text" name="category" id="category"
                               onChange={handleInputChange}
                               value={data.category}

                               className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                               placeholder=" " required/>
                        <label htmlFor="category"
                               className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300
                               transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600
                               peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0
                               peer-focus:scale-75 peer-focus:-translate-y-6">
                            Category</label>
                    </div>
                    <div className="relative z-0 w-full mb-6 group">
                        <input type="number" name="yearofpub" id="year"
                               onChange={handleInputChange}
                               value={data.yearofpub}
                               className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2
                               border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500
                               focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                               placeholder=" " required/>
                        <label htmlFor="yearofpub"
                               className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400
                               duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0
                               peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100
                               peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Year</label>
                    </div>
                </div>
                <div className="relative z-0 w-full mb-6 group">
                    <input type="text" name="descreption" id="descreption"
                           onChange={handleInputChange}
                           value={data.descreption}
                           className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                           placeholder=" " required/>
                    <label htmlFor="description"
                           className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                        description</label>
                </div>

                <button type="submit"
                        className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit
                </button>
            </form>

        </>
    )
}