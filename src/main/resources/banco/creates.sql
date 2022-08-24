/**
 * @author m-ant
 */

-- DATABASE
CREATE DATABASE spring_crud
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- EXTENÇÃO PARA GERAÇÃO DE UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- TABELAS
create table pedido (
	id UUID default uuid_generate_v4(),
	data timestamp not null,
	percentual_desconto decimal,
	valor decimal,
	primary key (id)
);

create table item (
	id UUID default uuid_generate_v4(),
	ativo boolean default true,
	tipo_item varchar(10),
	descricao varchar(150),
	valor decimal,
	primary key (id)
);

create table pedido_item (
	id UUID default uuid_generate_v4(),
	pedido_id UUID,
	item_id UUID,
	primary key (id),
	foreign key (pedido_id) references pedido (id),
	foreign key (item_id) references item (id)
);
