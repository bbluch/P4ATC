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


    /**
     * Insert method for bintree.
     * 
     * @param obj
     *            object inserted
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     * @return node inserted
     */
    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        int dim = level % 3; // 0=x, 1=y, 2=z

        boolean goLeft = false;
        boolean goRight = false;

        int ox = obj.getXorig();
        int oy = obj.getYorig();
        int oz = obj.getZorig();
        int ow = obj.getXwidth();
        int oh = obj.getYwidth();
        int od = obj.getZwidth();

        if (dim == 0) { // Splitting X
            int half = w / 2;
            if (ox < x + half)
                goLeft = true;
            if (ox + ow > x + half)
                goRight = true;

            if (goLeft)
                left = left.insert(obj, x, y, z, half, h, d, level + 1);
            if (goRight)
                right = right.insert(obj, x + half, y, z, half, h, d, level
                    + 1);
        }
        else if (dim == 1) { // Splitting Y
            int half = h / 2;
            if (oy < y + half)
                goLeft = true;
            if (oy + oh > y + half)
                goRight = true;

            if (goLeft)
                left = left.insert(obj, x, y, z, w, half, d, level + 1);
            if (goRight)
                right = right.insert(obj, x, y + half, z, w, half, d, level
                    + 1);
        }
        else { // Splitting Z
            int half = d / 2;
            if (oz < z + half)
                goLeft = true;
            if (oz + od > z + half)
                goRight = true;

            if (goLeft)
                left = left.insert(obj, x, y, z, w, h, half, level + 1);
            if (goRight)
                right = right.insert(obj, x, y, z + half, w, h, half, level
                    + 1);
        }

        return this;
    }

// @Override
// public BinNode insert(
// AirObject obj,
// int x,
// int y,
// int z,
// int size,
// int level) {
// int dim = level % 3;
// int half = size / 2;
// boolean goLeft = false;
// boolean goRight = false;
//
// int ox = obj.getXorig();
// int oy = obj.getYorig();
// int oz = obj.getZorig();
// int ow = obj.getXwidth();
// int oh = obj.getYwidth();
// int od = obj.getZwidth();
//
// if (dim == 0) { // X split
// if (ox < x + half)
// goLeft = true;
// if (ox + ow > x + half)
// goRight = true;
// }
// else if (dim == 1) { // Y split
// if (oy < y + half)
// goLeft = true;
// if (oy + oh > y + half)
// goRight = true;
// }
// else { // Z split
// if (oz < z + half)
// goLeft = true;
// if (oz + od > z + half)
// goRight = true;
// }
//
// if (goLeft)
// left = left.insert(obj, x, y, z, half, level + 1);
// if (goRight) {
// int rx = x + (dim == 0 ? half : 0);
// int ry = y + (dim == 1 ? half : 0);
// int rz = z + (dim == 2 ? half : 0);
// right = right.insert(obj, rx, ry, rz, half, level + 1);
// }
// return this;
// }


    /**
     * Deletes a node from the Bintree.
     * 
     * @param obj
     *            object inserted
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     * @return node deleted
     */
    @Override
    public BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        int dim = level % 3;
        boolean goLeft = false;
        boolean goRight = false;

        int ox = obj.getXorig();
        int oy = obj.getYorig();
        int oz = obj.getZorig();
        int ow = obj.getXwidth();
        int oh = obj.getYwidth();
        int od = obj.getZwidth();

        if (dim == 0) { // Split X
            int half = w / 2;
            if (ox < x + half)
                goLeft = true;
            if (ox + ow > x + half)
                goRight = true;

            if (goLeft)
                left = left.delete(obj, x, y, z, half, h, d, level + 1);
            if (goRight)
                right = right.delete(obj, x + half, y, z, half, h, d, level
                    + 1);
        }
        else if (dim == 1) { // Split Y
            int half = h / 2;
            if (oy < y + half)
                goLeft = true;
            if (oy + oh > y + half)
                goRight = true;

            if (goLeft)
                left = left.delete(obj, x, y, z, w, half, d, level + 1);
            if (goRight)
                right = right.delete(obj, x, y + half, z, w, half, d, level
                    + 1);
        }
        else { // Split Z
            int half = d / 2;
            if (oz < z + half)
                goLeft = true;
            if (oz + od > z + half)
                goRight = true;

            if (goLeft)
                left = left.delete(obj, x, y, z, w, h, half, level + 1);
            if (goRight)
                right = right.delete(obj, x, y, z + half, w, h, half, level
                    + 1);
        }

        // Merge logic: If both children are leaves and their combined contents
        // are small
        if (left.isLeaf() && right.isLeaf()) {
            if (left == BinEmpty.getInstance() && right == BinEmpty
                .getInstance()) {
                return BinEmpty.getInstance();
            }

            // Simple merges
            if (left instanceof BinLeaf && right == BinEmpty.getInstance())
                return left;
            if (right instanceof BinLeaf && left == BinEmpty.getInstance())
                return right;

            // Merge two BinLeaves
            if (left instanceof BinLeaf && right instanceof BinLeaf) {
                BinLeaf l = (BinLeaf)left;
                BinLeaf r = (BinLeaf)right;

                LinkedList<AirObject> union = new LinkedList<>();
                for (int i = 0; i < l.getObjects().size(); i++) {
                    union.append(l.getObjects().get(i));
                }
                for (int i = 0; i < r.getObjects().size(); i++) {
                    if (!union.contains(r.getObjects().get(i))) {
                        union.append(r.getObjects().get(i));
                    }
                }

                if (union.size() <= 3) {
                    BinLeaf newLeaf = new BinLeaf();
                    for (int i = 0; i < union.size(); i++) {
                        newLeaf.getObjects().append(union.get(i));
                    }
                    return newLeaf;
                }
            }
        }
        return this;
    }

