package com.feed.rssparser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.Data;

/**
 * DTO class for Item entity used with client interactions.
 */
@ApiModel(description="All details about the item dto.")
@Data
public class ItemDTO {
	private String title;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date publicationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date updatedDate;
}
