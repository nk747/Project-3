import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Simulation {
    private int maxWeight = 0;
    private ArrayList<Item> listOfItems;
    private File phase1 = new File("phase-1.txt");
    private File phase2 = new File("phase-2.txt");
    private ArrayList<Rocket> rocketU1;
    private ArrayList<Rocket> rocketU2;
    private boolean hasLanded = true;

    public Simulation() {
    }

    public ArrayList<Item> loadItems(int phase) throws FileNotFoundException {
        switch (phase) {
            case 1:
                AddItems(phase1);
                break;
            case 2:
                AddItems(phase2);
                break;
            default:
                System.out.println("no such phase!");
        }
        return listOfItems;
    }

    private void AddItems(File file) throws FileNotFoundException {
        listOfItems = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String object = scanner.nextLine();
            String[] arrOfname = object.split("=", 0);
            Item item = new Item();
            item.name = arrOfname[0];
            item.weight = Integer.parseInt(arrOfname[1]);
            listOfItems.add(item);
        }
    }


    public ArrayList<Rocket> loadU1(ArrayList<Item> U1Cargo) {
        System.out.println("Loading U1 rockets...");
        rocketU1 = new ArrayList<>();
        Rocket rocket = new U1();
        Iterator iterator = U1Cargo.iterator();
        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU1.add(rocket);
                rocket = new U1();
                System.out.println("Creating new U1 rocket...");
                rocket.carry(item);
            }
            if (!iterator.hasNext()) {
                rocketU1.add(rocket);
            }
        }
        return rocketU1;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> U2Cargo) {
        System.out.println("Loading U2 rockets...");
        rocketU2 = new ArrayList<>();
        Rocket rocket = new U2();
        Iterator iterator = U2Cargo.iterator();
        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU2.add(rocket);
                rocket = new U2();
                System.out.println("Creating new U2 rocket..");
                rocket.carry(item);
            }
            if (!iterator.hasNext()) {
                rocketU2.add(rocket);
            }
        }
        return rocketU2;
    }

    public void runSimulation(ArrayList<Rocket> rockets, int i) {
        for (Rocket rocket : rockets) {
            while (!rocket.launch()) {
                launchSimulation(i);
            }
            while (!rocket.land()) {
                while (!rocket.launch()) {
                    launchSimulation(i);
                }
                landSimulation(i);
            }
        }
    }

    public void launchSimulation(int i) {
        if (i == 1) {
            int counter1 = U1.getRocketU1Counter();
            counter1++;
            U1.setRocketU1Counter(counter1);

        } else {
            int counter1 = U2.getRocketU2Counter();
            counter1++;
            U2.setRocketU2Counter(counter1);

        }
    }

    public void landSimulation(int i) {
        if (i == 1) {
            int counter = U1.getRocketU1Counter();
            counter++;
            U1.setRocketU1Counter(counter);

        } else {
            int counter = U2.getRocketU2Counter();
            counter++;
            U2.setRocketU2Counter(counter);
        }
        hasLanded = false;
    }
}
