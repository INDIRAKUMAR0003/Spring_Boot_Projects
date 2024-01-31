package com.filehandlingwithsecurity.api.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filehandlingwithsecurity.api.book.dto.BookDetailsDto;
import com.filehandlingwithsecurity.api.book.entity.BookDetailsEntity;
import com.filehandlingwithsecurity.api.book.service.BookDetailsService;

@CrossOrigin
@RequestMapping("/book")
@RestController
public class BookDetailsController {

	@Autowired
	private BookDetailsService objBookDetailsService;
	
	@GetMapping
	public String bookApi()
	{
		return "Welcome Book API";
	}
	
	@PostMapping("/add")
	public ResponseEntity<BookDetailsDto> add(
			@RequestParam("bookauthor") String strAuthor, 
			@RequestParam("bookfile") MultipartFile file)
	{
		return objBookDetailsService.add(strAuthor, file);
	}
	
	@GetMapping("/{bookid}")
	public ResponseEntity<BookDetailsEntity> getIdDetails(@PathVariable("bookid") String strBookId)
	{
		return objBookDetailsService.getGivenIdDetails(strBookId);
	}
	
	@GetMapping("/download/{bookid}")
	public ResponseEntity<Resource> get(@PathVariable("bookid") String strBookId) throws Exception 
	{
		return objBookDetailsService.getIdDetails(strBookId);
	}
	
	
}
