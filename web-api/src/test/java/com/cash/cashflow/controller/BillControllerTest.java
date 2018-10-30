package com.cash.cashflow.controller;

import com.cash.cashflow.BaseControllerTest;
import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.SharedType;
import com.cash.cashflow.model.BillItemRequest;
import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.model.PayerRequest;
import com.cash.cashflow.model.ShareRequest;
import com.cash.cashflow.repository.BillRepository;
import org.assertj.core.util.Sets;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class BillControllerTest extends BaseControllerTest {

	@Autowired
	private BillRepository billRepository;

	@Test
	public void createShouldReturn200WhenRequestIsValid() {
		BillRequest billRequest = createBillRequest();
		String bill = post("/v1/bills", billRequest);
		try {
			JSONObject object = new JSONObject(bill);
			String id = object.getString("id");
			Bill billResponse = billRepository.findById(id).get();
			assertNotNull(billResponse);
		} catch (JSONException e) {
			fail("Exception: can't get bill id from response body" + bill);
		}
	}

	@Test
	public void createShouldReturn400WhenRequestIsNotValid() {
		BillRequest billRequest = createBillRequest();
		String temp = billRequest.getDescription();
		billRequest.setDescription(null);
		final long count = billRepository.count();
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setDescription(temp);

		temp = billRequest.getCurrencyCode();
		billRequest.setCurrencyCode(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setCurrencyCode(temp);

		temp = billRequest.getGroupId();
		billRequest.setGroupId(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setGroupId(temp);

		List<BillItemRequest> billItemRequest = billRequest.getItems();
		billRequest.setItems(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setItems(billItemRequest);

		Set<String> participants = billRequest.getParticipants();
		billRequest.setParticipants(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setParticipants(participants);

		List<ShareRequest> shares = billRequest.getShares();
		billRequest.setShares(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setShares(shares);

		List<PayerRequest> payers = billRequest.getPayers();
		billRequest.setPayers(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setPayers(payers);

		SharedType sharedType = billRequest.getSharedType();
		billRequest.setSharedType(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setSharedType(sharedType);

		BigDecimal amount = billRequest.getAmount();
		billRequest.setAmount(null);
		post("/v1/bills", billRequest, HttpStatus.BAD_REQUEST.value());
		assertEquals(count, billRepository.count());
		billRequest.setAmount(amount);
	}

	@Test
	public void createShouldReturn404WhenCurrencyIdNotFound() {
		BillRequest billRequest = createBillRequest();
		billRequest.setCurrencyCode("NOT_EXIST");
		final long count = billRepository.count();
		post("/v1/bills", billRequest, HttpStatus.NOT_FOUND.value());
		assertEquals(count, billRepository.count());
	}

	@Test
	public void createShouldReturn404WhenGroupIdNotFound() {
		BillRequest billRequest = createBillRequest();
		billRequest.setGroupId("NOT_EXIST");
		final long count = billRepository.count();
		post("/v1/bills", billRequest, HttpStatus.NOT_FOUND.value());
		assertEquals(count, billRepository.count());
	}

	@Test
	public void createShouldReturn404WhenUserIdNotFound() {
		BillRequest billRequest = createBillRequest();
		Set<String> participants = Sets.newHashSet();
		participants.add("NOT_EXIST");
		billRequest.setParticipants(participants);
		final long count = billRepository.count();
		post("/v1/bills", billRequest, HttpStatus.NOT_FOUND.value());
		assertEquals(count, billRepository.count());
	}
}
