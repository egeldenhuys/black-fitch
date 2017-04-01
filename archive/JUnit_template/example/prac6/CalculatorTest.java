import org.junit.Test;
import org.junit.Before; // for @Before
import static org.junit.Assert.*;
import junit.framework.TestCase;

public class CalculatorTest extends TestCase{

	private Calculator calc;

	@Before
	public void setUp() {
		this.calc = new Calculator();
	}

	@Test
	public void test_good() {
		assertNotNull(calc);
	}

    @Test
    public void test_adding_two_number_return_sum() {
            assertEquals("5 + 5 should be 10", 10, calc.add(5, 5));
			assertEquals("5 + 7 should be 9", 9, calc.add(5,7));

    }

	@Test
	public void test_devide_by_zero_should_work() {
		assertEquals("Device by zero works", 1, calc.divide(1,0));
	}
}
