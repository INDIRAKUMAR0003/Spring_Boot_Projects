package com.filehandlingwithsecurity.api.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailsDto {
	private String strBookName;
	private String strBookAuthor;
	private String strBookFormat;
	private String strDownloadURL;
}
