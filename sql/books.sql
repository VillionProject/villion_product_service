# CREATE TABLE book (
#                       PRIMARY KEY (순번),
#                       순번 INT,
#                       구분 VARCHAR(20),
#                       상품명 VARCHAR(255),
#                       ItemId VARCHAR(20),
#                       ISBN13 VARCHAR(20),
#                       부가기호 VARCHAR(20),
#                       저자 VARCHAR(100),
#                       출판사 VARCHAR(100),
#                       출간일 DATE,
#                       정가 VARCHAR(100),
#                       판매가 VARCHAR(100),
#                       대표분류_대분류명 VARCHAR(100),
#                       대표분류_영문명 VARCHAR(100)
# );


CREATE TABLE books (
                      PRIMARY KEY (id),
                      id INT,
                      category VARCHAR(20),
                      bookName VARCHAR(255),
                      itemId VARCHAR(20),
                      isbn13 VARCHAR(20),
                      additionalCode VARCHAR(20),
                      author VARCHAR(100),
                      publisher VARCHAR(100),
                      publicationDate DATE,
                      originalPrice VARCHAR(100),
                      sellingPrice VARCHAR(100),
                      mainGenre VARCHAR(100),
                      englishMainGenre VARCHAR(100),
                      productGroup VARCHAR(100)
);


LOAD DATA INFILE '/var/lib/mysql-files//Aladin_Weekly_Best_Sellers_Domestic_Books.csv'
INTO TABLE books
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

SHOW VARIABLES LIKE 'secure_file_priv';