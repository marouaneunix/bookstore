import React, { useEffect, useState } from "react";

import axios from "axios";
type Search = {
    categories: string,
    title: string,
}
export const Search = ({ onSearch }: any) => {
    const defaultSearchItem: Search = {
        title: '',
        categories: '',
    };
    const [searchItem, setSearchItem] = useState<Search>(defaultSearchItem);
    const handleClick = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        onSearch(searchItem)
    }
    return (
        <div>
            <form onSubmit={handleClick}>
                <div className="flex justify">
                    <div className="relative w-1/4  mr-2">
                        <input type="search" id="default-search" value={searchItem.title} onChange={(event) => setSearchItem({ ...searchItem, title: event.target.value })}
                            className="block w-full p-4 pl-10  py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500"
                            placeholder="Search by title" />
                    </div>

                    <div className="relative w-1/4 mr-2" >
                        <input type="search" id="default-search" value={searchItem.categories} onChange={(event) => setSearchItem({ ...searchItem, categories: event.target.value })}
                            className="block w-full p-4 pl-10  py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500"
                            placeholder="Search by categories" />
                    </div>
                    <div className="relative w-1/5">
                        <button type="submit" style={{ backgroundColor: 'blue' }}
                            className="block w-full   py-2 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:focus:border-blue-500
                                bg-blue-500 hover:bg-blue-700 text-white font-bold py-2    ">Search</button>
                    </div>
                </div>
            </form>
        </div>
    )
}