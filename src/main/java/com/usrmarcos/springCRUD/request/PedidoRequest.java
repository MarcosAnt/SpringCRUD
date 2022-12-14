/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usrmarcos.springCRUD.repository.entity.Item;
import com.usrmarcos.springCRUD.repository.entity.Pedido;

public class PedidoRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "pedido", required = true)
	private Pedido pedido;
	
	@JsonProperty(value = "itens", required = true)
	private List<Item> itens;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
}
