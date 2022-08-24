/**
 * @author m-ant
 */

-- DATABASE
CREATE DATABASE spring_crud
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1; 

-- TABELAS
create table produto (
	id UUID primary key,
	data timestamp not null,
	percentual_desconto decimal,
	valor decimal
);

create table item (
	id UUID primary key,
	ativo boolean default true,
	tipo_item varchar(10),
	valor decimal
);

create table pedido_item (
	id UUID primary key,
	pedido_id UUID,
	item_id UUID,
	foreign key (pedido_id) references produto (id),
	foreign key (item_id) references item (id)
);
