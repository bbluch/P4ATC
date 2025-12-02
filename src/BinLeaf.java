// -------------------------------------------------------------------------
/**
 * Leaf Node for Bintree.
 * 
 * @author benblucher
 * 
 * @author austink23
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
     * Place a description of your method here.
     * 
     * @return
     */
    public LinkedList<AirObject> getObjects() {
        return objects;
    }

    // ... insert/delete/isLeaf/countNodes/allIntersect implementation from
    // previous turn ...


    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        objects.append(obj);
        if (objects.size() > 3) { // Threshold
            if (!allIntersect()) {
                BinInternal internal = new BinInternal(BinEmpty.getInstance(),
                    BinEmpty.getInstance());
                for (int i = 0; i < objects.size(); i++) {
                    internal.insert(objects.get(i), x, y, z, size, level);
                }
                return internal;
            }
        }
        return this;
    }


    private boolean allIntersect() {
        if (objects.size() == 0)
            return false;
        AirObject first = objects.get(0);
        int ix = first.getXorig(), iy = first.getYorig(), iz = first.getZorig();
        int iw = first.getXwidth(), ih = first.getYwidth(), id = first
            .getZwidth();

        for (int i = 1; i < objects.size(); i++) {
            AirObject curr = objects.get(i);
            int nx = Math.max(ix, curr.getXorig());
            int ny = Math.max(iy, curr.getYorig());
            int nz = Math.max(iz, curr.getZorig());
            int nr = Math.min(ix + iw, curr.getXorig() + curr.getXwidth());
            int nb = Math.min(iy + ih, curr.getYorig() + curr.getYwidth());
            int nback = Math.min(iz + id, curr.getZorig() + curr.getZwidth());

            iw = nr - nx;
            ih = nb - ny;
            id = nback - nz;
            if (iw <= 0 || ih <= 0 || id <= 0)
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
        int size,
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
        StringBuilder nodeCollisions = new StringBuilder();
        boolean foundCollision = false;

        // Compare every pair
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
                    if (containsPoint(x, y, z, size, ix, iy, iz)) {
                        nodeCollisions.append("(").append(a.toString()).append(
                            ") and (").append(b.toString()).append(")\n");
                        foundCollision = true;
                    }
                }
            }
        }

        if (foundCollision) {
            sb.append("In leaf node (").append(x).append(", ").append(y).append(
                ", ").append(z).append(", ").append(size).append(", ").append(
                    size).append(", ").append(size).append(") ").append(level)
                .append("\n");
            sb.append(nodeCollisions);
        }
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

        // 1. Check intersection with node
        if (!boxIntersects(x, y, z, size, size, size, query[0], query[1],
            query[2], query[3], query[4], query[5])) {
            return 0;
        }

        // 2. Visit
        sb.append("In leaf node (").append(x).append(", ").append(y).append(
            ", ").append(z).append(", ").append(size).append(", ").append(size)
            .append(", ").append(size).append(") ").append(level).append("\n");

        // 3. Check objects inside
        for (int i = 0; i < objects.size(); i++) {
            AirObject obj = objects.get(i);
            if (boxIntersects(obj.getXorig(), obj.getYorig(), obj.getZorig(),
                obj.getXwidth(), obj.getYwidth(), obj.getZwidth(), query[0],
                query[1], query[2], query[3], query[4], query[5])) {

                // Intersection box origin
                int ix = Math.max(obj.getXorig(), query[0]);
                int iy = Math.max(obj.getYorig(), query[1]);
                int iz = Math.max(obj.getZorig(), query[2]);

                // Report only if intersection origin is in this node
                if (containsPoint(x, y, z, size, ix, iy, iz)) {
                    sb.append(obj.toString()).append("\n");
                }
            }
        }

        return 1;
    }


    // Helpers
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
        int size,
        int px,
        int py,
        int pz) {
        return px >= nx && px < nx + size && py >= ny && py < ny + size
            && pz >= nz && pz < nz + size;
    }
}
