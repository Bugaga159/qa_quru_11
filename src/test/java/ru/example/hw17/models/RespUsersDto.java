package ru.example.hw17.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespUsersDto {
	private String id;
	private String name;
	private String job;
	private String createdAt;
}