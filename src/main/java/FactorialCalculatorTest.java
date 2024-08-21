import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertEquals(calculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertEquals(calculator.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        FactorialCalculator calculator = new FactorialCalculator();
        calculator.factorial(-1);
    }
}
