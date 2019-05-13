create table permissao(
	id_permissao int auto_increment,
    descricao varchar(30) not null,
    primary key(id_permissao)
);

create table login_permissao(
	fk_Login varchar(30),
    fk_id_permissao int not null,
    primary key(fk_login, fk_id_permissao),
    foreign key(fk_login) references login(login),
    foreign key(fk_id_permissao) references permissao(id_permissao)
);
