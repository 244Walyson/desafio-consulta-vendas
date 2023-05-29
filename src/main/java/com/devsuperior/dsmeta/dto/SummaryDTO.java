package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;

import java.util.List;

public class SummaryDTO {

    private String sellerName;
    private Double total;

    public SummaryDTO(){}
    public SummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SummaryDTO(SummaryMinProjection proj) {
        sellerName = proj.getName();
        total = proj.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
