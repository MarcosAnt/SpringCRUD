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
import com.usrmarcos.springCRUD.service.ItemService;
import com.usrmarcos.springCRUD.util.ApiError;

@RestController
@RequestMapping("/item")
public class ItemController extends UtilController {

	@Autowired private ItemService itemService;
	
	Logger logger = getLogger(PedidoController.class);
	
	@PostMapping("/criar")
	@PatchMapping("/atualizar")
	public Object criarItem(@RequestBody(required = true) Item item) {
		try {
			
			item = itemService.createOrUpdate(item);
			
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível criar o pedido: Erro inesperado.");
		}
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@GetMapping("/listarItens/{itemId}")
	public Object listarItens(@PathParam("itemId") UUID itemId) {
		
		List<Item> itens = new ArrayList<Item>();
		
		try {
			if (itemId == null) {
				itens = itemService.findAll();
			}
			else {
				Item item = itemService.findById(itemId);
				itens.add(item);
			}
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível listar o(s) pedido(s): Erro inesperado.");
		}
		
		
		if (CollectionUtils.isEmpty(itens)) {
			return new ResponseEntity<List<Item>>(itens, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<Item>>(itens, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deletar/{itemId}")
	public Object deletarItem(@PathParam("itemId") UUID itemId) {
		
		if (itemId == null) {
			return new ResponseEntity<>("Nenhum item informado.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Item item = itemService.findById(itemId);
			
			if (item == null) {
				throw new RuntimeException("Item não encontrado.");
			}
			
			item.setAtivo(false);
			itemService.createOrUpdate(item);
			
			return ResponseEntity.ok();
			
		}
		catch (RuntimeException e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.BAD_REQUEST, message, "Não foi possível deletar: Item não encontrado.");
		}
		catch (Exception e) {
			String message = getExceptionMessage(e);
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, "Não foi possível deletar: Erro inesperado.");
		}
	}
}
