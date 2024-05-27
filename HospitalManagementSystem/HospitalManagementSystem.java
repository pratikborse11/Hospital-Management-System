package HospitalManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private Scanner scanner;
    private int nextPatientId;
    private int nextDoctorId;

    public HospitalManagementSystem(Scanner scanner) {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.scanner = scanner;
        this.nextPatientId = 1;
        this.nextDoctorId = 1;
        // Adding some sample doctors
        doctors.add(new Doctor(nextDoctorId++, "Dr. Smith", "Cardiology"));
        doctors.add(new Doctor(nextDoctorId++, "Dr. Johnson", "Neurology"));
        doctors.add(new Doctor(nextDoctorId++, "Dr. Williams", "Pediatrics"));
    }

    public void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();

        Patient newPatient = new Patient(nextPatientId++, name, age, gender);
        patients.add(newPatient);
        System.out.println("Patient Added Successfully!!");
    }

    public void viewPatients() {
        System.out.println("Patients: ");
        System.out.println("+------------+--------------------+----------+------------+");
        System.out.println("| Patient Id | Name               | Age      | Gender     |");
        System.out.println("+------------+--------------------+----------+------------+");
        for (Patient patient : patients) {
            System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", patient.getId(), patient.getName(), patient.getAge(), patient.getGender());
            System.out.println("+------------+--------------------+----------+------------+");
        }
    }

    public boolean getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void viewDoctors() {
        System.out.println("Doctors: ");
        System.out.println("+------------+--------------------+------------------+");
        System.out.println("| Doctor Id  | Name               | Specialization   |");
        System.out.println("+------------+--------------------+------------------+");
        for (Doctor doctor : doctors) {
            System.out.printf("| %-10s | %-18s | %-16s |\n", doctor.getId(), doctor.getName(), doctor.getSpecialization());
            System.out.println("+------------+--------------------+------------------+");
        }
    }

    public boolean getDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void bookAppointment() {
        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();

        if (getPatientById(patientId) && getDoctorById(doctorId)) {
            if (checkDoctorAvailability(doctorId, appointmentDate)) {
                Appointment newAppointment = new Appointment(patientId, doctorId, appointmentDate);
                appointments.add(newAppointment);
                System.out.println("Appointment Booked!");
            } else {
                System.out.println("Doctor not available on this date!!");
            }
        } else {
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public boolean checkDoctorAvailability(int doctorId, String appointmentDate) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId() == doctorId && appointment.getAppointmentDate().equals(appointmentDate)) {
                return false;
            }
        }
        return true;
    }

    public void viewAppointments() {
        System.out.println("Appointments: ");
        System.out.println("+------------+------------+------------+-----------------+");
        System.out.println("| Patient Id | Doctor Id  | Doctor Name| Appointment Date |");
        System.out.println("+------------+------------+------------+-----------------+");
        for (Appointment appointment : appointments) {
            Doctor doctor = findDoctorById(appointment.getDoctorId());
            if (doctor != null) {
                System.out.printf("| %-10s | %-10s | %-10s | %-15s |\n", appointment.getPatientId(), appointment.getDoctorId(), doctor.getName(), appointment.getAppointmentDate());
                System.out.println("+------------+------------+------------+-----------------+");
            }
        }
    }

    private Doctor findDoctorById(int doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                return doctor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalManagementSystem hms = new HospitalManagementSystem(scanner);

        while (true) {
            System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hms.addPatient();
                    break;
                case 2:
                    hms.viewPatients();
                    break;
                case 3:
                    hms.viewDoctors();
                    break;
                case 4:
                    hms.bookAppointment();
                    break;
                case 5:
                    hms.viewAppointments();
                    break;
                case 6:
                    System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                    return;
                default:
                    System.out.println("Enter valid choice!!!");
                    break;
            }
            System.out.println();
        }
    }
}
