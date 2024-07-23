public class Dessert {
    public int flover;
    public int price;
    public static int numDesserts;
    public Dessert(int f, int p) {
        flover = f;
        price = p;
        numDesserts += 1;
    }

    public void printDessert() {
        System.out.print(flover + " " + price + " " + numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
