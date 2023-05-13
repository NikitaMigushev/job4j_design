create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions (
	id serial primary key,
	name text
);

create table cars (
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values
	('Sedan'),
	('SUV'),
	('Hatchback');

insert into car_engines (name) values
	('V engine'),
	('Diesel engine'),
	('Flat engine'),
	('W engine');

insert into car_transmissions (name) values
	('Manual transmission'),
	('Automatic transmission'),
	('CVT transmission');

insert into cars (name, body_id, engine_id, transmission_id) values
	('Dodge Challenger', 3, 1, 2),
	('Ford Mustang', 3, 1, 2),
	('Tesla Model Y', 2, null, 2);

select
c.id
,c.name as "car_name"
,cb.name as "body_name"
,ce.name as "engine_name"
,ct.name as "transmission_name"

from cars as c
left join car_bodies as cb on cb.id = c.body_id
left join car_engines as ce on ce.id = c.engine_id
left join car_transmissions as ct on ct.id = c.transmission_id;

select * from car_bodies as cb
left join cars as c on c.body_id = cb.id
where c.body_id is null;

select * from car_engines as ce
left join cars as c on c.engine_id = ce.id
where c.engine_id is null;

select * from car_transmissions as ct
left join cars as c on c.transmission_id = ct.id
where c.transmission_id is null;