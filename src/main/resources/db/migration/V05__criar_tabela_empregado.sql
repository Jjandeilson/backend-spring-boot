create table empregado(
	id_empregado int auto_increment,
    cargo varchar(20) not null,
    nome_funcionario varchar(30) not null,
    cpf char(14) not null,
    rg char(12) not null,
    sexo char(1),
    estado char(2),
    cidade varchar(20),
    logradouro varchar(30),
    complemento varchar(20),
    bairro varchar(30),
    numero char(6),
    cep char(9),
    telefone char(14),
    pk_id_loja int not null,
    primary key(id_empregado),
    foreign key(pk_id_loja) references loja(id_loja)
);