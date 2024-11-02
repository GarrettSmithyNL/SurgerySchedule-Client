package Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.keyin.domain.Doctor.Doctor;
import com.keyin.domain.Hospital.Hospital;
import com.keyin.domain.Surgery.Surgery;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class MenuSystem {

    private RESTClient restClient;

    public static void main(String[] args) {
        MenuSystem menu = new MenuSystem();
        menu.showMainMenu();
    }

    public void showMainMenu() {
        restClient = new RESTClient();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Surgery Scheduler Menu ---");
            System.out.println("1. View Surgeries by Hospital");
            System.out.println("2. View Hospitals by City");
            System.out.println("3. View Surgeries by Doctor");
            System.out.println("4. View Upcoming Surgery for a Patient");
            System.out.println("5. Search Available Doctors");
            System.out.println("6. Search Surgeries by Time");
            System.out.println("7. Exit");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Hospital ID: ");
                    long hospitalId = scanner.nextLong();
                    System.out.println(viewSurgeriesByHospitalAndDate(hospitalId));
                    break;
                case 2:
                    System.out.print("Enter City: ");
                    String city = scanner.next();
                    System.out.println(viewHospitalsByProvinceOrCity(city));
                    break;
                case 3:
                    System.out.print("Enter Doctor ID: ");
                    long doctorId = scanner.nextLong();
                    System.out.println(viewSurgeriesByDoctor(doctorId));
                    break;
                case 4:
                    System.out.print("Enter Patient ID: ");
                    long patientId = scanner.nextLong();
                    System.out.println(viewUpcomingSurgeryForPatient(patientId));
                    break;
                case 5:
                    System.out.print("Enter Surgery Type: ");
                    String surgeryType = scanner.next();
                    System.out.println(searchAvailableDoctors(surgeryType));
                    break;
                case 6:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.next();
                    System.out.println(searchSurgeriesByTime(date));
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

    // 1. Method to fetch surgeries by hospital and date
    public String viewSurgeriesByHospitalAndDate(long hospitalId) {
        String results = "";
        try {
            List<Surgery> surgeries = restClient.fetchSurgeriesByHospitalAndDate(hospitalId);

            if (surgeries.isEmpty()) {
                results = "No surgeries found for this hospital.";
            } else {
                for (Surgery surgery : surgeries) {
                    results += "Surgery ID: " + surgery.getId() + ", Type: " + surgery.getTypeOfSurgery() + ", Date: " + surgery.getTimeStart() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving surgeries: " + e.getMessage();
        }
      return results;
    }

    // 2. Method to fetch hospitals by city
    public String viewHospitalsByProvinceOrCity(String city) {
        String results = "";
        try {
            List<Hospital> hospitals = restClient.fetchHospitalsByCity(city);
            if (hospitals.isEmpty()) {
                results = "No hospitals found in this city.";
            } else {
                for (Hospital hospital : hospitals) {
                    results += "Hospital ID: " + hospital.getId() + ", Name: " + hospital.getName() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving hospitals: " + e.getMessage();
        }
        return results;
    }

    // 3. Placeholder for surgeries by doctor
    public String viewSurgeriesByDoctor(long doctorId) {
        String results = "";
        try {
            List<Surgery> surgeries = restClient.fetchSurgeriesByDoctor(doctorId);

            if (surgeries.isEmpty()) {
                results = "No surgeries found for this doctor.";
            } else {
                for (Surgery surgery : surgeries) {
                    results += "Surgery ID: " + surgery.getId() + ", Type: " + surgery.getTypeOfSurgery() + ", Hospital: " + surgery.getHospital().getName() + ", Date: " + surgery.getTimeStart() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving surgeries by doctor: " + e.getMessage();
        }
        return results;
    }
    // 4. Placeholder for upcoming surgery by patient
    public String viewUpcomingSurgeryForPatient(long patientId) {
        String results = "";
        try {
            List<Surgery> surgeries = restClient.fetchUpcomingSurgeryForPatient(patientId);
            if (surgeries.isEmpty()) {
                results = "No upcoming surgeries found for this patient.";
            } else {
                for (Surgery surgery : surgeries) {
                    results += "Surgery ID: " + surgery.getId() + ", Type: " + surgery.getTypeOfSurgery() + ", Date: " + surgery.getTimeStart() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving upcoming surgery for patient: " + e.getMessage();
        }
        return results;
    }

    // 5. Method to search available doctors by surgery type
    public String searchAvailableDoctors(String surgeryType) {
        String results = "";
        try {
            List<Doctor> availableDoctors = restClient.fetchAvailableDoctors(surgeryType);
            if (availableDoctors.isEmpty()) {
                results = "No available doctors for this surgery type.";
            } else {
                for (Doctor doctor : availableDoctors) {
                    results += "Doctor ID: " + doctor.getId() + ", Name: " + doctor.getName() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving available doctors: " + e.getMessage();
        }
        return results;
    }

    // 6. Placeholder for searching surgeries by time
    public String searchSurgeriesByTime(String date) {
        String results = "";
        try {
            List<Surgery> surgeries = restClient.fetchSurgeriesByDate(date);
            if (surgeries.isEmpty()) {
                results = "No surgeries found for this date.";
            } else {
                for (Surgery surgery : surgeries) {
                    results += "Surgery ID: " + surgery.getId() + ", Type: " + surgery.getTypeOfSurgery() + ", Hospital: " + surgery.getHospital().getName() + ", Date: " + surgery.getTimeStart() + "\n";
                }
            }
        } catch (Exception e) {
            results = "Error retrieving surgeries by date: " + e.getMessage();
        }
        return results;
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }
        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }
}

