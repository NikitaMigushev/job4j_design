CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) values
	('John', 'Doe', 33, 'Denmark'),
	('Ivan', 'Petrov', 22, 'Russian Federation'),
	('Christian', 'Bale', 49, 'United Kingdom'),
	('Alexander', 'Greenfield', 37, 'Austria');

select * from customers where age = (select min(age) from customers);

CREATE TABLE salesorders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into salesorders (amount, customer_id) values
	(100, 1),
	(80, 2),
	(120, 3);

select * from customers
where customers.id NOT IN (select customer_id from salesorders);