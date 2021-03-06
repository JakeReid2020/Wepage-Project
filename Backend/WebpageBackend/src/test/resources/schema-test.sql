drop table if exists customer CASCADE;
drop table if exists item CASCADE; 
drop table if exists line_item CASCADE; 
drop table if exists order_table CASCADE; 
create table customer (id bigint generated by default as identity, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id));
create table item (id bigint generated by default as identity, description varchar(255) not null, name varchar(255) not null, price double not null, primary key (id));
create table line_item (id bigint generated by default as identity, quantity integer not null, item_id bigint, ordertable_id bigint, primary key (id));
create table order_table (id bigint generated by default as identity, customer_id bigint, primary key (id));
alter table line_item add constraint FKktf4dtpwa1ndqmjit4qir1fsi foreign key (item_id) references item on delete cascade;
alter table line_item add constraint FKj5hkem1gsr5dtam3fb7vwccsq foreign key (ordertable_id) references order_table;
alter table order_table add constraint FKdit09e676nqbguvt1k1w8mlj2 foreign key (customer_id) references customer;
