create table fornecedor(
	id_fornecedor int auto_increment,
    nome_fornecedor varchar(30) not null,
    estado char(2) not null,
    telefone char(14),
    email varchar(40),
    cnpj_fornecedor char(18),
    fk_id_loja int not null,
    primary key(id_fornecedor),
    foreign key(fk_id_loja) references loja(id_loja)
);