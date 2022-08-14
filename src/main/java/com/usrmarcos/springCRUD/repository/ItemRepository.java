/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usrmarcos.springCRUD.repository.entity.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {


}
