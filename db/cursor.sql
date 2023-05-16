BEGIN;
DECLARE
    cursor_products cursor for
                    select * from products;
FETCH LAST FROM cursor_products;
MOVE BACKWARD 6 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 9 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 6 FROM cursor_products;
FETCH FROM cursor_products;
MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;
CLOSE cursor_products;
COMMIT;
