/**
 * @author m-ant
 */

package com.usrmarcos.springCRUD.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usrmarcos.springCRUD.repository.ItemRepository;
import com.usrmarcos.springCRUD.repository.entity.Item;

@Service
public class ItemService {

	@Autowired private ItemRepository itemRepository;
	
	public Item createOrUpdate(Item item) {
		return itemRepository.save(item);
	}
	
	public Item findById(UUID id) {
		Optional<Item> result = itemRepository.findById(id);
		return result.get();
	}
	
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	
	public void delete(Item item) {
		itemRepository.delete(item);
	}
	
}
