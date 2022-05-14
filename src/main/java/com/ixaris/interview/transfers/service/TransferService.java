package com.ixaris.interview.transfers.service;

import com.ixaris.interview.transfers.model.BankAccount;
import com.ixaris.interview.transfers.model.Transaction;
import com.ixaris.interview.transfers.model.TransactionResponse;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransferService {


    public TransactionResponse processTransfer(String fileName) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Path path = FileSystems.getDefault().getPath("src/main/resources/", fileName);
            List<Transaction> transactions = transactionBeanBuilder(path);

            Long mostFrequentlyUsed = transactions.stream().map(x -> x.getSource_acct()).filter(x -> !x.equals(0L))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).
                    entrySet().stream().max((o1, o2)-> o1.getValue().compareTo(o2.getValue())).get().getKey();
            transactionResponse.setFrequentlyUsedBankAccount(mostFrequentlyUsed);

            List<BankAccount> bankAccounts =  extractBankAccounts(transactions);
            transactionResponse.getBalances().addAll(bankAccounts);

            Long highestBalanceAccNo = bankAccounts.stream().max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                    .get().getAccountNumber();
            transactionResponse.setHighestBalanceBankAccount(highestBalanceAccNo);
        } catch (NoSuchFileException ex) {
            throw new RuntimeException("File not found.");
        } catch (IOException ex) {
            throw new RuntimeException("Internal Server Error.");
        }
        return transactionResponse;
    }

    private List<BankAccount> extractBankAccounts(List<Transaction> transactions) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for(Transaction transaction : transactions) {
            if(!transaction.getSource_acct().equals(0L)) {
                BankAccount srcAccount = new BankAccount(transaction.getSource_acct());
                extractAccount(bankAccounts, srcAccount, BigDecimal.ZERO.subtract(transaction.getAmount()));
            }
            BankAccount destAccount = new BankAccount(transaction.getDestination_acct());
            extractAccount(bankAccounts, destAccount, transaction.getAmount());
        }
        return bankAccounts;
    }

    private void extractAccount(List<BankAccount> accounts, BankAccount account, BigDecimal amount) {
        int index = accounts.indexOf(account);
        if(index > -1) {
            BankAccount existAccount = accounts.get(index);
            existAccount.setAmount(existAccount.getAmount().add(amount));
        } else {
            account.setAmount(amount);
            accounts.add(account);
        }

    }

    private List<Transaction> transactionBeanBuilder(Path path) throws IOException {
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(Transaction.class);

        Reader reader = Files.newBufferedReader(path);

        CsvToBean<Transaction> cb = new CsvToBeanBuilder(reader)
                .withType(Transaction.class).withSkipLines(3)
                .withMappingStrategy(ms)
                .build();

        List<Transaction> transactions = cb.parse();
        reader.close();
        return transactions;
    }
}
