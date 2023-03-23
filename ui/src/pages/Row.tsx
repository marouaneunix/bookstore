import React, { useState } from "react";
import PopUp from "./PopUp";

type Book = {
  id: number;
  description: string;
  isbn: string;
  categories: string;
  title: string;
  auteur: string;
};

type RowProps = {
  book: Book;
  index: number;
  handleClickDelete: (id: number) => void;
};

function Row(props: RowProps) {
  const [showPopup, setShowPopup] = useState(false);

  const handlePopupClose = () => {
    setShowPopup(false);
  };

  const handleClickDelete = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.stopPropagation();
    props.handleClickDelete(props.book.id);
  };

  const handleClickRow = () => {
    setShowPopup(!showPopup);
  };

  return (
    <tr
      className="border-b bg-gray-50 dark:bg-gray-800 dark:border-gray-700"
      key={props.index}
      onClick={handleClickRow}
    >
      <th
        scope="row"
        className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
      >
        {props.index + 1}
      </th>
      <td className="px-6 py-4">{props.book.title}</td>
      <td className="px-6 py-4">{props.book.auteur}</td>
      <td className="px-6 py-4">{props.book.categories}</td>
      <td className="px-6 py-4">
        <button
          type="button"
          className="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
          onClick={handleClickDelete}
        >
          Delete
        </button>
      </td>
      {showPopup && (
        <PopUp
          description={props.book.description}
          title={props.book.title}
          handlePopupClose={handlePopupClose}
        />
      )}
    </tr>
  );
}


export default Row;