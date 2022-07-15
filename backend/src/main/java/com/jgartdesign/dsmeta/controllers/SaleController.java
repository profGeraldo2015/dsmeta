package com.jgartdesign.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jgartdesign.dsmeta.entities.Sale;
import com.jgartdesign.dsmeta.services.SaleService;
import com.jgartdesign.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@Autowired
	private SmsService smsservice;
	
	@GetMapping
	public Page<Sale> findSales(@RequestParam(value="minDate", defaultValue = "") String minDate,@RequestParam(value="maxDate", defaultValue = "") String maxDate, Pageable pageable){
		
		return service.findSales(minDate, maxDate, pageable);
	}
	@GetMapping("/notification")
	public void sendNotify(@RequestParam(value="msg", defaultValue = "Teste") String msg) {
		smsservice.sendSms(msg);
	}
	@GetMapping("{id}/notification")
	public void sendSmsId(@PathVariable Long id) {
		smsservice.sendSmsId(id);
	}
}
