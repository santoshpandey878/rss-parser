package com.feed.rssparser.services;

import com.feed.rssparser.entities.Item;
import java.util.List;

/**
 * Interface responsible to provide methods for item operations
 */
public interface ItemService {


	/**
	 * Method to get latest 10 items
	 * @return
	 */
	List<Item> getLatest10Items();

	/**
	 * Method to get a item by title
	 * @param title
	 * @return
	 */
	Item getItemByTitle(String title);

	/**
	 * Method to save item
	 * @param item
	 * @return
	 */
	Item saveItem(Item item);

	/**
	 * Method to save or update list of items
	 * @param items
	 */
    void saveOrUpdateItems(List<Item> items);

	/**
	 * Method to get paginated items
	 * @param page
	 * @param size
	 * @param sort
	 * @param direction
	 * @return
	 */
    List<Item> getPaginatedItems(Integer page, Integer size, String sort, String direction);
}
