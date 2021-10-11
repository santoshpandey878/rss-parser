package com.feed.rssparser.timers;

import com.feed.rssparser.services.FeedParserAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.net.URL;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * Task timer class responsible to start scheduler with fixed rate and delay and parse RSS feed.
 */
@Component
@EnableScheduling
public class RSSParserTimer {

	@Autowired
	private FeedParserAsyncService feedParserAsyncService;

	@Value("${rss.feed.url}")
	private String URL;

	/**
	 * Method to trigger timer/cron process to poll RSS Feed
	 * this process will start in every 5 minutes
	 */
	@Scheduled(fixedRate =  5*60*1000)
	public void feedParserJob(){
		try {
			try (XmlReader reader = new XmlReader(new URL(URL))) {
				SyndFeed feed = new SyndFeedInput().build(reader);
				feedParserAsyncService.parseRSSFeed(feed);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
}
