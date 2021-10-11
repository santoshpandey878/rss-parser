package com.feed.rssparser.services.impl;

import com.feed.rssparser.entities.Item;
import com.feed.rssparser.services.FeedParserAsyncService;
import com.feed.rssparser.services.ItemService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 *  Class responsible to handle business logic for RSS feed parsing
 */
@Service
@EnableAsync
public class FeedParserAsyncServiceImpl implements FeedParserAsyncService {

	private final ItemService itemService;

	@Autowired
	public FeedParserAsyncServiceImpl(ItemService itemService) {
		this.itemService = itemService;
	}

	@Async
	@Override
	public void parseRSSFeed(SyndFeed feed) {
		List<Item> items =  new LinkedList<>();

		for (SyndEntry entry : feed.getEntries()) {
			Item item = new Item();
			item.setTitle(entry.getTitle());
			item.setDescription(entry.getDescription().getValue());
			item.setPublicationDate(entry.getPublishedDate());
			item.setUpdatedDate(entry.getUpdatedDate());
			items.add(item);

			if(items.size() == 50) {
				break;
			}
		}
		itemService.saveOrUpdateItems(items);
	}
}
