import java.time.LocalDate;
public class Release{

    private static int nextId = 1;
    private int id;
    private String releaseWindow;
    private LocalDate startDate;
    private LocalDate endDate;

    public Release(String releaseWindow, LocalDate startDate, LocalDate endDate){
        // Validate inputs before assignment
        if (releaseWindow == null || releaseWindow.trim().isEmpty()) {
            throw new IllegalArgumentException("Release window cannot be null or empty!");
        }

        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null!");
        }

        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null!");
        }

        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Start date must be before or equal to end date!");
        }

        this.id = nextId++;
        this.releaseWindow = releaseWindow;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId(){
        return id;
    }

    public String getReleaseWindow(){
        return releaseWindow;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setReleaseWindow(String newName){
        this.releaseWindow = newName;
    }

    public void setStartDate(LocalDate newStart){
        this.startDate = newStart;
    }

    public void setEndDate(LocalDate newEnd){
        this.endDate = newEnd;
    }


    public String toString(){
        return String.format("Release #%-3d | %-10s | %s to %s",
                id, releaseWindow, startDate, endDate);
    }

}