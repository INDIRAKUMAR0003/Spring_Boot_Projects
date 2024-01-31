package com.filehandlingwithsecurity.api.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filehandlingwithsecurity.api.book.entity.BookDetailsEntity;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface BookDetailsDao extends JpaRepository<BookDetailsEntity, String> {

}
