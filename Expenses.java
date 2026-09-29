import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Expenses {
private Account accountExpenses;
private Double amountExpenses;
private LocalDate dateExpenses;
private String notesExpenses;

public Expenses(Account account, Double amount, LocalDate date, String notes ) {
    this.accountExpenses = account;
    this.amountExpenses = amount;
    this.dateExpenses = date;
    this.notesExpenses = notes;
}
//  Getters

    public Account getAccountObject(){
        return this.accountExpenses;
    }

    public Account getAccountExpenses() {
        return accountExpenses;
    }

    public String getNameAccountExpenses() {
        return this.accountExpenses.getName();
    }

    public Double getAmountExpenses() {
        return amountExpenses;
    }

    public LocalDate getDateExpenses() {
        return dateExpenses;
    }

    public String getNotesExpenses() {
        return notesExpenses;
    }

// setters
public void setAccountExpenses(Account accountExpenses) {
	this.accountExpenses = accountExpenses;
}

public void setAmountExpenses(Double amountExpenses) {
	this.amountExpenses = amountExpenses;
}

public void setDateExpenses(LocalDate dateExpenses) {
	this.dateExpenses = dateExpenses;
}

public void setNotesExpenses(String notesExpenses) {
	this.notesExpenses = notesExpenses;
}

// Helpers to parse
// Static helper: parses the line and links the right Category object


    public  static Expenses fromCsv(String line, ArrayList<Account> availableAccount) {
        String[] parts = line.split(",");
        String accountNameParsed = parts[0].trim();
        double amountExpensesParsed = Double.parseDouble(parts[1].trim());
        LocalDate dateExpensesParsed = LocalDate.parse(parts[2].trim());
        String notesExpensesParsed = parts[3].trim();

        // Find the matching Account object
        Account matchedAccount = null;
        for (Account item : availableAccount) {
            if (item.getName().equalsIgnoreCase(accountNameParsed)) {
                matchedAccount = item;
                break;
            }
        }
        

        // Fallback if not found to avoid crashed the code
        // Fallback if not found
        if (matchedAccount == null) {
            matchedAccount = new Account(null, accountNameParsed, "Auto-created", 0.0);
        }
        
        return new Expenses(matchedAccount, amountExpensesParsed, dateExpensesParsed, notesExpensesParsed);
    }


// Load the file

    public static ArrayList<Expenses> loadAll(String filename, ArrayList<Account> accounts) {
        ArrayList<Expenses> list = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) return list;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    list.add(Expenses.fromCsv(line, accounts));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
        return list;
    }

    // Save to a file
    public static void saveExpensesToCsv(String filename, ArrayList<Expenses> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Expenses item : list) {
                String accountName = item.getAccountExpenses() != null ? item.getAccountExpenses().getName() : "";
                writer.printf("%s,%.2f,%s,%s%n",
                        accountName,
                        item.getAmountExpenses(),
                        item.getDateExpenses(),
                        item.getNotesExpenses());
            }
            System.out.println("CSV saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

}


