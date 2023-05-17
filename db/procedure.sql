create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price_wo_tax integer)
language 'plpgsql'
as $$
	begin
	insert into products (name, producer, count, price_wo_tax)
	values (i_name, prod, i_count, i_price_wo_tax);
	end
$$;

call insert_data('product_2', 'producer_2', 15, 32);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price_w_tax = price + price * tax;
        end if;
    END;
$$;

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price_w_tax = price_wo_tax + price_wo_tax * tax;
            select into result sum(price_w_tax) from products;
        end if;
        return result;
    end;
$$;