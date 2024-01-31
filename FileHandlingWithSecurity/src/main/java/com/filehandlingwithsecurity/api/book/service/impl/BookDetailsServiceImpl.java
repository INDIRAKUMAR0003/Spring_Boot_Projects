package com.filehandlingwithsecurity.api.book.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filehandlingwithsecurity.api.book.dao.BookDetailsDao;
import com.filehandlingwithsecurity.api.book.dto.BookDetailsDto;
import com.filehandlingwithsecurity.api.book.entity.BookDetailsEntity;
import com.filehandlingwithsecurity.api.book.service.BookDetailsService;

@Service
public class BookDetailsServiceImpl implements BookDetailsService {

	@Autowired
	private BookDetailsDao objBookDetailsDao;
	private BookDetailsDto objBookDetailsDto= null;
	
	private String strStatus= "";
	private String strBookName= "";
	private String strDownloadUrl= "";
	
	
	@Override
	public ResponseEntity<BookDetailsDto> add(String strAuthorName, MultipartFile file) {
		
		try
		{
			strBookName= StringUtils.cleanPath(file.getOriginalFilename());
			
			BookDetailsEntity objBookDetailsEntity= BookDetailsEntity.builder()
					.strBookName(strBookName)
					.strBookAuthor(strAuthorName)
					.strBookFormat(file.getContentType())
					.byteBookData(file.getBytes())
					.build();
			
			BookDetailsEntity objBookDetailsEntity_Save= objBookDetailsDao.save(objBookDetailsEntity);
			
			strDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/book/download/")
					.path(objBookDetailsEntity_Save.getStrBookId())
					.toUriString();
			
			objBookDetailsDto= BookDetailsDto.builder()
					.strBookName(objBookDetailsEntity_Save.getStrBookName())
					.strBookAuthor(objBookDetailsEntity_Save.getStrBookAuthor())
					.strBookFormat(objBookDetailsEntity_Save.getStrBookFormat())
					.strDownloadURL(strDownloadUrl)
					.build(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(objBookDetailsDto);
	}

	@Override
	public ResponseEntity<BookDetailsDto> update(String strBookId, String strAuthorName, MultipartFile file) {
		
		try
		{
			Optional<BookDetailsEntity> objBookDetailsEntity_Find= objBookDetailsDao.findById(strBookId);
			
			if(objBookDetailsEntity_Find.isPresent())
			{
				strBookName= StringUtils.cleanPath(file.getOriginalFilename());
				BookDetailsEntity objBookDetailsEntity_Update= 
					   BookDetailsEntity.builder()
										.strBookId(objBookDetailsEntity_Find.get().getStrBookId())
										.strBookAuthor(strAuthorName)
										.strBookFormat(file.getContentType())
										.strBookName(strBookName)
										.byteBookData(file.getBytes())
										.build();
				
				BookDetailsEntity objBookDetailsEntity_Save= objBookDetailsDao.save(objBookDetailsEntity_Update);
				
				strDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath()
														   .path("/book/download/")
														   .path(objBookDetailsEntity_Find.get().getStrBookId())
														   .toUriString();
				objBookDetailsDto= BookDetailsDto.builder()
												 .strBookName(objBookDetailsEntity_Save.getStrBookName())
												 .strBookAuthor(objBookDetailsEntity_Save.getStrBookAuthor())
												 .strBookFormat(objBookDetailsEntity_Save.getStrBookFormat())
												 .strDownloadURL(strDownloadUrl)
												 .build();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(objBookDetailsDto);
	}

	@Override
	public String delete(String strBookId) {
		
		if(objBookDetailsDao.existsById(strBookId))
		{
			objBookDetailsDao.deleteById(strBookId);
			strStatus= "Successfully Deleted for given Id - "+strBookId;
		}
		else
			strStatus= strBookId+" Id Does Not Exists";
		return strStatus;
	}

	@Override
	public ResponseEntity<BookDetailsEntity> getGivenIdDetails(String strBookId) {
		
		return ResponseEntity.ok().body(objBookDetailsDao.findById(strBookId).orElse(new BookDetailsEntity()));
	}

	@Override
	public ResponseEntity<List<BookDetailsEntity>> getAllBookDetails() {
		
		return ResponseEntity.ok().body(objBookDetailsDao.findAll());
	}

	@Override
	public ResponseEntity<Resource> getIdDetails(String strBookId) throws Exception {
		
		BookDetailsEntity objBookDetailsEntity= null;
		
		objBookDetailsEntity= objBookDetailsDao
				.findById(strBookId)
				.orElseThrow(()->new Exception(strBookId+" - Id does Not Exists."));
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(objBookDetailsEntity.getStrBookFormat()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "Book Name - "+objBookDetailsEntity.getStrBookName())
				.body(new ByteArrayResource(objBookDetailsEntity.getByteBookData()));
	}

}
