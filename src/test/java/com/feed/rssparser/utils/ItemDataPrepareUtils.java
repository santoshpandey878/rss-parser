package com.feed.rssparser.utils;

import com.feed.rssparser.entities.Item;
import java.util.Date;

public final class ItemDataPrepareUtils {

	private ItemDataPrepareUtils() {}

	public static Item prepareItemResponse() {
		Item item = new Item();
		item.setId(1L);
		item.setTitle("Title 1");
		item.setDescription("Description 1");
		item.setPublicationDate(new Date());
		return item;
	}

	public static Item prepareItemRequest() {
		Item item = new Item();
		item.setTitle("Title 1");
		item.setDescription("Description 1");
		item.setPublicationDate(new Date());
		return item;
	}
}
