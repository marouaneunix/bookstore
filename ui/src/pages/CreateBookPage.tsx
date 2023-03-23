import React, {useEffect, useState} from "react";
import PageHero from "../layout/PageHero";
import {useFormik} from "formik";
import * as Yup from "yup";
import {IoMdAddCircle} from "react-icons/all";
import axios from "axios";
import swal from "sweetalert";


export const CreateBookPage = () => {

    const [booksISBN, setBooksISBN] = useState([]);

    useEffect(() => {
        const fetchBooks = async () => {
            const response = await axios("/api/v1/books");
            const books = response.data;
            const fetchedBooksISBN = [];
            books.map((book) => {
                fetchedBooksISBN.push(book.isbn);
            });
            console.log('fetchedBooksISBN => ', fetchedBooksISBN);
            setBooksISBN(fetchedBooksISBN);
        };

        fetchBooks();
    }, []);


    const initialValues = {
        name: "",
        author: "",
        isbn: "",
        category: "",
        description: ""
    };

    const formik = useFormik({
        initialValues,
        validationSchema: Yup.object({
            name: Yup.string().required("Required"),
            author: Yup.string().required("Required"),
            isbn: Yup.string().test('is-unique', 'This ISBN value already exists.', (value) => {
                return value === undefined || booksISBN.indexOf(value) === -1;
            }).required("Required"),
            category: Yup.string().required("Required"),
            description: Yup.string().min(100).required("Required")
        }),
        onSubmit: async (values) => {
            try {
                const response = await axios.post('/api/v1/books', values);
                const data = response.data;
                console.log('axios response data => ', data);
                formik.resetForm();
                swal({
                    title: "Book Created!",
                    text: `Book: ${values.name} CREATED!`,
                    icon: "success",
                    button: "OK!",
                });
            } catch (error) {
                console.log(error);
                swal({
                    title: "Oops!",
                    text: `${error.message}`,
                    icon: "error",
                    button: "OK!",
                });
            }
        }
    });

    return (
        <>
            <PageHero title="Create books"/>
            <div className="w-[90%] lg:w-3/4 mx-auto">
                <div className="flex items-center mx-4 my-8 p-8 bg-white shadow-2xl drop-shadow-md">
                    <span className="text-4xl text-primary mr-6">
                      <IoMdAddCircle />
                    </span>
                    <h2 className="uppercase text-4xl tracking-widest font-semibold">
                        Add book
                    </h2>
                </div>

                <div className="flex m-4 p-8 bg-white shadow-lg">
                    <div className="w-3/4">
                        <form onSubmit={formik.handleSubmit}>
                            {/* name input */}
                            <div className="flex flex-col space-y-1 mb-8">
                                <label htmlFor="name" className="tracking-wider">
                                    Book name:
                                </label>
                                <input
                                    type="text"
                                    name="name"
                                    id="name"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.name}
                                    className="form-input rounded w-full bg-gray-100"
                                    placeholder="Enter book name"
                                />
                                {formik.touched.name && formik.errors.name && (
                                    <p className="text-xs font-semibold text-red-500">
                                        {formik.errors.name}
                                    </p>
                                )}
                            </div>
                            {/* author input */}
                            <div className="flex flex-col space-y-1 mb-8">
                                <label htmlFor="author" className="tracking-wider">
                                    Author :
                                </label>
                                <input
                                    type="text"
                                    name="author"
                                    id="author"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.author}
                                    className="form-input rounded w-full bg-gray-100"
                                    placeholder="Enter author name"
                                />
                                {formik.touched.author && formik.errors.author && (
                                    <p className="text-xs font-semibold text-red-500">
                                        {formik.errors.author}
                                    </p>
                                )}
                            </div>
                            {/* isbn input */}
                            <div className="flex flex-col space-y-1 mb-8">
                                <label htmlFor="isbn" className="tracking-wider">
                                    ISBN:
                                </label>
                                <input
                                    type="text"
                                    name="isbn"
                                    id="isbn"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.isbn}
                                    className="form-input rounded w-full bg-gray-100"
                                    placeholder="Enter book isbn"
                                />
                                {formik.touched.isbn && formik.errors.isbn && (
                                    <p className="text-xs font-semibold text-red-500">
                                        {formik.errors.isbn}
                                    </p>
                                )}
                            </div>
                            {/* category input */}
                            <div className="flex flex-col space-y-1 mb-8">
                                <label htmlFor="category" className="tracking-wider">
                                    Category:
                                </label>
                                <select
                                    name="category"
                                    id="category"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.category}
                                    className="form-select bg-gray-100"
                                >
                                    <option value=""></option>
                                    <option>Classics</option>
                                    <option>Crime</option>
                                    <option>Fantasy</option>
                                    <option>Humour</option>
                                    <option>Horror</option>
                                    <option>Fiction</option>
                                    <option>Mystery</option>
                                    <option>Poetry</option>
                                    <option>Romance</option>
                                    <option>War</option>
                                </select>
                                {formik.touched.category && formik.errors.category && (
                                    <p className="text-xs font-semibold text-red-500">
                                        {formik.errors.category}
                                    </p>
                                )}
                            </div>
                            {/* description input */}
                            <div className="flex flex-col space-y-1 mb-8">
                                <label htmlFor="description" className="tracking-wider">
                                    Description:
                                </label>
                                <textarea
                                    name="description"
                                    id="description"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.description}
                                    className="form-textarea w-full h-52 bg-gray-100 rounded-md"
                                    placeholder="Book description"
                                ></textarea>
                                {formik.touched.description && formik.errors.description && (
                                    <p className="text-xs font-semibold text-red-500">
                                        {formik.errors.description}
                                    </p>
                                )}
                            </div>
                            <hr/>
                            <button
                                type="submit"
                                className="px-4 py-2 block mt-3 ml-auto text-primary border border-primary hover:text-white hover:bg-primary rounded-md"
                            >
                                Create Book
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}