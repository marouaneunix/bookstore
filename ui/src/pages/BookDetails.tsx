import PageHero from "../layout/PageHero";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";


type Book = {
    id: number;
    name: string;
    author: string;
    isbn: string;
    category: string;
    description: string;
}

const BookDetails = () => {

    const {bookId} = useParams();

    const [book, setBooks] = useState<Book>(null);


    useEffect(() => {
        const fetchBooks = async () => {
            const response = await axios(`/api/v1/books/${bookId}`)
            console.log(response.data);
            setBooks(response.data)
        }
        fetchBooks();
    }, [bookId])

    return (
        <>
            <PageHero title="Book detail"/>
            {book && (
                <div className='w-3/4 lg:w-1/2 mx-auto p-12 rounded-2xl shadow-2xl'>
                    <h2 className='font-bold text-4xl tracking-wide mb-5'>{book.name}</h2>
                    <p className='max-w-3xl tracking-wider leading-8 text-gray-500 mb-6'>{book.description}</p>
                    <div className='flex flex-col w-full sm:w-3/4 lg:w-1/2 space-y-5'>
                        <div className='flex justify-between'>
                            <p className='text-lg font-semibold tracking-wider text-gray-600'>Author :</p>
                            <p>{book.author}</p>
                        </div>
                        <div className='flex justify-between'>
                            <p className='text-lg font-semibold tracking-wider text-gray-600'>ISBN :</p>
                            <p>{book.isbn}</p>
                        </div>
                        <div className='flex justify-between'>
                            <p className='text-lg font-semibold tracking-wider text-gray-600'>Category :</p>
                            <p>{book.category}</p>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default BookDetails;