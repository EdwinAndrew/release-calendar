import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    static void loadSampleData(ReleaseManager releaseManager, MilestoneManager milestoneManager) {
        // Create sample releases
        Release r1 = new Release("R25.1.0", LocalDate.of(2025, 10, 1), LocalDate.of(2025, 10, 31));
        Release r2 = new Release("R25.2.0", LocalDate.of(2025, 11, 1), LocalDate.of(2025, 11, 30));
        Release r3 = new Release("R26.1.0", LocalDate.of(2026, 1, 15), LocalDate.of(2026, 2, 28));

        releaseManager.addRelease(r1);
        releaseManager.addRelease(r2);
        releaseManager.addRelease(r3);

        // Create sample milestones for R25.1.0
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Release Scope Deadline", LocalDate.of(2025, 10, 5)));
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Dev Complete", LocalDate.of(2025, 10, 15)));
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Regression Start", LocalDate.of(2025, 10, 20)));
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Regression End", LocalDate.of(2025, 10, 25)));
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Code Freeze", LocalDate.of(2025, 10, 28)));
        milestoneManager.addMilestone(new Milestone(r1.getId(), "Production Deployment", LocalDate.of(2025, 10, 31)));

        // Create sample milestones for R25.2.0
        milestoneManager.addMilestone(new Milestone(r2.getId(), "Release Scope Deadline", LocalDate.of(2025, 11, 5)));
        milestoneManager.addMilestone(new Milestone(r2.getId(), "Dev Complete", LocalDate.of(2025, 11, 18)));
        milestoneManager.addMilestone(new Milestone(r2.getId(), "Code Freeze", LocalDate.of(2025, 11, 28)));
        milestoneManager.addMilestone(new Milestone(r2.getId(), "Production Deployment", LocalDate.of(2025, 11, 30)));

        // Create sample milestones for R26.1.0
        milestoneManager.addMilestone(new Milestone(r3.getId(), "Planning Complete", LocalDate.of(2026, 1, 20)));
        milestoneManager.addMilestone(new Milestone(r3.getId(), "Alpha Release", LocalDate.of(2026, 2, 10)));
        milestoneManager.addMilestone(new Milestone(r3.getId(), "Production Deployment", LocalDate.of(2026, 2, 28)));

        System.out.println("Sample data loaded: 3 releases with multiple milestones.");
    }



    static void main() {
        Scanner scanner = new Scanner(System.in);
        ReleaseManager releaseManager = new ReleaseManager();
        MilestoneManager milestoneManager = new MilestoneManager();
        loadSampleData(releaseManager, milestoneManager);
        while (true) {
            System.out.println("\n=== Release Calendar Menu ===");
            System.out.println("1: Add Release");
            System.out.println("2: View Releases");
            System.out.println("3: Add Milestone");
            System.out.println("4: View Milestones");
            System.out.println("5: View a Release's Milestones");
            System.out.println("6: Check Number of Days till Milestone deadline");
            System.out.println("7: Update Release");
            System.out.println("8: Delete Release");
            System.out.println("9: Update Milestone Date");
            System.out.println("10: Update Milestone Name");
            System.out.println("11: Delete Milestone");
            System.out.println("12: Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();
            if (choice.isEmpty() || choice.equals("12")){
                break;
            } else if (choice.equals("1")) {
                System.out.print("Enter Release Window: ");
                String releaseWindow = scanner.nextLine();

                try {
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());
                    Release release = new Release(releaseWindow, startDate, endDate);
                    releaseManager.addRelease(release);
                    System.out.println("✓ Release added successfully!");
                } catch (Exception e) {
                    System.out.println("Error: Invalid date format.");
                    continue;
                }

            } else if (choice.equals("2")) {
                ArrayList<Release> releases = releaseManager.getAllReleases();
                if (releases.isEmpty()) {
                    System.out.println("No releases found.");
                } else {
                    System.out.println("\n=== Release List ===");
                    for (Release r : releases) {
                        System.out.println(r.toString());
                    }
                }
            } else if (choice.equals("3")){
                try {
                    System.out.print("Enter Release ID: ");
                    int releaseId = Integer.parseInt(scanner.nextLine());

                    if (releaseManager.getRelease(releaseId) == null) {
                        System.out.println("Error: Release ID " + releaseId + " does not exist.");
                        continue;
                    }

                    System.out.print("Enter Milestone Name: ");
                    String milestoneName = scanner.nextLine();
                    System.out.print("Enter Key date (YYYY-MM-DD): ");
                    LocalDate keyDate = LocalDate.parse(scanner.nextLine());
                    Milestone milestone = new Milestone(releaseId, milestoneName, keyDate);
                    milestoneManager.addMilestone(milestone);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice.equals("4")) {
                ArrayList<Milestone> milestones = milestoneManager.getMilestones();
                if (milestones.isEmpty()) {
                    System.out.println("No milestones found.");
                } else {
                    System.out.println("\n=== Milestone List ===");
                    for (Milestone m : milestones) {
                        System.out.println(m.toString());
                    }
                }
            } else if (choice.equals("5")) {
                try {
                    System.out.println("Enter Release ID: ");
                    int releaseID = Integer.parseInt(scanner.nextLine());
                    System.out.println("Release ID   |  Release Window | Duration");
                    System.out.println(releaseManager.getRelease(releaseID));
                    System.out.println("=== Milestones ===");
                    ArrayList<Milestone> milestoneList = milestoneManager.getMilestonesByRelease(releaseID);
                    for (Milestone milestone: milestoneList){
                        System.out.println(milestone.toString());
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice.equals("6")) {
                System.out.println("Enter Milestone ID: ");
                try{
                    int milestoneId = Integer.parseInt(scanner.nextLine());
                    Milestone targetMilestone = milestoneManager.getMilestone(milestoneId);
                    if (targetMilestone == null){
                        System.out.println("Error: Milestone not found.");
                        continue;
                    }
                    LocalDate keyDate = targetMilestone.getKeyDate();
                    LocalDate today = LocalDate.now();
                    long difference = today.until(keyDate, ChronoUnit.DAYS);
                    if (difference == 1){
                        System.out.println("Milestone is " + difference +" day from today");
                    }else{
                        System.out.println("Milestone is " + difference +" days from today");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Milestone ID provide is not a number");
                }
            } else if (choice.equals("7")) {
                try{
                    System.out.print("Enter releaseID: ");
                    int releaseId = Integer.parseInt(scanner.nextLine());
                    Release target = releaseManager.getRelease(releaseId);

                    if (target == null) {
                        System.out.println("Error: Release not found.");
                        continue;
                    }

                    System.out.print("Enter new Release Window name: ");
                    String releaseWindow = scanner.nextLine();
                    System.out.print("Enter new start date (YYYY-MM-DD): ");
                    LocalDate newStart = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter new end date (YYYY-MM-DD): ");
                    LocalDate newEnd = LocalDate.parse(scanner.nextLine());
                    releaseManager.updateRelease(releaseId, releaseWindow, newStart, newEnd);
                    System.out.println("✓ Release updated successfully!");
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice.equals("8")) {
                try {
                    System.out.print("Enter release ID: ");
                    int releaseId = Integer.parseInt(scanner.nextLine());

                    ArrayList<Milestone> relatedMilestones = milestoneManager.getMilestonesByRelease(releaseId);
                    if (!relatedMilestones.isEmpty()) {
                        System.out.println("Warning: This release has " + relatedMilestones.size() + " milestone(s).");
                        System.out.print("Continue with deletion? (yes/no): ");
                        String confirm = scanner.nextLine();
                        if (!confirm.equalsIgnoreCase("yes")) {
                            System.out.println("Deletion cancelled.");
                            continue;
                        }
                    }

                    releaseManager.deleteRelease(releaseId);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice.equals("9")) {
                System.out.println("Enter milestone id: ");
                try {
                    int milestoneId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new date: ");
                    LocalDate newDate = LocalDate.parse(scanner.nextLine());
                    milestoneManager.updateMilestoneDate(milestoneId, newDate);
                    System.out.println("✓ Milestone updated successfully!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }   else if (choice.equals("10")) {
                System.out.println("Enter milestone id: ");
                try {
                    int milestoneId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter milestone name: ");
                    String milestoneName = scanner.nextLine();
                    milestoneManager.updateMilestoneName(milestoneId, milestoneName);
                    System.out.println("✓ Milestone updated successfully!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice.equals("11")) {
                System.out.println("Enter milestone id: ");
                try {
                    int milestoneId = Integer.parseInt(scanner.nextLine());
                    milestoneManager.deleteMilestone(milestoneId);
                    System.out.println("✓ Milestone deleted successfully!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid choice. Please enter 1-12.");
            }



        }
        scanner.close();
    }






}
