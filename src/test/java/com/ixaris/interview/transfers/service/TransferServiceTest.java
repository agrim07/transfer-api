package com.ixaris.interview.transfers.service;

import com.ixaris.interview.transfers.model.TransactionResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.NoSuchFileException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransferService.class)
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Before
    public void setup() {
        //transferService = new TransferService();
    }

    @Test
    public void testValidFile() {
        TransactionResponse response = transferService.processTransfer("transfers.txt");
        assertFalse(response.getBalances().isEmpty());
        assertEquals(Long.parseLong("112233"), response.getFrequentlyUsedBankAccount().longValue());
        assertEquals(Long.parseLong("334455"), response.getHighestBalanceBankAccount().longValue());
    }

    @Test(expected = RuntimeException.class)
    public void testFileNotFound() {
        TransactionResponse response = transferService.processTransfer("noAvailable.txt");
    }
}
