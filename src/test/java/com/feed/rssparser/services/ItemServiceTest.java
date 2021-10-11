package com.feed.rssparser.services;

import com.feed.rssparser.dao.ItemRepository;
import com.feed.rssparser.entities.Item;
import com.feed.rssparser.services.impl.ItemServiceImpl;
import com.feed.rssparser.utils.ItemDataPrepareUtils;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class ItemServiceTest {

	@Mock private ItemRepository itemRepository;
	@InjectMocks private ItemServiceImpl itemService;
	private Item itemResponse;

	@Before
	public void init() {
		itemResponse = ItemDataPrepareUtils.prepareItemResponse();
	}

	@Test
	public void testGetLatest10Items_ShouldReturnItems() {
		Mockito.when(itemRepository.findFirst10ByOrderByPublicationDateDesc())
		.thenReturn(Arrays.asList(itemResponse));

		List<Item> response = itemService.getLatest10Items();

		Assert.assertEquals(1, response.size());
		Assert.assertEquals(1, response.get(0).getId().longValue());
	}

	@Test
	public void testGetItemByTitle_ShouldReturnItem() {
		Mockito.when(itemRepository.findFirstByTitle(Mockito.anyString()))
		.thenReturn(itemResponse);

		Item response = itemService.getItemByTitle("Title 1");

		Assert.assertEquals(1, response.getId().longValue());
	}

	@Test
	public void testSaveItem_ShouldReturnSavedItem() {
		Mockito.when(itemRepository.save(Mockito.any(Item.class)))
		.thenReturn(itemResponse);

		Item response = itemService.saveItem(ItemDataPrepareUtils.prepareItemRequest());

		Assert.assertEquals(1, response.getId().longValue());
	}

	@Test
	public void testSaveOrUpdateItems_ShouldReturnNothing() {
		Mockito.when(itemRepository.findFirstByTitle(Mockito.anyString()))
				.thenReturn(itemResponse);

		Mockito.when(itemRepository.save(Mockito.any(Item.class)))
				.thenReturn(itemResponse);

		itemService.saveOrUpdateItems(Arrays.asList(ItemDataPrepareUtils.prepareItemRequest()));
	}

}
