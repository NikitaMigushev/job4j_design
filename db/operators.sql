CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

select b.title from book b
INTERSECT
SELECT m.name from movie m;

select b.title from book b
EXCEPT
SELECT m.name from movie m;

SELECT b.title from book b
UNION
SELECT m.name from movie m
EXCEPT
SELECT b.title from book b
INTERSECT
SELECT m.name from movie m;