import { Fragment, useState } from "react";
import {
    Button,
    Dialog,
    DialogHeader,
    DialogBody,
    DialogFooter,
} from "@material-tailwind/react";


type Book = {
    id: number,
    isbn: string,
    title: string,
    author: string,
    categories: string,
    description: string,
}

export const TableRow = ({ item, column, deleteBook }: any) => {
    const defaultBook: Book = {
        id: 0,
        isbn: '',
        title: '',
        author: '',
        categories: '',
        description: '',
    };
    const [book, setBook] = useState<Book>(defaultBook);
    const [open, setOpen] = useState(false);
    const handleOpen = () => {
        setBook(item);
        setOpen(!open);
    }
    return (
        <>
            <tr  >
                {
                    column.map((columnItem: any, index: number) => {

                        if (columnItem.heading === "Action") {
                            let key = item[`${columnItem.value}`];
                            const onDelete = async (key: any) => {
                                deleteBook(key);
                            }
                            return (<td className="px-6 py-4" key={index}>
                                <button onClick={() => onDelete(key)} className="inline-flex items-center px-4 py-2 bg-red-600 hover:bg-red-700 text-white text-sm font-medium rounded-md">
                                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                                    </svg>

                                    Delete
                                </button>
                            </td>)
                        }
                        return <td className="px-6 py-4" onClick={handleOpen} key={index}>{item[`${columnItem.value}`]}</td>
                    })
                }
            </tr>
            <Dialog open={open} handler={handleOpen} style={{
                position: 'absolute',
                top: '30%',
                border: '1px solid grey',
                left: '25%',
                transform: 'translate(-50%, -50%)',
                width: '700px',
                backgroundColor: 'white'
            }}>
                <DialogHeader>{book.title}</DialogHeader>
                <DialogBody divider>
                    {book.description}
                </DialogBody>
                <DialogFooter>
                    <Button
                        onClick={handleOpen}
                        className="mr-1"
                    >
                        Close
                    </Button>
                </DialogFooter>
            </Dialog>
        </>
    )
}