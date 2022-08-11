package com.usrmarcos.desafioSenior.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {

	@Transient
	private UUID id = UUID.randomUUID();
	
	@OneToMany
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@OneToMany
	@JoinColumn(name = "item_id")
	private Item item;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
