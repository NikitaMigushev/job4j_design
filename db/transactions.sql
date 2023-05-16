begin transaction;

savepoint first_savepoint;

insert into items (name, price, qty) values
 ('productA', 100, 10),
 ('productB', 120, 15);

 savepoint second_savepoint;

 update items set qty = 25 where id = 2;

select * from items;

ROLLBACK TO second_savepoint;

select * from items;

ROLLBACK TO first_savepoint;

select * from items;

ROLLBACK;

begin transaction;

insert into items (name, price, qty) values
 ('productA', 100, 10),
 ('productB', 120, 15);

commit;

select * from items;

