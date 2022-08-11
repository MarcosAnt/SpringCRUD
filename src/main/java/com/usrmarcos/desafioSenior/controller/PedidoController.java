package com.usrmarcos.desafioSenior.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usrmarcos.desafioSenior.entity.Pedido;
import com.usrmarcos.desafioSenior.service.ItemService;
import com.usrmarcos.desafioSenior.service.PedidoService;
import com.usrmarcos.desafioSenior.util.ApiError;

@RestController
@RequestMapping("/pedido")
public class PedidoController extends UtilController {
	
	@Autowired private PedidoService pedidoService;
	@Autowired private ItemService itemService;
	
	
	Logger logger = getLogger(PedidoController.class);

	@PostMapping("/criar")
	public Object criarPedido(@RequestBody(required = true) Pedido pedido) {
		
		try {
			pedido = pedidoService.create(pedido);
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível criar o pedido: Erro inesperado");
		}
		
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
}
