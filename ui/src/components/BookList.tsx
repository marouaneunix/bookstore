import {Book} from "../pages/BooksPage";
import React from "react";
import  trashIcon  from '../assets/trash.svg'


type Props= {
    books: Array<Book>,
    onBookDelete: (bookId: number) => void
}
export const BookList = ({books, onBookDelete}: Props) => {

    const handleDeleteClick = (bookId: number) => {
        onBookDelete(bookId);
    }

    return (

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table className="w-full text-sm text-left text-gray-500">
                <thead className="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" className="px-6 py-3">
                        Book name
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Category
                    </th>
                    <th scope="col" className="px-6 py-3">
                        <span className="sr-only">Actions</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                {
                    books.map(book => (
                        <tr key={book.id} className="bg-white border-b hover:bg-gray-50">
                            <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                                {book.name}
                            </th>
                            <td className="px-6 py-4">
                                <CategoryBadgeList categories={book.categories}/>
                            </td>
                            <td className="px-6 py-4 text-right">
                                <button onClick={() => handleDeleteClick(book.id)} className="font-medium text-blue-600 hover:underline">
                                    <img src={trashIcon} alt="SVG as an image" className="w-5"/>
                                </button>
                            </td>
                        </tr>
                    ))
                }


                </tbody>
            </table>
        </div>

    )
}

type CategoryProps = {
    categories: string
}
const CategoryBadgeList = ({categories}: CategoryProps) => {
    const cat = categories.split(',')
        .map((category, index) => <span key={index}
                                        className="bg-blue-100 text-blue-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded">{category}</span>)
    return <span>{cat}</span>
}