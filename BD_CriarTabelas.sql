create table Site
(
url varchar(256) not null,
nome varchar(256) not null,
email varchar(256) not null,
telefone varchar(24) not null,
senha varchar(256) not null,
CONSTRAINT primary_key PRIMARY KEY (url)
);

create table Hotel
(
    cnpj varchar(256) not null,
    nome varchar(256) not null,
    cidade varchar(256) not null, 
    senha varchar(256) not null
    CONSTRAINT primary_key PRIMARY KEY (cnpj)
);

create table Promocao
(
    id integer not null generated always as identity (start with 1, increment by 1),
    url varchar(256) references Site(url),
    cnpj varchar(256) references Hotel(cnpj),
    data_inicio Date not null, 
    data_fim Date not null,
    preco Decimal not null
); 
