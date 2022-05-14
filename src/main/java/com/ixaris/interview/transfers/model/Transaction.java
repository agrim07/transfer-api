package com.ixaris.interview.transfers.model;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {


        //SOURCE_ACCT, DESTINATION_ACCT, AMOUNT, DATE, TRANSFERID

        @CsvBindByPosition(position = 0)
        private Long source_acct;

        @CsvBindByPosition(position = 1)
        private Long destination_acct;

        @CsvBindByPosition(position = 2)
        private BigDecimal amount;

        @CsvBindByPosition(position = 3)
        private String date;

        @CsvBindByPosition(position = 4)
        private String transferid;


}


