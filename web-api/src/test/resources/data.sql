-- user
insert into `user` (id, name, email)
values('17437c17-d9dd-425d-b3c3-827e45dc5f0a','John Smith','john.smith@gmail.com');
insert into `user` (id, name, email)
values('f300e926-6e26-46f5-8ea3-1189ee5396c4','Olga Mock','olga.mock@gmail.com');
-- group
insert into `groups` (id, name)
values ('ae509b34-6171-4ece-9f7b-23fd6bcf5dbb', 'family');
-- user_group
insert into user_group (user_id, group_id)
values ('17437c17-d9dd-425d-b3c3-827e45dc5f0a', 'ae509b34-6171-4ece-9f7b-23fd6bcf5dbb'), ('f300e926-6e26-46f5-8ea3-1189ee5396c4', 'ae509b34-6171-4ece-9f7b-23fd6bcf5dbb');
-- currency
insert into currency (code, name, country)
values ('USD', 'USA dollar', 'USA');
-- category
insert into category (id, name)
values ('fa0df06f-ff8a-4ee3-a022-bb36b52d7afb', 'household appliances');