import {Link, useNavigate} from "react-router-dom";

const BooksList = ({books}) => {

    const navigate = useNavigate();


    return (

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
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
                            onClick={() => navigate(`/books/${book.id}`, {replace: true}) }
                            className="bg-white cursor-pointer border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 capitalize">
                            <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                {book.name}
                            </th>
                            <td className="px-6 py-4">
                                {book.author}
                            </td>
                            <td className="px-6 py-4">
                                {book.isbn}
                            </td>
                            <td className="px-6 py-4">
                                {book.category}
                            </td>
                            <td className="px-6 py-4 text-right">
                                <a href="#" className="font-medium text-blue-600 dark:text-blue-500 hover:underline">Delete</a>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>

    );
};

export default BooksList;