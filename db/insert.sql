insert into role (name) values
 ('admin'),
 ('user');

insert into users (name, role_id) values
    ('Ivan', 1),
    ('Petr', 2);

insert into rules (name) values
    ('rule1'),
    ('rule2');

insert into role_rules (role_id, rule_id) values
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2);

insert into category (name) values
  ('category1'),
  ('category2');

insert into state (name) values
  ('state 1'),
  ('state 2');

insert into item (name, category_id, state_id) values
 ('item1', 1, 1),
 ('item2', 2, 2);

insert into comments (name, item_id) values
 ('comment1', 1),
 ('comment2', 1),
 ('comment1', 2),
 ('comment2', 2);



