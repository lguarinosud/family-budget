import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Account {
    private Category category;
    private String categoryName; 
    private String name;
    private String description;
    private Double budgetAccount;
    
    public Account (Category category, String accountName, String accountDescription, Double budget) {
        this.category = category;
        this.name = accountName;
        if (accountDescription == null) {
            this.description = "";
        } else {
            this.description = accountDescription;
        }

        if (budget == null) {
            this.budgetAccount = 0.0;
        }
        else {
            this.budgetAccount = budget;
        }
    }

    // Getters


    public String getCategoryName(){
        categoryName = category.getName();

        return categoryName; 
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Double getBudget(){
        return this.budgetAccount;
    }
// Setters
    public void setName(String newName){
        this.name = newName; 
    }

    public void setDescription(String newDescription){
        this.description = newDescription; 
    }

    public void setBudget(Double newBudget){
        this.budgetAccount = newBudget; 
    }

// Helpers to parse
// Static helper: parses the line and links the right Category object
    public static Account fromCsv(String line, ArrayList<Category> availableCategories) {
        String[] parts = line.split(",");
        String categoryName = parts[0].trim();
        String accountName = parts[1].trim();
        String accountDescription = parts[2].trim();
        double budgetAccount = Double.parseDouble(parts[3].trim());

        // Find the matching Category object
        Category matchedCategory = null;
        for (Category item : availableCategories) {
            if (item.getName().equalsIgnoreCase(categoryName)) {
                matchedCategory = item;
                break;
            }
        }

        // Fallback if not found
        if (matchedCategory == null) {
            matchedCategory = new Category(categoryName, "Auto-created", 0.0);
        }

        return new Account(matchedCategory, accountName, accountDescription, budgetAccount);
    }


    // Load the file 

    public static ArrayList<Account> loadAll(String filename, ArrayList<Category> categories) {
    ArrayList<Account> list = new ArrayList<>();
    File file = new File(filename);

    if (!file.exists()) return list;

    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                list.add(Account.fromCsv(line, categories));
            }
        }
    } catch (Exception e) {
        System.out.println("Error reading " + filename + ": " + e.getMessage());
    }
    return list;
}


    // Save to a file


    public static void saveAccountToCsv(String filename, ArrayList<Account> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Account item : list) {
                writer.printf("%s,%s,%s,%.2f%n", item.getCategoryName(),item.getName(), item.getDescription(), item.getBudget());
            }
            System.out.println("CSV saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    

}
