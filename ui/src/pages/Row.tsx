type Book = {
    id: number;
    description: string;
    isbn: string;
    categories: string;
    title: string;
    auteur:string
}

type props ={
    book: Book;
    index:number;
    hundleClickDelete:any;

}



function Row (props : props){

    const hundleClick =(event : any) =>{
        props.hundleClickDelete(event);
    }

    return(
        <tr className="border-b bg-gray-50 dark:bg-gray-800 dark:border-gray-700" key={props.index}>
    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
       {props.index+1}
    </th>
    <td className="px-6 py-4">
    {props.book.title}
    </td>
    <td className="px-6 py-4">
    {props.book.auteur}
    </td>
    <td className="px-6 py-4">
    {props.book.description}
    </td>
    <td className="px-6 py-4">
    {props.book.categories}
    </td>
    <td className="px-6 py-4">
    <button type="button"
     className="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
     onClick={(event)=>{hundleClick(props.book.id)}}
     >Delete
     </button>
    </td>
    </tr>

    )



}
export default Row;