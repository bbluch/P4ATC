// -------------------------------------------------------------------------
/**
 * Internal Node for Bintree.
 * 
 * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public class BinInternal implements BinNode {

    private BinNode left;
    private BinNode right;

    // ----------------------------------------------------------
    /**
     * Create a new BinInternal object.
     * 
     * @param left
     * @param right
     */
    public BinInternal(BinNode left, BinNode right) {
        this.left = left;
        this.right = right;
    }

    // ... insert/delete/isLeaf/countNodes/getters from previous turn ...


    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        int dim = level % 3;
        int half = size / 2;
        boolean goLeft = false;
        boolean goRight = false;

        int ox = obj.getXorig();
        int oy = obj.getYorig();
        int oz = obj.getZorig();
        int ow = obj.getXwidth();
        int oh = obj.getYwidth();
        int od = obj.getZwidth();

        if (dim == 0) { // X split
            if (ox < x + half)
                goLeft = true;
            if (ox + ow > x + half)
                goRight = true;
        }
        else if (dim == 1) { // Y split
            if (oy < y + half)
                goLeft = true;
            if (oy + oh > y + half)
                goRight = true;
        }
        else { // Z split
            if (oz < z + half)
                goLeft = true;
            if (oz + od > z + half)
                goRight = true;
        }

        if (goLeft)
            left = left.insert(obj, x, y, z, half, level + 1);
        if (goRight) {
            int rx = x + (dim == 0 ? half : 0);
            int ry = y + (dim == 1 ? half : 0);
            int rz = z + (dim == 2 ? half : 0);
            right = right.insert(obj, rx, ry, rz, half, level + 1);
        }
        return this;
    }


    @Override
    public BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        // (Use logic provided in previous response for delete/merge)
        // Re-implementing briefly for completeness of this file context:
        int dim = level % 3;
        int half = size / 2;
        boolean goLeft = false, goRight = false;

        int ox = obj.getXorig();
        int oy = obj.getYorig();
        int oz = obj.getZorig();
        int ow = obj.getXwidth();
        int oh = obj.getYwidth();
        int od = obj.getZwidth();

        if (dim == 0) {
            if (ox < x + half)
                goLeft = true;
            if (ox + ow > x + half)
                goRight = true;
        }
        else if (dim == 1) {
            if (oy < y + half)
                goLeft = true;
            if (oy + oh > y + half)
                goRight = true;
        }
        else {
            if (oz < z + half)
                goLeft = true;
            if (oz + od > z + half)
                goRight = true;
        }

        if (goLeft)
            left = left.delete(obj, x, y, z, half, level + 1);
        if (goRight) {
            int rx = x + (dim == 0 ? half : 0);
            int ry = y + (dim == 1 ? half : 0);
            int rz = z + (dim == 2 ? half : 0);
            right = right.delete(obj, rx, ry, rz, half, level + 1);
        }

        // Merge logic
        if (left.isLeaf() && right.isLeaf()) {
            if (left == BinEmpty.getInstance() && right == BinEmpty
                .getInstance())
                return BinEmpty.getInstance();
            // Basic merge check (could be expanded)
            if (left instanceof BinLeaf && right == BinEmpty.getInstance())
                return left;
            if (right instanceof BinLeaf && left == BinEmpty.getInstance())
                return right;

            // Check combine count
            if (left instanceof BinLeaf && right instanceof BinLeaf) {
                // (Simplified) If sum of sizes is small, try merge.
                // Full logic needs unique checking.
                BinLeaf l = (BinLeaf)left;
                BinLeaf r = (BinLeaf)right;
                // Union
                LinkedList<AirObject> union = new LinkedList<>();
                for (int i = 0; i < l.getObjects().size(); i++)
                    union.append(l.getObjects().get(i));
                for (int i = 0; i < r.getObjects().size(); i++) {
                    if (!union.contains(r.getObjects().get(i)))
                        union.append(r.getObjects().get(i));
                }
                if (union.size() <= 3) { // Threshold
                    BinLeaf newLeaf = new BinLeaf();
                    for (int i = 0; i < union.size(); i++)
                        newLeaf.getObjects().append(union.get(i));
                    return newLeaf;
                }
            }
        }
        return this;
    }


    @Override
    public boolean isLeaf() {
        return false;
    }


    @Override
    public int countNodes() {
        return 1 + left.countNodes() + right.countNodes();
    }


    public BinNode getLeft() {
        return left;
    }


    public BinNode getRight() {
        return right;
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
        // Internal nodes just pass the traversal down to children
        int half = size / 2;
        int dim = level % 3;

        left.collisions(sb, x, y, z, half, level + 1);

        int rx = x + (dim == 0 ? half : 0);
        int ry = y + (dim == 1 ? half : 0);
        int rz = z + (dim == 2 ? half : 0);
        right.collisions(sb, rx, ry, rz, half, level + 1);
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

        // 1. Check if this node intersects the query box
        if (!intersects(x, y, z, size, size, size, query[0], query[1], query[2],
            query[3], query[4], query[5])) {
            return 0; // Prune
        }

        // 2. Visit
        sb.append("I (").append(x).append(", ").append(y).append(", ").append(z)
            .append(", ").append(size).append(", ").append(size).append(", ")
            .append(size).append(") ").append(level).append("\n");

        // 3. Recurse
        int half = size / 2;
        int dim = level % 3;
        int count = 1;

        count += left.intersect(sb, query, x, y, z, half, level + 1);

        int rx = x + (dim == 0 ? half : 0);
        int ry = y + (dim == 1 ? half : 0);
        int rz = z + (dim == 2 ? half : 0);
        count += right.intersect(sb, query, rx, ry, rz, half, level + 1);

        return count;
    }


    private boolean intersects(
        int x1,
        int y1,
        int z1,
        int w1,
        int h1,
        int d1,
        int x2,
        int y2,
        int z2,
        int w2,
        int h2,
        int d2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2
            && z1 < z2 + d2 && z1 + d1 > z2;
    }
}
