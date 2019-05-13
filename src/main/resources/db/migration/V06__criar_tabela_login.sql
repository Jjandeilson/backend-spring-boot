create table login(
	login varchar(30),
    senha varchar(150),
    pk_id_empregado int auto_increment,
    primary key(login),
    foreign key(pk_id_empregado) references empregado(id_empregado)
);