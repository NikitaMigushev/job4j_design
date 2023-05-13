insert into devices (name, price) values
	('device1', 100),
	('device2', 120),
	('device3', 140

insert into people (name) values
	('Ivan'),
	('Petr'),
	('John');

insert into devices_people (device_id, people_id) values
	(1, 1),
	(2, 1),
	(1, 2),
	(1, 3),
	(2, 3),
	(3, 3);

select avg(price) from devices;

select p.name, avg(d.price) from people as p
join devices_people as dp on dp.people_id = p.id
join devices as d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price) from people as p
join devices_people as dp on dp.people_id = p.id
join devices as d on d.id = dp.device_id
group by p.name
having avg(d.price) > 105;