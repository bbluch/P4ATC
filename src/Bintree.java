// -------------------------------------------------------------------------
/**
 * Bintree Wrapper Class.
 * * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public class Bintree {
    private BinNode root;
    private final int worldSize = 1024;

    // ----------------------------------------------------------
    /**
     * Create a new Bintree object.
     */
    public Bintree() {
        root = BinEmpty.getInstance();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param obj
     */
    public void insert(AirObject obj) {
        root = root.insert(obj, 0, 0, 0, worldSize, 0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param obj
     */
    public void delete(AirObject obj) {
        root = root.delete(obj, 0, 0, 0, worldSize, 0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        int count = traversePrint(root, 0, 0, 0, worldSize, 0, sb);
        sb.append(count).append(" Bintree nodes printed\r\n");
        return sb.toString();
    }


    private int traversePrint(
        BinNode node,
        int x,
        int y,
        int z,
        int size,
        int level,
        StringBuilder sb) {
        // Indentation logic matching previous pattern
        for (int i = 0; i < level; i++)
            sb.append("  ");
        
//        int width = 1024;
//        int height = 1024;
//        int depth = 1024;
//        
//        // Reconstruct the node's true dimensions by tracing the splits (Level to 0)
//        for (int currentLevel = 0; currentLevel <= level; currentLevel++) {
//            int dim = currentLevel % 3;
//            int currentSize = 1024 / (1 << currentLevel);
//            
//            if (dim == 0) { // Split on X
//                width = currentSize;
//            } else if (dim == 1) { // Split on Y
//                height = currentSize;
//            } else if (dim == 2) { // Split on Z
//                depth = currentSize;
//            }
//        }
        int currentW = 1024, currentH = 1024, currentD = 1024;

        for (int k = 0; k < level; k++) {
            int dim = k % 3;
            if (dim == 0) {
                currentW /= 2;
            } else if (dim == 1) {
                currentH /= 2;
            } else {
                currentD /= 2;
            }
        }
        
        if (node instanceof BinEmpty) {
            sb.append("E (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(currentW).append(", ").append(currentH)
                .append(", ").append(currentD).append(") ").append(level).append("\r\n");
            return 1;
        }
        else if (node instanceof BinLeaf) {
            BinLeaf leaf = (BinLeaf)node;
            sb.append("Leaf with ").append(leaf.getObjects().size()).append(
                " objects (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(currentW).append(", ").append(currentH)
                .append(", ").append(currentD).append(") ").append(level).append(
                    "\n");

            LinkedList<AirObject> objs = leaf.getObjects();
            for (int i = 0; i < objs.size(); i++) {
                for (int k = 0; k < level; k++)
                    sb.append("  ");
                sb.append("(").append(objs.get(i).toString()).append(")\r\n");
            }
            return 1;
        }
        else {
            // Internal
            sb.append("I (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(currentW).append(", ").append(currentH)
                .append(", ").append(currentD).append(") ").append(level).append(
                    "\r\n");

            BinInternal internal = (BinInternal)node;
            int half = size / 2;
            int dim = level % 3;
            int count = 1;

            count += traversePrint(internal.getLeft(), x, y, z, half, level + 1,
                sb);

            int rx = x + (dim == 0 ? half : 0);
            int ry = y + (dim == 1 ? half : 0);
            int rz = z + (dim == 2 ? half : 0);
            count += traversePrint(internal.getRight(), rx, ry, rz, half, level
                + 1, sb);

            return count;
        }
    }

    // --- New Methods Hooked In ---


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public String collisions() {
        StringBuilder sb = new StringBuilder();
        sb.append("The following collisions exist in the database:\n");
        root.collisions(sb, 0, 0, 0, worldSize, 0);
        return sb.toString();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param x
     * @param y
     * @param z
     * @param w
     * @param h
     * @param d
     * @return
     */
    public String intersect(int x, int y, int z, int w, int h, int d) {
        StringBuilder sb = new StringBuilder();
        sb.append("The following objects intersect (").append(x).append(", ")
            .append(y).append(", ").append(z).append(", ").append(w).append(
                ", ").append(h).append(", ").append(d).append("):\n");

        int[] query = { x, y, z, w, h, d };
        int count = root.intersect(sb, query, 0, 0, 0, worldSize, 0);

        sb.append(count).append(" nodes were visited in the bintree\n");
        return sb.toString();
    }
}
