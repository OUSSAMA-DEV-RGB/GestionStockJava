import java.util.Scanner;

public class StockManagementApp {

    static int[] codesProduits = new int[100];
    static String[] nomsProduits = new String[100];
    static int[] quantites = new int[100];
    static double[] prix = new double[100];
    static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product code: ");
                    int code = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    ajouterProduit(code, name, quantity, price);
                    break;
                case 2:
                    System.out.print("Enter product code to modify: ");
                    int modifyCode = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    modifierProduit(modifyCode, newName, newQuantity, newPrice);
                    break;
                case 3:
                    System.out.print("Enter product code to delete: ");
                    int deleteCode = scanner.nextInt();
                    supprimerProduit(deleteCode);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    System.out.print("Enter product name to search: ");
                    String searchName = scanner.nextLine();
                    rechercherProduit(searchName);
                    break;
                case 6:
                    System.out.println("Total stock value: " + calculerValeurStock());
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\nStock Management Menu");
        System.out.println("1. Add Product");
        System.out.println("2. Modify Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Display Products");
        System.out.println("5. Search Product");
        System.out.println("6. Calculate Total Stock Value");
        System.out.println("0. Exit");
    }

    static void ajouterProduit(int code, String nom, int quantite, double prixProduit) {
        if (productCount < codesProduits.length) {
            codesProduits[productCount] = code;
            nomsProduits[productCount] = nom;
            quantites[productCount] = quantite;
            prix[productCount] = prixProduit;
            productCount++;
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Inventory is full. Cannot add more products.");
        }
    }

    static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < productCount; i++) {
            if (codesProduits[i] == code) {
                nomsProduits[i] = nouveauNom;
                quantites[i] = nouvelleQuantite;
                prix[i] = nouveauPrix;
                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    static void supprimerProduit(int code) {
        for (int i = 0; i < productCount; i++) {
            if (codesProduits[i] == code) {
                for (int j = i; j < productCount - 1; j++) {
                    codesProduits[j] = codesProduits[j + 1];
                    nomsProduits[j] = nomsProduits[j + 1];
                    quantites[j] = quantites[j + 1];
                    prix[j] = prix[j + 1];
                }
                productCount--;
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    static void afficherProduits() {
        System.out.println("\nProduct List:");
        for (int i = 0; i < productCount; i++) {
            System.out.println("Code: " + codesProduits[i] + ", Name: " + nomsProduits[i] + ", Quantity: " + quantites[i] + ", Price: " + prix[i]);
        }
    }

    static void rechercherProduit(String nom) {
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Code: " + codesProduits[i] + ", Name: " + nomsProduits[i] + ", Quantity: " + quantites[i] + ", Price: " + prix[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }

    static double calculerValeurStock() {
        double total = 0;
        for (int i = 0; i < productCount; i++) {
            total += quantites[i] * prix[i];
        }
        return total;
    }
}
