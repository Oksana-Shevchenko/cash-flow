package com.cash.cashflow.validator.impl;

import com.cash.cashflow.exception.ApiException;
import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.validator.BillRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly;
import static org.springframework.util.CollectionUtils.isEmpty;


@Component
public class BillRequestValidatorImpl implements BillRequestValidator {

	@Override
	public void validateBillRequest(BillRequest request) {
		if (isEmptyOrWhitespaceOnly(request.getDescription())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Description is blank", "description.is.blank");
		}
		if (isEmptyOrWhitespaceOnly(request.getCurrencyCode())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Currency code is blank", "currency.code.is.blank");
		}
		if (isEmptyOrWhitespaceOnly(request.getGroupId())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Group id is blank", "group.id.is.blank");
		}
		if (isEmpty(request.getItems())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Bill items are blank", "bill.items.are.blank");
		}
		if (isEmpty(request.getParticipants())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Participants are blank", "participants.are.blank");
		}
		if (isEmpty(request.getShares())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Shares are blank", "shares.are.blank");
		}
		if (isEmpty(request.getPayers())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Payers are blank", "payers.are.blank");
		}
		if (request.getSharedType() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Shared type is blank", "shared.type.is.blank");
		}
		if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Amount is blank", "shared.type.is.blank");
		}
	}
}
