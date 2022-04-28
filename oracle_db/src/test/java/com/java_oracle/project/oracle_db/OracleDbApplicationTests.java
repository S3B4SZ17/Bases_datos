package com.java_oracle.project.oracle_db;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OracleDbApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(OracleDbApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Test 1: Get a JSON list of Staff ...............................")
	void get_Staff_test() throws InterruptedException {
		String command = "curl --location --request GET localhost:8181/staff/allStaff";
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			log.info(process.getInputStream().toString());
			
		} catch (IOException e) {
			log.error(e.toString());
		}

		assertEquals(0,process.waitFor(),"Command completed successfully.");
		
	}

}