// @Override
// public BinNode delete(
// AirObject obj,
// int x,
// int y,
// int z,
// int size,
// int level) {
// // (Use logic provided in previous response for delete/merge)
// // Re-implementing briefly for completeness of this file context:
// int dim = level % 3;
// int half = size / 2;
// boolean goLeft = false, goRight = false;
//
// int ox = obj.getXorig();
// int oy = obj.getYorig();
// int oz = obj.getZorig();
// int ow = obj.getXwidth();
// int oh = obj.getYwidth();
// int od = obj.getZwidth();
//
// if (dim == 0) {
// if (ox < x + half)
// goLeft = true;
// if (ox + ow > x + half)
// goRight = true;
// }
// else if (dim == 1) {
// if (oy < y + half)
// goLeft = true;
// if (oy + oh > y + half)
// goRight = true;
// }
// else {
// if (oz < z + half)
// goLeft = true;
// if (oz + od > z + half)
// goRight = true;
// }
//
// if (goLeft)
// left = left.delete(obj, x, y, z, half, level + 1);
// if (goRight) {
// int rx = x + (dim == 0 ? half : 0);
// int ry = y + (dim == 1 ? half : 0);
// int rz = z + (dim == 2 ? half : 0);
// right = right.delete(obj, rx, ry, rz, half, level + 1);
// }
//
// // Merge logic
// if (left.isLeaf() && right.isLeaf()) {
// if (left == BinEmpty.getInstance() && right == BinEmpty
// .getInstance())
// return BinEmpty.getInstance();
// // Basic merge check (could be expanded)
// if (left instanceof BinLeaf && right == BinEmpty.getInstance())
// return left;
// if (right instanceof BinLeaf && left == BinEmpty.getInstance())
// return right;
//
// // Check combine count
// if (left instanceof BinLeaf && right instanceof BinLeaf) {
// // (Simplified) If sum of sizes is small, try merge.
// // Full logic needs unique checking.
// BinLeaf l = (BinLeaf)left;
// BinLeaf r = (BinLeaf)right;
// // Union
// LinkedList<AirObject> union = new LinkedList<>();
// for (int i = 0; i < l.getObjects().size(); i++)
// union.append(l.getObjects().get(i));
// for (int i = 0; i < r.getObjects().size(); i++) {
// if (!union.contains(r.getObjects().get(i)))
// union.append(r.getObjects().get(i));
// }
// if (union.size() <= 3) { // Threshold
// BinLeaf newLeaf = new BinLeaf();
// for (int i = 0; i < union.size(); i++)
// newLeaf.getObjects().append(union.get(i));
// return newLeaf;
// }
// }
// }
// return this;
// }


    /**
     * Checks if node is leaf.
     * 
     * @return t/f for decision
     */
    @Override
    public boolean isLeaf() {
        return false;
    }


    /**
     * Counts nodes in Bintree.
     * 
     * @return num of nodes in Bintree.
     */
    @Override
    public int countNodes() {
        return 1 + left.countNodes() + right.countNodes();
    }


    // ----------------------------------------------------------
    /**
     * Retrieves left node.
     * 
     * @return left node
     */
    public BinNode getLeft() {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Retrieves right node
     * 
     * @return right node
     */
    public BinNode getRight() {
        return right;
    }

    // --- New Methods ---


    /**
     * Traverse for collisions.
     * 
     * @param sb
     *            StringBuilder for output
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     *            Node level
     */
    @Override
    public void collisions(
        StringBuilder sb,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        int dim = level % 3;

        if (dim == 0) {
            int half = w / 2;
            left.collisions(sb, x, y, z, half, h, d, level + 1);
            right.collisions(sb, x + half, y, z, half, h, d, level + 1);
        }
        else if (dim == 1) {
            int half = h / 2;
            left.collisions(sb, x, y, z, w, half, d, level + 1);
            right.collisions(sb, x, y + half, z, w, half, d, level + 1);
        }
        else {
            int half = d / 2;
            left.collisions(sb, x, y, z, w, h, half, level + 1);
            right.collisions(sb, x, y, z + half, w, h, half, level + 1);
        }
    }

// @Override
// public void collisions(
// StringBuilder sb,
// int x,
// int y,
// int z,
// int size,
// int level) {
// // Internal nodes just pass the traversal down to children
// int half = size / 2;
// int dim = level % 3;
//
// left.collisions(sb, x, y, z, half, level + 1);
//
// int rx = x + (dim == 0 ? half : 0);
// int ry = y + (dim == 1 ? half : 0);
// int rz = z + (dim == 2 ? half : 0);
// right.collisions(sb, rx, ry, rz, half, level + 1);
// }


    /**
     * Traverse for intersections with a query box.
     * 
     * @param sb
     *            StringBuilder for output
     * @param query
     *            The query box details {x, y, z, w, h, d}
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     * @param level
     *            Node level
     * @return Number of nodes visited
     */
    @Override
    public int intersect(
        StringBuilder sb,
        int[] query,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        // 1. Check if the QUERY BOX overlaps THIS NODE'S region.
        // We do NOT check 'objects' here because BinInternal doesn't have them.
        if (!intersects(x, y, z, w, h, d, query[0], query[1], query[2],
            query[3], query[4], query[5])) {
            return 0; // Prune: The query box is completely outside this node.
        }

        // 2. Visit this internal node (add "I" to the output)
        sb.append("I (").append(x).append(", ").append(y).append(", ").append(z)
            .append(", ").append(w).append(", ").append(h).append(", ").append(
                d).append(") ").append(level).append("\n");

        int dim = level % 3;
        int count = 1; // Count this node

        // 3. Recurse to children.
        // The children will handle finding the actual objects if they are
        // leaves.
        if (dim == 0) { // Split X
            int half = w / 2;
            count += left.intersect(sb, query, x, y, z, half, h, d, level + 1);
            count += right.intersect(sb, query, x + half, y, z, half, h, d,
                level + 1);
        }
        else if (dim == 1) { // Split Y
            int half = h / 2;
            count += left.intersect(sb, query, x, y, z, w, half, d, level + 1);
            count += right.intersect(sb, query, x, y + half, z, w, half, d,
                level + 1);
        }
        else { // Split Z
            int half = d / 2;
            count += left.intersect(sb, query, x, y, z, w, h, half, level + 1);
            count += right.intersect(sb, query, x, y, z + half, w, h, half,
                level + 1);
        }
        return count;
    }


// @Override
// public int intersect(
// StringBuilder sb,
// int[] query,
// int x,
// int y,
// int z,
// int size,
// int level) {
//
// // 1. Check if this node intersects the query box
// if (!intersects(x, y, z, size, size, size, query[0], query[1], query[2],
// query[3], query[4], query[5])) {
// return 0; // Prune
// }
//
// // 2. Visit
// sb.append("I (").append(x).append(", ").append(y).append(", ").append(z)
// .append(", ").append(size).append(", ").append(size).append(", ")
// .append(size).append(") ").append(level).append("\n");
//
// // 3. Recurse
// int half = size / 2;
// int dim = level % 3;
// int count = 1;
//
// count += left.intersect(sb, query, x, y, z, half, level + 1);
//
// int rx = x + (dim == 0 ? half : 0);
// int ry = y + (dim == 1 ? half : 0);
// int rz = z + (dim == 2 ? half : 0);
// count += right.intersect(sb, query, rx, ry, rz, half, level + 1);
//
// return count;
// }
    private boolean objsIntersect(AirObject a, AirObject b) {
        return boxIntersects(a.getXorig(), a.getYorig(), a.getZorig(), a
            .getXwidth(), a.getYwidth(), a.getZwidth(), b.getXorig(), b
                .getYorig(), b.getZorig(), b.getXwidth(), b.getYwidth(), b
                    .getZwidth());
    }


    private boolean boxIntersects(
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


    private boolean containsPoint(
        int nx,
        int ny,
        int nz,
        int nw,
        int nh,
        int nd,
        int px,
        int py,
        int pz) {
        return px >= nx && px < nx + nw && py >= ny && py < ny + nh && pz >= nz
            && pz < nz + nd;
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
