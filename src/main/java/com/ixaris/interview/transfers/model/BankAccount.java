package com.ixaris.interview.transfers.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class BankAccount {

    private Long accountNumber;
    private BigDecimal amount = BigDecimal.ZERO;

    public BankAccount() {

    }

    public BankAccount(final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BankAccount account = (BankAccount) o;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
