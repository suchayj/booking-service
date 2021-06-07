package com.bookingservice.bookingservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bookingservice.bookingservice.validator.ContainerSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {

    @NotNull(message = "Please provide Container Size")
    @ContainerSize(message = "Container Size must be Either 20 or 40")
    private Integer containerSize;

    @NotNull(message = "Please provide Container Type")
    private ContainerTypeEnum containerType;

    @NotNull(message = "Please provide Origin")
    @Size(min = 5, max = 20, message = "Minimum 5 Characters and Maximum 20 Characters Allowed")
    private String origin;

    @NotNull(message = "Please provide Destination")
    @Size(min = 5, max = 20, message = "Minimum 5 Characters and Maximum 20 Characters Allowed")
    private String destination;

    @NotNull(message = "Please provide Quantity")
    @Min(value = 1, message = "Minimum Quantity 1 is Allowed")
    @Max(value = 100, message = "Maximum Quantity 100 is Allowed")
    private Integer quantity;

}
