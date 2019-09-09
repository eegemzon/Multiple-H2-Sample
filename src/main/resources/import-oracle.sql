DROP SEQUENCE oracle_seq IF EXISTS;

CREATE SEQUENCE oracle_seq
MINVALUE 1
MAXVALUE 999999999999999999
START WITH 1
INCREMENT BY 1
NOCACHE
ORDER;

insert into oracle_user (id, name, my_address) values (oracle_seq.nextval, 'Eineil-Oracle', 'Taguig');
