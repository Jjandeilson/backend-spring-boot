create table compra(
	id_compra int auto_increment,
    quantidade int not null,
    pk_id_loja int not null,
    pk_id_fornecedor int not null,
    pk_id_produto int not null,
    primary key(id_compra),
    foreign key(pk_id_loja) references loja(id_loja),
    foreign key(pk_id_fornecedor) references fornecedor(id_fornecedor),
    foreign key(pk_id_produto) references produto(pk_id_produto)
);