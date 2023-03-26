import { useState } from "react";

   type Book={
        id: number ,
        title: string,
        isbn:"",
        author: string,
        description: string, 
        categories : string 
    }

const DefaultBook:Book ={
    id:0,
    title:"",
    isbn:"",
    author:"",
    description:"",
    categories:""
}

export const BookLine =({book,onDeleteBook }: any)=>{

  const [showModal, setShowModal] = useState(false);


 const onDelete =()=>{
    onDeleteBook(book.id);
 }

    return(
        <>

          <tr  key={book.id}>
                  <td onClick={()=>setShowModal(true)} className="px-6 py-4 whitespace-nowrap">
                    <div className="flex items-center">
                        <div className="ml-4">
                          <div className="text-sm font-medium text-gray-900">{book.id}</div>
                        </div>
                        </div>
                  </td>
                  <td onClick={()=>setShowModal(true)} className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm text-gray-900">{book.title}</div>
                  </td>
                  <td onClick={()=>setShowModal(true)} className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm text-gray-900">{book.author}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span onClick={()=>setShowModal(true)}  className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800" >
                          {book.isbn}
                    </span>
                  </td>  
                  <td onClick={()=>setShowModal(true)} className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{book.categories}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                    <button className="text-indigo-600 hover:text-indigo-900"onClick ={onDelete}> delete
                    </button>
                  </td>
          </tr>
          {showModal ? (
        <>
          <div
            className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none"
          >
            <div className="relative w-auto my-6 mx-auto max-w-3xl">
              {/*content*/}
              <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                {/*header*/}
                <div className="flex items-start justify-between p-5 border-b border-solid border-slate-200 rounded-t">
                  <h3 className="text-3xl font-semibold">
                    Book Description
                  </h3>
                  <button
                    className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                    onClick={() => setShowModal(false)}
                  >
                    <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                      ×
                    </span>
                  </button>
                </div>
                {/*body*/}
                <div className="relative p-6 flex-auto">
                  <p className="my-4 text-slate-500 text-lg leading-relaxed">
                    {book.description}
                  </p>
                </div>
                {/*footer*/}
                <div className="flex items-center justify-end p-6 border-t border-solid border-slate-200 rounded-b">
                  <button
                    className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                    type="button"
                    onClick={() => setShowModal(false)}
                  >
                    Close
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
        </>
      ) : null}
   </>
    )
}