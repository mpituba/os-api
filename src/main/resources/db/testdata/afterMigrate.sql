set foreign_key_checks = 0;

delete from estado;

set foreign_key_checks = 1;

alter table estado auto_increment = 1;

insert into estado (id, nome, uf) values (1, 'Minas Gerais', 'MG');
insert into estado (id, nome, uf) values (2, 'São Paulo', 'SP');
insert into estado (id, nome, uf) values (3, 'Ceará', 'CE');
