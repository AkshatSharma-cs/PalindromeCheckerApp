// PROBLEM 8

import java.util.*;

class Patient {
    private final String patientId;
    private final String name;
    private final int age;
    private final String diagnosis;

    public Patient(String patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDiagnosis() { return diagnosis; }

    @Override
    public String toString() {
        return "PatientID: " + patientId + ", Name: " + name +
                ", Age: " + age + ", Diagnosis: " + diagnosis;
    }
}

public class HospitalSystem {
    private final Map<String, Patient> patientRecords; // patientId -> Patient

    public HospitalSystem() {
        patientRecords = new HashMap<>();
    }

    // Add new patient record
    public void addPatient(Patient patient) {
        patientRecords.put(patient.getPatientId(), patient);
    }

    // Lookup patient by ID
    public Patient getPatient(String patientId) {
        return patientRecords.get(patientId);
    }

    // Search patients by diagnosis
    public List<Patient> searchByDiagnosis(String diagnosis) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patientRecords.values()) {
            if (p.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                result.add(p);
            }
        }
        return result;
    }

    // Demo
    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();

        // Add patients
        system.addPatient(new Patient("P001", "Alice", 30, "Diabetes"));
        system.addPatient(new Patient("P002", "Bob", 45, "Hypertension"));
        system.addPatient(new Patient("P003", "Charlie", 25, "Diabetes"));

        // Lookup by ID
        System.out.println(system.getPatient("P002"));

        // Search by diagnosis
        System.out.println("Patients with Diabetes:");
        system.searchByDiagnosis("Diabetes").forEach(System.out::println);
    }
}
