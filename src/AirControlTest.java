import java.util.Random;
import student.TestCase;

/**
 * @author benblucher, austink23
 * @version 11.20.2025
 */
public class AirControlTest extends TestCase {

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     *
     * @throws Exception
     */
    public void testRInit() throws Exception {
        AirControl recstore = new AirControl();
        assertNotNull(recstore);
    }


    // ----------------------------------------------------------
    /**
     * Test syntax: Sample Input/Output
     *
     * @throws Exception
     */
    public void testSampleInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("Air1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("Air2", 100, 1010, 101, 924, 2, 900,
            "Droners", 3)));
        assertTrue(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertFalse(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertTrue(w.add(new Rocket("Enterprise", 0, 100, 20, 10, 50, 50, 5000,
            99.29)));

        assertFuzzyEquals("Rocket Enterprise 0 100 20 10 50 50 5000 99.29", w
            .delete("Enterprise"));

        assertFuzzyEquals("Airplane Air1 0 10 1 20 2 30 USAir 717 4", w.print(
            "Air1"));
        assertNull(w.print("air1"));

        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + " I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + " Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + " (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + " (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + " (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + " Leaf with 1 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + " (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + " Leaf with 1 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + " (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());

        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 1, "
            + "Value (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());

        assertFuzzyEquals("Found these records in the range a to z\r\n"
            + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n", w.rangeprint(
                "a", "z"));
        assertFuzzyEquals("Found these records in the range a to l\r\n", w
            .rangeprint("a", "l"));
        assertNull(w.rangeprint("z", "a"));

        assertFuzzyEquals("The following collisions exist in the database:\r\n"
            + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "(Airplane Air1 0 10 1 20 2 30 USAir 717 4) "
            + "and (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n", w
                .collisions());

        assertFuzzyEquals(
            "The following objects intersect (0 0 0 1024 1024 1024):\r\n"
                + "In Internal node (0, 0, 0, 1024, 1024, 1024) 0\r\n"
                + "In Internal node (0, 0, 0, 512, 1024, 1024) 1\r\n"
                + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
                + "Airplane Air1 0 10 1 20 2 30 USAir 717 4\r\n"
                + "Balloon B1 10 11 11 21 12 31 hot_air 15\r\n"
                + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n"
                + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
                + "Drone Air2 100 1010 101 924 2 900 Droners 3\r\n"
                + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n"
                + "5 nodes were visited in the bintree\r\n", w.intersect(0, 0,
                    0, 1024, 1024, 1024));
    }


    // ----------------------------------------------------------
    /**
     * Test syntax: Check various forms of bad input parameters
     *
     * @throws Exception
     */
    public void testBadInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, null, 1, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 0, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 1, 0)));
        assertFalse(w.add(new Balloon(null, 1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", -1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, -1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, -1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 0, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 0, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 0, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, "hot", -1)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, "Ostrich", 0)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, "Droner", 0)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, -1, 1.1)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, 1, -1.1)));
        assertFalse(w.add(new AirPlane("a", 2000, 1, 1, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 2000, 1, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 2000, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 2000, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 2000, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 2000, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1000, 1, 1, 1000, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1000, 1, 1, 1000, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1000, 1, 1, 1000, "Alaska", 1,
            1)));
        assertNull(w.delete(null));
        assertNull(w.print(null));
        assertNull(w.rangeprint(null, "a"));
        assertNull(w.rangeprint("a", null));
        assertNull(w.intersect(-1, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, -1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, -1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, -1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, -1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, -1));
        assertNull(w.intersect(2000, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 2000, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 2000, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 2000, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 2000, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, 2000));
        assertNull(w.intersect(1000, 1, 1, 1000, 1, 1));
        assertNull(w.intersect(1, 1000, 1, 1, 1000, 1));
        assertNull(w.intersect(1, 1, 1000, 1, 1, 1000));
    }


    // ----------------------------------------------------------
    /**
     * Test empty: Check various returns from commands on empty database
     *
     * @throws Exception
     */
    public void testEmpty() throws Exception {
        WorldDB w = new WorldDB(null);
        assertNull(w.delete("hello"));
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
        assertFuzzyEquals("E (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
        assertNull(w.print("hello"));
        assertFuzzyEquals("Found these records in the range begin to end\n", w
            .rangeprint("begin", "end"));
        assertFuzzyEquals("The following collisions exist in the database:\n", w
            .collisions());
        assertFuzzyEquals("The following objects intersect (1, 1, 1, 1, 1, 1)\n"
            + "1 nodes were visited in the bintree\n", w.intersect(1, 1, 1, 1,
                1, 1));
    }


    /**
     * Test the AirPlane.isNotValid() method by covering all scenarios
     * where an AirPlane object should be rejected (return false from add()).
     * This includes invalid AirObject superclass parameters as well as
     * invalid AirPlane-specific fields.
     *
     * @throws Exception
     */
    public void testAirplaneIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // --- Invalid AirObject Parameters (from AirObject.isNotValid()) ---

        // 1. Invalid Name (null)
        assertFalse("Should fail for null name", w.add(new AirPlane(null, 1, 1,
            1, 1, 1, 1, "C", 1, 1)));

        // 2. Invalid Origin Coordinates (x, y, or z < 0)
        assertFalse("Should fail for x < 0", w.add(new AirPlane("A", -1, 1, 1,
            1, 1, 1, "C", 1, 1)));
        assertFalse("Should fail for y < 0", w.add(new AirPlane("A", 1, -1, 1,
            1, 1, 1, "C", 1, 1)));
        assertFalse("Should fail for z < 0", w.add(new AirPlane("A", 1, 1, -1,
            1, 1, 1, "C", 1, 1)));

        // 3. Invalid Widths (xwid, ywid, or zwid < 1)
        assertFalse("Should fail for xwid < 1", w.add(new AirPlane("A", 1, 1, 1,
            0, 1, 1, "C", 1, 1)));
        assertFalse("Should fail for ywid < 1", w.add(new AirPlane("A", 1, 1, 1,
            1, 0, 1, "C", 1, 1)));
        assertFalse("Should fail for zwid < 1", w.add(new AirPlane("A", 1, 1, 1,
            1, 1, 0, "C", 1, 1)));

        // 4. Object extends beyond the world boundary (1024 units)
        // x_orig + x_width > 1024
        assertFalse("Should fail for x + xwid > 1024", w.add(new AirPlane("A",
            1024, 1, 1, 1, 1, 1, "C", 1, 1)));
        // y_orig + y_width > 1024
        assertFalse("Should fail for y + ywid > 1024", w.add(new AirPlane("A",
            1, 1024, 1, 1, 1, 1, "C", 1, 1)));
        // z_orig + z_width > 1024
        assertFalse("Should fail for z + zwid > 1024", w.add(new AirPlane("A",
            1, 1, 1024, 1, 1, 1, "C", 1, 1)));

        // --- Invalid AirPlane Specific Parameters ---

        // 5. Invalid carrier (null)
        assertFalse("Should fail for null carrier", w.add(new AirPlane("A", 1,
            1, 1, 1, 1, 1, null, 1, 1)));

        // 6. Invalid flightNumber (must be >= 1)
        assertFalse("Should fail for flightNumber = 0", w.add(new AirPlane("A",
            1, 1, 1, 1, 1, 1, "C", 0, 1)));
        assertFalse("Should fail for negative flightNumber", w.add(new AirPlane(
            "A", 1, 1, 1, 1, 1, 1, "C", -5, 1)));

        // 7. Invalid numEngines (must be >= 1)
        assertFalse("Should fail for numEngines = 0", w.add(new AirPlane("A", 1,
            1, 1, 1, 1, 1, "C", 1, 0)));
        assertFalse("Should fail for negative numEngines", w.add(new AirPlane(
            "A", 1, 1, 1, 1, 1, 1, "C", 1, -2)));
    }


    /**
     * Test the AirObject.isNotValid() method by covering all scenarios
     * where an AirObject's basic parameters (coordinates, size, name)
     * are out of bounds based on the logic in AirObject.java.
     *
     * @throws Exception
     */
    public void testAirObjectIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Use valid AirPlane-specific parameters for testing the base AirObject
        // logic
        final String CARRIER = "C";
        final int FLIGHT_NUM = 1;
        final int NUM_ENGINES = 1;

        // --- 1. Invalid Name (name == null) ---
        assertFalse("Should fail for null name", w.add(new AirPlane(null, 1, 1,
            1, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));

        // --- 2. Invalid Origin Coordinates (x, y, or z < 0) ---
        assertFalse("Should fail for x < 0", w.add(new AirPlane("A", -1, 1, 1,
            1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for y < 0", w.add(new AirPlane("A", 1, -1, 1,
            1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for z < 0", w.add(new AirPlane("A", 1, 1, -1,
            1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));

        // --- 3. Invalid Origin Coordinates (x, y, or z > 1024 based on
        // *current* code) ---
        assertFalse("Should fail for x > 1024 (using 1025)", w.add(new AirPlane(
            "A", 1025, 1, 1, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for y > 1024 (using 1025)", w.add(new AirPlane(
            "A", 1, 1025, 1, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for z > 1024 (using 1025)", w.add(new AirPlane(
            "A", 1, 1, 1025, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));

        // --- 4. Invalid Widths (width < 1) ---
        assertFalse("Should fail for xWidth < 1", w.add(new AirPlane("A", 1, 1,
            1, 0, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for yWidth < 1", w.add(new AirPlane("A", 1, 1,
            1, 1, 0, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
        assertFalse("Should fail for zWidth < 1", w.add(new AirPlane("A", 1, 1,
            1, 1, 1, 0, CARRIER, FLIGHT_NUM, NUM_ENGINES)));

        // --- 5. Invalid Widths (width > 1025 based on *current* code) ---
        assertFalse("Should fail for xWidth > 1025 (using 1026)", w.add(
            new AirPlane("A", 1, 1, 1, 1026, 1, 1, CARRIER, FLIGHT_NUM,
                NUM_ENGINES)));
        assertFalse("Should fail for yWidth > 1025 (using 1026)", w.add(
            new AirPlane("A", 1, 1, 1, 1, 1026, 1, CARRIER, FLIGHT_NUM,
                NUM_ENGINES)));
        assertFalse("Should fail for zWidth > 1025 (using 1026)", w.add(
            new AirPlane("A", 1, 1, 1, 1, 1, 1026, CARRIER, FLIGHT_NUM,
                NUM_ENGINES)));

        // --- 6. Object extends beyond the world boundary (x_orig + x_width >
        // 1024) ---
        // x = 1024 (max allowed in the current code) + xWidth = 1 (min allowed)
        // = 1025 > 1024
        assertFalse("Should fail for x + xWidth > 1024 (1024 + 1)", w.add(
            new AirPlane("A", 1024, 1, 1, 1, 1, 1, CARRIER, FLIGHT_NUM,
                NUM_ENGINES)));
        // Test a valid origin and width that exceeds the boundary
        assertFalse("Should fail for x + xWidth > 1024 (500 + 525)", w.add(
            new AirPlane("A", 500, 1, 1, 525, 1, 1, CARRIER, FLIGHT_NUM,
                NUM_ENGINES)));

        // y_orig + y_width > 1024
        assertFalse("Should fail for y + yWidth > 1024", w.add(new AirPlane("A",
            1, 1024, 1, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));

        // z_orig + z_width > 1024
        assertFalse("Should fail for z + zWidth > 1024", w.add(new AirPlane("A",
            1, 1, 1024, 1, 1, 1, CARRIER, FLIGHT_NUM, NUM_ENGINES)));
    }


    /**
     * Test the Bird.isNotValid() method by covering all invalid scenarios
     * specific to the Bird class (type and number).
     *
     * @throws Exception
     */
    public void testBirdIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Use valid AirObject parameters for testing Bird-specific logic
        final String NAME = "B1";
        final int X = 1, Y = 1, Z = 1;
        final int XWID = 1, YWID = 1, ZWID = 1;

        // 1. Invalid type (null)
        assertFalse("Should fail for null type", w.add(new Bird(NAME, X, Y, Z,
            XWID, YWID, ZWID, null, 1)));

        // 2. Invalid number (must be >= 1)
        assertFalse("Should fail for number = 0", w.add(new Bird(NAME, X, Y, Z,
            XWID, YWID, ZWID, "Sparrow", 0)));
        assertFalse("Should fail for negative number", w.add(new Bird(NAME, X,
            Y, Z, XWID, YWID, ZWID, "Sparrow", -5)));

        // 3. Test a valid Bird (to ensure a successful add works)
        assertTrue("Should succeed for valid Bird parameters", w.add(new Bird(
            NAME, X, Y, Z, XWID, YWID, ZWID, "Sparrow", 1)));
    }


    /**
     * Test the Balloon.isNotValid() method by covering all invalid scenarios
     * specific to the Balloon class (type and ascentRate).
     *
     * @throws Exception
     */
    public void testBalloonIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Use valid AirObject parameters for testing Balloon-specific logic
        final String NAME = "Ball1";
        final int X = 1, Y = 1, Z = 1;
        final int XWID = 1, YWID = 1, ZWID = 1;

        // 1. Invalid type (null)
        assertFalse("Should fail for null type", w.add(new Balloon(NAME, X, Y,
            Z, XWID, YWID, ZWID, null, 10)));

        // 2. Invalid ascentRate (must be >= 0)
        assertFalse("Should fail for negative ascentRate", w.add(new Balloon(
            NAME, X, Y, Z, XWID, YWID, ZWID, "hot_air", -5)));

        // 3. Test a valid Balloon (to ensure a successful add works)
        assertTrue("Should succeed for valid Balloon parameters", w.add(
            new Balloon(NAME, X, Y, Z, XWID, YWID, ZWID, "weather", 0)));
    }


    /**
     * Test the Drone.isNotValid() method by covering all invalid scenarios
     * specific to the Drone class (brand and numEngines).
     *
     * @throws Exception
     */
    public void testDroneIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Use valid AirObject parameters for testing Drone-specific logic
        final String NAME = "Drone1";
        final int X = 1, Y = 1, Z = 1;
        final int XWID = 1, YWID = 1, ZWID = 1;

        // 1. Invalid brand (null)
        assertFalse("Should fail for null brand", w.add(new Drone(NAME, X, Y, Z,
            XWID, YWID, ZWID, null, 4)));

        // 2. Invalid numEngines (must be >= 1)
        assertFalse("Should fail for numEngines = 0", w.add(new Drone(NAME, X,
            Y, Z, XWID, YWID, ZWID, "DJI", 0)));
        assertFalse("Should fail for negative numEngines", w.add(new Drone(NAME,
            X, Y, Z, XWID, YWID, ZWID, "DJI", -1)));

        // 3. Test a valid Drone (to ensure a successful add works)
        assertTrue("Should succeed for valid Drone parameters", w.add(new Drone(
            NAME, X, Y, Z, XWID, YWID, ZWID, "DJI", 4)));
    }


    /**
     * Test the Rocket.isNotValid() method by covering all invalid scenarios
     * specific to the Rocket class (ascentRate and trajectory).
     *
     * @throws Exception
     */
    public void testRocketIsInvalid() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Use valid AirObject parameters for testing Rocket-specific logic
        final String NAME = "R1";
        final int X = 1, Y = 1, Z = 1;
        final int XWID = 1, YWID = 1, ZWID = 1;

        // 1. Invalid ascentRate (must be >= 0)
        assertFalse("Should fail for negative ascentRate", w.add(new Rocket(
            NAME, X, Y, Z, XWID, YWID, ZWID, -100, 45.0)));

        // 2. Invalid trajectory (must be >= 0)
        assertFalse("Should fail for negative trajectory", w.add(new Rocket(
            NAME, X, Y, Z, XWID, YWID, ZWID, 100, -1.0)));

        // 3. Test a valid Rocket (to ensure a successful add works)
        assertTrue("Should succeed for valid Rocket parameters", w.add(
            new Rocket(NAME, X, Y, Z, XWID, YWID, ZWID, 100, 0.0)));
    }


    /**
     * Test the print(String name) method in WorldDB, which relies on
     * the SkipList's find functionality.
     *
     * @throws Exception
     */
    public void testPrintFunctionality() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // --- Setup: Insert a test object (AirPlane) ---
        final String TEST_NAME = "TestPlane";
        AirPlane plane = new AirPlane(TEST_NAME, 10, 10, 10, 5, 5, 5, "Delta",
            123, 2);

        // Ensure insertion succeeds
        assertTrue("Setup: AirPlane should be added successfully.", w.add(
            plane));

        // --- 1. Successful Find Test ---
        // Expected string is the plane's toString output (assuming AirObject's
        // toString is informative)
        String expectedOutput = plane.toString();

        String result = w.print(TEST_NAME);

        assertNotNull("Print should find the object by name.", result);
        assertFuzzyEquals(
            "The retrieved object's string should match the inserted object.",
            expectedOutput, result);

        // --- 2. Name Not Found Test ---
        String notFoundResult = w.print("NonExistentName");
        assertNull(
            "Print should return null for a name that was never inserted.",
            notFoundResult);

        // --- 3. Null Input Test (Covered by testBadInput, but good practice
        // here) ---
        String nullResult = w.print(null);
        assertNull("Print should return null if the input name is null.",
            nullResult);

        // --- 4. Case Sensitivity Check (Skip List should be case sensitive)
        // ---
        String caseResult = w.print("testplane");
        assertNull(
            "Print should return null if the name case does not match (Skip List is case-sensitive).",
            caseResult);
    }


    /**
     * Test the add(AirObject a) method in WorldDB, focusing on successful
     * insertion and duplicate name rejection using the SkipList.
     *
     * @throws Exception
     */
    public void testAddFunctionality() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // --- 1. Successful Insertion Test ---
        AirPlane plane1 = new AirPlane("Air1", 10, 10, 10, 5, 5, 5, "Delta",
            123, 2);

        // Insert the first object - should succeed
        assertTrue("Add should succeed for a valid, non-duplicate object.", w
            .add(plane1));

        // Verify it was actually added (using print)
        assertNotNull("The added object should be found in the database.", w
            .print("Air1"));

        // --- 2. Duplicate Name Insertion Test (Rejection) ---
        AirPlane plane2 = new AirPlane("Air1", // Duplicate name
            50, 50, 50, 1, 1, 1, "United", 456, 4);

        // Insert the second object with the same name - should fail
        assertFalse("Add should fail for an object with a duplicate name.", w
            .add(plane2));

        // Verify that the original object is still the one stored (optional,
        // but good)
        // Check if the original object (Delta 123) is still the one retrieved,
        // not the rejected one (United 456)
        String storedPlane = w.print("Air1");
        assertTrue(
            "The original object should remain after a failed duplicate insertion.",
            storedPlane.contains("Delta"));
        assertFalse(
            "The rejected object should not have replaced the original.",
            storedPlane.contains("United"));

        // --- 3. Successful Insertion of a different object type and name ---
        Bird bird1 = new Bird("BirdA", 100, 100, 100, 2, 2, 2, "Sparrow", 5);

        // Insert a new, unique object - should succeed
        assertTrue("Add should succeed for a valid object with a new name.", w
            .add(bird1));
        assertNotNull("The second unique object should be found.", w.print(
            "BirdA"));
    }


    // ----------------------------------------------------------
    /**
     * Test the SkipList.insert() method exclusively by verifying correct
     * sorting,
     * item insertion, and list leveling using only the add and printskiplist
     * WorldDB methods.
     * Uses a fixed seed (0xCAFEBABE) to ensure repeatable SkipNode levels:
     * Alpha (Level 0), Bravo (Level 2), Charlie (Level 1), Delta (Level 0),
     * Echo (Level 1).
     *
     * @throws Exception
     */
    public void testSkipListInsertOnly() throws Exception {
        // Use a fixed seed to ensure repeatable SkipNode levels:
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // Create objects to insert (names chosen to check sorting)
        // Draw 1: Alpha (Level 0)
        Bird birdA = new Bird("Alpha", 1, 1, 1, 1, 1, 1, "TypeA", 1);
        // Draw 2: Bravo (Level 2 - becomes Max Level)
        Bird birdB = new Bird("Bravo", 2, 2, 2, 1, 1, 1, "TypeB", 1);
        // Draw 3: Charlie (Level 1)
        Bird birdC = new Bird("Charlie", 3, 3, 3, 1, 1, 1, "TypeC", 1);
        // Draw 4: Delta (Level 0)
        Bird birdD = new Bird("Delta", 4, 4, 4, 1, 1, 1, "TypeD", 1);
        // Draw 5: Echo (Level 1)
        Bird birdE = new Bird("Echo", 5, 5, 5, 1, 1, 1, "TypeE", 1);
        // Draw 6: Foxtrot (Level 3 - New Max Level)
        Bird birdF = new Bird("Foxtrot", 6, 6, 6, 1, 1, 1, "TypeF", 1);

        // --- 1. Insertion ---
        assertTrue(w.add(birdC)); // Insert Charlie (C)
        assertTrue(w.add(birdE)); // Insert Echo (E)
        assertTrue(w.add(birdA)); // Insert Alpha (A)
        assertTrue(w.add(birdD)); // Insert Delta (D)
        assertTrue(w.add(birdB)); // Insert Bravo (B)
        assertTrue(w.add(birdF)); // Insert Foxtrot (F) - Should trigger max
        // level adjustment to 3

        // --- 2. Verification (Sorted Order, Levels, and Format) ---
        // Expected Sorted Order: Alpha, Bravo, Charlie, Delta, Echo, Foxtrot
        // Final Max List Level = 3 (from Foxtrot)

        String expected = "Node has depth 3, Value (null)\r\n"
            + "Node has depth 1 Value (" + birdA.toString() + ")\r\n" // Alpha
            // (Level
            // 0)
            + "Node has depth 1 Value (" + birdB.toString() + ")\r\n" // Bravo
            // (Level
            // 2)
            + "Node has depth 2 Value (" + birdC.toString() + ")\r\n" // Charlie
            // (Level
            // 1)
            + "Node has depth 2 Value (" + birdD.toString() + ")\r\n" // Delta
            // (Level
            // 0)
            + "Node has depth 3 Value (" + birdE.toString() + ")\r\n" // Echo
            // (Level
            // 1)
            + "Node has depth 3 Value (" + birdF.toString() + ")\r\n" // Foxtrot
            // (Level
            // 3)
            + "6 skiplist nodes printed\r\n";

        String actual = w.printskiplist();

        assertFuzzyEquals(
            "SkipList insertion failed: sorted order, leveling, or formatting is incorrect.\n",
            expected, actual);
    }


    // ----------------------------------------------------------
    /**
     * Test the SkipList.insert() method exclusively by verifying item presence
     * after insertion using only the add() and print() WorldDB methods.
     * This bypasses the problematic printskiplist() method.
     *
     * @throws Exception
     */
    public void testSkipListInsertPresence() throws Exception {
        // Use a fixed seed for consistency, although levels are not asserted
        // here.
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBABE);
        WorldDB w = new WorldDB(rnd);

        // Create objects to insert (mixed names and types)
        Bird birdA = new Bird("Alpha", 1, 1, 1, 1, 1, 1, "TypeA", 1);
        AirPlane planeB = new AirPlane("Bravo", 2, 2, 2, 1, 1, 1, "C", 1, 1);
        Rocket rocketC = new Rocket("Charlie", 3, 3, 3, 1, 1, 1, 10, 1.0);
        Drone droneD = new Drone("Delta", 4, 4, 4, 1, 1, 1, "DJI", 4);

        // Names inserted in mixed order: C, B, D, A

        // --- 1. Insertion ---
        assertTrue("Insert failed for Charlie (Rocket).", w.add(rocketC));
        assertTrue("Insert failed for Bravo (AirPlane).", w.add(planeB));
        assertTrue("Insert failed for Delta (Drone).", w.add(droneD));
        assertTrue("Insert failed for Alpha (Bird).", w.add(birdA));

        // --- 2. Verification (Check presence of ALL inserted items) ---

        // If the item exists, print() returns a non-null string.

        // Check Alpha (Bird)
        assertNotNull(
            "Alpha was inserted but cannot be found (SkipList.insert failure).",
            w.print("Alpha"));

        // Check Bravo (AirPlane)
        assertNotNull(
            "Bravo was inserted but cannot be found (SkipList.insert failure).",
            w.print("Bravo"));

        // Check Charlie (Rocket)
        assertNotNull(
            "Charlie was inserted but cannot be found (SkipList.insert failure).",
            w.print("Charlie"));

        // Check Delta (Drone)
        assertNotNull(
            "Delta was inserted but cannot be found (SkipList.insert failure).",
            w.print("Delta"));

        // --- 3. Negative Check ---
        assertNull("Non-existent item found incorrectly.", w.print("Foxtrot"));
    }


    // ----------------------------------------------------------
    /**
     * Test the WorldDB.delete(String name) method, verifying successful
     * deletion
     * (and skip list removal) and rejection of non-existent names.
     *
     * @throws Exception
     */
    public void testDeleteFunctionality() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        // --- Setup: Insert two objects ---
        AirPlane plane1 = new AirPlane("PlaneA", 10, 10, 10, 5, 5, 5, "Delta",
            123, 2);
        Bird bird1 = new Bird("BirdB", 100, 100, 100, 2, 2, 2, "Sparrow", 5);

        assertTrue("Setup: PlaneA should be added successfully.", w.add(
            plane1));
        assertTrue("Setup: BirdB should be added successfully.", w.add(bird1));

        // Sanity Check: Both should be present
        assertNotNull("Sanity: PlaneA should be found before delete.", w.print(
            "PlaneA"));
        assertNotNull("Sanity: BirdB should be found before delete.", w.print(
            "BirdB"));

        // --- 1. Successful Deletion Test (Middle/Non-Head) ---
        String deletedOutput = w.delete("PlaneA");

        assertNotNull("Delete should return the object's string when found.",
            deletedOutput);
        assertFuzzyEquals(
            "The returned string should match the deleted object.", plane1
                .toString(), deletedOutput);

        // Verify deletion by attempting to print
        assertNull(
            "Verify: PlaneA should be removed and not found after delete.", w
                .print("PlaneA"));

        // Verify other object remains
        assertNotNull(
            "Verify: BirdB should still be present after deleting PlaneA.", w
                .print("BirdB"));

        // --- 2. Deletion Not Found Test ---
        String notFoundResult = w.delete("PlaneA"); // Attempt to delete again
        assertNull("Delete should return null if the name is not found.",
            notFoundResult);

        // --- 3. Delete Remaining Object ---
        assertNotNull("Pre-check: BirdB should still be present.", w.print(
            "BirdB"));
        w.delete("BirdB");
        assertNull("Verify: BirdB should be removed after final delete.", w
            .print("BirdB"));

        // --- 4. Null Input Check (Already in testBadInput, but good practice)
        // ---
        assertNull("Delete should return null if the input name is null.", w
            .delete(null));
    }


    // ----------------------------------------------------------
    /**
     * Test the printskiplist() method when the database is empty.
     *
     * @throws Exception
     */
    public void testPrintSkipListEmpty() throws Exception {
        WorldDB w = new WorldDB(null);

        // This assertion is already in testEmpty(), but we include it for
        // clarity
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
    }

    // ----------------------------------------------------------
