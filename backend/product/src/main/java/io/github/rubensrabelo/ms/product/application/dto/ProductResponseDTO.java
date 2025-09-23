package io.github.rubensrabelo.ms.product.application.dto;

import java.util.Objects;

public class ProductResponseDTO {

    private Long id;
    private String name;
    private double unitPrice;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDTO product = (ProductResponseDTO) o;
        return Double.compare(unitPrice, product.unitPrice) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice);
    }
}
