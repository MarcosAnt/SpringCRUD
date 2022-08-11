package com.usrmarcos.desafioSenior.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usrmarcos.desafioSenior.entity.Item;
import com.usrmarcos.desafioSenior.entity.Pedido;
import com.usrmarcos.desafioSenior.entity.PedidoItem;
import com.usrmarcos.desafioSenior.request.PedidoRequest;
import com.usrmarcos.desafioSenior.service.ItemService;
import com.usrmarcos.desafioSenior.service.PedidoItemService;
import com.usrmarcos.desafioSenior.service.PedidoService;
import com.usrmarcos.desafioSenior.util.ApiError;

@RestController
@RequestMapping("/pedido")
public class PedidoController extends UtilController {
	
	@Autowired private PedidoService pedidoService;
	@Autowired private ItemService itemService;
	@Autowired private PedidoItemService pedidoItemService;
	
	
	Logger logger = getLogger(PedidoController.class);

	@PostMapping("/criar")
	@PatchMapping("/atualizar")
	public Object criarPedido(@RequestBody(required = true) PedidoRequest pedidoRequest) {
		
		try {
			Pedido pedido = pedidoRequest.getPedido();
			List<Item> itens = pedidoRequest.getItens();
			List<PedidoItem> pedidoItens = new ArrayList<PedidoItem>();
			
			pedido = pedidoService.createOrUpdate(pedido);
			
			for (Item item : itens) {
				PedidoItem pedidoItem = new PedidoItem();
				pedidoItem.setPedido(pedido);
				pedidoItem.setItem(item);
				
				pedidoItens.add(pedidoItem);
			}
			
			pedidoItemService.createOrUpdate(pedidoItens);
			
			UUID pedidoId = pedido.getId();
			pedidoRequest.getPedido().setId(pedidoId);
			
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível criar o pedido: Erro inesperado");
		}
		
		return new ResponseEntity<PedidoRequest>(pedidoRequest, HttpStatus.OK);
	}
}
