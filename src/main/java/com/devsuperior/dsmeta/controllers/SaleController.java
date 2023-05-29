package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<List<ReportDTO>> getReport(ReportDTO dto,
										  @RequestParam(value = "minDate", defaultValue = "") String minDate,
										  @RequestParam(value = "maxDate", defaultValue = "") String maxDate, @RequestParam(value = "name", defaultValue = "") String name){
		LocalDate dateMax, dateMin;
		if (maxDate == null || maxDate.equals("")){
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else{
			dateMax = LocalDate.parse(maxDate);
		}
		if(minDate == null || minDate.equals("")){
			dateMin = dateMax.minusYears(1L);
		}
		else{
			dateMin = LocalDate.parse(minDate);
		}
		List<ReportDTO> res = service.report(dto, dateMin, dateMax, name);
		return ResponseEntity.ok(res);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(SummaryDTO dto, @RequestParam(value = "minDate", defaultValue = "") String minDate,
												 @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
												 @RequestParam(value = "name", defaultValue = "") String name) {
		LocalDate dateMin, dateMax;
		if(maxDate == null || maxDate.equals("")){
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else{
			dateMax = LocalDate.parse(maxDate);
		}
		if (minDate == null || minDate.equals("")){
			dateMin = dateMax.minusYears(1);
		}
		else {
			dateMin = LocalDate.parse(minDate);
		}
		List<SummaryDTO> res = service.getSummary(dateMin, dateMax, name);
		return ResponseEntity.ok(res);
	}
}
