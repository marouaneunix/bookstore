package ma.norsys.bookstore.book;

record BookDTO(Long id, String name) {
    public BookDTO(Book boo) {
        this(boo.getId(), boo.getName());
    }
}
