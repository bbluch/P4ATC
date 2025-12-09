/**
 * Leaf Node for Bintree.
 * 
 * @author benblucher
 * @author austink23
 * 
 * @version Nov 20, 2025
 */
public class BinLeaf implements BinNode {

    private LinkedList<AirObject> objects;

    // ----------------------------------------------------------
    /**
     * Create a new BinLeaf object.
     */
    public BinLeaf() {
        objects = new LinkedList<>();
    }


    // ----------------------------------------------------------
    /**
     * Returns the linkedlist of objects.
     * 
     * @return LinkedList of objects
     */
    public LinkedList<AirObject> getObjects() {
        return objects;
    }


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
        // Find the correct insertion index to maintain sorted order (by Name)
        int index = 0;
        while (index < objects.size()) {
            AirObject curr = objects.get(index);
            if (obj.getName().compareTo(curr.getName()) < 0) {
                break;
            }
            index++;
        }
        objects.add(index, obj);

        if (objects.size() > 3) { // Threshold
            if (!allIntersect()) {
                BinInternal internal = new BinInternal(BinEmpty.getInstance(),
                    BinEmpty.getInstance());
                for (int i = 0; i < objects.size(); i++) {
                    internal.insert(objects.get(i), x, y, z, w, h, d, level);
                }
                return internal;
            }
        }
        return this;
    }


    private boolean allIntersect() {

        AirObject first = objects.get(0);
        // initial x
        int ix = first.getXorig();
        int iy = first.getYorig();
        int iz = first.getZorig();
        int iw = first.getXwidth();
        int ih = first.getYwidth();
        int id = first.getZwidth();

        for (int i = 1; i < objects.size(); i++) {
            AirObject curr = objects.get(i);
            // new x
            int nx = Math.max(ix, curr.getXorig());
            int ny = Math.max(iy, curr.getYorig());
            int nz = Math.max(iz, curr.getZorig());
            // new box
            int nr = Math.min(ix + iw, curr.getXorig() + curr.getXwidth());
            int nb = Math.min(iy + ih, curr.getYorig() + curr.getYwidth());
            int nback = Math.min(iz + id, curr.getZorig() + curr.getZwidth());

            if (nr - nx <= 0 || nb - ny <= 0 || nback - nz <= 0)
                return false;
            ix = nx;
            iy = ny;
            iz = nz;
        }
        return true;
    }


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
        objects.remove(obj);
        if (objects.size() == 0)
            return BinEmpty.getInstance();
        return this;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }


    /**
     * Traverse for collisions.
     * * @param sb
     * StringBuilder for output
     * 
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

        sb.append("In leaf node (").append(x).append(", ").append(y).append(
            ", ").append(z).append(", ").append(w).append(", ").append(h)
            .append(", ").append(d).append(") ").append(level).append("\n");

        // Check for collisions between objects in this leaf
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                AirObject a = objects.get(i);
                AirObject b = objects.get(j);

                if (objsIntersect(a, b)) {
                    // Calculate Intersection Origin
                    int ix = Math.max(a.getXorig(), b.getXorig());
                    int iy = Math.max(a.getYorig(), b.getYorig());
                    int iz = Math.max(a.getZorig(), b.getZorig());

                    // Check if origin is within this node's bounds
                    // This ensures duplicates are avoided if objects span
                    // multiple nodes
                    if (containsPoint(x, y, z, w, h, d, ix, iy, iz)) {
                        sb.append("(").append(a.toString()).append(") and (")
                            .append(b.toString()).append(")\n");
                    }
                }
            }
        }
    }


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
        // 1. Check intersection with node bounds
        if (!boxIntersects(x, y, z, w, h, d, query[0], query[1], query[2],
            query[3], query[4], query[5])) {
            return 0;
        }

        // 2. Visit
        sb.append("In leaf node (").append(x).append(", ").append(y).append(
            ", ").append(z).append(", ").append(w).append(", ").append(h)
            .append(", ").append(d).append(") ").append(level).append("\n");

        // 3. Check objects
        for (int i = 0; i < objects.size(); i++) {
            AirObject obj = objects.get(i);
            if (boxIntersects(obj.getXorig(), obj.getYorig(), obj.getZorig(),
                obj.getXwidth(), obj.getYwidth(), obj.getZwidth(), query[0],
                query[1], query[2], query[3], query[4], query[5])) {

                int ix = Math.max(obj.getXorig(), query[0]);
                int iy = Math.max(obj.getYorig(), query[1]);
                int iz = Math.max(obj.getZorig(), query[2]);

                if (containsPoint(x, y, z, w, h, d, ix, iy, iz)) {
                    sb.append(obj.toString()).append("\n");
                }
            }
        }
        return 1;
    }


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
}
