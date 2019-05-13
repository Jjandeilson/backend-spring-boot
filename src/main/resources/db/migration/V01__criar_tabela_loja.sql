create table loja(
	id_loja int auto_increment,
    nome_loja varchar(20) not null,
    estado char(2) not null,
    cidade varchar(20) not null,
    logradouro varchar(30),
    numero char(6),
    complemento varchar(20),
    bairro varchar(30),
    cep char(9),
    cnpj char(18) unique not null,
    primary key(id_loja)
);