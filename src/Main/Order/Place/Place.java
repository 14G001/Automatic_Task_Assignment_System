package Main.Order.Place;
import java.util.ArrayList;

public class Place {
    public static final int NEVER_CLEANED = 0;

    private String direction;
    private ArrayList<Integer> surfaceResidueTypes;
    private int numberOfPetsLiving;
    // private int lastCleanByEnterpriseDate; // <-- Enterprise knows this

    public Place(String direction, int[] surfaceResiduesToAdd, int numberOfPetsLiving) {
        this.direction = direction;
        this.surfaceResidueTypes = new ArrayList<Integer>();
        for (int surfaceResidueTypeID:surfaceResiduesToAdd) {
            this.surfaceResidueTypes.add(surfaceResidueTypeID);
        }
        this.numberOfPetsLiving = numberOfPetsLiving;
    }

    public String getDirection() { return direction; }
    public ArrayList<Integer> getSurfaceResidueTypes() { return surfaceResidueTypes; }
    public int getNumberOfPetsLiving() { return numberOfPetsLiving; }
}
