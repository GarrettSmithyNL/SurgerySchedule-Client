package Client;

import com.keyin.domain.Address.Address;
import com.keyin.domain.Doctor.Doctor;
import com.keyin.domain.Hospital.Hospital;
import com.keyin.domain.Patient.Patient;
import com.keyin.domain.Surgery.Surgery;
import com.keyin.domain.types.SurgeryTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestMenuSystem {
  @Mock
  private static RESTClient mockRestClient;

  private static Address mockAddress;
  private static Patient mockPatient;
  private static Hospital mockHospital;
  private static Doctor mockDoctor;
  private static Surgery mockSurgery;
  private static List<SurgeryTypes> mockTypes = List.of(SurgeryTypes.KNEE_REPLACEMENT);
  private static Date mockSurgeryStart;
  private static Date mockSurgeryEnd;


  @BeforeAll
  public static void setUp() {
    mockRestClient = new RESTClient();
    setUpAddress();
    setUpPatient();
    setUpHospital();
    setUpDoctor();
    setUpDates();
    setUpSurgery();
  }

  private static void setUpAddress() {
    mockAddress = new Address();
    mockAddress.setHouseNumber(1);
    mockAddress.setCity("St. John's");
    mockAddress.setStreet("Main Road");
    mockAddress.setPostalCode("A1A1A1");
  }

  private static void setUpPatient() {
    mockPatient = new Patient();
    mockPatient.setAddress(mockAddress);
    mockPatient.setName("John Doe");
    mockPatient.setMcpNumber("123456789");

  }

  private static void setUpHospital() {
    mockHospital = new Hospital();
    mockHospital.setName("General Hospital");
    mockHospital.setListOfSurgeriesThatCanBeDone(mockTypes);
  }

  private static void setUpDoctor() {
    mockDoctor = new Doctor();
    mockDoctor.setName("Dr. Jane Doe");
    mockDoctor.setListOfPossibleSurgeries(mockTypes);
  }

  private static void setUpDates() {
    mockSurgeryStart = new Date(43200000);
    mockSurgeryEnd = new Date(61200000);
  }

  private static void setUpSurgery() {
    mockSurgery = new Surgery();
    mockSurgery.setHospital(mockHospital);
    mockSurgery.setPatient(mockPatient);
    mockSurgery.setDoctorDoingSurgery(mockDoctor);
    mockSurgery.setTypeOfSurgery(mockTypes.getFirst());
    mockSurgery.setTimeStart(mockSurgeryStart);
    mockSurgery.setTimeEnd(mockSurgeryEnd);
  }

  @Test
  public void testViewSurgeriesByHospitalAndDate() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Surgery> mockSurgeryList = List.of(mockSurgery);
    Mockito.when(mockRestClient.fetchSurgeriesByHospitalAndDate(0)).thenReturn(mockSurgeryList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.viewSurgeriesByHospitalAndDate(0).contains("KNEE"));
  }

  @Test

  public void testViewHospitalsByProvinceOrCity() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Hospital> mockHospitalList = List.of(mockHospital);
    Mockito.when(mockRestClient.fetchHospitalsByCity("St. John's")).thenReturn(mockHospitalList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.viewHospitalsByProvinceOrCity("St. John's").contains("General Hospital"));
  }

  @Test
  public void testViewSurgeriesByDoctor() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Surgery> mockSurgeryList = List.of(mockSurgery);
    Mockito.when(mockRestClient.fetchSurgeriesByDoctor(0)).thenReturn(mockSurgeryList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.viewSurgeriesByDoctor(0).contains("KNEE"));
  }

  @Test
  public void testViewUpcomingSurgeryForPatient() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Surgery> mockSurgeryList = List.of(mockSurgery);
    Mockito.when(mockRestClient.fetchUpcomingSurgeryForPatient(0)).thenReturn(mockSurgeryList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.viewUpcomingSurgeryForPatient(0).contains("KNEE"));
  }

  @Test
  public void testSearchAvailableDoctors() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Doctor> mockDoctorList = List.of(mockDoctor);
    Mockito.when(mockRestClient.fetchAvailableDoctors("KNEE_REPLACEMENT")).thenReturn(mockDoctorList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.searchAvailableDoctors("KNEE_REPLACEMENT").contains("Doe"));
  }

  @Test
  public void testSearchSurgeriesByTime() throws Exception {
    MenuSystem mockMenu = new MenuSystem();
    List<Surgery> mockSurgeryList = List.of(mockSurgery);
    Mockito.when(mockRestClient.fetchSurgeriesByDate("1970-01-01")).thenReturn(mockSurgeryList);
    mockMenu.setRestClient(mockRestClient);
    Assertions.assertTrue(mockMenu.searchSurgeriesByTime("1970-01-01").contains("KNEE"));
  }
}
