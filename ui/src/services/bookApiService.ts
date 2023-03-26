import axios from "axios";
import {BookCriteria} from "../components/SearchBooksForm";


export const fetchBooks = async () => {
    try {
        const response = await axios("/api/v1/books");
        return response.data;
    } catch (e: any) {
        throw new Error(`Failed to fetch data from API. Status: ${e.status}`)
    }
}

export const fetchBooksByCriteria = async (bookCriteria: BookCriteria) => {
    try {
        const response = await axios(`/api/v1/books/v2?name=${bookCriteria.name}&categories=${bookCriteria.categories.replace(" ", ",")}`);
        return response.data;
    }catch(e: any) {
        throw new Error(`Failed to fetch data from API. Status: ${e.status}`);
    }
}

export const deleteBookById = async (bookId: number) => {
    // TODO backend implementation
    try {
        await axios.delete(`/api/v1/books/${bookId}`)
    }catch (error) {
        throw new Error('Failed to delete Book');
    }
}