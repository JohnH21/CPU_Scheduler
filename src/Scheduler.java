/**
 * Program: CPU Scheduler
 * Class: Operating System
 * Author: John Huynh
 * Date: March 4, 2020
 * Description: This class is for methods that are the same for both SJF and RR with Queue methods
 */

import java.io.IOException;
import java.util.ArrayList;

public class Scheduler{

    //Variables
    public ArrayList<PCB> jobQueue = new ArrayList<>();
    public ArrayList<PCB> readyQueue = new ArrayList<>(3);
    public ArrayList<PCB> CPU = new ArrayList<>();
    public ArrayList<PCB> diskQueue = new ArrayList<>();
    public ArrayList<PCB> IOWQueue = new ArrayList<>();

    /**
     * Print method
     */
    public void printTimer(int timer){
        if(CPU.isEmpty() && diskQueue.isEmpty() && IOWQueue.isEmpty() && readyQueue.isEmpty() && jobQueue.isEmpty()){
            System.out.print("Timer: " + timer);
            System.out.print("\n    CPU:          ");
            System.out.print("\n    Job Queue:    ");
            for (int i = 0; i < jobQueue.size(); i++) {
                System.out.print(jobQueue.get(i).getID() + " ");
            }
            System.out.print("\n    Ready Queue:  ");
            for (int i = 0; i < readyQueue.size(); i++) {
                System.out.print(readyQueue.get(i).getID() + " ");
            }
            System.out.print("\n    Disk Queue:   ");
            for (int i = 0; i < diskQueue.size(); i++) {
                System.out.print(diskQueue.get(i).getID() + " ");
            }
            System.out.print("\n    IOW Queue:    ");
            for (int i = 0; i < IOWQueue.size(); i++) {
                System.out.print(IOWQueue.get(i).getID() + " ");
            }
            System.out.println("\n");
        }
        else {
            System.out.print("Timer: " + timer);
            System.out.print("\n    CPU:          " + CPU.get(0).getID());
            System.out.print("\n    Job Queue:    ");
            for (int i = 0; i < jobQueue.size(); i++) {
                System.out.print(jobQueue.get(i).getID() + " ");
            }
            System.out.print("\n    Ready Queue:  ");
            for (int i = 0; i < readyQueue.size(); i++) {
                System.out.print(readyQueue.get(i).getID() + " ");
            }
            System.out.print("\n    Disk Queue:   ");
            for (int i = 0; i < diskQueue.size(); i++) {
                System.out.print(diskQueue.get(i).getID() + " ");
            }
            System.out.print("\n    IOW Queue:    ");
            for (int i = 0; i < IOWQueue.size(); i++) {
                System.out.print(IOWQueue.get(i).getID() + " ");
            }
            System.out.println("\n");
        }

    }

