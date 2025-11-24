insert into USERS (USERID, EMAIL, PHONENUMBER, FULLNAME, STATUS, REGISTERDATE, BIRTHDAY) values
(1, 'ivan@mail.ru', '+79161234567', 'Иван Иванов', 'ACTIVE', '2024-01-15 10:00:00', '1990-05-20'),
(2, 'petr@mail.ru', '+79161234568', 'Петр Петров', 'ACTIVE', '2024-01-16 11:00:00', '1985-08-15'),
(3, 'maria@mail.ru', '+79161234569', 'Мария Сидорова', 'ACTIVE', '2024-01-17 12:00:00', '1992-12-10');

insert into PRODUCTS (PRODUCT_ID, SKU, NAME, DESCRIPTION, PRICE, STATUS, STOCKQUANTITY, CATEGORY, IMAGEURL, CREATEDAT, UPDATEDAT) values
(1, 'NOTEBOOK-001', 'Ноутбук Lenovo', 'Игровой ноутбук', 75000.00, 'ACTIVE', 10, 'ELECTRONICS', '/images/lenovo.jpg', '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
(2, 'PHONE-001', 'iPhone 15', 'Смартфон Apple', 90000.00, 'ACTIVE', 25, 'ELECTRONICS', '/images/iphone.jpg', '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
(3, 'BOOK-001', 'Война и мир', 'Книга Л. Толстого', 1500.00, 'ACTIVE', 100, 'BOOKS', '/images/book.jpg', '2024-01-15 10:00:00', '2024-01-15 10:00:00');