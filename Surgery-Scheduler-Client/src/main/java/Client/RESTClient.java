package Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.keyin.domain.Hospital.Hospital;
import com.keyin.domain.Surgery.Surgery;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RESTClient {

    private static final String BASE_URL = "http://localhost:8080";
    private Gson gson = new Gson();

    public String get(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
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

    public List<Surgery> fetchSurgeriesByHospitalAndDate(long hospitalId) throws Exception {
        String jsonResponse = get("/surgery/hospital-surgeries/" + hospitalId);
        return gson.fromJson(jsonResponse, TypeToken.getParameterized(List.class, Surgery.class).getType());
    }

    public List<Hospital> fetchHospitalsByCity(String city) throws Exception {
        String jsonResponse = get("/hospitals/city/" + city);
        return gson.fromJson(jsonResponse, TypeToken.getParameterized(List.class, Hospital.class).getType());
    }

    public List<Surgery> fetchSurgeriesByDoctor(long doctorId) throws Exception {
        String jsonResponse = get("/surgery/surgeries/doctor/" + doctorId);
        return gson.fromJson(jsonResponse, TypeToken.getParameterized(List.class, Surgery.class).getType());
    }
}
