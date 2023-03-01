-- CRIA TABELAS ESTADO, CIDADE, PESSOA e EXECUTOR --

create table estado 
(id bigint not null auto_increment,
 nome varchar(25) not null,
 uf varchar(2) not null,
 primary key (id)
 ) engine=InnoDB default charset=utf8;
 
 
create table cidade 
(id bigint not null auto_increment,
 nome varchar(59),
 cidade_estado_id bigint,
 primary key (id),
 constraint fk_cidade_estado_id__estado_id 
 foreign key (cidade_estado_id) references estado (id)
 ) engine=InnoDB default charset=utf8;
 
 create table pessoa
 (id bigint not null auto_increment,
  tipo_pessoa integer,
  tratamento varchar(60),
  nome varchar(60),
  ativo bit,
  data_criacao datetime(6) not null,
  data_ultima_desativacao datetime(6),
  endereco_cep varchar(9),
  endereco_logradouro varchar(60),
  endereco_numero varchar(10),
  endereco_bairro varchar(60),
  endereco_complemento varchar(60),
  endereco_cidade_id bigint,
  primary key (id),
  constraint fk_endereco_cidade_id__cidade_id
  foreign key (endereco_cidade_id) references cidade (id)
  ) engine=InnoDB default charset=utf8;

create table executor 
(id bigint not null auto_increment,
 data_cadastro datetime(6) not null,
 documento varchar(64),
 email varchar(255),
 tipo_documento varchar(40) not null,
 titulo varchar(60),
 executor_pessoa_id bigint,
 primary key (id),
 constraint fk_executor_pessoa_id__pessoa_id
 foreign key (executor_pessoa_id) references pessoa (id)
 ) engine=InnoDB default charset=utf8;
 
