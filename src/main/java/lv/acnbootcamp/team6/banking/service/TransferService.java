package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.dto.TransferRequest;

import java.util.List;

public interface TransferService {

    List<TransactionResponse> transfer(TransferRequest request);

    List<TransactionResponse> getTransactionsByAccount(Long accountId);
}
