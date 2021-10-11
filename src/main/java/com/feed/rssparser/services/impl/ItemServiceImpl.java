package com.feed.rssparser.services.impl;

import com.feed.rssparser.core.utils.NullUtil;
import com.feed.rssparser.dao.ItemRepository;
import com.feed.rssparser.entities.Item;
import com.feed.rssparser.services.ItemService;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Class responsible to handle business logic for item operations
 */
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> getLatest10Items() {
		return itemRepository.findFirst10ByOrderByPublicationDateDesc();
	}

	@Override
	public Item getItemByTitle(String title) {
		return itemRepository.findFirstByTitle(title);
	}

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Transactional
	@Override
	public void saveOrUpdateItems(List<Item> items) {
		items.forEach(item -> {
			Item existingItem = getItemByTitle(item.getTitle());
			if(NullUtil.isNull(existingItem)) {
				saveItem(item);
			} else if(NullUtil.isNotNull(item.getUpdatedDate())){
				if(NullUtil.isNull(existingItem.getUpdatedDate())
				|| (item.getUpdatedDate().after(existingItem.getUpdatedDate()))) {
					existingItem.setDescription(item.getDescription());
					existingItem.setUpdatedDate(item.getUpdatedDate());
					saveItem(existingItem);
				}
			}
		});
	}

	@Override
	public List<Item> getPaginatedItems(Integer page, Integer size, String sort, String direction) {
		Sort.Direction dir = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Pageable paging = PageRequest.of(page, size, Sort.by(dir , sort));
		Page<Item> pagedResult = itemRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new LinkedList<Item>();
		}
	}
}
