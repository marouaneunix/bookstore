import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Book } from "./BooksPage";

const defaultBook: Book = {
    id: 0,
    name: "",
    author: "",
    categorie: "",
    isbn: ""

};

export const BookDetailsPage = () => {
    const [book, setBook] = useState<Book>(defaultBook);
    console.log(book);


    const { id } = useParams();

    useEffect(() => {
        fetchBook();
    }, [])

    const fetchBook = async () => {
        const result = await axios.get(`/books/${id}`)
        setBook(result.data);
    }


    return (

        <div className="max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">

            <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Book Description</h5>

            <ul className="space-y-4 text-gray-500 list-disc list-inside dark:text-gray-400">
                <li>
                    name

                    <li>{book.name}</li>

                </li>
                <li>
                    Author

                    <li>{book.author}</li>


                </li>
                <li>
                    categories
                    <li>{book.categorie}</li>

                </li>
                <li>
                    ISBN
                    <li>{book.isbn}</li>

                </li>
            </ul>

        </div>
    );
}