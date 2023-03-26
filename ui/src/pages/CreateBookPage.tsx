import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from "axios";

class Book {
  name: string;
  author: string;
  categories: string;
  description: string;
  isbn: string;

  constructor(name: string, author: string, categories: string, description: string,isbn: string) {
      this.name = name;
      this.author = author;
      this.categories = categories;
      this.description = description;
      this.isbn=isbn
  }
}

export const CreateBookPage = () => {

    var name: string;
    var description: string;
    var categories: string;
    var author: string;
    var isbn: string;

    const create = () => {
        if (name && description && categories && author && isbn) {
            let book = new Book(name, author, categories, description,isbn);
            alert(book.name+book.author+book.categories+book.description+book.isbn)
            axios.post('/api/books', book)
                .then(() => {
                    toast.success('Success Notification!', {
                        position: toast.POSITION.TOP_RIGHT,
                        autoClose: 3000,
                    });
                })
                .catch((error) => {
                    toast.error(`Error: ${error.message}`, {
                        position: toast.POSITION.TOP_RIGHT,
                        autoClose: 3000,
                    });
                });
        } 
        else {
            toast.warning("missong fields", {
                position: toast.POSITION.TOP_RIGHT,
                autoClose: 3000,
            });
        }
    }

    const getTitle = (event:any) => {
        name = event.target.value;
    }

    const getDescription = (event:any) => {
        description = event.target.value;
    }

    const getAuthor = (event:any) => {
        author = event.target.value;
    }

    const getCategories = (event:any) => {
        categories = event.target.value;
    }
    const getIsbn = (event:any) => {
      isbn = event.target.value;
    }

    return (
        <>
            
            <ToastContainer />
            <div className="flex flex-row p-2 rounded shadow-md items-center">
                <div className="basis-1/4  mx-2">
                    <div>
                        <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                        <input type="text" onChange={(e) => getTitle(e)} id="name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="title" required />
                    </div>
                </div>
                <div className="basis-1/4 mx-2">
                    <div>
                        <label htmlFor="author" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Author</label>
                        <input type="text" onChange={(e) => getAuthor(e)} id="author" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="author" required />
                    </div>
                </div>
                <div className="basis-1/4  mx-2 ">
                    <div>
                        <label htmlFor="description" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description</label>
                        <input type="text" onChange={(e) => getDescription(e)} id="description" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="description" required />
                    </div>
                </div>
                <div className="basis-1/4  mx-2 ">
                    <div>
                        <label htmlFor="categories" className="block text-sm font-medium text-gray-900 dark:text-white">Categories</label>
                        <input type="text" onChange={(e) => getCategories(e)} id="categories" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="categories (category1, category2,...." required />
                    </div>
                </div>
                <div className="basis-1/4  mx-2 ">
                    <div>
                        <label htmlFor="isbn" className="block text-sm font-medium text-gray-900 dark:text-white">Isbn</label>
                        <input type="text" onChange={(e) => getIsbn(e)} id="isbn" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="isbn" required />
                    </div>
                </div>

                <div className="basis-1/4 mx-2">
                    <div>
                        <label htmlFor="action" className="block text-sm font-medium text-gray-900 dark:text-white">Action</label>
                        <button type="button" onClick={() => create()} className="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900">Save</button>
                    </div>
                </div>
            </div>
        </>
    )
}