package com.bookstore.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private Double price;
}