    /**
     * Shortest Job First
     */
    public String SJF() throws IOException {
        boolean running = true;
        int timer=0; //System timer

        /**
         * Algorithm that runs shortest job first
         */
        while(running) {

            /**
             * If the CPU is empty, push the smallest job next
             */
            if(CPU.isEmpty() && readyQueue.size() < 3) {
                /**
                 * If IOWait queue is empty, populate the readyQueue using the jobQueue
                 */
                if(IOWQueue.isEmpty() && readyQueue.size() < 3) {
                    for (int i = 0; i < 3; i++) {
                        if(readyQueue.size() == 3) {
                            break;
                        }
                        else{
                            if(!jobQueue.isEmpty()) {
                                readyQueue.add(jobQueue.get(0));
                                jobQueue.remove(0);
                            }
                            else{
                                break;
                            }

                        }
                    }
                }
                /**
                 * Else If IOWait queue is not empty, populate the readyQueue using IOWait queue
                 */
                else if(!IOWQueue.isEmpty() && readyQueue.size() < 3){
                    for(int i = 0; i < IOWQueue.size(); i++){
                        if(readyQueue.size() == 3) {
                            break;
                        }
                        else{
                            readyQueue.add(IOWQueue.get(i));
                            IOWQueue.remove(i);
                        }
                    }
                }


                /**
                 * Find the Shortest Job in ready Queue and push into CPU [Works]
                 * If 2 PCB has the small Burst Time, Compare ID
                 */
                int indexOfSmallest = 0;//return the index where the smallest CPU burst is
                int smallest = 999; //for the smallest CPU burst
                for(int i = 0; i < readyQueue.size(); i++){
                    if ((int)readyQueue.get(i).getCPUBurst().get(0) < smallest) {
                        smallest = (int)readyQueue.get(i).getCPUBurst().get(0); //finds the smallest CPU burst in the PCB
                        indexOfSmallest = i;//finds the smallest index

                        if((int)readyQueue.get(i).getCPUBurst().get(0) == smallest){
                            if(readyQueue.get(i).getID() < readyQueue.get(indexOfSmallest).getID()){
                                indexOfSmallest = readyQueue.get(i).getID();
                            }
                        }
                    }
                }

                CPU.add(readyQueue.get(indexOfSmallest)); //push the smallest PCB job to CPU
                readyQueue.remove(indexOfSmallest);
                /**
                 * While the CPU is still empty; push the smallest job into the CPU and before decrementing print the Queues
                 * Before decrementing the CPU Burst
                 * Check if all Queues are filled than print
                 */
                printTimer(timer);
            }
            else{ //If CPU is not empty continue running

                /**
                 * If device Queue is not empty, decrement along with CPU
                 */

                if(!diskQueue.isEmpty()){

                    /**
                     * Get PCB to decrement the CPUBurst down to 0 [Work in Progress]
                     * (If diskQueue and readyQueue is not empty)
                     */
                    for(int i = 0; i < (int)CPU.get(0).getCPUBurst().get(0); i++){

                        /**
                         * CPU decrementing
                         */
                        int newCPUBurst = (int)CPU.get(0).getCPUBurst().get(0) - 1; //creates new CPUBurst for next CPU run
                        CPU.get(0).getCPUBurst().set(0,newCPUBurst); //replace the value at index 0 in CPUBurst Arraylist
                        timer++; //Increment timer every time CPU Burst is subtract 1

                        for(int y = 0; y < diskQueue.size(); y++){
                            /**
                             * IO decrementing
                             */
                            int newIOBurst = (int)diskQueue.get(y).getIOBurst().get(0) - 1;
                            diskQueue.get(y).getIOBurst().set(0,newIOBurst);

                            /**
                             * Check if any PCB IOBurst at index 0 are == 0 than push to IOWait Queue
                             */
                            if((int)diskQueue.get(y).getIOBurst().get(0) == 0){
                                diskQueue.get(y).getIOBurst().remove(0);//once IOBurst hits 0 remove it from IO Arraylist
                                IOWQueue.add(diskQueue.get(y)); //pushes the PCB to IO Wait queue
                                diskQueue.remove(y); //remove from Disk queue
                                break;
                            }//end if
                        }

                        /**
                         * Check once CPU Burst in current PCB goes to 0 than push it to Device Queue
                         */
                        if((int)CPU.get(0).getCPUBurst().get(0) == 0){
                            CPU.get(0).getCPUBurst().remove(0);//Once the CPUBurst hits 0 than remove it from CPU Arraylist
                            if(CPU.get(0).getCPUBurst().isEmpty() && CPU.get(0).getIOBurst().isEmpty()){
                                CPU.remove(0);
                            }
                            else {
                                diskQueue.add(CPU.get(0));//pushes the PCB in CPU to Disk queue
                                CPU.remove(0); //remove PCB from the CPU
                            }
                            break;
                        }


                    }

                }//end if
                else{ //Only decrement CPU | diskQueue is empty, but CPU is not empty

                    /**
                     * Get PCB to minus the CPUBurst down to 0 [Work in Progress]
                     */
                    for(int i = 0; i <= (int)CPU.get(0).getCPUBurst().get(0); i++){

                        int newCPUBurst = (int)CPU.get(0).getCPUBurst().get(0) - 1;
                        CPU.get(0).getCPUBurst().set(0, newCPUBurst);
                        timer++; //Increment timer every time CPU Burst is subtract 1

                        /**
                         * Check once CPU Burst in current PCB goes to 0 than push it to Device Queue
                         */
                        if((int)CPU.get(0).getCPUBurst().get(0) == 0){
                            CPU.get(0).getCPUBurst().remove(0);//Once the CPUBurst hits 0 than remove it from CPU Arraylist
                            if(CPU.get(0).getCPUBurst().isEmpty() && CPU.get(0).getIOBurst().isEmpty()){
                                CPU.remove(0);
                            }
                            else {
                                diskQueue.add(CPU.get(0));//pushes the PCB in CPU to Disk queue
                                CPU.remove(0); //remove PCB from the CPU
                            }
                            break;
                        }

                    }//end for loop
                }//end else

            }//end else
            /**
             * Once all Queues are empty, stop running
             */
            if(CPU.isEmpty() && diskQueue.isEmpty() && IOWQueue.isEmpty() && readyQueue.isEmpty() && jobQueue.isEmpty()){
                printTimer(timer);
                running = false; //stops the while loop
            }

        }//end while loop

        return "Timer: " + timer + "\n" +
                "   CPU:           " + "\n" +
                "   Job Queue:     " + "\n" +
                "   Ready Queue:   " + "\n" +
                "   Device Queue:  " + "\n" +
                "   IOWait Queue:  " + "\n";
    }//end SJF


