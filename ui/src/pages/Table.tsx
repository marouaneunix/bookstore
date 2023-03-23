import axios from "axios";
import Row from "./Row"

type Book = {
    id: number;
    description: string;
    isbn: string;
    categories: string;
    title: string;
    auteur:string
}

type props={
    books : Array<Book>;
    hundleClickDelete: any;

}

function displayTable(props :props){

    
        
    return(

        <>
        
             <div className="relative overflow-x-auto shadow-md sm:rounded-lg" style={{width : 1500 , marginLeft : 50}}>
            <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" className="px-6 py-3">
                            id
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Title
                        </th>
                        <th scope="col" className="px-6 py-3">
                            auteur
                        </th>
                       
                        <th scope="col" className="px-6 py-3">
                            Categorie
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Action
                        </th>
                    </tr>
                </thead>


                <tbody>
                    
                    {props.books.map( (book,index )=> <Row index={index} book={book} handleClickDelete={props.hundleClickDelete}/> )}
                  
                    
                </tbody>
            </table>
            </div>

        </>

    )






}

export default displayTable;