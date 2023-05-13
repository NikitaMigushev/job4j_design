select * from people as p
join passport as pas on p.id = pas.people_id;

select * from people as p
join jobs as j on p.job_id = j.id;

select * from countries as c
join countries_visited_by_people as cvp on c.id = cvp.country_id;