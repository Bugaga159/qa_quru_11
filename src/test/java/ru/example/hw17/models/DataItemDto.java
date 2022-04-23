package ru.example.hw17.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItemDto {
	private String color;
	private int year;
	private String name;
	private int id;
	@JsonProperty("pantone_value")
	private String pantoneValue;
}