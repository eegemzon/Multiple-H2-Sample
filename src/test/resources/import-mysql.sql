DROP TABLE mysql_user IF EXISTS;

create table mysql_user (id bigint not null, my_address varchar(255) not null, name varchar(255), primary key (id));

insert into mysql_user (id, name, my_address) values (1, 'Eineil-Mysql', 'Taguig');