import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final int phase1 = 1;
        final int phase2 = 2;
        long u1TotalCost;
        long u2TotalCost;
        int phase1Rockets;
        int phase2Rockets;
        ArrayList<Item> phase1Items;
        ArrayList<Item> phase2Items;

        Simulation simulation = new Simulation();

        System.out.println("\t \t \t \t *** MISSION TO MARS *** \n");
        phase1Items = simulation.loadItems(1);
        phase2Items = simulation.loadItems(2);

        System.out.println("________________________________________\n" +
                "|order of simulation :                 |\n" +
                "|  -simulating u1 rockets for phase 1. |\n" +
                "|  -simulating u1 rockets for phase 2. |\n" +
                "|  -simulating u2 rockets for phase 1. |\n" +
                "|  -simulating u2 rockets for phase 2. |\n" +
                "|  -Conclusion.                        |\n" +
                "|______________________________________|\n");

        System.out.println("*** > \t Beginning simulation for U1 rockets...\n");
        System.out.println("SIMULATING  FOR  PHASE - 1 ");
        simulation.runSimulation(simulation.loadU1(phase1Items), 1);
        System.out.println("\nSimulation completed for phase-1. \n");
        phase1Rockets = U1.getRocketU1Counter();
        System.out.println("Total U1 rockets used for phase-1 : " + phase1Rockets + "\n");

        System.out.println("\nSIMULATING  FOR  PHASE - 2 \n");
        simulation.runSimulation(simulation.loadU1(phase2Items), 1);
        System.out.println("\t Simulation completed for phase-2 \n");
        phase2Rockets = U1.getRocketU1Counter() - phase1Rockets;
        System.out.println("Total U1 rockets used for phase-2 : " + phase2Rockets + "\n");

        System.out.println("A total of " + U1.getRocketU1Counter() + " rockets was used.");
        u1TotalCost = totalCost(1);

        System.out.println("\n\n *** > \t Beginning simulation for U2 rockets...\n");
        System.out.println("SIMULATING  FOR  PHASE - 1 ");
        simulation.runSimulation(simulation.loadU2(phase1Items), 2);
        System.out.println("\t Simulation completed for phase-1 \n");
        phase1Rockets = U2.getRocketU2Counter();
        System.out.println("Total U2 rockets used for phase-1 : " + phase1Rockets);

        System.out.println("\nSIMULATING  FOR  PHASE - 2 \n");
        simulation.runSimulation(simulation.loadU2(phase2Items), 2);
        System.out.println("\t Simulation completed for phase-2 \n");
        phase2Rockets = U2.getRocketU2Counter() - phase1Rockets;
        System.out.println("Total U2 rockets used for phase-2 : " + phase2Rockets);

        System.out.println("A total of " + U2.getRocketU2Counter() + " rockets was used." + "\n");
        u2TotalCost = totalCost(2);

        System.out.println("\nTotal budget required for U1 rockets : " + u1TotalCost);
        System.out.println("Total budget required for U2 rockets : " + u2TotalCost);

        if (u1TotalCost > u2TotalCost) {
            System.out.println("Using U2 rockets will be cheaper in this mission!");
        } else {
            System.out.println("Using U1 rockets will be cheaper in this mission!");
        }

    }

    public static long totalCost(int i) {
        return (i == 1) ? U1.getTotalCost() : U2.getTotalCost();
    }
}
