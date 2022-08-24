/**
 * @author m-ant
 */
 
 -- TABELA PEDIDO
insert into pedido (data) values ('2022-08-24 16:35');
insert into pedido (data) values ('2022-08-24 16:35');
insert into pedido (data) values ('2022-08-24 16:35');

-- TABELA ITEM
insert into item (ativo, tipo_item, descricao, valor) values (true, 'PRODUTO', 'Monitor 21.5"', 750.99);
insert into item (ativo, tipo_item, descricao, valor) values (true, 'PRODUTO', 'Kit Mouse+Teclado c/ fio', 99.99);
insert into item (ativo, tipo_item, descricao, valor) values (true, 'SERVICO', 'Entrega Rápida', 100.00);
insert into item (ativo, tipo_item, descricao, valor) values (false, 'SERVICO', 'Devolução', 100.00);

-- TABELA PEDIDO_ITEM
insert into pedido_item (pedido_id, item_id) values ('ba214c3b-ebe1-4540-92e6-ef6243e5f290', 'b898d8da-7fce-4cbd-a152-2443c5acb33b');
insert into pedido_item (pedido_id, item_id) values ('ba214c3b-ebe1-4540-92e6-ef6243e5f290', '1bd2d1f3-109f-4562-aa58-89806e80283a');
insert into pedido_item (pedido_id, item_id) values ('ba214c3b-ebe1-4540-92e6-ef6243e5f290', '9153d9e6-8bea-4629-8f2f-70d55d4d1c16');
