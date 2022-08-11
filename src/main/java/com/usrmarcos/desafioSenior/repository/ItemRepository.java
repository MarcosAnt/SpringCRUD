package com.usrmarcos.desafioSenior.repository;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.usrmarcos.desafioSenior.entity.Item;

@Repository
public class ItemRepository extends SimpleJpaRepository<Item, UUID> {

	public ItemRepository(Class<Item> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
