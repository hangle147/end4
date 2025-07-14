package com.example.endmd4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên sản phẩm phải từ 5 đến 50 ký tự")
    private String name;

    @Min(value = 100000, message = "Giá sản phẩm phải từ 100.000 trở lên")
    private double price;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @NotNull(message = "Phải chọn loại sản phẩm")
    private ProductType productType;
}

