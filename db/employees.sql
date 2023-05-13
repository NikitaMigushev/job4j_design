create table departments (
	id serial primary key,
	name text
);

create table employees (
	id serial primary key,
	name text
);

insert into departments (name) values
	('IT'),
	('Administration'),
	('HR'),
	('Finance');

insert into employees (name, department_id) values
	('Ivan', 1),
	('Petr', 2),
	('John', 3),
	('Peter', 4);

select * from employees as e
left join departments as d on e.department_id = d.id;

select * from employees as e
right join departments as d on e.department_id = d.id;

select * from employees as e
full join departments as d on e.department_id = d.id;

select * from employees as e
cross join departments as d;

select * from departments as d left join employees as e on d.id = e.department_id
where e.id is null;
