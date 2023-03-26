import {useState} from "react";



export type BookCriteria = {
    name: string;
    categories: string;
}

const defaultBookCriteria = {
    name:"",
    categories: ""
}

type Props = {
    onSearch: (bookCriteria: BookCriteria) => void
}
export const SearchBooksForm = ({onSearch}: Props) => {

    const [bookCriteria, setBookCriteria] = useState<BookCriteria>(defaultBookCriteria);

    const handleSearchClick = () => {
        onSearch(bookCriteria);
    }
    return (

        <form>
            <div className="mb-6">
                <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900">Name</label>
                <input type="text" id="name"
                       onChange={(event) => setBookCriteria({...bookCriteria, name: event.target.value})}
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                       placeholder="Atomic Habbits" required />
            </div>
            <div className="mb-6">
                <label htmlFor="categories" className="block mb-2 text-sm font-medium text-gray-900">categories</label>
                <input type="text" id="categories"
                       onChange={(event) => setBookCriteria({...bookCriteria, categories: event.target.value})}
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                       required />
            </div>
            <button type="button"
                    onClick={handleSearchClick}
                    className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Search
            </button>
        </form>

)
}