create table type (
	id serial primary key,
	name text,

);

create table product (
	id serial primary key,
	name text,
	type_id int references type(id)
 expired_date date,
 price float
);

insert into type (name) values
	('СЫР'),
	('МОЛОКО')
 	('МОРОЖЕНОЕ');

insert into product (name, type_id, expired_date, price) values
    ('Сыр плавленный', 1, '2023.06.01', 100),
	('Cыр моцарелла', 1, '2023.05.01', 120),
	('Брынза', 1, '2023.05.10', 90),
	('Адыгейский', 1, '2023.07.01', 70),
	('Альметте', 1, '2023.10.01', 54),
	('Жерве', 1, '2023.01.01', 33),
	('Аперифре', 1, '2023.08.01', 22),
	('Маскарпоне', 1, '2023.09.10', 120),
 	('Фета', 1, '2023.10.01', 100),
	('Тофу', 1, '2023.10.01', 120),
	('Филадельфия', 1, '2023.05.01', 100),
	('Цельное молоко', 2, '2023.05.01', 120),
	('Нормализованное молоко', 2, '2023.06.01', 120),
	('Восстановленное молоко', 2, '2023.05.01', 120),
	('Ультрапастеризованное молоко', 2, '2023.06.01', 120),
	('Пастеризованное молоко', 2, '2023.06.01', 120),
	('Стерилизованное молоко', 2, '2023.10.01', 100),
	('Топлёное молоко', 2, '2023.05.01', 120),
	('Безлактозное молоко', 2, '2023.06.01', 100),
	('Пломбир', 3, '2023.10.01', 120),
	('Сливочное мороженое', 3, '2023.06.01', 120),
	('Сорбет', 3, '2023.05.01', 120),
	('Фруктовый лёд', 3, '2023.10.01', 100),
	('Щербет', 3, '2023.05.01', 100);

select * from product as p
join type as t on p.type_id = t.id
where t.name = 'СЫР';

select * from product as p
where p.name like '%мороженое%';

select * from product as p
where p.expired_date < current_date;

select * from product
where price = (select max(price) from product);


select t.name, count(p.name) from type as t
join product as p on p.type_id = t.id
group by t.name;

select * from product as p
join type as t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select t.name, count(p.name) from type as t
join product as p on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select * from product as p
join type as t on t.id = p.type_id;