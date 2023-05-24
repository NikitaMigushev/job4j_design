CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values
 (1, 'CompanyA'),
 (2, 'CompanyB'),
 (3, 'CompanyC'),
 (4, 'CompanyD'),
 (5, 'CompanyE')

 insert into person (id, name, company_id) values
 	(1, 'Ivan', 1),
	(2, 'Segey', 1),
	(3, 'Petr', 1),
	(4, 'John', 2),
	(5, 'Alice', 2),
	(6, 'Kate', 2),
	(7, 'Nalaie', 3),
	(8, 'Bratt', 4),
	(9, 'Sophy', 4),
	(10, 'Valera', 5),
	(11, 'Igor', 5)

select * from person as p
join company as c on p.company_id = c.id
where c.id != 5;

select
p.name,
c.name as company_name
from person as p
join company as c on p.company_id = c.id;

select c.name as company_name, count(p.id) as number_of_employees
from company c
join person as p on p.company_id = c.id
group by c.id, c.name
having count(p.id) = (
    select MAX(employee_count)
    from (
        select count(id) as employee_count
        from person
        group by company_id
    ) as counts
)


