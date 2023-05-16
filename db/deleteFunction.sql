create or replace function f_delete_data(d_id integer)
returns void
language 'plpgsql'
as
$$
    begin
		delete from products
		where id = d_id;
    end;
$$;

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

select f_delete_data(2);