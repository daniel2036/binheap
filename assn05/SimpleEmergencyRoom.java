package assn05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        if(patients.isEmpty()){
            return null;
        }
        int maxIndex = 0;
    	for(int i=1; i< patients.size(); i++) {
            if (patients.get(maxIndex).getPriority().compareTo(patients.get(i).getPriority()) < 0) {
                maxIndex = i;
            }
        }
        return patients.remove(maxIndex);
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
