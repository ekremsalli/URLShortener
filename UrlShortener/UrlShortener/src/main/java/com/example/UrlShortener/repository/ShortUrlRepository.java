package com.example.UrlShortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UrlShortener.model.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long>{
	
	Optional<ShortUrl> findAllByCode(String code);
	
}
