package com.example.UrlShortener.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data 
@Entity
public class ShortUrlDto {
	private long id;
	private String url;
	private String code;
}
