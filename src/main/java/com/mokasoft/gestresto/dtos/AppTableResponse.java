package com.mokasoft.gestresto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppTableResponse {
    private Long tableId;
    @NotBlank(message = "table name is required")
    private String tableNumber;
    @Min(value = 0)
    private int customerNumber;
    private boolean available;
    private RoomResponse roomResponse;
    private Long saleId;
}
