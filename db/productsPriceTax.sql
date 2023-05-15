create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

ALTER TABLE products
RENAME COLUMN price to price_wo_tax,
ADD COLUMN price_w_tax;

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price_w_tax = price_wo_tax * (1 + 0.2)
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax();

insert into products (name, producer, count, price_wo_tax) values
('Product_A', 'Producer_XYZ', 100, 150);

drop trigger tax_trigger on products;

create or replace function tax()
    returns trigger as
$$
    BEGIN
		NEW.price_w_tax := NEW.price_wo_tax * (1 + 0.2);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger before_insert_tax
	before insert on products
	for each row
	execute function tax();

insert into products (name, producer, count, price_wo_tax) values
('Product_B', 'Producer_ABC', 80, 125);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price_wo_tax integer,
    price_w_tax integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION insert_product_trigger_function()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO history_of_price (name, price_wo_tax, price_w_tax, date)
    VALUES (NEW.name, NEW.price_wo_tax, NEW.price_w_tax, CURRENT_TIMESTAMP);

    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger insert_product_trigger
	after insert on products
	for each row
	execute function insert_product_trigger_function();

insert into products (name, producer, count, price_wo_tax) values
('Product_BBB', 'Producer_CCC', 140, 75);
