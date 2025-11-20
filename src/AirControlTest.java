import java.util.Random;
import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
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
// public void testSampleInput() throws Exception {
// Random rnd = new Random();
// rnd.setSeed(0xCAFEBEEF);
// WorldDB w = new WorldDB(rnd);
//
// assertTrue(w.add(new Balloon("B1",
// 10, 11, 11, 21, 12, 31, "hot_air", 15)));
// assertTrue(w.add(new AirPlane("Air1",
// 0, 10, 1, 20, 2, 30, "USAir", 717, 4)));
// assertTrue(w.add(new Drone("Air2",
// 100, 1010, 101, 924, 2, 900, "Droners", 3)));
// assertTrue(w.add(new Bird("pterodactyl",
// 0, 100, 20, 10, 50, 50, "Dinosaur", 1)));
// assertFalse(w.add(new Bird("pterodactyl",
// 0, 100, 20, 10, 50, 50, "Dinosaur", 1)));
// assertTrue(w.add(new Rocket("Enterprise",
// 0, 100, 20, 10, 50, 50, 5000, 99.29)));
//
// assertFuzzyEquals(
// "Rocket Enterprise 0 100 20 10 50 50 5000 99.29",
// w.delete("Enterprise"));
//
// assertFuzzyEquals("Airplane Air1 0 10 1 20 2 30 USAir 717 4",
// w.print("Air1"));
// assertNull(w.print("air1"));
//
// assertFuzzyEquals(
// "I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
// + " I (0, 0, 0, 512, 1024, 1024) 1\r\n"
// + " Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
// + " (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
// + " (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
// + " (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
// + " Leaf with 1 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
// + " (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
// + " Leaf with 1 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
// + " (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
// + "5 Bintree nodes printed\r\n",
// w.printbintree());
//
// assertFuzzyEquals(
// "Node has depth 3, Value (null)\r\n"
// + "Node has depth 3, "
// + "Value (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
// + "Node has depth 1, "
// + "Value (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
// + "Node has depth 2, "
// + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
// + "Node has depth 2, "
// + "Value (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
// + "4 skiplist nodes printed\r\n",
// w.printskiplist());
//
// assertFuzzyEquals(
// "Found these records in the range a to z\r\n"
// + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n",
// w.rangeprint("a", "z"));
// assertFuzzyEquals(
// "Found these records in the range a to l\r\n",
// w.rangeprint("a", "l"));
// assertNull(w.rangeprint("z", "a"));
//
// assertFuzzyEquals(
// "The following collisions exist in the database:\r\n"
// + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
// + "(Airplane Air1 0 10 1 20 2 30 USAir 717 4) "
// + "and (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
// + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
// + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n",
// w.collisions());
//
// assertFuzzyEquals(
// "The following objects intersect (0 0 0 1024 1024 1024):\r\n"
// + "In Internal node (0, 0, 0, 1024, 1024, 1024) 0\r\n"
// + "In Internal node (0, 0, 0, 512, 1024, 1024) 1\r\n"
// + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
// + "Airplane Air1 0 10 1 20 2 30 USAir 717 4\r\n"
// + "Balloon B1 10 11 11 21 12 31 hot_air 15\r\n"
// + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n"
// + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
// + "Drone Air2 100 1010 101 924 2 900 Droners 3\r\n"
// + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n"
// + "5 nodes were visited in the bintree\r\n",
// w.intersect(0, 0, 0, 1024, 1024, 1024));
// }


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
//        assertTrue(
//            "The original object should remain after a failed duplicate insertion.",
//            storedPlane.contains("Delta"));
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
     * Test the WorldDB.delete(String name) method, verifying successful deletion
     * (and skip list removal) and rejection of non-existent names.
     *
     * @throws Exception
     */
    public void testDeleteFunctionality() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        
        // --- Setup: Insert two objects ---
        AirPlane plane1 = new AirPlane("PlaneA", 10, 10, 10, 5, 5, 5, "Delta", 123, 2);
        Bird bird1 = new Bird("BirdB", 100, 100, 100, 2, 2, 2, "Sparrow", 5);
        
        assertTrue("Setup: PlaneA should be added successfully.", w.add(plane1));
        assertTrue("Setup: BirdB should be added successfully.", w.add(bird1));
        
        // Sanity Check: Both should be present
        assertNotNull("Sanity: PlaneA should be found before delete.", w.print("PlaneA"));
        assertNotNull("Sanity: BirdB should be found before delete.", w.print("BirdB"));
        
        // --- 1. Successful Deletion Test (Middle/Non-Head) ---
        String deletedOutput = w.delete("PlaneA");
        
        assertNotNull("Delete should return the object's string when found.", deletedOutput);
        assertFuzzyEquals("The returned string should match the deleted object.", 
                          plane1.toString(), deletedOutput);
        
        // Verify deletion by attempting to print
        assertNull("Verify: PlaneA should be removed and not found after delete.", w.print("PlaneA"));
        
        // Verify other object remains
        assertNotNull("Verify: BirdB should still be present after deleting PlaneA.", w.print("BirdB"));
        
        // --- 2. Deletion Not Found Test ---
        String notFoundResult = w.delete("PlaneA"); // Attempt to delete again
        assertNull("Delete should return null if the name is not found.", notFoundResult);

        // --- 3. Delete Remaining Object ---
        assertNotNull("Pre-check: BirdB should still be present.", w.print("BirdB"));
        w.delete("BirdB");
        assertNull("Verify: BirdB should be removed after final delete.", w.print("BirdB"));
        
        // --- 4. Null Input Check (Already in testBadInput, but good practice) ---
        assertNull("Delete should return null if the input name is null.", w.delete(null));
    }

}