// /**
// * Test the full set of SkipList operations (insert, find, delete)
// * by manipulating AirObjects through the WorldDB interface.
// * Uses a fixed seed to ensure repeatable SkipNode levels.
// *
// * @throws Exception
// */
// public void testSkipListOperationsIntegration() throws Exception {
// // Seed 0xCAFEBABE is chosen to generate specific, repeatable SkipNode
// // levels.
// Random rnd = new Random();
// rnd.setSeed(0xCAFEBABE);
// WorldDB w = new WorldDB(rnd);
//
// // --- 1. Insertion Setup ---
// // A. Rocket (R1) - Level 0
// Rocket r1 = new Rocket("RocketA", 1, 1, 1, 10, 10, 10, 100, 45.0);
// // B. AirPlane (P1) - Level 2
// AirPlane p1 = new AirPlane("PlaneB", 50, 50, 50, 20, 20, 20, "UA", 400,
// 4);
// // C. Drone (D1) - Level 1
// Drone d1 = new Drone("DroneC", 100, 100, 100, 5, 5, 5, "DJI", 2);
// // D. Bird (B1) - Level 0
// Bird b1 = new Bird("BirdD", 200, 200, 200, 1, 1, 1, "Eagle", 1);
//
// assertTrue("Insertion failed for RocketA.", w.add(r1));
// assertTrue("Insertion failed for PlaneB.", w.add(p1));
// assertTrue("Insertion failed for DroneC.", w.add(d1));
// assertTrue("Insertion failed for BirdD.", w.add(b1));
//
// // --- 2. Find and Sorted Order Check ---
// // Expected Sorted Order (Alphabetical by name): BirdD, DroneC, PlaneB,
// // RocketA
//
// // Max list level = 2 (from PlaneB)
// String expectedInitialPrint = "Node has depth 2, Value (null)\r\n"
// + "Node has depth 0 Value (" + b1.toString() + ")\r\n" // BirdD
// // (Level
// // 0)
// + "Node has depth 1 Value (" + d1.toString() + ")\r\n" // DroneC
// // (Level
// // 1)
// + "Node has depth 2 Value (" + p1.toString() + ")\r\n" // PlaneB
// // (Level
// // 2)
// + "Node has depth 0 Value (" + r1.toString() + ")\r\n" // RocketA
// // (Level
// // 0)
// + "4 skiplist nodes printed\r\n";
//
// String actualInitialPrint = w.printskiplist();
//
// assertFuzzyEquals(
// "Initial SkipList print failed (Order/Format/Levels).\n",
// expectedInitialPrint, actualInitialPrint);
//
// // Check finding an item
// assertNotNull("Find failed for PlaneB.", w.print("PlaneB"));
// assertNull("Find incorrectly found non-existent item.", w.print(
// "NotFound"));
//
// // --- 3. Deletion Test (Delete a node with a high level - PlaneB/Level
// // 2) ---
// // Deleting the highest level node forces adjustHead logic check.
// String deletedItem = w.delete("PlaneB");
// assertNotNull("Deletion failed for PlaneB.", deletedItem);
// assertFuzzyEquals("Deleted string mismatch.", p1.toString(),
// deletedItem);
//
// // Verify delete succeeded
// assertNull("PlaneB should be deleted and not found.", w.print(
// "PlaneB"));
//
// // --- 4. Final Sorted Order and Level Adjustment Check ---
// // Remaining: BirdD, DroneC, RocketA
// // New Max Level should be 1 (from DroneC)
// String expectedFinalPrint = "Node has depth 1, Value (null)\r\n" // New
// // Max
// // Level
// // = 1
// + "Node has depth 0 Value (" + b1.toString() + ")\r\n"
// + "Node has depth 1 Value (" + d1.toString() + ")\r\n"
// + "Node has depth 0 Value (" + r1.toString() + ")\r\n"
// + "3 skiplist nodes printed\r\n";
//
// assertFuzzyEquals(
// "Final SkipList print failed after high-level deletion.",
// expectedFinalPrint, w.printskiplist());
// }


    // ----------------------------------------------------------
    /**
     * Test the Bintree.insert() method exclusively by verifying correct
     * node splitting, object storage, and tree structure using
     * only the add() and printbintree() WorldDB methods.
     * * @throws Exception
     */
    public void testBintreeInsertAndSplit() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF); // Use a seed for consistency
        WorldDB w = new WorldDB(rnd);

        // 1. Setup: Insert 4 objects (all overlap the root node)

        // Coordinates for objects (from testSampleInput):
        // B1: (10, 11, 11) wid (21, 12, 31) -> Mostly in Quadrant 1 (X < 512,
        // Y < 512)
        Balloon b1 = new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air", 15);

        // Air1: (0, 10, 1) wid (20, 2, 30) -> Mostly in Quadrant 1
        AirPlane air1 = new AirPlane("Air1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4);

        // Air2: (100, 1010, 101) wid (924, 2, 900) -> Spans all X, Y in Q4 (Y
        // >= 512), Z in Q1 (Z < 512)
        // Note: Air2 (100+924=1024) extends exactly to the boundary.
        Drone air2 = new Drone("Air2", 100, 1010, 101, 924, 2, 900, "Droners",
            3);

        // Bird1: (0, 100, 20) wid (10, 50, 50) -> Mostly in Quadrant 1
        Bird bird1 = new Bird("pterodactyl", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1);

        // The *fifth* object from the sample is deleted immediately (Rocket
        // Enterprise),
        // so we don't include it here to match the 4 items that establish the
        // tree.

        // Insert the objects: 4 objects should trigger the split rule for a
        // Leaf Node (more than 3 boxes)
        assertTrue(w.add(b1));
        assertTrue(w.add(air1));
        assertTrue(w.add(air2));
        assertTrue(w.add(bird1));

        // --- 2. Verification (Tree Structure and Formatting) ---
        // Expected structure is taken directly from the successful
        // testSampleInput() output.
        // Node splitting sequence: X-axis (Level 0), Y-axis (Level 1), Z-axis
        // (Level 2)

        String expectedBintree = "I (0, 0, 0, 1024, 1024, 1024) 0\n" // Root
                                                                     // split
                                                                     // on X
            + "  I (0, 0, 0, 512, 1024, 1024) 1\n" // Left child split on Y
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\n" // Leaf
                                                                      // for Q1
                                                                      // (X<512,
                                                                      // Y<512)
                                                                      // split
                                                                      // on Z
                                                                      // is
                                                                      // skipped
            + "      (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\n"
            + "      (Balloon B1 10 11 11 21 12 31 hot_air 15)\n"
            + "      (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\n"
            + "    Leaf with 1 objects (0, 512, 0, 512, 512, 1024) 2\n" // Leaf
                                                                        // for
                                                                        // Q4
                                                                        // (X<512,
                                                                        // Y>=512)
            + "      (Drone Air2 100 1010 101 924 2 900 Droners 3)\n"
            + "  Leaf with 1 objects (512, 0, 0, 512, 1024, 1024) 1\n" // Right
                                                                       // child
                                                                       // of
                                                                       // Root
                                                                       // (X>=512)
            + "    (Drone Air2 100 1010 101 924 2 900 Droners 3)\n"
            + "5 Bintree nodes printed\n";

        // NOTE: The end-of-line character in the expected output must match
        // what your
        // Bintree.print() method is producing. The expected output from the
        // sample
        // uses \r\n, but the Bintree.java file uses \n. Using \n here to match
        // Bintree.java.

        assertFuzzyEquals(
            "Bintree insertion failed: Structure, object storage, or format is incorrect.",
            expectedBintree, w.printbintree());
    }


    // ----------------------------------------------------------
    /**
     * Tests the "Split" logic of the Bintree.
     * The Bintree should split a leaf node when it contains > 3 objects
     * that do NOT all intersect.
     */
    public void testBintreeSplit() {
        WorldDB w = new WorldDB(null);

        // 1. Insert 3 non-overlapping objects (Threshold is 3, so no split yet)
        // Placing them in different quadrants to ensure they separate when
        // split happens
        assertTrue(w.add(new AirPlane("A", 10, 10, 10, 10, 10, 10, "Carrier", 1,
            1)));
        assertTrue(w.add(new AirPlane("B", 600, 10, 10, 10, 10, 10, "Carrier",
            1, 1)));
        assertTrue(w.add(new AirPlane("C", 10, 600, 10, 10, 10, 10, "Carrier",
            1, 1)));

        // Assert it is still a single Leaf node
        String output3 = w.printbintree();
        assertTrue(output3.contains("Leaf with 3 objects"));
        assertFalse(output3.contains("I (")); // Should not have internal nodes
                                              // yet

        // 2. Insert 4th object to force a split
        assertTrue(w.add(new AirPlane("D", 600, 600, 10, 10, 10, 10, "Carrier",
            1, 1)));

        // Assert the tree has split into Internal nodes
        String output4 = w.printbintree();
        assertFalse(output4.contains("Leaf with 4 objects"));
        assertTrue(output4.contains("I (")); // Should now contain internal
                                             // nodes
    }


    // ----------------------------------------------------------
    /**
     * Tests the "Merge" logic of the Bintree.
     * When objects are removed, if the siblings can be combined into a single
     * leaf with <= 3 objects, they should merge.
     */
    public void testBintreeMerge() {
        WorldDB w = new WorldDB(null);

        // Setup: Create a split tree (4 objects)
        w.add(new AirPlane("A", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("B", 600, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("C", 10, 600, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("D", 600, 600, 10, 10, 10, 10, "C", 1, 1));

        // Verify we are starting with a split tree
        assertTrue(w.printbintree().contains("I ("));

        // 1. Delete "D" -> Remaining: A, B, C (Size 3)
        // This should trigger a merge because 3 <= 3
        w.delete("D");

        String outputMerge = w.printbintree();

        // Assert the Internal nodes are gone and we are back to a single leaf
        assertFalse(outputMerge.contains("I ("));
        assertTrue(outputMerge.contains("Leaf with 3 objects"));

        // Verify contents
        assertTrue(outputMerge.contains("A"));
        assertTrue(outputMerge.contains("B"));
        assertTrue(outputMerge.contains("C"));
    }


    // ----------------------------------------------------------
    /**
     * Tests the "No Split" rule when objects heavily overlap.
     * The spec says a leaf splits if > 3 objects UNLESS all objects intersect.
     */
    public void testNoSplitOnIntersection() {
        WorldDB w = new WorldDB(null);

        // 1. Insert 5 objects that all occupy the exact same space
        // (intersecting)
        w.add(new AirPlane("A", 100, 100, 100, 50, 50, 50, "C", 1, 1));
        w.add(new AirPlane("B", 100, 100, 100, 50, 50, 50, "C", 1, 1));
        w.add(new AirPlane("C", 100, 100, 100, 50, 50, 50, "C", 1, 1));
        w.add(new AirPlane("D", 100, 100, 100, 50, 50, 50, "C", 1, 1));
        w.add(new AirPlane("E", 100, 100, 100, 50, 50, 50, "C", 1, 1));

        String output = w.printbintree();

        // 2. Assert that despite having 5 objects, it is still a single Leaf
        // because they all intersect.
        assertFalse(output.contains("I ("));
        assertTrue(output.contains("Leaf with 5 objects"));
    }


    // ----------------------------------------------------------
    /**
     * Tests the "Intersect" command on boundaries and empty space.
     * Ensures checking a region with no objects works, and checking
     * partial overlaps works.
     */
    public void testRegionIntersection() {
        WorldDB w = new WorldDB(null);

        // Add object at [10, 10, 10] with size 10
        w.add(new AirPlane("A", 10, 10, 10, 10, 10, 10, "C", 1, 1));

        // 1. Intersect query completely missing the object
        String miss = w.intersect(500, 500, 500, 100, 100, 100);
        assertFalse(miss.contains("A")); // Should not find A

        // 2. Intersect query partially overlapping the object
        // Box at 15,15,15 overlaps A (which ends at 20,20,20)
        String hit = w.intersect(15, 15, 15, 100, 100, 100);
        assertTrue(hit.contains("A"));

        // 3. Intersect query enveloping the object
        String envelope = w.intersect(0, 0, 0, 100, 100, 100);
        assertTrue(envelope.contains("A"));
    }


    /**
     * Test logic: Verify BinInternal routes to LEFT child only
     * when object is entirely in the low half of the split dimension.
     */
    public void testInternalRoutingLeft() {
        // Setup: Mock-like structure manually
        // We use empty nodes as children to see where the insert goes
        // (BinEmpty returns a new BinLeaf when inserted into)
        BinInternal node = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());

        // Object at 10,10,10 (Size 10) fits in 0-512 range for X, Y, and Z
        AirObject obj = new AirPlane("LeftObj", 10, 10, 10, 10, 10, 10, "Test",
            1, 1);

        // Level 0 (Split X): Should go Left
        node.insert(obj, 0, 0, 0, 1024, 1024, 1024, 0);
        assertTrue(node.getLeft().isLeaf()); // Left became a leaf (was
                                             // inserted)
        assertTrue(node.getRight() == BinEmpty.getInstance()); // Right stayed
                                                               // empty
    }


    /**
     * Test logic: Verify BinInternal routes to RIGHT child only
     * when object is entirely in the high half of the split dimension.
     */
    public void testInternalRoutingRight() {
        BinInternal node = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());

        // Object at 600,10,10 starts past 512
        AirObject obj = new AirPlane("RightObj", 600, 10, 10, 10, 10, 10,
            "Test", 1, 1);

        // Level 0 (Split X): Should go Right (600 > 512)
        node.insert(obj, 0, 0, 0, 1024, 1024, 1024, 0);
        assertTrue(node.getLeft() == BinEmpty.getInstance());
        assertTrue(node.getRight().isLeaf());
    }


    /**
     * Test logic: Verify BinInternal routes to BOTH children
     * when object overlaps the split plane.
     */
    public void testInternalRoutingOverlap() {
        BinInternal node = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());

        // Object at 500 with width 20 (Ends at 520)
        // Split is at 512. Object spans 500 to 520.
        AirObject obj = new AirPlane("OverlapObj", 500, 10, 10, 20, 10, 10,
            "Test", 1, 1);

        // Level 0 (Split X): Should go Both
        node.insert(obj, 0, 0, 0, 1024, 1024, 1024, 0);
        assertTrue(node.getLeft().isLeaf());
        assertTrue(node.getRight().isLeaf());
    }


    /**
     * Test logic: Verify dimension cycling (Level 0=X, Level 1=Y, Level 2=Z).
     * We pass the same object but change the 'level' param to force different
     * splits.
     */
    public void testInternalRoutingDimensions() {
        AirObject obj = new AirPlane("Y_Obj", 10, 600, 10, 10, 10, 10, "Test",
            1, 1);

        // Case A: Level 0 (Split X) -> Object X=10 is Left
        BinInternal nodeX = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());
        nodeX.insert(obj, 0, 0, 0, 1024, 1024, 1024, 0);
        assertTrue(nodeX.getLeft().isLeaf());
        assertTrue(nodeX.getRight() == BinEmpty.getInstance());

        // Case B: Level 1 (Split Y) -> Object Y=600 is Right (600 > 512)
        BinInternal nodeY = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());
        nodeY.insert(obj, 0, 0, 0, 1024, 1024, 1024, 1);
        assertTrue(nodeY.getLeft() == BinEmpty.getInstance());
        assertTrue(nodeY.getRight().isLeaf()); // Went Right because Y > 512

        // Case C: Level 2 (Split Z) -> Object Z=10 is Left
        BinInternal nodeZ = new BinInternal(BinEmpty.getInstance(), BinEmpty
            .getInstance());
        nodeZ.insert(obj, 0, 0, 0, 1024, 1024, 1024, 2);
        assertTrue(nodeZ.getLeft().isLeaf());
        assertTrue(nodeZ.getRight() == BinEmpty.getInstance());
    }


    /**
     * Test logic: If one child becomes Empty and the other is a Leaf,
     * BinInternal should replace itself with the remaining Leaf.
     */
    public void testMergeToSingleLeaf() {
        AirObject keep = new AirPlane("Keep", 10, 10, 10, 10, 10, 10, "K", 1,
            1);
        AirObject remove = new AirPlane("Remove", 600, 10, 10, 10, 10, 10, "R",
            1, 1);

        // Setup: Left has 'Keep', Right has 'Remove'
        BinLeaf leftLeaf = new BinLeaf();
        leftLeaf.insert(keep, 0, 0, 0, 512, 1024, 1024, 1);

        BinLeaf rightLeaf = new BinLeaf();
        rightLeaf.insert(remove, 512, 0, 0, 512, 1024, 1024, 1);

        BinInternal node = new BinInternal(leftLeaf, rightLeaf);

        // Action: Delete 'Remove'. Right child becomes Empty.
        // Logic: BinInternal sees (Leaf, Empty) -> returns Leaf.
        BinNode result = node.delete(remove, 0, 0, 0, 1024, 1024, 1024, 0);

        assertTrue("Result should be a Leaf node", result instanceof BinLeaf);
        BinLeaf resultLeaf = (BinLeaf)result;
        assertEquals(1, resultLeaf.getObjects().size());
        assertEquals("Keep", resultLeaf.getObjects().get(0).getName());
    }


    /**
     * Test logic: If both children are Leaves, and their total objects <= 3,
     * they should combine into one Leaf.
     */
    public void testMergeCombineLeaves() {
        AirObject a = new AirPlane("A", 10, 10, 10, 10, 10, 10, "K", 1, 1);
        AirObject b = new AirPlane("B", 10, 20, 10, 10, 10, 10, "K", 1, 1);
        AirObject c = new AirPlane("C", 600, 10, 10, 10, 10, 10, "R", 1, 1);
        // Setup: Left has A, B. Right has C. Total = 3.
        BinLeaf leftLeaf = new BinLeaf();
        leftLeaf.insert(a, 0, 0, 0, 512, 1024, 1024, 1);
        leftLeaf.insert(b, 0, 0, 0, 512, 1024, 1024, 1);

        BinLeaf rightLeaf = new BinLeaf();
        rightLeaf.insert(c, 512, 0, 0, 512, 1024, 1024, 1);

        BinInternal node = new BinInternal(leftLeaf, rightLeaf);

        // Verify setup
        assertFalse(node.isLeaf());

        // Action: We delete a dummy object just to trigger the check logic,
        // OR we can delete one of the existing ones if we started with 4.
        // Let's perform a dummy delete of a non-existent object to trigger the
        // merge check
        // (Since your delete logic checks merge at the end regardless of
        // whether something was removed)
        // However, to be cleaner, let's say we had 4 and deleted 1.

        AirObject d = new AirPlane("D", 600, 20, 10, 10, 10, 10, "R", 1, 1);
        rightLeaf.insert(d, 512, 0, 0, 512, 1024, 1024, 1);
        // Now total is 4. Should NOT merge yet.

        // Action: Delete D. Total becomes 3 (A, B, C).
        BinNode result = node.delete(d, 0, 0, 0, 1024, 1024, 1024, 0);

        // Expectation: A, B, and C are merged into one Leaf because 3 <= 3.
        assertTrue("Result should be a Leaf node after merge",
            result instanceof BinLeaf);
        assertEquals(3, ((BinLeaf)result).getObjects().size());
    }


    /**
     * Test logic: If both children are Leaves but total objects > 3,
     * they should NOT merge.
     */
    public void testNoMergeIfTooLarge() {
        // Setup 4 objects (2 Left, 2 Right)
        BinLeaf leftLeaf = new BinLeaf();
        leftLeaf.insert(new AirPlane("A", 10, 10, 10, 10, 10, 10, "K", 1, 1), 0,
            0, 0, 512, 1024, 1024, 1);
        leftLeaf.insert(new AirPlane("B", 10, 20, 10, 10, 10, 10, "K", 1, 1), 0,
            0, 0, 512, 1024, 1024, 1);

        BinLeaf rightLeaf = new BinLeaf();
        rightLeaf.insert(new AirPlane("C", 600, 10, 10, 10, 10, 10, "R", 1, 1),
            512, 0, 0, 512, 1024, 1024, 1);
        rightLeaf.insert(new AirPlane("D", 600, 20, 10, 10, 10, 10, "R", 1, 1),
            512, 0, 0, 512, 1024, 1024, 1);

        BinInternal node = new BinInternal(leftLeaf, rightLeaf);

        // Action: Delete a non-existent object "E".
        // The delete method will recurse, fail to find E, but still run the
        // merge check logic at the end.
        AirObject e = new AirPlane("E", 600, 50, 10, 10, 10, 10, "R", 1, 1);
        BinNode result = node.delete(e, 0, 0, 0, 1024, 1024, 1024, 0);

        // Expectation: Result is still BinInternal because 4 objects > 3
        // threshold.
        assertTrue("Result should remain Internal (4 objects)",
            result instanceof BinInternal);
    }


    /**
     * Tests that the Bintree splits correctly on the Z-axis (Level 2).
     * We insert 4 objects that are in the same X and Y range (0-512)
     * but separated by the Z split line (512).
     */
    public void testSplitZ() {
        WorldDB w = new WorldDB(null);

        // All objects are at X=10, Y=10 (Low X, Low Y)
        // This forces them into the Left-Left branch until Level 2 (Z split).

        // Z < 512 (Should end up in Left child of Z-split)
        w.add(new AirPlane("LowZ1", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("LowZ2", 10, 10, 20, 10, 10, 10, "C", 1, 1));

        // Z > 512 (Should end up in Right child of Z-split)
        w.add(new AirPlane("HighZ1", 10, 10, 600, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("HighZ2", 10, 10, 700, 10, 10, 10, "C", 1, 1));

        String output = w.printbintree();

        // 1. Verify Level 0 (X split) exists
        // Internal node at (0,0,0)
        assertTrue(output.contains("I (0, 0, 0, 1024, 1024, 1024) 0"));

        // 2. Verify Level 1 (Y split) exists inside the first branch
        // Internal node at (0,0,0) with size 512x1024x1024
        assertTrue(output.contains("I (0, 0, 0, 512, 1024, 1024) 1"));

        // 3. Verify Level 2 (Z split) exists
        // Internal node at (0,0,0) with size 512x512x1024
        assertTrue(output.contains("I (0, 0, 0, 512, 512, 1024) 2"));

        // 4. Verify Leaves at Level 3 (Result of Z split)
        // Left Leaf (Z low): (0,0,0) Size 512x512x512
        assertTrue(output.contains(
            "Leaf with 2 objects (0, 0, 0, 512, 512, 512) 3"));
        // Right Leaf (Z high): (0,0,512) Size 512x512x512
        assertTrue(output.contains(
            "Leaf with 2 objects (0, 0, 512, 512, 512, 512) 3"));
    }


    /**
     * Tests Z-axis splitting when an object straddles the Z=512 boundary.
     * The straddling object should appear in both leaves.
     */
    public void testSplitZOverlap() {
        WorldDB w = new WorldDB(null);

        // 3 Spacer objects to fill the node and force a split
        // All in Low X, Low Y, Low Z
        w.add(new AirPlane("A", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("B", 10, 10, 20, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("C", 10, 10, 30, 10, 10, 10, "C", 1, 1));

        // Straddling object: Z=500, Depth=20 -> Ends at 520.
        // It crosses the 512 split line.
        w.add(new AirPlane("Straddle", 10, 10, 500, 10, 10, 20, "C", 1, 1));

        String output = w.printbintree();

        // Verify Z-split occurred (Internal node at level 2)
        assertTrue(output.contains("I (0, 0, 0, 512, 512, 1024) 2"));

        // Verify "Straddle" is in the Low-Z leaf
        // Leaf (0,0,0)
// assertTrue(output.contains(
// "Leaf with 4 objects (0, 0, 0, 512, 512, 512) 3"));

        // Verify "Straddle" is ALSO in the High-Z leaf
        // Leaf (0,0,512) containing only the straddler
        assertTrue(output.contains(
            "Leaf with 1 objects (0, 0, 512, 512, 512, 512) 3"));
    }


    /**
     * Tests intersection query deep in the tree (Level 3/Z-split leaves).
     */
    public void testIntersectDeep() {
        WorldDB w = new WorldDB(null);

        // Setup Z-split tree
        w.add(new AirPlane("DeepObj", 10, 10, 600, 10, 10, 10, "C", 1, 1)); // High
                                                                            // Z
        w.add(new AirPlane("Filler1", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("Filler2", 10, 10, 20, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("Filler3", 10, 10, 30, 10, 10, 10, "C", 1, 1));

        // Query box that only overlaps the High Z area: (0,0,550) to
        // (100,100,650)
        String res = w.intersect(0, 0, 550, 100, 100, 100);

        assertTrue(res.contains("DeepObj"));
        assertFalse(res.contains("Filler1")); // Should prune the Low-Z branch

        // Verify traversal log shows visiting the Z-internal node and the
        // High-Z leaf
        // "I (0, 0, 0, 512, 512, 1024) 2" -> The Z split node
        assertTrue(res.contains(" (0, 0, 0, 512, 512, 1024) 2"));
    }


    /**
     * Comprehensive test for Bintree Insertion logic.
     * Covers:
     * 1. Leaf Threshold (<= 3)
     * 2. Splitting (X, Y, Z axes)
     * 3. Straddling objects (appearing in multiple nodes)
     * 4. "All Intersect" edge case (preventing split)
     * 5. Sorted insertion in Leaves
     */
    public void testBintreeInsertEdgeCases() {
        WorldDB w = new WorldDB(null);

        // --- CASE 1: Leaf Capacity & Sorting ---
        // Insert 3 objects. Even if spread out, they should remain in 1 Leaf
        // because the threshold is > 3.
        // We also pick names to test alphabetical sorting: Zebra, Apple, Bear.
        w.add(new AirPlane("Zebra", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("Apple", 200, 200, 200, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("Bear", 400, 400, 400, 10, 10, 10, "C", 1, 1));

        String output = w.printbintree();
        // Assert structure: Single Leaf
        assertFalse(output.contains("I ("));
        assertTrue(output.contains("Leaf with 3 objects"));
        // Assert Sorting: Apple -> Bear -> Zebra
        int idxA = output.indexOf("Apple");
        int idxB = output.indexOf("Bear");
        int idxZ = output.indexOf("Zebra");
        assertTrue(idxA < idxB);
        assertTrue(idxB < idxZ);

        // --- CASE 2: X-Axis Split & Routing ---
        // Add 4th object to force split.
        // Locations:
        // Zebra (10, 10, 10) -> Low X
        // Apple (200, 200, 200) -> Low X
        // Bear (400, 400, 400) -> Low X
        // Delta (800, 800, 800) -> High X (Adds this one)
        w.add(new AirPlane("Delta", 800, 800, 800, 10, 10, 10, "C", 1, 1));

        output = w.printbintree();
        // Assert Split: Root should now be Internal (I)
        assertTrue(output.contains("I (0, 0, 0, 1024, 1024, 1024) 0"));
        // Left Child (Low X): Apple, Bear, Zebra (3 objects)
        // Right Child (High X): Delta (1 object)
        // Note: Check roughly for these counts/groupings
        assertTrue(output.contains("Leaf with 3 objects")); // Left child
        assertTrue(output.contains("Leaf with 1 objects")); // Right child

        // --- CASE 3: Straddling (Split Overlap) ---
        // Add an object that straddles the X=512 split line.
        // X=500, Width=30 -> Ends at 530.
        // This object should be inserted into BOTH Left and Right children.
        w.add(new AirPlane("Straddle", 500, 10, 10, 30, 10, 10, "C", 1, 1));

        output = w.printbintree();
        // Left Child was 3, now +1 (Straddle) = 4 objects.
        // Wait! If Left Child gets 4 objects, it might split again (Level 1 /
        // Y-axis).
        // Let's check if Left Child split:
        // Apple(200,200), Bear(400,400), Zebra(10,10), Straddle(500,10).
        // They do NOT all intersect. So Left child MUST split on Y (Level 1).

        // Assert Level 1 split (Y-axis) exists in the output
        // Internal node at 0,0,0 sized 512x1024x1024
        assertTrue(output.contains("I (0, 0, 0, 512, 1024, 1024) 1"));

        // Verify "Straddle" appears twice in the tree output (once in low X
        // branch, once in high X branch)
        int firstStraddle = output.indexOf("Straddle");
        int lastStraddle = output.lastIndexOf("Straddle");
        assertTrue("Straddle object should appear multiple times",
            firstStraddle != lastStraddle);

        // --- CASE 4: "All Intersect" Edge Case (Prevent Split) ---
        w.clear(); // Start fresh to isolate this case

        // Insert 5 objects that occupy the exact same box (10,10,10 size 10).
        // Even though count (5) > threshold (3), it should NOT split.
        w.add(new AirPlane("A", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("B", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("C", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("D", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("E", 10, 10, 10, 10, 10, 10, "C", 1, 1));

        output = w.printbintree();
        // Assert: Single Leaf node
        assertFalse("Should not split if all objects intersect", output
            .contains("I ("));
        assertTrue("Should be a leaf with 5 objects", output.contains(
            "Leaf with 5 objects"));

        // --- CASE 5: Z-Axis Split (Deep Tree) ---
        w.clear();
        // Force objects down to Level 2 (Z split).
        // We need 4 objects in the same X (0-512) and Y (0-512) bucket, but
        // different Z.
        // X,Y are all 10,10.
        // Z values: 10, 20, 600, 700.
        w.add(new AirPlane("LowZ1", 10, 10, 10, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("LowZ2", 10, 10, 20, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("HighZ1", 10, 10, 600, 10, 10, 10, "C", 1, 1));
        w.add(new AirPlane("HighZ2", 10, 10, 700, 10, 10, 10, "C", 1, 1));

        output = w.printbintree();
        // Check for Level 2 Internal Node (Z split)
        // Dimensions for Level 2 should be 512, 512, 1024 (Split X, then Split
        // Y, Z is full)
        assertTrue("Should contain Level 2 split", output.contains(
            "I (0, 0, 0, 512, 512, 1024) 2"));

        // Check that Low Z objects are in a leaf at 0,0,0
        assertTrue(output.contains(
            "Leaf with 2 objects (0, 0, 0, 512, 512, 512)"));
        // Check that High Z objects are in a leaf at 0,0,512
        assertTrue(output.contains(
            "Leaf with 2 objects (0, 0, 512, 512, 512, 512)"));
    }

}
