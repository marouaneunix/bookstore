CREATE TABLE  IF NOT EXISTS book (
     id SERIAL,
     name VARCHAR(128) NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS category (
    book_id BIGINT NOT NULL,
    category_name varchar(64) NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id)
)