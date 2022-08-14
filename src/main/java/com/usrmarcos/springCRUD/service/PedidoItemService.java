/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.springCRUD.repository.PedidoItemRepository;
import com.usrmarcos.springCRUD.repository.entity.Pedido;
import com.usrmarcos.springCRUD.repository.entity.PedidoItem;

@Service
public class PedidoItemService {

	@Autowired private PedidoItemRepository pedidoItemRepository;
	
	public void deletarItensPedido(Pedido pedido) {
		pedidoItemRepository.deletarItensPedido(pedido);
	}
	
	public PedidoItem createOrUpdate(PedidoItem pedidoItem) {
		return pedidoItemRepository.save(pedidoItem);
	}
	
	public List<PedidoItem> createOrUpdate(List<PedidoItem> pedidoItens) {
		pedidoItens.stream().forEach(pedidoItem -> {
			pedidoItem = pedidoItemRepository.save(pedidoItem);
		});
		
		return pedidoItens;
	}
	
	public PedidoItem findById(UUID id) {
		Optional<PedidoItem> result = pedidoItemRepository.findById(id);
		return result.get();
	}
	
	public List<PedidoItem> findAll() {
		return pedidoItemRepository.findAll();
	}
	
	public void delete(PedidoItem pedidoItem) {
		pedidoItemRepository.delete(pedidoItem);
	}
}
