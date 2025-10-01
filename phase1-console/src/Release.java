import java.time.LocalDate;
public class Release{

    private static int nextId = 1;
    private int id;
    private String releaseWindow;
    private LocalDate startDate;
    private LocalDate endDate;

    public Release(String releaseWindow, LocalDate startDate, LocalDate endDate){
        this.id = nextId++;
        this.releaseWindow = releaseWindow;

        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Start date must be before or equal to end date!");
        }else{
            this.startDate = startDate;
            this.endDate = endDate;
        }
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

    public String toString(){
        return String.format("Release #%-3d | %-10s | %s to %s",
                id, releaseWindow, startDate, endDate);
    }

}