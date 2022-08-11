package com.usrmarcos.desafioSenior.repository;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.usrmarcos.desafioSenior.entity.PedidoItem;

@Repository
public class PedidoItemRepository extends SimpleJpaRepository<PedidoItem, UUID> {

	public PedidoItemRepository(Class<PedidoItem> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
