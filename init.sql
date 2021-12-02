drop database shop;
create database shop;

\c shop;

create table product_category (   
    id serial primary key,
    category_name varchar(255)
);

create table product (
    id serial primary key,
    name varchar(255),
    shortdescription varchar(255),
    cost integer,
    rating_total integer default 0,
    rating_counter integer default 0,
    category_id integer references product_category
);

create table role_table (
    id serial primary key,
    name varchar(255)
);

create table user_table (
    id serial primary key,
    login varchar(255),
    password varchar(255),
    role_id integer references role_table
);

create table basket (
    user_id integer references user_table,
    product_id integer references product
);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');
insert into product_category(category_name) values ('PHONE');
insert into product_category(category_name) values ('LAPTOP');