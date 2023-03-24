import { useState } from "react";

type searchProps = {
  onSearchBook: (searchTerm: string, catSearchTerm: string) => void
}

export const BookSearch = (props: searchProps) => {
  const [searchTerm, setSearchTerm] = useState("")
  const [catSearchTerm, setCatSearchTerm] = useState("")

  const handleSubmit = (event: any) => {
    console.log(catSearchTerm);
    event.preventDefault();
    props.onSearchBook(searchTerm, catSearchTerm);
  };



  return (
    <div className="flex flex-col space-y-2">
      <div className="flex flex-col space-y-2">
        <form className="flex space-x-2" onSubmit={handleSubmit}>
          <div className="flex items-center">
            <input type="text" id="search1" placeholder="Search 1" className="p-2 border rounded-md mr-2" value={searchTerm} onChange={(event) => setSearchTerm(event.target.value)} />
            <input type="text" id="search2" placeholder="Search 2" className="p-2 border rounded-md mr-2" value={catSearchTerm} onChange={(event) => setCatSearchTerm(event.target.value)} />
            <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">Search</button>
          </div>
        </form>
      </div>

    </div>

  );
}