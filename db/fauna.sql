CREATE DATABASE fauna WITH ENCODING = 'UTF8';

CREATE TABLE FAUNA (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
	('Fennec fox', 12, '1775.01.01'),
	('Rufous hornero', 10, '1822.01.01'),
	('European fallow deer', 16, '1758.01.01'),
	('Blue marlin', 27, '1882.01.01'),
	('Plains zebra', 25, '1778.01.01'),
	('Siamese fighting fish', 3, '1909.01.01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 5 and avg_age < 20;

select * from fauna where discovery_date is null;

select * from fauna where EXTRACT(YEAR FROM discovery_date) < 1950;