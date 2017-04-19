package tictactoe;

/**
 * Created by Bobakanoosh on 4/18/2017.
 */
public class Marker {

    private String type;
    private boolean enabled;
    private int location;

    public Marker(String type, boolean enabled, int location) {

        this.type = type;
        this.enabled = enabled;
        this.location = location;

    }

    public Marker(){

        this.location = 0;
        this.type = "NONE";
        this.enabled = true;

    }

    @Override
    public String toString() {

        return "Marker{" +
                "type='" + type + '\'' +
                ", enabled=" + enabled +
                ", location=" + location +
                '}';

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

}
