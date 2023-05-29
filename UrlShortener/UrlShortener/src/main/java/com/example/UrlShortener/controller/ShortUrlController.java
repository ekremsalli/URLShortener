package com.example.UrlShortener.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UrlShortener.dto.ShortUrlDto;
import com.example.UrlShortener.dto.converter.ShortUrlDtoConverter;
import com.example.UrlShortener.model.ShortUrl;
import com.example.UrlShortener.request.ShortUrlRequest;
import com.example.UrlShortener.request.converter.ShortUrlRequestConverter;
import com.example.UrlShortener.service.ShortUrlService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/ShortUrl")
public class ShortUrlController {
	
	private final ShortUrlDtoConverter shortUrlDtoConverter;
	private final ShortUrlRequestConverter shortUrlRequestConverter;
	private final ShortUrlService service;
	
	public ShortUrlController(ShortUrlDtoConverter shortUrlDtoConverter, ShortUrlRequestConverter shortUrlRequestConverter,ShortUrlService service) {
		this.shortUrlDtoConverter = shortUrlDtoConverter;
		this.shortUrlRequestConverter = shortUrlRequestConverter;
		this.service = service;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ShortUrlDto>> getAllUrls(){
		return new ResponseEntity<List<ShortUrlDto>>(
				shortUrlDtoConverter.convertToDto(service.getAllShortUrl()),HttpStatus.OK
		);
	}
	
	@GetMapping("/show/{code}")
	public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String code){
		return new ResponseEntity<ShortUrlDto>(
				shortUrlDtoConverter.convertToDto(service.getUrlByCode(code)),HttpStatus.OK
		);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<ShortUrlDto> redirect(@Valid @NotEmpty @PathVariable String code) throws URISyntaxException{
		

		ShortUrl shortUrl = service.getUrlByCode(code);
		    
		URI url = new URI("http://" + shortUrl.getUrl()); 
		    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(url);
		    
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody ShortUrlRequest shortUrlRequest){
		ShortUrl shortUrl = shortUrlRequestConverter.convertToEntity(shortUrlRequest);
		
		return new ResponseEntity<ShortUrlDto>(shortUrlDtoConverter.convertToDto(service.create(shortUrl)),HttpStatus.CREATED);
	}
	
	
	
	
	
}
