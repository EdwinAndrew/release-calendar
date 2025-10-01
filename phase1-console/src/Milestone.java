import java.time.LocalDate;

public class Milestone {
    private LocalDate keyDate;
    private int id;
    private static int nextId = 1;
    private String milestoneName;
    private int releaseId;

    public Milestone(int releaseId, String milestoneName, LocalDate keyDate){
        this.id = nextId++;
        this.keyDate = keyDate;
        this.milestoneName = milestoneName;
        this.releaseId = releaseId; // link to parent release
    }

    public LocalDate getKeyDate() {
        return keyDate;
    }

    public String getMilestoneName(){
        return milestoneName;
    }

    public int getReleaseId(){
        return releaseId;
    }

    public void updateKeyDate(LocalDate newDate){
        this.keyDate = newDate;
    }

    public void updateMilestoneName(String updatedName){
        this.milestoneName = updatedName;
    }

    public String toString(){
        return String.format("%-5s | %-30s | %s | Release: %d",
                "#" + id, milestoneName, keyDate, releaseId);
    }
}
