import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Category {
    private String name;
    private String description;
    private Double budget;

    public Category(String name, String description, Double budget) {
        this.name = name;
        this.description = description;
        if (budget == null) {
            this.budget = 0.0;
        }
        else {
            this.budget = budget;
        }
    }
// Getters
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Double getBudget(){
        return this.budget;
    }
// Setters
    public void setName(String newName){
        this.name = newName; 
    }

    public void setDescription(String newDescription){
        this.description = newDescription; 
    }

    public void setBudget(Double newBudget){
        this.budget = newBudget; 
    }

// Helpers to parse
// Static helper: parses the line and links the right Category object
    public static Category fromCsv(String line) {
        String[] parts = line.split(",");
        String categoryName = parts[0].trim();
        String categoryDescription = parts[1].trim();
        double budgetCategory = Double.parseDouble(parts[2].trim());

        // Find the matching Category object
        

        // Fallback if not found
        
        return new Category(categoryName, categoryDescription, budgetCategory);
    }


// Save to a file


    public static void saveCategoriesToCsv(String filename, ArrayList<Category> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Category item : list) {
                writer.printf("%s,%s,%.2f%n", item.getName(), item.getDescription(), item.getBudget());
            }
            System.out.println("CSV saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

// Load the file

    public static ArrayList<Category> loadAll(String filename) {
        ArrayList<Category> list = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) return list;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    list.add(Category.fromCsv(line));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
        return list;
    }


}
    