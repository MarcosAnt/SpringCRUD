package com.usrmarcos.desafioSenior.repository;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.usrmarcos.desafioSenior.entity.Pedido;

@Repository
public class PedidoRepository extends SimpleJpaRepository<Pedido, UUID> {

	public PedidoRepository(Class<Pedido> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	
}
