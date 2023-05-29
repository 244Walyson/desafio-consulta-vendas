package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SummaryDTO {

    private String sellerName;
    private Double total;

    public SummaryDTO(){}
    public SummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SummaryDTO(Seller entity) {
        this.sellerName = entity.getName();
        this.total = entity.getSales().stream().mapToDouble(Sale::getAmount).sum();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
