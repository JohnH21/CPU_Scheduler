import java.util.ArrayList;

/**
 * Program: CPU Scheduler
 * Class: Operating System
 * Author: John Huynh
 * Date: March 4, 2020
 * Description: This is the Process object class (PCB)
 */
public class PCB {

    //Variables
    private int ID;
    private ArrayList CPUBurst;
    private ArrayList IOBurst;
    private int CPUIndex;
    private int IOIndex;
    private int CPUcounter;
    private int IOcounter;

    //Constructors
    public PCB(){
    }

    public PCB(int ID, ArrayList<Integer> CPUBurst, ArrayList<Integer> IOBurst) {
        this.ID = ID;
        this.CPUBurst = CPUBurst;
        this.IOBurst = IOBurst;
    }

    //Setters and Getters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList getCPUBurst() {
        return CPUBurst;
    }

    public void setCPUBurst(ArrayList CPUBurst) {
        this.CPUBurst = CPUBurst;
    }

    public ArrayList getIOBurst() {
        return IOBurst;
    }

    public void setIOBurst(ArrayList IOBurst) {
        this.IOBurst = IOBurst;
    }

    //methods
    //Maybe toString

    @Override
    public String toString() {
        return "PCB{" +
                "ID='" + ID + '\'' +
                ", CPUBurst=" + CPUBurst +
                ", IOBurst=" + IOBurst +
                '}';
    }

}
