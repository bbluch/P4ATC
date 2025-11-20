// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author benblucher, austink23
 * @version Nov 14, 2025
 */
public class Rocket extends AirObject {
    // ~ Fields ................................................................
    private int ascentRate;
    private double trajectory;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Rocket object.
     * 
     * @param name
     * @param x
     * @param y
     * @param z
     * @param xWidth
     * @param yWidth
     * @param zWidth
     * @param ascentRate
     * @param trajectory
     */
    public Rocket(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        int ascentRate,
        double trajectory) {

        super(name, x, y, z, xWidth, yWidth, zWidth);
        this.ascentRate = ascentRate;
        this.trajectory = trajectory;
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Get the current value of ascentRate.
     * 
     * @return The value of ascentRate for this object.
     */
    public int getAscentRate() {
        return ascentRate;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of ascentRate for this object.
     * 
     * @param ascentRate
     *            The new value for ascentRate.
     */
    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of trajectory.
     * 
     * @return The value of trajectory for this object.
     */
    public double getTrajectory() {
        return trajectory;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of trajectory for this object.
     * 
     * @param trajectory
     *            The new value for trajectory.
     */
    public void setTrajectory(float trajectory) {
        this.trajectory = trajectory;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the rocket params
     */
    public boolean isNotValid() {
        if (super.isNotValid()) {
            return true;
        }
        if (ascentRate < 0) {
            return true;
        }
        return trajectory < 0;
    }
    
    /**
     * Returns the full string representation of the Rocket object:
     * Rocket {name} {x} {y} {z} {xwid} {ywid} {zwid} {ascentRate} {trajectory}
     * @return Full Rocket string
     */
    @Override
    public String toString() {
        return "Rocket " + super.toString() + " " 
             + getAscentRate() + " " + getTrajectory();
    }

}
