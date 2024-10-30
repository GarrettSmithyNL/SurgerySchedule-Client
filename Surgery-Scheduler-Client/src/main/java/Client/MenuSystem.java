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

    private Gson gson = new Gson();
    private RESTClient restClient = new RESTClient();

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

    // 1. Method to fetch surgeries by hospital and date
    public void viewSurgeriesByHospitalAndDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Hospital ID: ");
        long hospitalId = scanner.nextLong();

        try {
            String jsonResponse = getApiResponse("/surgery/hospital-surgeries/" + hospitalId);
            parseAndDisplaySurgeriesByHospitalAndDate(jsonResponse);
        } catch (Exception e) {
            System.out.println("Error retrieving surgeries: " + e.getMessage());
        }
    }

    // 2. Method to fetch hospitals by city
    public void viewHospitalsByProvinceOrCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter City: ");
        String city = scanner.next();
        restClient.fetchAndDisplayHospitalsByCity(city, this);
    }


    public void displayHospitals(List<Hospital> hospitals) {
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found in this city.");
            return;
        }
        for (Hospital hospital : hospitals) {
            System.out.println("Hospital ID: " + hospital.getId() + ", Name: " + hospital.getName());
        }
    }

    // 3. Placeholder for surgeries by doctor
    public void viewSurgeriesByDoctor() {
        System.out.println("Feature to view surgeries by doctor is pending setup.");
    }

    // 4. Placeholder for upcoming surgery by patient
    public void viewUpcomingSurgeryForPatient() {
        System.out.println("Feature to view upcoming surgeries by patient is pending setup.");
    }

    // 5. Method to search available doctors by surgery type
    public void searchAvailableDoctors() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Surgery Type: ");
        String surgeryType = scanner.next();

        try {
            String jsonResponse = getApiResponse("/doctors/possible-surgery/" + surgeryType);
            List<Doctor> availableDoctors = parseJsonResponse(jsonResponse, Doctor.class);
            displayDoctors(availableDoctors);
        } catch (Exception e) {
            System.out.println("Error retrieving available doctors: " + e.getMessage());
        }
    }

    // 6. Placeholder for searching surgeries by time
    public void searchSurgeriesByTime() {
        System.out.println("Feature to search surgeries by time is pending setup.");
    }

    // Helper method to make an API call
    public String getApiResponse(String endpoint) throws Exception {
        String baseUrl = "http://localhost:8080"; // Adjust if needed
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder response = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        conn.disconnect();
        return response.toString();
    }

    // Generic method to parse JSON into a list of specified type
    public <T> List<T> parseJsonResponse(String jsonResponse, Class<T> classType) {
        return gson.fromJson(jsonResponse, TypeToken.getParameterized(List.class, classType).getType());
    }

    // Parsing and displaying surgeries by hospital and date
    private void parseAndDisplaySurgeriesByHospitalAndDate(String jsonResponse) {
        List<Surgery> surgeries = parseJsonResponse(jsonResponse, Surgery.class);
        if (surgeries.isEmpty()) {
            System.out.println("No surgeries found for this hospital.");
            return;
        }
        for (Surgery surgery : surgeries) {
            System.out.println("Surgery ID: " + surgery.getId() + ", Type: " + surgery.getTypeOfSurgery() + ", Date: " + surgery.getTimeStart());
        }
    }

    // Parsing and displaying hospitals by city
    private void parseAndDisplayHospitalsByCity(String jsonResponse) {
        List<Hospital> hospitals = parseJsonResponse(jsonResponse, Hospital.class);
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found in this city.");
            return;
        }
        for (Hospital hospital : hospitals) {
            System.out.println("Hospital ID: " + hospital.getId() + ", Name: " + hospital.getName());
        }
    }

    // Displaying doctor information
    private void displayDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("No available doctors for this surgery type.");
            return;
        }
        for (Doctor doctor : doctors) {
            System.out.println("Doctor ID: " + doctor.getId() + ", Name: " + doctor.getName());
        }
    }


}

