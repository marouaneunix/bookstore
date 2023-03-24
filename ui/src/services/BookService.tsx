import axios from 'axios'
const BOOK_BASE_API_URL='http://localhost/api/books/';
class BookService{
    deleteBook(){
        return axios.delete(BOOK_BASE_API_URL)
    }
}
export default new BookService();