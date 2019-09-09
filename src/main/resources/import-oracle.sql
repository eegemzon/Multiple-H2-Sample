DROP TABLE mysql_user IF EXISTS;
DROP SEQUENCE oracle_seq IF EXISTS;

CREATE SEQUENCE oracle_seq
MINVALUE 1
MAXVALUE 999999999999999999
START WITH 1
INCREMENT BY 1
NOCACHE
ORDER;

create table oracle_user (id bigint not null, my_address varchar(255) not null, name varchar(255), primary key (id));

insert into oracle_user (id, name, my_address) values (oracle_seq.nextval, 'Eineil-Oracle', 'Taguig');
