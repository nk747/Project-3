public class Item {
    public String name;
    public int weight;

    public Item() {
    } // default constructor

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