    /**
     * Round Robin
     */
    public String RR(int timeQuantum){
        boolean running = true;
        int timer=0; //System timer

        /**
         * Algorithm that runs shortest job first
         */
        while(running) {

            /**
             * If the CPU is empty, push the smallest job next
             */

            if (CPU.isEmpty() && readyQueue.size() < 3) {
                /**
                 * If IOWait queue is empty, populate the readyQueue using the jobQueue
                 */
                if (IOWQueue.isEmpty() && readyQueue.size() < 3) {
                    for (int i = 0; i < 3; i++) {
                        if (readyQueue.size() == 3) {
                            break;
                        }
                        else {
                            if (!jobQueue.isEmpty()) {
                                readyQueue.add(jobQueue.get(0));
                                jobQueue.remove(0);
                            }
                            else {
                                break;
                            }

                        }
                    }
                    CPU.add(readyQueue.get(0));
                    readyQueue.remove(0);
                }
                /**
                 * Else If IOWait queue is not empty, populate the readyQueue using IOWait queue
                 */
                else if (!IOWQueue.isEmpty() && readyQueue.size() < 3) {
                    for (int i = 0; i < IOWQueue.size(); i++) {
                        if (readyQueue.size() == 3) {
                            break;
                        } else {
                            readyQueue.add(IOWQueue.get(i));
                            IOWQueue.remove(i);
                        }
                    }
                    CPU.add(readyQueue.get(0));
                    readyQueue.remove(0);
                }

                printTimer(timer);
            }
            /**
             * Else If the CPU is empty and readyQueue is size 3
             */
            else if (CPU.isEmpty() && readyQueue.size() == 3) {
                /**
                 * First Comes First Served in Ready Queue for PCB to push to CPU
                 */
                CPU.add(readyQueue.get(0)); //push the next PCB job in line to CPU
                readyQueue.remove(0);

                /**
                 * While the CPU is still empty; push the smallest job into the CPU and before decrementing print the Queues
                 * Before decrementing the CPU Burst
                 * Check if all Queues are filled than print
                 */
                printTimer(timer);

            }
            else{ //If CPU is not empty; continue running
                /**
                 * If device Queue is not empty, decrement along with CPU
                 */

                if(!diskQueue.isEmpty()){

                    /**
                     * Get PCB to decrement the CPUBurst down to timeQuantum [Work in Progress]
                     * (If diskQueue and readyQueue is not empty)
                     */
                    int timeIndex = 0;
                    for(int i = 0; i < timeQuantum; i++){
                        if(!CPU.isEmpty()) {
                            /**
                             * CPU decrementing; to Time Quantum
                             */
                            int newCPUBurst = (int) CPU.get(0).getCPUBurst().get(0) - 1; //creates new CPUBurst for next CPU run
                            CPU.get(0).getCPUBurst().set(0, newCPUBurst); //replace the value at index 0 in CPUBurst Arraylist
                            timer++; //Increment timer every time CPU Burst is subtract 1
                            timeIndex++;

                            for (int y = 0; y < diskQueue.size(); y++) {
                                /**
                                 * IO decrementing; to Time Quantum
                                 */
                                int newIOBurst = (int) diskQueue.get(y).getIOBurst().get(0) - 1;
                                diskQueue.get(y).getIOBurst().set(0, newIOBurst);

                                /**
                                 * Check if any PCB IOBurst at index 0 are == 0 than push to IOWait Queue
                                 */
                                if ((int) diskQueue.get(y).getIOBurst().get(0) == 0) {
                                    diskQueue.get(y).getIOBurst().remove(0);//once IOBurst hits 0 remove it from IO Arraylist
                                    IOWQueue.add(diskQueue.get(y)); //pushes the PCB to IO Wait queue
                                    diskQueue.remove(y); //remove from Disk queue
                                }//end if
                            }//end for loop

                            /**
                             * Check once CPU Burst in current PCB goes to 0 than push it to Device Queue
                             */
                            if ((int) CPU.get(0).getCPUBurst().get(0) == 0) {
                                CPU.get(0).getCPUBurst().remove(0);//Once the CPUBurst hits 0 than remove it from CPU Arraylist
                                if (CPU.get(0).getCPUBurst().isEmpty() && CPU.get(0).getIOBurst().isEmpty()) {
                                    CPU.remove(0);
                                } else {
                                    diskQueue.add(CPU.get(0));//pushes the PCB in CPU to Disk queue
                                    CPU.remove(0); //remove PCB from the CPU
                                }
                                break;
                            }
                            /**
                             * Once CPU completes the timeQuantum; if there are still burst left push back into readyQueue
                             */
                            else if ((int) CPU.get(0).getCPUBurst().get(0) > 0 && timeIndex == timeQuantum) {
                                readyQueue.add(CPU.get(0)); //add back the PCB from the CPU back to readyQueue
                                CPU.remove(0); //removes PCB from CPU
                            }//end else if

                        }//end if
                        else{

                            break;
                        }
                    }//end outer for loop CPU Decrement


                }//end if
                /**
                 * Only decrements CPU
                 */
                else{ //Only decrement CPU | diskQueue is empty, but CPU is not empty

                    /**
                     * Get PCB to minus the CPUBurst down to timeQuantum
                     */
                    int timeIndex = 0;

                    for(int i = 0; i < timeQuantum; i++){
                        if(!CPU.isEmpty()) {
                            int newCPUBurst = (int) CPU.get(0).getCPUBurst().get(0) - 1;
                            CPU.get(0).getCPUBurst().set(0, newCPUBurst);
                            timer++; //Increment timer every time CPU Burst is subtract 1
                            timeIndex++;

                            /**
                             * Check once CPU Burst in current PCB goes to 0 than push it to Device Queue
                             */

                            if ((int) CPU.get(0).getCPUBurst().get(0) == 0) {
                                CPU.get(0).getCPUBurst().remove(0);//Once the CPUBurst hits 0 than remove it from CPU Arraylist
                                if (CPU.get(0).getCPUBurst().isEmpty() && CPU.get(0).getIOBurst().isEmpty()) {
                                    CPU.remove(0);
                                } else {
                                    diskQueue.add(CPU.get(0));//pushes the PCB in CPU to Disk queue
                                    CPU.remove(0); //remove PCB from the CPU
                                }
                                break;
                            }
                            /**
                             * Once CPU completes the timeQuantum; if there are still burst left push back into readyQueue
                             */
                            else if ((int) CPU.get(0).getCPUBurst().get(0) > 0 && timeIndex == timeQuantum) {
                                readyQueue.add(CPU.get(0)); //add back the PCB from the CPU back to readyQueue
                                CPU.remove(0); //removes PCB from CPU
                            }//end else if
                        }
                        else{
                            break;
                        }

                    }//end for loop

                }//end else

            }//end else
            /**
             * Once all Queues are empty, stop running
             */
            if(CPU.isEmpty() && diskQueue.isEmpty() && IOWQueue.isEmpty() && readyQueue.isEmpty() && jobQueue.isEmpty()){
                printTimer(timer);
                running = false; //stops the while loop
            }

        }//end while loop

        return "Timer: " + timer + "\n" +
                "   CPU:           " + "\n" +
                "   Job Queue:     " + "\n" +
                "   Ready Queue:   " + "\n" +
                "   Device Queue:  " + "\n" +
                "   IOWait Queue:  " + "\n";
    }//end Round Robin



}
