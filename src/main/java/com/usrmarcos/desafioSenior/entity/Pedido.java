package com.usrmarcos.desafioSenior.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private UUID id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;
	
	@Column(name = "pencentual_desconto")
	private Long percentualDesconto;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Long percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	
}
