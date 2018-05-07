CREATE TABLE employees
(
  id SERIAL,
  first_name varchar,
  last_name varchar,
  nick_name varchar,
  logical_delete boolean DEFAULT false
);