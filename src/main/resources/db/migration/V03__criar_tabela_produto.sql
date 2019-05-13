create table produto(
	id_produto int auto_increment,
    nome_produto varchar(20) not null,
    preco_fabrica decimal(6,2) not null,
    preco_loja decimal (6,2) not null,
    tipo varchar(20) not null,
    pk_id_fornecedor int not null,
    pk_id_loja int not null,
    primary key(id_produto),
    foreign key(pk_id_fornecedor) references fornecedor(id_fornecedor),
    foreign key(pk_id_loja) references loja(id_loja)
);