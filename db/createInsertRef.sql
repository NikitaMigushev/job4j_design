create table jobs(
	id serial primary key,
	name varchar(255),
	salary int
);

create table people(
	id serial primary key,
	name varchar(255),
	job_id int references jobs(id)
);

create table passport(
	id serial primary key,
	passport_id int,
	people_id int references people(id)
);


create table countries(
	id serial primary key,
	name varchar(255)
);

create table countries_visited_by_people(
	id serial primary key,
	country_id int references countries(id),
	people_id int references people(id)
);

CREATE TYPE contact_type as ENUM('tel', 'email');

create table contacts(
	id serial primary key,
	contact_type contact_type,
	people_id int references people(id),
	contact varchar(255)

);

insert into jobs(name, salary) values
	('Manager', 69934),
	('Software Engineer', 114214),
	('Enterpreneur', 99664),
	('Scientist', 93498);
	

insert into people(name, job_id) values
	('Ivan', 1),
	('Pert', 2),
	('John', 3),
	('Nikita', 4);
	
insert into passport(passport_id, people_id) values
	(1111, 1),
	(2222, 2),
	(3333, 3),
	(4444, 4);
	
	
insert into countries(name) values
	('Russian Federation'),
	('Germany'),
	('United States'),
	('Australia');
	
insert into countries_visited_by_people(country_id, people_id) values
	(1, 1),
	(1, 2),
	(2, 3),
	(2, 4),
	(3, 3),
	(3, 4);

insert into contacts(contact_type, people_id, contact) values
	('tel', 1, '111-11-11'),
	('email', 1, 'ivan@mail.ru'),
	('tel', 2, '222-22-22'),
	('email', 2, 'petr@mail.ru'),
	('tel', 3, '333-33-33'),
	('email', 3, 'john@mail.ru'),
	('tel', 4, '444-44-44'),
	('email', 4, 'nikita@mail.ru')