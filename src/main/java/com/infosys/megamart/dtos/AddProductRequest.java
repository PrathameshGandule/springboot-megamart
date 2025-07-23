package com.infosys.megamart.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {

    @NotBlank(message = "productName must not be blank")
    private String productName;

    @NotBlank(message = "description must not be blank")
    private String description;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "price must be positive")
    private Double price;

    @NotNull(message = "discount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "discount must be positive")
    private Double discount;

    @NotNull(message = "deliveryCharges is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "deliveryCharges must be positive")
    private Double deliveryCharges;

    @NotNull(message = "avgRating is required")
    @Min(value = 0, message = "avgRating must be positive")
    @Max(value = 5, message = "avgRating must be 5 or less")
    private Integer avgRating;

    @NotBlank(message = "sellerName must not be blank")
    private String sellerName;
}

