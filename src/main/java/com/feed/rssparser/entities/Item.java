package com.feed.rssparser.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity class for item table
 */
@Data
@Entity
@Table(name = "feed_item")
public class Item implements Serializable{
	private static final long serialVersionUID = 4181157730494809671L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "title", unique = true, nullable = false)
	private String title;

	@Lob
	private String description;

	@Column(name = "publication_date", unique = true, nullable = false)
	private Date publicationDate;

	@Column(name = "updated_date")
	private Date updatedDate;

}
