package com.feed.rssparser.dao;

import com.feed.rssparser.entities.Item;
import java.util.List;

/**
 * Repository to handle all database operation for item.
 */
public interface ItemRepository extends BaseRepository<Item, Long>{

  /**
   * Method to get latest 10 items
   * @return
   */
  List<Item> findFirst10ByOrderByPublicationDateDesc();

  /**
   * Method to get a item by title
   * @param title
   * @return
   */
  Item findFirstByTitle(String title);
}
