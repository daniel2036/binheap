package assn05;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom emergencyRoom=new SimpleEmergencyRoom();
        for(int i=1;i<=10;i++) {
            emergencyRoom.addPatient(i + "SS", i + 100);
        }
        Patient p = emergencyRoom.dequeue();
        System.out.println(p.getPriority());
        p = emergencyRoom.dequeue();
        System.out.println(p.getPriority());

       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */

 //var list =new List <int> [][1,3, 5, 4, 6, 13, 10, 9, 8, 15, 17];

        MaxBinHeapER maxBinHeapER = new MaxBinHeapER<>();
        maxBinHeapER.enqueue(1,5);
        maxBinHeapER.enqueue(2,7);
        maxBinHeapER.enqueue(3,5);
        maxBinHeapER.enqueue(4,5);
        maxBinHeapER.enqueue(5,5);
//        int size = maxBinHeapER.size();
//        var v1 = maxBinHeapER.dequeue();
//        var v2 = maxBinHeapER.dequeue();
//        var v3 = maxBinHeapER.dequeue();
//        var v4 = maxBinHeapER.dequeue();
//        var v5 = maxBinHeapER.dequeue();
//        var v1 =  maxBinHeapER.getMax();
//
//        var ps = maxBinHeapER.getAsArray();
//        for (int i=0;i<size;i++) {
//            System.out.print(","+ps[i].getPriority());
//        }
//
//
//        for(int  i=1;i<size;i++) {
//            System.out.println("");
//            System.out.println("Deque " + i);
//            int v3 = (int) maxBinHeapER.dequeue();
//            ps = maxBinHeapER.getAsArray();
//            for (int j = 0; j < maxBinHeapER.size(); j++) {
//                System.out.print("," + ps[j].getPriority());
//            }
//        }

        /*
        Part 3
         */
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
//        var v1 = transfer.dequeue();
//        var v2 = transfer.dequeue();
//        var v3 = transfer.dequeue();
//        var v4 = transfer.dequeue();

        /*
        Part4
         */
        compareRuntimes();
    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public  static  void DequeueAll(SimpleEmergencyRoom simpleER)
    {
        for (int i=0;i<10000;i++)
            simpleER.dequeue();
    }

    public  static  void DequeueAll(MaxBinHeapER complexER)
    {
        for (int i=0;i<10000;i++)
            complexER.dequeue();
    }
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (1) Here
        long startTime = System.nanoTime();
        DequeueAll(simplePQ);
        long elapsedTime = System.nanoTime()-startTime;
        results[0] = elapsedTime;
        results[1] = elapsedTime/100000;

        System.out.println("Simple Time:"+ results[1]);
        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (2) Here
        long astartTime = System.nanoTime();
        DequeueAll(binHeap);
        long aelapsedTime = System.nanoTime()-astartTime;
        results[2] = aelapsedTime;
        results[3] = aelapsedTime/100000;
        System.out.println("Complex Time:"+ results[3]);
        return results;
    }



}



