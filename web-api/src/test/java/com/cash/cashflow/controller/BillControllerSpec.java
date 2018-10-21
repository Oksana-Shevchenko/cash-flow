package com.cash.cashflow.controller;

import com.cash.cashflow.domain.SharedType;
import com.cash.cashflow.model.BillItemRequest;
import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.model.PayerRequest;
import com.cash.cashflow.model.ShareRequest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class BillControllerSpec {

	@LocalServerPort
	private int port;

	private static final String EXISTING_USER_ID = "17437c17-d9dd-425d-b3c3-827e45dc5f0a";
	private static final String EXISTING_GROUP_ID = "ae509b34-6171-4ece-9f7b-23fd6bcf5dbb";
	private static final String EXISTING_CATEGORY_ID = "fa0df06f-ff8a-4ee3-a022-bb36b52d7afb";

	@Test
	public void createShouldReturn200WhenRequestIsValid() {
		PayerRequest payerRequest = PayerRequest
				.builder()
				.amount(BigDecimal.valueOf(50.0))
				.userId(EXISTING_USER_ID)
				.build();

		ShareRequest shareRequest = ShareRequest
				.builder()
				.amount(BigDecimal.valueOf(50.0))
				.share(BigDecimal.valueOf(1))
				.userId(EXISTING_USER_ID)
				.build();
		BillItemRequest billItemRequest = BillItemRequest
				.builder()
				.amount(BigDecimal.valueOf(50.0))
				.categoryId(EXISTING_CATEGORY_ID)
				.title("Player")
				.build();
		BillRequest billRequest = BillRequest
				.builder()
				.description("test")
				.amount(BigDecimal.valueOf(50.00))
				.currencyCode("USD")
				.groupId(EXISTING_GROUP_ID)
				.participants(asSet(EXISTING_USER_ID))
				.sharedType(SharedType.EQUAL)
				.payers(Lists.newArrayList(payerRequest))
				.shares(Lists.newArrayList(shareRequest))
				.items(Lists.newArrayList(billItemRequest))
				.build();

		RestTemplate template = new RestTemplate();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		final HttpEntity<BillRequest> entity = new HttpEntity<>(billRequest, headers);

		ResponseEntity<String> bill = template.exchange("http://localhost:" + port + "/v1/bills", HttpMethod.POST, entity, String.class, Collections.emptyMap());
		assertEquals(HttpStatus.OK, bill.getStatusCode());
	}
}
