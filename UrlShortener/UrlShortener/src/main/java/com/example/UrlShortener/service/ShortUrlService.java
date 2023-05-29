package com.example.UrlShortener.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.UrlShortener.model.ShortUrl;
import com.example.UrlShortener.repository.ShortUrlRepository;
import com.example.UrlShortener.util.RandomStringGenerator;

@Service
public class ShortUrlService {
	
	private final ShortUrlRepository repository;
	private final RandomStringGenerator randomStringGenerator;



	public ShortUrlService(ShortUrlRepository repository, RandomStringGenerator randomStringGenerator) {
		super();
		this.repository = repository;
		this.randomStringGenerator = randomStringGenerator;
	}

	public List<ShortUrl> getAllShortUrl() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public ShortUrl getUrlByCode(String code) {
		return repository.findAllByCode(code).orElseThrow(
			() -> new RuntimeException("bulunamadı")
		);
	}

	public ShortUrl create(ShortUrl shortUrl) {
		if(shortUrl.getCode() == null || shortUrl.getCode().isEmpty()) {
			shortUrl.setCode(generateCode());
		}
	
		shortUrl.setCode(shortUrl.getCode().toUpperCase());
		
		return repository.save(shortUrl);
	}
	
	private String generateCode() {
		String code;
		
		do {
			code = randomStringGenerator.generateRandomString();
		}
		while(repository.findAllByCode(code).isPresent());
		
		return code;
	}
}
