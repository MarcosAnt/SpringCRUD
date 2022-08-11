package com.usrmarcos.desafioSenior.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.desafioSenior.entity.Pedido;
import com.usrmarcos.desafioSenior.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired private PedidoRepository pedidoRepository;

	public Pedido create(Pedido pedido) {
		
		return pedidoRepository.save(pedido);
		
	}
}
