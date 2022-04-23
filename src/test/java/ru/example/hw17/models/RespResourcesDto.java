package ru.example.hw17.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespResourcesDto{
	@JsonProperty("per_page")
	private int perPage;
	private int total;
	private List<DataItemDto> data;
	private int page;
	@JsonProperty("total_pages")
	private int totalPages;
	private SupportDto support;
}