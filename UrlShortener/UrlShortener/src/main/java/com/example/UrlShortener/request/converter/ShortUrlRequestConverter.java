package com.example.UrlShortener.request.converter;

import org.springframework.stereotype.Component;

import com.example.UrlShortener.model.ShortUrl;
import com.example.UrlShortener.request.ShortUrlRequest;

@Component
public class ShortUrlRequestConverter {
	public ShortUrl convertToEntity(ShortUrlRequest shortUrlRequest) {
		return ShortUrl.builder()
				.url(shortUrlRequest.getUrl())
				.code(shortUrlRequest.getCode())
				.build();
	}
}
