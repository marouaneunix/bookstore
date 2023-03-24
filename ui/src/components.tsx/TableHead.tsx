export const TableHead = () => {
    return (
        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" className="px-6 py-3">
                    Book name
                </th>
                <th scope="col" className="px-6 py-3">
                    author
                </th>
                <th scope="col" className="px-6 py-3">
                    Category
                </th>
                <th scope="col" className="px-6 py-3">
                    ISBN
                </th>
                <th scope="col" className="px-6 py-3">
                    remove
                </th>
            </tr>
        </thead>
    );
}