/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usrmarcos.springCRUD.repository.entity.Pedido;
import com.usrmarcos.springCRUD.repository.entity.PedidoItem;


@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID> {

	@Query("delete from PedidoItem pi where pi.pedido = :pedido")
	public abstract void deletarItensPedido(@Param("pedido") Pedido pedido);
	
}