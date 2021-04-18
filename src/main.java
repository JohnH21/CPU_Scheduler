import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Program: CPU Scheduler
 * Class: Operating System
 * Author: John Huynh
 * Date: March 4, 2020
 * Description: CPU Scheduler for Shortest Job First and Round Robin
 */


public class main {

    public static void main(String[] args) throws IOException {

        //Variables
        boolean schedule = true;

        Scanner console = new Scanner(System.in);
        //Compare the first iteration of the PCB to other PCB for the shortest job

        /**
         * Populating the PCB with CPU and IO Burst Values Used for both SJF and RR (IN ORDER)
         */
        ArrayList<Integer> CPU1 = new ArrayList<Integer>();
        ArrayList<Integer> IO1 =  new ArrayList<Integer>();
        CPU1.add(5);
        IO1.add(2);
        CPU1.add(6);
        IO1.add(3);
        CPU1.add(3);

//        CPU1.add(4);
//        IO1.add(3);
//        CPU1.add(5);
//        IO1.add(2);
//        CPU1.add(3);

        ArrayList<Integer> CPU2 = new ArrayList<Integer>();
        ArrayList<Integer> IO2 =  new ArrayList<Integer>();
        CPU2.add(3);
        IO2.add(1);
        CPU2.add(2);
        IO2.add(2);
        CPU2.add(4);
        IO2.add(1);
        CPU2.add(3);

//        CPU2.add(2);
//        IO2.add(1);
//        CPU2.add(4);

        ArrayList<Integer> CPU3 = new ArrayList<Integer>();
        ArrayList<Integer> IO3 =  new ArrayList<Integer>();
        CPU3.add(2);
        IO3.add(6);
        CPU3.add(2);
        IO3.add(6);
        CPU3.add(2);

//        CPU3.add(3);
//        IO3.add(2);
//        CPU3.add(2);
//        IO3.add(3);
//        CPU3.add(2);


        ArrayList<Integer> CPU4 = new ArrayList<Integer>();
        ArrayList<Integer> IO4 =  new ArrayList<Integer>();
        CPU4.add(3);
        IO4.add(4);
        CPU4.add(2);

//        CPU4.add(5);
//        IO4.add(3);
//        CPU4.add(2);

        ArrayList<Integer> CPU5 = new ArrayList<Integer>();
        ArrayList<Integer> IO5 =  new ArrayList<Integer>();
        CPU5.add(6);
        IO5.add(5);
        CPU5.add(6);

//        CPU5.add(4);
//        IO5.add(2);
//        CPU5.add(2);

        /**
         * Reverse Order
         */
        ArrayList<Integer> CPUr1 = new ArrayList<Integer>();
        ArrayList<Integer> IOr1 =  new ArrayList<Integer>();
        CPUr1.add(6);
        IOr1.add(5);
        CPUr1.add(6);

//        CPUr1.add(4);
//        IOr1.add(2);
//        CPUr1.add(2);

        ArrayList<Integer> CPUr2 = new ArrayList<Integer>();
        ArrayList<Integer> IOr2 =  new ArrayList<Integer>();
        CPUr2.add(3);
        IOr2.add(4);
        CPUr2.add(2);

//        CPUr2.add(5);
//        IOr2.add(3);
//        CPUr2.add(2);

        ArrayList<Integer> CPUr3 = new ArrayList<Integer>();
        ArrayList<Integer> IOr3 =  new ArrayList<Integer>();
        CPUr3.add(2);
        IOr3.add(6);
        CPUr3.add(2);
        IOr3.add(6);
        CPUr3.add(2);

//        CPUr3.add(3);
//        IOr3.add(2);
//        CPUr3.add(2);
//        IOr3.add(3);
//        CPUr3.add(2);

        ArrayList<Integer> CPUr4 = new ArrayList<Integer>();
        ArrayList<Integer> IOr4 =  new ArrayList<Integer>();
        CPUr4.add(3);
        IOr4.add(1);
        CPUr4.add(2);
        IOr4.add(2);
        CPUr4.add(4);
        IOr4.add(1);
        CPUr4.add(3);

//        CPUr4.add(2);
//        IOr4.add(1);
//        CPUr4.add(4);

        ArrayList<Integer> CPUr5 = new ArrayList<Integer>();
        ArrayList<Integer> IOr5 =  new ArrayList<Integer>();
        CPUr5.add(5);
        IOr5.add(2);
        CPUr5.add(6);
        IOr5.add(3);
        CPUr5.add(3);

//        CPUr5.add(4);
//        IOr5.add(3);
//        CPUr5.add(5);
//        IOr5.add(2);
//        CPUr5.add(3);


        /**
         * Creating Processes from 1 - 5 and hard coded data into the object (IN ORDER)
         */
        PCB pcb1 = new PCB(1, CPU1, IO1);
        PCB pcb2 = new PCB(2, CPU2, IO2);
        PCB pcb3 = new PCB(3, CPU3, IO3);
        PCB pcb4 = new PCB(4, CPU4, IO4);
        PCB pcb5 = new PCB(5, CPU5, IO5);

        PCB pcbr1 = new PCB(1, CPUr1,IOr1);
        PCB pcbr2 = new PCB(2, CPUr2,IOr2);
        PCB pcbr3 = new PCB(3, CPUr3,IOr3);
        PCB pcbr4 = new PCB(4, CPUr4,IOr4);
        PCB pcbr5 = new PCB(5, CPUr5,IOr5);
        /**
         * ArrayList that hold all PCB objects and populate the Job Queue
         */
        Scheduler session = new Scheduler();

        /**
         * Switch and while loop pick either SJF or RR
         */
        while(schedule){

            System.out.print("********************************************************\n" +
                             "1 = Shortest Job First (SJF)\n" +
                             "2 = Round Robin (RR)\n" +
                             "3 = Exit\n" +
                             "********************************************************\n" +
                             "Enter: ");
            int type = console.nextInt();

            switch (type){

                case 1: //Shortest Job First
                    System.out.print("********************************************************\n" +
                            "1 = Shortest Job First (SJF)\n" +
                            "2 = Shortest Job First Reverse\n" +
                            "3 = Exit\n" +
                            "********************************************************\n" +
                            "Enter: ");
                    int type2 = console.nextInt();

                    switch(type2){

                        case 1:
                            session.jobQueue.add(pcb1);
                            session.jobQueue.add(pcb2);
                            session.jobQueue.add(pcb3);
                            session.jobQueue.add(pcb4);
                            session.jobQueue.add(pcb5);
                            /**
                             * Runs through all the PCB and displays the result in a loop
                             */
                            session.SJF();

                            schedule = false;

                            break;

                        case 2:
                            session.jobQueue.add(pcbr1);
                            session.jobQueue.add(pcbr2);
                            session.jobQueue.add(pcbr3);
                            session.jobQueue.add(pcbr4);
                            session.jobQueue.add(pcbr5);
                            /**
                             * Runs through all the PCB and displays the result in a loop
                             */
                            session.SJF();

                            schedule = false;

                            break;

                        case 3:
                            schedule = false;
                            System.exit(type2);
                            break;
                    }//end inner switch

                    break;//end main case 1


                case 2: //Round Robin

                    session.jobQueue.add(pcb1);
                    session.jobQueue.add(pcb2);
                    session.jobQueue.add(pcb3);
                    session.jobQueue.add(pcb4);
                    session.jobQueue.add(pcb5);

                    /**
                     * Prompt User to enter a value for the Time Quantum
                     */
                    System.out.print("Enter a Time Quantum value: ");
                    int timeQuantum = console.nextInt();

                    /**
                     * Runs through all the PCB and displays the result in a loop
                     */

                    session.RR(timeQuantum);

                    schedule = false;

                    break;

                case 3: //Exit
                    schedule = false;
                    System.exit(type);
                    break;

            }//end switch
        }//end while loop


    }//end main
}//end class
