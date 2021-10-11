package com.feed.rssparser.services;

import com.rometools.rome.feed.synd.SyndFeed;

/**
 * Interface responsible to provide methods to parse RSS feed
 */
public interface FeedParserAsyncService {

	/**
	 * Method to parse RSS Feed
	 * @param feed
	 */
	void parseRSSFeed(SyndFeed feed);

}
