package com.filehandlingwithsecurity.api.book.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.filehandlingwithsecurity.api.book.dto.BookDetailsDto;
import com.filehandlingwithsecurity.api.book.entity.BookDetailsEntity;

public interface BookDetailsService {

	public ResponseEntity<BookDetailsDto> add(String strAuthorName, MultipartFile file);
	public ResponseEntity<BookDetailsDto> update(String strBookId, String strAuthorName, MultipartFile file);
	public String delete(String strBookId);
	public ResponseEntity<BookDetailsEntity> getGivenIdDetails(String strBookId);
	public ResponseEntity<Resource> getIdDetails(String strBookId) throws Exception;
	public ResponseEntity<List<BookDetailsEntity>> getAllBookDetails();
}
