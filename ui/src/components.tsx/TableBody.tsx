import { Link } from "react-router-dom";
import { Book } from "../pages/BooksPage";


type bookProps = {
    book: Book;
    onDeleteBook: (book: Book) => void;
}
export const TableBody = (props: bookProps) => {

    const deleteBook = (book: Book) => {
        props.onDeleteBook(book)

    }


    return (
        <>

            <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 cursor-pointer rounded-md shadow-sm" >
                <Link
                    className="btn btn-primary mx-2"
                    to={`/viewbook/${props.book.id}`}
                >
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        {props.book.name}
                    </th>
                </Link>
                <td className="px-6 py-4">
                    {props.book.author}
                </td>
                <td className="px-6 py-4">
                    {props.book.categorie}
                </td>
                <td className="px-6 py-4">
                    {props.book.isbn}
                </td>
                <td className="px-6 py-4 text-right">
                    <button className="font-medium text-blue-600 dark:text-red-500 hover:underline" onClick={() => deleteBook(props.book)}>remove</button>
                </td>
                
            </tr>
        </>
    )
}