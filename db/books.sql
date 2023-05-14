ALTER table orders
ADD COLUMN order_date date,
ADD COLUMN order_period int,
ADD COLUMN expiration_date date

update orders
set order_date = '2023.05.08',
order_period = 60,
expiration_date = TO_DATE('2023.05.08', 'YYYY.MM.DD') + 60
where orders.id = 5;

select * from orders;

create view show_orders_with_less_10_days_expiration as
select
s.name as student
,o.id
,a.name as author
,b.name as book
,o.expiration_date
,EXTRACT(DAY FROM AGE(o.expiration_date, CURRENT_DATE)) as days_till_expiration

from orders as o

left join students as s on o.student_id = s.id
left join books as b on o.book_id = b.id
left join authors as a on b.author_id = a.id

where EXTRACT(DAY FROM AGE(o.expiration_date, CURRENT_DATE)) < 10;

select * from show_orders_with_less_10_days_expiration;