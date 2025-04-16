import java.util.*;

// === Data Classes ===
class Hospital {
    String name;
    String location;

    Hospital(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String toString() {
        return name + " - " + location;
    }
}

class Doctor {
    String name;
    String specialization;
    String schedule;
    String contactinfo;

    static String[] scheduleOptions = {
        "Mon-Fri, 9AM - 5PM",
        "Tue-Sat, 10AM - 6PM",
        "Mon, Wed, Fri - 8AM - 2PM",
        "Thu-Sun, 12PM - 8PM",
        "Weekends Only, 10AM - 4PM"
    };

    static int nextScheduleIndex = 0;

    Doctor(String name, String specialization, String contactinfo) {
        this.name = name;
        this.specialization = specialization;
        this.contactinfo = contactinfo;
        this.schedule = autoAssignSchedule();
    }

    private String autoAssignSchedule() {
        String assignedSchedule = scheduleOptions[nextScheduleIndex];
        nextScheduleIndex = (nextScheduleIndex + 1) % scheduleOptions.length;
        return assignedSchedule;
    }

    public String toString() {
        return name + " (" + specialization + "), Schedule: " + schedule + ", Contact Info: " + contactinfo;
    }
}


class Patient {
    String name;
    int age ,contact_no ,adhar_no;
    String history = "";

    Patient(String name, int age , int contact_no ,int adhar_no) {
        this.name = name;
        this.age = age;
	this.contact_no = contact_no;
	this.adhar_no = adhar_no;

    }

    public String toString() {
	return name + ", Age: " + age + ", Adhar Number: " + adhar_no + ", Contact_no: " + contact_no;

    }
}

class Appointment {
    String patientName;
    String doctorName;
    String date;
    String contactNumber;

    Appointment(String patientName, String doctorName, String date , String contactNumber) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
	this.contactNumber =  contactNumber ;
    }

    public String toString() {
        return "Appointment: " + patientName + " Contact: " + contactNumber + " with Dr. " + doctorName + " on " + date;
    }
}

// === Admin Module ===
class AdminModule {
    List<Hospital> hospitals = new ArrayList<>();
    List<Doctor> doctors = new ArrayList<>();

    void addHospital(String name, String location) {
        hospitals.add(new Hospital(name, location));
        System.out.println("Hospital added.");
    }

    void removeHospital(String name) {
        hospitals.removeIf(h -> h.name.equalsIgnoreCase(name));
        System.out.println("Hospital removed if it existed.");
    }

    void addDoctor(String name, String specialization , String contactinfo) {
        doctors.add(new Doctor(name, specialization ,contactinfo));
        System.out.println("Doctor added.");
    }

    void removeDoctor(String name) {
        doctors.removeIf(d -> d.name.equalsIgnoreCase(name));
        System.out.println("Doctor removed if it existed.");
    }

    void listHospitals() {
        System.out.println("Hospitals:");
        hospitals.forEach(System.out::println);
    }

    void listDoctors() {
        System.out.println("Doctors:");
        doctors.forEach(System.out::println);
    }

    List<Doctor> getDoctors() {
        return doctors;
    }
}

// === Doctor Module ===
class DoctorModule {
    void requestLabTest(String patientName, String testType) {
        System.out.println("Lab Test Requested for " + patientName + ": " + testType);
    }
}

// === Patient Module ===
class PatientModule {
    List<Patient> patients = new ArrayList<>();
    List<Appointment> appointments = new ArrayList<>();

    void registerPatient(String name, int age ,int contact_no ,int adhar_no) {
        patients.add(new Patient(name, age , contact_no ,adhar_no));
        System.out.println("Patient registered.");
    }

    void bookAppointment(String patientName, String doctorName, String date , String contactNumber) {
        appointments.add(new Appointment(patientName, doctorName, date ,contactNumber ));
        System.out.println("Appointment booked.");
    }

    void generateBill(String patientName, double amount) {
        System.out.println("Billing for " + patientName + ": ₹" + amount);
    }

