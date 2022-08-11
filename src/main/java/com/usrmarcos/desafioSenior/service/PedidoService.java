package com.usrmarcos.desafioSenior.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.desafioSenior.entity.Pedido;
import com.usrmarcos.desafioSenior.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired private PedidoRepository pedidoRepository;

	public Pedido createOrUpdate(Pedido pedido) {
		return pedidoRepository.save(pedido);
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
