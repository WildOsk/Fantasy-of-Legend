package com.Petin.FantasyOfLegend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Petin.FantasyOfLegend.Rest.LigaImpl;

@SpringBootTest
class FantasyOfLegendApplicationTests {

	@Test
	void contextLoads() {
		LigaImpl liga = new LigaImpl();
		
		String r = liga.codigoRandom();
		
		assertTrue(r!=null);
	}

}
