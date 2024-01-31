package com.filehandlingwithsecurity.api.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="BOOKDETAILS")
public class BookDetailsEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="BOOK_ID")
	private String strBookId;
	
	@Column(name="BOOK_NAME")
	private String strBookName;
	
	@Column(name="BOOK_AUTHOR")
	private String strBookAuthor;
	
	@Column(name="BOOK_FILE_FORMAT")
	private String strBookFormat;
	
	@Lob
	@Column(name="BOOK_DATA")
	private byte[] byteBookData;
}
