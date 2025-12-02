// -------------------------------------------------------------------------
/**
 * Flyweight Empty Node for Bintree.
 * 
 * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public class BinEmpty implements BinNode {

    private static BinEmpty instance = new BinEmpty();

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public static BinEmpty getInstance() {
        return instance;
    }


    private BinEmpty() {
    }

    // ... insert/delete/isLeaf/countNodes implementation from previous turn ...


    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        BinLeaf leaf = new BinLeaf();
        return leaf.insert(obj, x, y, z, size, level);
    }


    @Override
    public BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        return this;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public int countNodes() {
        return 1;
    }

    // --- New Methods ---


    @Override
    public void collisions(
        StringBuilder sb,
        int x,
        int y,
        int z,
        int size,
        int level) {
        // Empty nodes have no objects, so no collisions.
    }


    @Override
    public int intersect(
        StringBuilder sb,
        int[] query,
        int x,
        int y,
        int z,
        int size,
        int level) {
        return 1;
    }

}
