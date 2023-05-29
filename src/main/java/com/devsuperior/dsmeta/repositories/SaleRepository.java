package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj.date BETWEEN :minDate AND :maxDate AND UPPER(obj.seller.name) LIKE UPPER((CONCAT('%', :name, '%')))")
    List<Sale> report(LocalDate minDate, LocalDate maxDate, String name);

//    @Query("SELECT NEW com.devsuperior.dsmeta.dto.SummaryDTO(s.name, SUM(sale.amount)) " +
//            "FROM Seller s " +
//            "JOIN s.sales sale " +
//            "WHERE sale.date BETWEEN :dateMin AND :dateMax " +
//            "AND UPPER(s.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
//            "GROUP BY s.name")

    //@Query(nativeQuery = true, value = "SELECT name, SUM(tb_sales.amount) FROM tb_Seller INNER JOIN tb_sales ON tb_sales.id = tb_seller.id WHERE tb_sales.date BETWEEN :dateMin AND :dateMax AND UPPERR(tb_seller.name) LIKE UPPER(CONCAT(('%', name, '%')) GROUP BY tb_seller.name")
    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) AS total " +
            "FROM tb_seller " +
            "INNER JOIN tb_sales ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :dateMin AND :dateMax " +
            "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "GROUP BY tb_seller.name")
    List<SummaryMinProjection> getSummary(LocalDate dateMin, LocalDate dateMax, String name);


}
