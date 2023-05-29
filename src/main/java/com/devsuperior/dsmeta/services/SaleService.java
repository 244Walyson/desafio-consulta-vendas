package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<ReportDTO> report(ReportDTO dto, LocalDate minDate, LocalDate maxDate, String name) {
		List<Sale> result = repository.report(minDate, maxDate, name);
		return result.stream().map(x -> new ReportDTO(x)).toList();
	}

	public List<SummaryDTO> getSummary(LocalDate dateMin, LocalDate dateMax, String name) {
		List<SummaryMinProjection> proj = repository.getSummary(dateMin, dateMax, name);
		return proj.stream().map(SummaryDTO::new).toList();
	}
}
