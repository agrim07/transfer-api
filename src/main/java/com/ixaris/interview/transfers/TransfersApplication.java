package com.ixaris.interview.transfers;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <p>
 * 	A Command-line application to parse and process a transfers file and provide the business requirements, namely:
 * 	<ul>
 * 	    <li>1. Print the final balances on all bank accounts</li>
 * 	    <li>2. Print the bank account with the highest balance</li>
 * 	    <li>3. Print the most frequently used source bank account</li>
 * 	</ul>
 * </p>
 */
@SpringBootApplication
@Log4j2
public class TransfersApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransfersApplication.class, args);
		log.info("Transfer Application has started...");
	}
}
