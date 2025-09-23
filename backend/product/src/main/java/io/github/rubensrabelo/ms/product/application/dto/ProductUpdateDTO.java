package io.github.rubensrabelo.ms.product.application.dto;

public class ProductUpdateDTO {

    private String name;
    private double unitPrice;

    public ProductUpdateDTO() {
    }

    public ProductUpdateDTO(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
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
}
