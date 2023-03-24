import {useNavigate} from "react-router-dom";
import {RxCrossCircled} from "react-icons/all";
import axios from "axios";
import swal from "sweetalert";


const BooksList = ({books, onDeleteBook}) => {

    const navigate = useNavigate();

    const onDeleteBookHandler = async (id, name) => {
        try {
            await axios.delete(`/api/v1/books/${id}`);
            const filteredBooks = books.filter((book) => book.id != id);
            onDeleteBook(filteredBooks);
            swal({
                title: "Book Deleted!",
                text: `Book: "${name}" DELETED!`,
                icon: "success",
                button: "OK!",
            });
        } catch (error) {
            console.log(error);
            swal({
                title: "Oops!",
                text: `${error.message}`,
                icon: "error",
                button: "OK!",
            });
        }
    };


    return (

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg mb-40">
            <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" className="px-6 py-3">
                        Book name
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Author
                    </th>
                    <th scope="col" className="px-6 py-3">
                        ISBN
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Category
                    </th>
                    <th scope="col" className="px-6 py-3">
                        <span className="sr-only">Delete</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                    {books.map((book) => (
                        <tr key={book.id}
                            className="bg-white cursor-pointer border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 capitalize">
                            <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white" onClick={() => navigate(`/books/${book.id}`, {replace: true}) }>
                                {book.name}
                            </th>
                            <td className="px-6 py-4" onClick={() => navigate(`/books/${book.id}`, {replace: true}) }>
                                {book.author}
                            </td>
                            <td className="px-6 py-4" onClick={() => navigate(`/books/${book.id}`, {replace: true}) }>
                                {book.isbn}
                            </td>
                            <td className="px-6 py-4" onClick={() => navigate(`/books/${book.id}`, {replace: true}) }>
                                {book.category}
                            </td>
                            <td className="px-6 py-4 text-right">
                                <span className="font-bold text-xl text-red-700" onClick={() => onDeleteBookHandler(book.id, book.name)}>
                                    <RxCrossCircled />
                                </span>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>

    );
};

export default BooksList;