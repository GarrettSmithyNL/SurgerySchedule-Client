package Client;

import java.util.Scanner;

public class MenuSystem {

    public static void main(String[] args) {
        MenuSystem menu = new MenuSystem();
        menu.showMainMenu();
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Surgery Scheduler Menu ---");
            System.out.println("1. View Surgeries by Hospital and Date");
            System.out.println("2. View Hospitals by Province/City");
            System.out.println("3. View Surgeries by Doctor");
            System.out.println("4. View Upcoming Surgery for a Patient");
            System.out.println("5. Search Available Doctors");
            System.out.println("6. Search Surgeries by Time");
            System.out.println("7. Exit");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewSurgeriesByHospitalAndDate();
                    break;
                case 2:
                    viewHospitalsByProvinceOrCity();
                    break;
                case 3:
                    viewSurgeriesByDoctor();
                    break;
                case 4:
                    viewUpcomingSurgeryForPatient();
                    break;
                case 5:
                    searchAvailableDoctors();
                    break;
                case 6:
                    searchSurgeriesByTime();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Placeholder methods for the menu options.
    public void viewSurgeriesByHospitalAndDate() {
        System.out.println("Surgeries by Hospital and Date feature coming soon...");
        // Later: Add logic to call API to fetch and display surgeries by hospital and date
    }

    public void viewHospitalsByProvinceOrCity() {
        System.out.println("Hospitals by Province/City feature coming soon...");
        // Later: Add logic to call API to fetch and display hospitals by city or province
    }

    public void viewSurgeriesByDoctor() {
        System.out.println("Surgeries by Doctor feature coming soon...");
        // Later: Add logic to call API to fetch and display surgeries by doctor
    }

    public void viewUpcomingSurgeryForPatient() {
        System.out.println("Upcoming Surgery for Patient feature coming soon...");
        // Later: Add logic to call API to fetch and display upcoming surgeries for a patient
    }

    public void searchAvailableDoctors() {
        System.out.println("Search Available Doctors feature coming soon...");
        // Later: Add logic to call API to fetch and display available doctors for surgery
    }

    public void searchSurgeriesByTime() {
        System.out.println("Search Surgeries by Time feature coming soon...");
        // Later: Add logic to call API to search for surgeries by specific time
    }
}
