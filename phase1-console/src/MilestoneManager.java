import java.time.LocalDate;
import java.util.ArrayList;

public class MilestoneManager {
    private ArrayList<Milestone> milestoneList;
    public MilestoneManager(){
        this.milestoneList = new ArrayList<>();
    }

    public void addMilestone(Milestone milestone){
        if(milestone == null){
            System.out.println("Invalid Milestone");
        }else{
            milestoneList.add(milestone);
        }
    }


    public ArrayList<Milestone> getMilestones(){
        return milestoneList;
    }

    public Milestone getMilestone(int id){
        for (Milestone milestone: milestoneList){
            if (milestone.getId() == id){
                return milestone;
            }
        }
        return null;
    }

    public ArrayList<Milestone> getMilestonesByRelease(int releaseId){
        ArrayList<Milestone> result = new ArrayList<>();
        for (Milestone milestone: milestoneList){
            if (milestone.getReleaseId() == releaseId){
                result.add(milestone);
            }
        }
        return result;
    }

    public void updateMilestoneDate(int id, LocalDate newDate){
        Milestone target = getMilestone(id);
        if (target != null) {
            target.updateKeyDate(newDate);
            System.out.println("Milestone #" + id + " date updated!");
        } else {
            System.out.println("Error: Milestone with ID " + id + " not found.");
        }
    }

    public void updateMilestoneName(int id, String newName){
        Milestone target = getMilestone(id);
        if (target != null) {
            target.updateMilestoneName(newName);
            System.out.println("Milestone #" + id + " name updated!");
        } else {
            System.out.println("Error: Milestone with ID " + id + " not found.");
        }
    }

    public void deleteMilestone(int id){
        Milestone target = getMilestone(id);

        if (target != null){
            milestoneList.remove(target);
            System.out.println(target + " was deleted.");
        }else{
            System.out.println("Error: Milestone with ID " + id + " not found.");
        }

    }



}
