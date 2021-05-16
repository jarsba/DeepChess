package datastructureproject.datastructures;

import org.junit.*;

import static org.junit.Assert.*;

public class RandomImpTest {

    RandomImp random;

    public RandomImpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.random = new RandomImp();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void randomReturnsInteger() {
        try {
            int a = (int) this.random.nextInt();
        } catch (ClassCastException e) {
            Assert.fail();
        }
    }

    @Test
    public void randomReturnsLong() {
        try {
            long a = (long) this.random.nextLong();
        } catch (ClassCastException e) {
            Assert.fail();
        }
    }

    @Test
    public void randomReturnsTwoDistinctIntegers() {
        int a = this.random.nextInt();
        int b = this.random.nextInt();
        assertTrue(a != b);
    }

    @Test
    public void randomReturnsTwoDistinctLongs() {
        long a = this.random.nextLong();
        long b = this.random.nextLong();
        assertTrue(a != b);
    }

    @Test
    public void randomReturnsIntBelowUpperBound() {
        int a = this.random.nextInt(10);
        assertTrue(a < 10 && a >= 0);
    }

    @Test
    public void randomReturnsLongBelowUpperBound() {
        long a = this.random.nextLong(100);
        assertTrue(a < 100 && a >= 0);
    }

    @Test
    public void randomReturnsIntBetweenLowerAndUpperBound() {
        int a = this.random.nextInt(5,100);
        assertTrue(a < 100 && a >= 5);
    }

    @Test
    public void randomReturnsLongBetweenLowerAndUpperBound() {
        long a = this.random.nextLong(1000,2000);
        assertTrue(a < 2000 && a >= 1000);
    }

    @Test
    public void randomReturnDistinctValues() {

        int prev = -1;

        for(int i = 0; i < 10; i++) {
            int a = this.random.nextInt();
            if(prev != -1) {
                assertTrue(a != prev);
            }
            prev = a;
        }
    }


}
