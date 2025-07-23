package com.infosys.megamart.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

    @NotNull(message = "productId is required")
    @Min(value = 0, message = "productId must be positive")
    private Integer productId;

    @NotNull(message = "quantity is required")
    @Min(value = 0, message = "quantity must be positive")
    private Integer quantity;
}