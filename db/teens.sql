CREATE TYPE gender AS ENUM ('M', 'F');

create table teens (
	name text,
	gender gender
);

insert into teens (name, gender) values
	('Ivan', 'M'),
	('Petr', 'M'),
	('Kate', 'F'),
	('Sveta', 'F');

SELECT t1.name AS name1, t1.gender AS gender1, t2.name AS name2, t2.gender AS gender2
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender;
