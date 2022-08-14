/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usrmarcos.springCRUD.repository.entity.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

	
}
