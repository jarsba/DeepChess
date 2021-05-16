package datastructureproject.datastructures;

import org.junit.*;

import static org.junit.Assert.*;

public class HashMapImpTest {

    HashMapImp<Integer, String> map;

    public HashMapImpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.map = new HashMapImp<>();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void mapIsInitializedCorrectly() {
        assertTrue(this.map.isEmpty());
    }

    @Test
    public void mapDoesntContainItem() {
        this.map.put(2, "two");
        assertNull(this.map.get(3));
    }

    @Test
    public void mapContainsItem() {
        this.map.put(2, "two");
        assertEquals("two", this.map.get(2));
    }

    @Test
    public void mapExtendsWhenFull() {
        HashMapImp<Integer, String> map = new HashMapImp<>(5);
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");

        assertEquals(6, map.size());

    }

    @Test
    public void mapRemovesItem() {
        this.map.put(2, "two");
        this.map.put(3, "three");
        this.map.put(4, "four");

        assertEquals(3, this.map.size());
        assertEquals("three", this.map.get(3));

        this.map.remove(3);

        assertEquals(2, this.map.size());
        assertNull(this.map.get(3));
    }

    @Test
    public void mapSizeZeroAfterClear() {
        this.map.put(1, "one");
        this.map.put(2, "two");
        this.map.put(3, "three");
        this.map.put(4, "four");
        this.map.put(5, "five");
        this.map.put(6, "six");

        assertEquals(6, map.size());

        this.map.clear();

        assertEquals(0, map.size());

    }
}
