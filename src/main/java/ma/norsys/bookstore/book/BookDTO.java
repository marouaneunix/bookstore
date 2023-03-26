package ma.norsys.bookstore.book;

record BookDTO(Long id, String name, String categories) {
    public BookDTO(Book boo) {
        this(boo.getId(), boo.getName(), String.join(",", boo.getCategories()));
    }
}
