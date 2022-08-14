/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usrmarcos.springCRUD.repository.entity.Item;
import com.usrmarcos.springCRUD.repository.entity.Pedido;
import com.usrmarcos.springCRUD.request.PedidoRequest;
import com.usrmarcos.springCRUD.service.PedidoItemService;
import com.usrmarcos.springCRUD.service.PedidoService;
import com.usrmarcos.springCRUD.util.ApiError;

@RestController
@RequestMapping("/pedido")
public class PedidoController extends UtilController {
	
	@Autowired private PedidoService pedidoService;
	@Autowired private PedidoItemService pedidoItemService;
	
	
	Logger logger = getLogger(PedidoController.class);

	@PostMapping("/criar")
	@PatchMapping("/atualizar")
	public Object criarPedido(@RequestBody(required = true) PedidoRequest pedidoRequest) {
		
		try {
			Pedido pedido = pedidoRequest.getPedido();
			List<Item> itens = pedidoRequest.getItens();
			
			pedido = pedidoService.createOrUpdate(pedido, itens);
			
			UUID pedidoId = pedido.getId();
			pedidoRequest.getPedido().setId(pedidoId);
			
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível criar o pedido: Erro inesperado.");
		}
		
		return new ResponseEntity<PedidoRequest>(pedidoRequest, HttpStatus.OK);
	}
	
	@GetMapping("/listarPedidos/{pedidoId}")
	public Object listarPedidos(@PathParam("pedidoId") UUID pedidoId) {
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			if (pedidoId == null) {
				pedidos = pedidoService.findAll();
			}
			else {
				Pedido pedido = pedidoService.findById(pedidoId);
				pedidos.add(pedido);
			}
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível listar o(s) pedido(s): Erro inesperado.");
		}
		
		
		if (CollectionUtils.isEmpty(pedidos)) {
			return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deletar/{pedidoId}")
	public Object deletarPedido(@PathParam("pedidoId") UUID pedidoId) {
		
		if (pedidoId == null) {
			return new ResponseEntity<>("Nenhum pedido informado.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Pedido pedido = pedidoService.findById(pedidoId);
			
			if (pedido == null) {
				throw new RuntimeException("Pedido não encontrado.");
			}
			
			pedidoItemService.deletarItensPedido(pedido);
			
			pedidoService.delete(pedido);
			return ResponseEntity.ok();
			
		}
		catch (RuntimeException e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.BAD_REQUEST, message, "Não foi possível deletar: Pedido não encontrado.");
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível deletar: Erro inesperado.");
		}
	}
}

