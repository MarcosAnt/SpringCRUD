/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.springCRUD.repository.PedidoRepository;
import com.usrmarcos.springCRUD.repository.entity.Item;
import com.usrmarcos.springCRUD.repository.entity.Pedido;
import com.usrmarcos.springCRUD.repository.entity.PedidoItem;

@Service
public class PedidoService {
	
	@Autowired private PedidoItemService pedidoItemService;
	
	@Autowired private PedidoRepository pedidoRepository;

	public Pedido createOrUpdate(Pedido pedido, List<Item> itens) {
		
		pedido = pedidoRepository.save(pedido);
		
		List<PedidoItem> pedidoItens = new ArrayList<PedidoItem>();
		for (Item item : itens) {
			PedidoItem pedidoItem = new PedidoItem();
			pedidoItem.setPedido(pedido);
			pedidoItem.setItem(item);
			
			pedidoItens.add(pedidoItem);
		}
		
		pedidoItemService.createOrUpdate(pedidoItens);
		
		
		return pedido;
		
	}
	
	public Pedido findById(UUID id) {
		Optional<Pedido> result = pedidoRepository.findById(id);
		return result.get();
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public void delete(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
	
}
