import React, { useEffect, useState } from "react";
import axios from "axios";

type Search = {
    categories: string,
    name: string,
}
export const SearchPage = ({ onSearch }: any) => {
    const defaultSearch: Search = {
        name: '',
        categories: '',
    };
    const [search, setSearch] = useState<Search>(defaultSearch);
    const handleSearch = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        onSearch(search)
    }
    return (
        <div>
            <form onSubmit={handleSearch}>
                <div className="flex justify">
                    <div className="relative w-1/4  mr-2">
                        <input type="search" id="default-search" value={search.name} onChange={(event) => setSearch({ ...search, name: event.target.value })}
                            className="block w-full p-4 pl-10  py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500"
                            placeholder="Search by name" />
                    </div>

                    <div className="relative w-1/4 mr-2" >
                        <input type="search" id="default-search" value={search.categories} onChange={(event) => setSearch({ ...search, categories: event.target.value })}
                            className="block w-full p-4 pl-10  py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500"
                            placeholder="Search by categories" />
                    </div>
                    <div className="relative w-1/5">
                        <div>Action</div>
                        <div><button type="submit" style={{ backgroundColor: 'green' }}
                            className="block w-full   py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500
                                bg-blue-500 hover:bg-blue-700 text-white font-bold py-2    ">Search</button></div>
                    </div>
                </div>
            </form>
        </div>
    )
}