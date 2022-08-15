/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.springCRUD.enuns.TipoItem;
import com.usrmarcos.springCRUD.repository.PedidoRepository;
import com.usrmarcos.springCRUD.repository.entity.Item;
import com.usrmarcos.springCRUD.repository.entity.Pedido;
import com.usrmarcos.springCRUD.repository.entity.PedidoItem;

@Service
public class PedidoService {
	
	@Autowired private PedidoItemService pedidoItemService;
	
	@Autowired private PedidoRepository pedidoRepository;

	public Pedido createOrUpdate(Pedido pedido, List<Item> itens) {
		
		Double percentualDesconto = pedido.getPercentualDesconto();
		Double valorTotal = new Double(0);
		
		if (percentualDesconto != null && percentualDesconto > 0) {
			valorTotal = calcularValorTotal(percentualDesconto, itens);
		}
		
		pedido.setValor(valorTotal);
		
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
	
	private Double calcularValorTotal(Double percentualDesconto, List<Item> itens) {
		
		List<Item> produtos = itens.stream()
								   .filter( i -> TipoItem.PRODUTO.equals(i.getTipoItem()))
								   .collect(Collectors.toList());
		
		Double valorProdutos = produtos.stream()
									 .mapToDouble(p -> p.getValor())
									 .sum();
		
		valorProdutos = valorProdutos - (valorProdutos * percentualDesconto);
		
		List<Item> servicos = itens.stream()
				   .filter( i -> TipoItem.PRODUTO.equals(i.getTipoItem()))
				   .collect(Collectors.toList());

		Double valorServicos = servicos.stream()
							 .mapToDouble(p -> p.getValor())
							 .sum();
		
		Double valorTotal = valorServicos + valorProdutos;
		
		return valorTotal;
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
