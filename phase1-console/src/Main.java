import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Release release = new Release("v1.0", LocalDate.now(), LocalDate.now().plusDays(1));
        System.out.println(release);


        Release release2 = new Release("v2.0", LocalDate.now().plusDays(7), LocalDate.now().plusDays(30));
        System.out.println(release2);

        Milestone scopeDeadline = new Milestone(release.getId(), "Release Scope Deadline", LocalDate.of(2025, 10, 5));
        Milestone devComplete = new Milestone(release.getId(), "Dev Complete", LocalDate.of(2025, 10, 15));
        Milestone regressionStart = new Milestone(release.getId(), "Regression Start", LocalDate.of(2025, 10, 20));
        Milestone regressionEnd = new Milestone(release.getId(), "Regression End", LocalDate.of(2025, 10, 25));
        Milestone codeFreeze = new Milestone(release.getId(), "Code Freeze", LocalDate.of(2025, 10, 28));
        Milestone prodDeploy = new Milestone(release.getId(), "Production Deployment", LocalDate.of(2025, 10, 30));

        System.out.println("\nMilestones for " + release.getReleaseWindow() + ":");
        System.out.println(scopeDeadline);
        System.out.println(devComplete);
        System.out.println(regressionStart);
        System.out.println(regressionEnd);
        System.out.println(codeFreeze);
        System.out.println(prodDeploy);


        ReleaseManager releaseManager = new ReleaseManager();

        releaseManager.addRelease(release);
        releaseManager.addRelease(release2);
        System.out.println(releaseManager.getAllReleases());
        releaseManager.updateRelease(1,"R25.1.0", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        System.out.println(releaseManager.getAllReleases());





    }
}