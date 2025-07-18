package project_pw;

import java.util.ArrayList;
import java.util.Scanner;

import project.Appointment;
import project.Doctor;
import project.Patient;

public class HospitalManagement {

    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Patients");
            System.out.println("5. View Doctors");
            System.out.println("6. View Appointments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {  // validation for number input
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> addPatient(scanner);
                case 2 -> addDoctor(scanner);
                case 3 -> scheduleAppointment(scanner);
                case 4 -> viewPatients();
                case 5 -> viewDoctors();
                case 6 -> viewAppointments();
                case 0 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close(); // ✅ Close scanner properly
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();  // ✅ allows full name

        System.out.print("Enter patient age: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid age: ");
            scanner.next();
        }
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();

        Patient patient = new Patient(name, gender, age);
        patients.add(patient);
        System.out.println("✅ Patient added successfully!");
    }

    private static void addDoctor(Scanner scanner) {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter doctor's specialty: ");
        String specialty = scanner.nextLine();

        Doctor doctor = new Doctor(name, specialty);
        doctors.add(doctor);
        System.out.println("✅ Doctor added successfully!");
    }

    private static void scheduleAppointment(Scanner scanner) {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("⚠️ Please add at least one patient and one doctor before scheduling an appointment.");
            return;
        }

        // Select Patient
        System.out.println("Select patient by index:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(i + ". " + patients.get(i));
        }
        int patientIndex = getValidIndex(scanner, patients.size(), "patient");

        // Select Doctor
        System.out.println("Select doctor by index:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println(i + ". " + doctors.get(i));
        }
        int doctorIndex = getValidIndex(scanner, doctors.size(), "doctor");

        // Date
        System.out.print("Enter appointment date (e.g., 2025-07-20): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(patients.get(patientIndex), doctors.get(doctorIndex), date);
        appointments.add(appointment);
        System.out.println("✅ Appointment scheduled successfully!");
    }

    // Helper for index validation
    private static int getValidIndex(Scanner scanner, int size, String type) {
        int index;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid " + type + " index: ");
                scanner.next();
            }
            index = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (index >= 0 && index < size) {
                break;
            } else {
                System.out.print("⚠️ Invalid index. Please enter a valid " + type + " index: ");
            }
        }
        return index;
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("⚠️ No patients added.");
        } else {
            System.out.println("===== Patient List =====");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("⚠️ No doctors added.");
        } else {
            System.out.println("===== Doctor List =====");
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("⚠️ No appointments scheduled.");
        } else {
            System.out.println("===== Appointment List =====");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
}
