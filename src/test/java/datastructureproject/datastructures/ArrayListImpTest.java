package datastructureproject.datastructures;

import datastructureproject.board.Board;
import org.junit.*;

import static org.junit.Assert.*;

public class ArrayListImpTest {

    ArrayListImp<String> list;

    public ArrayListImpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.list = new ArrayListImp<>();
    }

    @After
    public void tearDown() {
        this.list = null;
    }

    @Test
    public void listIsInitializedCorrectly() {
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void listUpdatesAfterAdd() {
        this.list.add("first");
        assertEquals(1, this.list.size());
        assertEquals("first", this.list.get(0));
    }

    @Test
    public void listContainsItem() {
        this.list.add("first");
        this.list.contains("first");
        assertTrue(this.list.contains("first"));
    }

    @Test
    public void listDoesntContainsItem() {
        this.list.add("first");
        assertFalse(this.list.contains("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listThrowsErrorForBadIndex() {
        this.list.add("first");
        this.list.add("second");
        this.list.add("third");

        this.list.get(4);
    }

    @Test
    public void listExtendsWhenFull() {
        ArrayListImp<String> list = new ArrayListImp<>(5);
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");
        list.add("sixth");

        assertEquals(6, list.size());

    }

    @Test
    public void listEmptyAfterClear() {
        this.list.add("first");
        this.list.add("second");
        this.list.add("third");
        this.list.clear();
        assertEquals(0, this.list.size());
    }

    @Test
    public void listRemovesItem() {
        this.list.add("first");
        this.list.add("second");
        this.list.add("third");
        this.list.remove(1);
        assertEquals(2, this.list.size());
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(1));
    }
}
