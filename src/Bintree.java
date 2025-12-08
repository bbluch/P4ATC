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
     * 
     * @param obj
     */
    public void insert(AirObject obj) {
        root = root.insert(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param obj
     */
    public void delete(AirObject obj) {
        root = root.delete(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        int count = traversePrint(root, 0, 0, 0, worldSize, worldSize,
            worldSize, 0, sb);
        sb.append(count).append(" Bintree nodes printed\n");
        return sb.toString();
    }


    private int traversePrint(
        BinNode node,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level,
        StringBuilder sb) {
        for (int i = 0; i < level; i++)
            sb.append("  ");

        if (node instanceof BinEmpty) {
            sb.append("E (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(w).append(", ").append(h).append(
                    ", ").append(d).append(") ").append(level).append("\n");
            return 1;
        }
        else if (node instanceof BinLeaf) {
            BinLeaf leaf = (BinLeaf)node;
            sb.append("Leaf with ").append(leaf.getObjects().size()).append(
                " objects (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(w).append(", ").append(h).append(
                    ", ").append(d).append(") ").append(level).append("\n");

            LinkedList<AirObject> objs = leaf.getObjects();
            for (int i = 0; i < objs.size(); i++) {
                for (int k = 0; k < level; k++)
                    sb.append("  ");
                sb.append("(").append(objs.get(i).toString()).append(")\n");
            }
            return 1;
        }
        else {
            sb.append("I (").append(x).append(", ").append(y).append(", ")
                .append(z).append(", ").append(w).append(", ").append(h).append(
                    ", ").append(d).append(") ").append(level).append("\n");

            BinInternal internal = (BinInternal)node;
            int dim = level % 3;
            int count = 1;

            if (dim == 0) {
                int half = w / 2;
                count += traversePrint(internal.getLeft(), x, y, z, half, h, d,
                    level + 1, sb);
                count += traversePrint(internal.getRight(), x + half, y, z,
                    half, h, d, level + 1, sb);
            }
            else if (dim == 1) {
                int half = h / 2;
                count += traversePrint(internal.getLeft(), x, y, z, w, half, d,
                    level + 1, sb);
                count += traversePrint(internal.getRight(), x, y + half, z, w,
                    half, d, level + 1, sb);
            }
            else {
                int half = d / 2;
                count += traversePrint(internal.getLeft(), x, y, z, w, h, half,
                    level + 1, sb);
                count += traversePrint(internal.getRight(), x, y, z + half, w,
                    h, half, level + 1, sb);
            }
            return count;
        }
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public String collisions() {
        StringBuilder sb = new StringBuilder();
        sb.append("The following collisions exist in the database:\n");
        root.collisions(sb, 0, 0, 0, worldSize, worldSize, worldSize, 0);
        return sb.toString();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
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
        sb.append("The following objects intersect (").append(x).append(" ")
            .append(y).append(" ").append(z).append(" ").append(w).append(" ")
            .append(h).append(" ").append(d).append("):\n");

        int[] query = { x, y, z, w, h, d };
        int count = root.intersect(sb, query, 0, 0, 0, worldSize, worldSize,
            worldSize, 0);
        

        sb.append(count).append(" nodes were visited in the bintree\n");
        return sb.toString();
    }
}