    void trackHistory(String patientName) {
        System.out.println("Tracking history for " + patientName);
        // Placeholder logic
        System.out.println("Visited: General Checkup, Lab Test:  Blood test");
    }
}

// === Main System ===
public class IHMS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminModule admin = new AdminModule();
        DoctorModule doctor = new DoctorModule();
        PatientModule patient = new PatientModule();

        while (true) {
            System.out.println("\n--- Integrated Hospital Management System (IHMS) ---");
            System.out.println("1. Admin Module");
            System.out.println("2. Doctor Module");
            System.out.println("3. Patient Module");
            System.out.println("4. Exit");
            System.out.print("Choose Module: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1: // Admin
                    System.out.println("\n--- Admin Operations ---");
                    System.out.println("1. Add Hospital");
                    System.out.println("2. Remove Hospital");
                    System.out.println("3. Add Doctor");
                    System.out.println("4. Remove Doctor");
                    System.out.println("5. List Hospitals");
                    System.out.println("6. List Doctors");
                    System.out.print("Enter option: ");
                    int a = sc.nextInt();
                    sc.nextLine();
                    switch (a) {
                        case 1:
                            System.out.print("Hospital Name: ");
                            String hName = sc.nextLine();
                            System.out.print("Location: ");
                            String hLoc = sc.nextLine();
                            admin.addHospital(hName, hLoc);
                            break;
                        case 2:
                            System.out.print("Hospital Name to remove: ");
                            admin.removeHospital(sc.nextLine());
                            break;
                        case 3:
                            System.out.print("Doctor Name: ");
                            String dName = sc.nextLine();
                            System.out.print("Specialization: ");
                            String spec = sc.nextLine();
			    System.out.print("Contact Info: ");
                            String contact = sc.nextLine();
                            admin.addDoctor(dName, spec ,contact);
                            break;
                        case 4:
                            System.out.print("Doctor Name to remove: ");
                            admin.removeDoctor(sc.nextLine());
                            break;
                        case 5:
                            admin.listHospitals();
                            break;
                        case 6:
                            admin.listDoctors();
                            break;
                    }
                    break;

                case 2: // Doctor
                    System.out.println("\n--- Doctor Operations ---");
                    System.out.print("Enter Patient Name for Lab Test: ");
                    String pName = sc.nextLine();
                    System.out.print("Enter Test Type: ");
                    String test = sc.nextLine();
                    doctor.requestLabTest(pName, test);
                    break;

                case 3: // Patient
                    System.out.println("\n--- Patient Operations ---");
                    System.out.println("1. Register");
                    System.out.println("2. Book Appointment");
                    System.out.println("3. Billing & Payment");
                    System.out.println("4. Track History");
                    System.out.print("Enter option: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    switch (p) {
                        case 1:
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("Age: ");
                            int age = sc.nextInt();
                            sc.nextLine();
			    System.out.print("Contact Number: ");
                            int PContact = sc.nextInt();
                            sc.nextLine();
			    System.out.print("Adhar Number: ");
                            int AdharNo = sc.nextInt();
                            sc.nextLine();
                            patient.registerPatient(name, age , PContact ,AdharNo);
                            break;
                        case 2:
                            System.out.print("Patient Name: ");
                            String patName = sc.nextLine();
                            System.out.print("Doctor Name: ");
                            String docName = sc.nextLine();
                            System.out.print("Date (YYYY-MM-DD): ");
                            String date = sc.nextLine();
			    System.out.print("Contact Number: ");
                            String PetContact = sc.nextLine();
                            sc.nextLine();
                            patient.bookAppointment(patName, docName, date ,PetContact);
                            break;
                        case 3:
                            System.out.print("Patient Name: ");
                            String billName = sc.nextLine();
                            System.out.print("Amount: ");
                            double amount = sc.nextDouble();
                            patient.generateBill(billName, amount);
                            break;
                        case 4:
                            System.out.print("Patient Name: ");
                            String histName = sc.nextLine();
                            patient.trackHistory(histName);
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Exiting IHMS.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
