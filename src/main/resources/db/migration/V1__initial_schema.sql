CREATE TABLE books
(
    id      SERIAL PRIMARY KEY,
    sku     VARCHAR(255)   NOT NULL,
    price   DECIMAL(10, 2) NOT NULL,
    on_sale BOOLEAN        NOT NULL,
    title   VARCHAR(255)   NOT NULL,
    author  VARCHAR(255)   NOT NULL,
    year    INTEGER        NOT NULL
);

-- Insert initial data, shouldn't be done in production
INSERT INTO public.books (sku, price, on_sale, title, author, year)
VALUES ('SKU1984', 9.99, TRUE, '1984', 'George Orwell', 1949),
       ('SKU1960', 12.99, FALSE, 'To Kill a Mockingbird', 'Harper Lee', 1960),
       ('SKU1925', 10.99, TRUE, 'The Great Gatsby', 'F. Scott Fitzgerald', 1925),
       ('SKU1813', 8.99, FALSE, 'Pride and Prejudice', 'Jane Austen', 1813),
       ('SKU1961', 11.99, TRUE, 'Catch-22', 'Joseph Heller', 1961);
