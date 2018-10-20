package com.cash.cashflow.controller;

import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.repository.GroupRepository;
import com.cash.cashflow.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bills")
public class BillController {

	@Autowired
	private BillService billService;

	@Autowired
	private GroupRepository repository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createBill(@RequestBody BillRequest request) {
		return ResponseEntity.ok(billService.createBill(request));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllGroups() {
		repository.findAll();
		return ResponseEntity.ok("Success!!!");
	}
}
