package com.ixaris.interview.transfers.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionResponse {

    private List<BankAccount> balances = new ArrayList<>();
    private Long highestBalanceBankAccount;
    private Long frequentlyUsedBankAccount;
}
