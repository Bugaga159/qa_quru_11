package ru.example.hw17.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespSingleResourceDto {
    private DataItemDto data;
    private SupportDto support;
}
