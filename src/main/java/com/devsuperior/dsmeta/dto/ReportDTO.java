package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

import java.time.LocalDate;

public class ReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String SellerName;

    public ReportDTO(){}
    public ReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        SellerName = sellerName;
    }

    public ReportDTO(Sale entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.amount = entity.getAmount();
        SellerName = entity.getSeller().getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return SellerName;
    }
}
