package com.cash.cashflow.controller;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.projection.BillProjection;
import com.cash.cashflow.util.ProjectionUtils;
import com.cash.cashflow.model.BillRequest;
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
	private ProjectionUtils projectionUtils;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createBill(@RequestBody BillRequest request) {
		final Bill bill = billService.createBill(request);
		return ResponseEntity.ok(projectionUtils.toResource(bill, BillProjection.class));
	}

}
