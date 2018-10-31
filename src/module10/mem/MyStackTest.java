package module10.mem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Inside setUpBeforeClass");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Inside tearDownAfterClass");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Inside setUp");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Inside tearDown");
	}

	@Test
	void test1() {
		System.out.println("Inside test1");
	}
	
	@Test
	void test2() {
		throw new NullPointerException();
	}
	
	@Test
	void test3() {
		fail("Not implemented for test3");
	}

	@Test
	void test4() {
		System.out.println("Inside test4");
	}
}
