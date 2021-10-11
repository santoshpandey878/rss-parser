package com.feed.rssparser.controller;

import com.feed.rssparser.core.utils.DtoConverter;
import com.feed.rssparser.dto.ItemDTO;
import com.feed.rssparser.entities.Item;
import com.feed.rssparser.services.ItemService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Item Controller responsible to handle all client requests related to items and return the response.
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("/items")
@Api(value = "Item Controller responsible to handle all client requests related to items.")
public class ItemController {

	private final ItemService itemService;
	private final DtoConverter dtoConverter;

	@Autowired
	public ItemController(ItemService itemService,
						  DtoConverter dtoConverter) {
		this.itemService = itemService;
		this.dtoConverter = dtoConverter;
	}


	/**
	 * API to get 10 newest items
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to get 10 newest items")
	public List<ItemDTO> getLatest10Items() {
		List<Item> items = itemService.getLatest10Items();
		return items.stream()
				.map(shop -> dtoConverter.convert(shop, ItemDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("paginated")
	@ApiOperation(value = "API to get paginated items")
	public List<ItemDTO> getPaginatedItems(
			@RequestParam Integer page,
			@RequestParam Integer size,
			@RequestParam String sort,
			@RequestParam String direction)
	{
		List<Item> items = itemService.getPaginatedItems(page, size, sort, direction);
		return items.stream()
				.map(shop -> dtoConverter.convert(shop, ItemDTO.class))
				.collect(Collectors.toList());
	}
}
