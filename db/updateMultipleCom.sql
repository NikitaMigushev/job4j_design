update orders 
set order_date = '2023.05.08',
order_period = 60,
expiration_date = TO_DATE('2023.05.08', 'YYYY.MM.DD') + 60
where orders.id = 5;
