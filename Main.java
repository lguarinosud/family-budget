import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

        public static void main(String[] args) {

        

        // Arrays per class
        //ArrayList<Category> categoryList = new ArrayList<>();
       // ArrayList<Account> accountList = new ArrayList<>();
        

        ArrayList<Category>  categoryList = Category.loadAll("Category.csv");
        ArrayList<Account> accountList = Account.loadAll("Account.csv", categoryList);
        ArrayList<Expenses> expensesList = Expenses.loadAll("Expenses.csv", accountList);

        
        Scanner scanner = new Scanner(System.in);


        int choice = 0;

        while (choice != 6) {
            System.out.println("\nMain Menu");
            System.out.println("1. Category");
            System.out.println("2. Accounts");
            System.out.println("3. Expenses");
            System.out.println("4. List");
            System.out.println("5. Save");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {System.out.println("1 Category selected.");
                        
                        int subchoice = 0;
                        while (subchoice != 5) {
                            System.out.println("\nCategory Menu");
                            System.out.println("1. Add");
                            System.out.println("2. Delete");
                            System.out.println("3. Modify");
                            System.out.println("4. List");
                            System.out.println("5. Return");
                            System.out.print("Choose an option: ");

                            subchoice = scanner.nextInt();
                            scanner.nextLine();

                            

                            switch(subchoice) {
                                case 1 -> {System.out.println("============= Add Category ==============\n");
                                   String newName;
                                    String newDescription;
                                    Double newBudget;

                                    // Adding new name to category
                                    System.out.print("Input Category name: ");
                                    newName = scanner.nextLine();
                                    //scanner.nextLine();// workaround to fix the orphan new line caracter.
                                    System.out.println("New name:" + newName);
                                    

                                    // adding description to category
                                    System.out.print("Describe what this category is about: ");
                                    newDescription = scanner.nextLine();
                                    System.out.println("New Description:" + newDescription);

                                    // adding budget to category
                                    System.out.print("Monthly budget: ");
                                    newBudget = scanner.nextDouble();
                                    System.out.println("New Budget: £" +newBudget+"\n");
                                    scanner.nextLine(); // workaround to fix the orphan new line caracter.

                                    // Creating the new Category with constructor
                                    Category newCategory = new Category( newName, newDescription, newBudget);
                                     
                                    // Adding new Category to the list
                                    categoryList.add(newCategory);
                                    
                                    System.out.println("****New Category created****\n");
                                    System.out.println("Name: " + newName+"\n" + "Description:"  + newDescription+"\n" + "Budget: " + newBudget+"\n");
                                    System.out.println("****New Category created****");

                                    System.out.println("============= Add completed ==============");
                                    
                                } 
                                case 2 -> {System.out.println("============= Delete Category ==============\n");
                                    System.out.print("Name of the category to be deleted: ");
                                    String deleteName = scanner.nextLine();

                                    System.out.print("Are you sure you want to delete '" + deleteName + "'? (yes/no): ");
                                    String confirmation = scanner.nextLine();

                                    if (confirmation.equalsIgnoreCase("yes")) {
                                        // removeIf returns true if an element was found and removed
                                        boolean removed = categoryList.removeIf(category -> category.getName().equalsIgnoreCase(deleteName));

                                        if (removed) {
                                            System.out.println("Category '" + deleteName + "' was deleted successfully.\n");
                                        } else {
                                            System.out.println("Category '" + deleteName + "' was not found.\n");
                                        }
                                    } else {
                                        System.out.println("Deletion cancelled.\n");
                                    }

                                    System.out.println("============= Delete completed ==============");
                                }
                                case 3 -> {System.out.println("============= Modify Category==============\n");
                                System.out.print("Name of the category to be modify: ");
                                String targetName = scanner.nextLine();
                                String updateName;
                                boolean updated = false;

                                    for (Category categories : categoryList) {
                                        if (categories.getName().equalsIgnoreCase(targetName)) {
                                            System.out.print("Category found. Provide new name: ");
                                            updateName = scanner.nextLine();
                                            categories.setName(updateName);
                                            updated = true;
                                            
                                        }
                                        
                                    }
                                
                                if (updated) {
                                    System.out.println("Category updated successfully!\n");
                                    } else {
                                    System.out.println("Category not found.");
                                }
                                  
                                    System.out.println("============= Modify completed ==============");



                                }
                                case 4 -> {System.out.println("============= List Category ==============\n");

                                    

                                System.out.println("+-----------------+---------------------------+------------+");
                                System.out.printf("| %-15s | %-25s | %-10s |%n", "Category", "Description", "Budget");
                                System.out.println("+-----------------+---------------------------+------------+");

                                for (Category item : categoryList) {
                                    System.out.printf("| %-15s | %-25s | %-10s |%n", 
                                    item.getName(), item.getDescription(), item.getBudget() );
                                }
                                System.out.println("+-----------------+---------------------------+------------+");

                                System.out.println("============= List Completed ==============\n");
                                        
       
                                }
                                case 5 -> {System.out.println("5 Return.");}
                                default -> System.out.println("Please choose an option from 1 to 5.");
                            }
                            
                            

                        }
                      
                    }
                
              
                    case 2 -> {System.out.println("2 Accounts selected.");
                        
                        int subchoice = 0;
                        while (subchoice != 5) {
                            System.out.println("\nAccount Menu");
                            System.out.println("1. Add");
                            System.out.println("2. Delete");
                            System.out.println("3. Modify");
                            System.out.println("4. List");
                            System.out.println("5. Return");
                            System.out.print("Choose an option: ");

                            subchoice = scanner.nextInt();
                            scanner.nextLine();
                 

                            switch(subchoice) {
                                case 1 -> {System.out.println("============= Add Account==============\n");
                                  
                                   String newCategoryName;
                                    String newAccountName;
                                    String newAccountDescription;
                                    Double newAccountBudget;

                                    
                            // Adding Category
                                    System.out.print("Input Category name: ");
                                    newCategoryName = scanner.nextLine().trim();
                                    String categoryNameMatched = null;
                                    Category newCategory = null;

                                    for (Category cat : categoryList) {
                                        if (newCategoryName.equalsIgnoreCase(cat.getName())) {
                                            categoryNameMatched = cat.getName();
                                            newCategory = cat;
                                            System.out.println("categoryNameMatched found: " + categoryNameMatched);
                                            break; 
                                        }
                                    }

                                    
                                    if (newCategory == null) {
                                        System.out.println("Category '" + newCategoryName + "' not found in the list, try again.");
                                        break; 
                                    }

                                    System.out.println("Category assigned: " + categoryNameMatched);
                                    
                                    // Adding new name to Account
                                    System.out.print("Input Account name: ");
                                    newAccountName = scanner.nextLine();
                                    //scanner.nextLine();// workaround to fix the orphan new line caracter.
                                    System.out.println("New name:" + newAccountName);
                                    

                                    // adding description to Account
                                    System.out.print("Describe what this account is about: ");
                                    newAccountDescription = scanner.nextLine();
                                    System.out.println("New Description:" + newAccountDescription);

                                    // adding budget to Acount
                                    System.out.print("Monthly budget: ");
                                    newAccountBudget = scanner.nextDouble();
                                    System.out.println("New Budget: £" +newAccountBudget+"\n");
                                    scanner.nextLine(); // workaround to fix the orphan new line caracter.

                                    // Creating the new Category with constructor
                                    Account newAccount = new Account( newCategory, newAccountName, newAccountDescription, newAccountBudget);
                                     
                                    // Adding new Category to the list
                                    accountList.add(newAccount);
                                    
                                    System.out.println("****New Account created****\n");
                                    System.out.println("Category: " + categoryNameMatched+"\n" + "Name: " + newAccountName+"\n" + "Description:"  + newAccountDescription+"\n" + "Budget: " + newAccountBudget+"\n");
                                    System.out.println("****New Account created****");

                                    System.out.println("============= Add completed ==============");
                                    
                                } 
                                
                                case 2 -> {System.out.println("============= Delete Account==============\n");
                                    
                                    System.out.print("Name of the account to be deleted: ");
                                    String deleteName = scanner.nextLine();

                                    System.out.print("Are you sure you want to delete '" + deleteName + "'? (yes/no): ");
                                    String confirmation = scanner.nextLine();

                                    if (confirmation.equalsIgnoreCase("yes")) {
                                        // removeIf returns true if an element was found and removed
                                        boolean removed = accountList.removeIf(account -> account.getName().equalsIgnoreCase(deleteName));

                                        if (removed) {
                                            System.out.println("Account '" + deleteName + "' was deleted successfully.\n");
                                        } else {
                                            System.out.println("Account '" + deleteName + "' was not found.\n");
                                        }
                                    } else {
                                        System.out.println("Deletion cancelled.\n");
                                    }

                                    System.out.println("============= Delete completed ==============");
                                }
                                case 3 -> {System.out.println("============= Modify Account==============\n");
                                System.out.print("Name of the account to be modify: ");
                                String targetName = scanner.nextLine();
                                String updateName;
                                boolean updated = false;

                                    for (Account acc : accountList) {
                                        if (acc.getName().equalsIgnoreCase(targetName)) {
                                            System.out.print("Category found. Provide new name: ");
                                            updateName = scanner.nextLine();
                                            acc.setName(updateName);
                                            updated = true;
                                            
                                        }
                                        
                                    }
                                
                                if (updated) {
                                    System.out.println("Account updated successfully!\n");
                                    } else {
                                    System.out.println("Account not found.");
                                }
                                  
                                    System.out.println("============= Modify completed ==============");



                                }
                                case 4 -> {System.out.println("============= List Accounts=============");
                                System.out.println("+-----------------+Accounts+------------+");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                System.out.printf("| %-15s | %-25s | %-25s | %-15s | %n", "Category", "Account", "description", "Budget");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");

                                for (Account acc : accountList) {
                                    String categoryNameParsed = acc.getCategoryName();
                                    String categoryNameMatched = null;
                                    for (Category cat : categoryList) {
                                        
                                        if (categoryNameParsed.equalsIgnoreCase(cat.getName())) {
                                            categoryNameMatched = categoryNameParsed;
                                            break;
                                        }
                                    
                                
                                    }
                                
                                if (categoryNameMatched ==null) {
                                            System.out.println("Category '" + categoryNameParsed + "' not found in the list, try again.");
                                            break; 
                                        }    

                                System.out.printf("| %-15s | %-25s | %-25s | %-15.2f |%n", 
                                categoryNameMatched, 
                                acc.getName(), 
                                acc.getDescription(), 
                                acc.getBudget()
                                );
                                 }

                            System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                            System.out.println("============= List Completed ==============\n");
                            }
                                


                                case 5 -> {System.out.println("5 Return.");}
                            }

                        }
                    }
                    
                    
                    case 3 -> {System.out.println("3 Expenses selected.");
                                            
                        int subchoice = 0;
                        while (subchoice != 5) {
                            System.out.println("\nExpenses Menu");
                            System.out.println("1. Add");
                            System.out.println("2. Delete");
                            System.out.println("3. Modify");
                            System.out.println("4. List");
                            System.out.println("5. Return");
                            System.out.print("Choose an option: ");

                            subchoice = scanner.nextInt();
                            scanner.nextLine();
                 

                            switch(subchoice) {
                                case 1 -> {System.out.println("============= Add Expense==============\n");
                                  
                                   String newAccountName;
                                    Double newExpensesAmount;
                                    LocalDate newExpensesDate;
                                    String newExpensesNotes;

                                    
                            // Adding Expenses
                                    System.out.print("Input account name: ");
                                    newAccountName = scanner.nextLine().trim();
                                    String accountNameMatched = null;
                                    Account newAccount = null;

                                    for (Account acc : accountList) {
                                        System.out.print("Account " + acc.getName() );
                                        if (newAccountName.equalsIgnoreCase(acc.getName())) {
                                            accountNameMatched = acc.getName();
                                            newAccount = acc;
                                            System.out.println("Account found: " + accountNameMatched);
                                            break; 
                                        }
                                    }

                                     
                                    if (newAccount == null) {
                                        System.out.println("Account '" + newAccountName + "' not found in the list, try again or create one");
                                        break;  
                                    }

                                    System.out.println("Account assigned: " + accountNameMatched);
                                    
                                    // Adding Amount to Expense
                                    System.out.print("Input Amount of expense: ");
                                    newExpensesAmount = scanner.nextDouble();
                                    scanner.nextLine(); // workaround to fix the orphan new line caracter.

                                    System.out.println("New Amount:" + newExpensesAmount);
                                    

                                    // adding date to Expenses
                                    System.out.print("Input the date (YYYY-MM-DD): ");
                                    String dateInput = scanner.nextLine().trim();

                                    newExpensesDate = LocalDate.parse(dateInput);
                                    System.out.println("New date: " + newExpensesDate);

                                    // adding Notes to Expenses
                                    System.out.print("Notes: ");
                                    newExpensesNotes = scanner.nextLine();
                                    System.out.println("New Notes: " +newExpensesNotes+"\n");

                                    // Creating the new Category with constructor
                                    Expenses newExpenses = new Expenses( newAccount, newExpensesAmount, newExpensesDate, newExpensesNotes);
                                     
                                    // Adding new Category to the list
                                    expensesList.add(newExpenses);
                                    
                                    System.out.println("****New Expense created****\n");
                                    System.out.println("Account: " + accountNameMatched+"\n" + "Amount: " + newExpensesAmount+"\n" + "Date:"  + newExpensesDate+"\n" + "Notes: " + newExpensesNotes+"\n");
                                    System.out.println("****New Expense created****");

                                    System.out.println("============= Add completed ==============");
                                    
                                } 
                                
                                case 2 -> {
                                System.out.println("============= Delete Expense ==============\n");

                                System.out.print("Name of the Account/Expense: ");
                                String deleteName = scanner.nextLine().trim();

                                System.out.print("Date of the Expense (YYYY-MM-DD): ");
                                LocalDate parsedDate = LocalDate.parse(scanner.nextLine().trim());

                                System.out.print("Amount of the Expense: ");
                                double deleteAmount = scanner.nextDouble();
                                scanner.nextLine();   //workaround
                                System.out.print("Are you sure you want to delete '" + deleteName + "' on " + parsedDate + " for " + deleteAmount + "? (yes/no): ");
                                String confirmation = scanner.nextLine().trim();

                                if (confirmation.equalsIgnoreCase("yes")) {
                                    Expenses expenseToDelete = null;

                                    
                                    for (Expenses exp : expensesList) {
                                        boolean matchesName = exp.getNameAccountExpenses().equalsIgnoreCase(deleteName);
                                        boolean matchesAmount = Double.compare(exp.getAmountExpenses(), deleteAmount) == 0;
                                        boolean matchesDate = exp.getDateExpenses().equals(parsedDate);
                                        

                                        // Check all three conditions are true
                                        if (matchesName && matchesDate && matchesAmount) {
                                            expenseToDelete = exp;
                                            break; // It will stop at first match for now.
                                        }
                                    }

                                    // Remove the object outside the loop to avoid modification errors
                                    if (expenseToDelete != null) {
                                        expensesList.remove(expenseToDelete);
                                        System.out.println("Expense was deleted successfully.\n");
                                    } else {
                                        System.out.println("No matching expense was found.\n");
                                    }
                                } else {
                                    System.out.println("Deletion cancelled.\n");
                                }

                                System.out.println("============= Delete completed ==============");
                            }
                                case 4 -> {System.out.println("============= List Expenses=============");
                                System.out.println("+-----------------+Expenses+------------+");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %n", "Account", "Amount", "Date", "Notes");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");

                                for (Expenses exp : expensesList) {
                                    String accountNameParsed = exp.getNameAccountExpenses();
                                    String expensesNameMatched = null;
                                    for (Account acc : accountList) {
                                        
                                        if (accountNameParsed.equalsIgnoreCase(acc.getName())) {
                                            expensesNameMatched = accountNameParsed;
                                            break;
                                        }
                                    
                                
                                    }
                                
                                if (expensesNameMatched ==null) {
                                            System.out.println("Expenses with account '" + accountNameParsed + "' not found in the list, try again.");
                                            break; 
                                        }    

                                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %n", expensesNameMatched, exp.getAmountExpenses(), exp.getDateExpenses(),  exp.getDateExpenses());
                                 }

                            System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                            System.out.println("============= List Completed ==============\n");
                            }
                                


                                case 5 -> {System.out.println("5 Return.");}
                            }

                        }
                    }

                    
                    
                    case 4 -> {System.out.println("4 List selected.");
                        System.out.println("List selected.");
                        int subchoice = 0;
                        while (subchoice != 5) {

                            System.out.println("\nList Menu");
                            System.out.println("1. List Categories");
                            System.out.println("2. List Accounts");
                            System.out.println("3. List Expenses");
                            System.out.println("4. List Category Balance");
                            System.out.println("5. Return");
                            System.out.print("Choose an option: ");

                            subchoice = scanner.nextInt();
                            scanner.nextLine();

                            switch(subchoice) {
                                case 1 -> {System.out.println("1 ListCategories");
                                System.out.println("+-----------------+Categories+------------+");
                                System.out.println("+-----------------+---------------------------+------------+");
                                System.out.printf("| %-15s | %-25s | %-10s |%n", "Category", "Description", "Budget");
                                System.out.println("+-----------------+---------------------------+------------+");

                                for (Category item : categoryList) {
                                    System.out.printf("| %-15s | %-25s | %-10s |%n", 
                                    item.getName(), item.getDescription(), item.getBudget() );
                                }
                                System.out.println("+-----------------+---------------------------+------------+");

                                System.out.println("============= List Completed ==============\n");


                                }

                                case 2 -> {System.out.println("2 List Accounts");
                                System.out.println("+-----------------+Accounts+------------+");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                System.out.printf("| %-15s | %-25s | %-25s | %-15s | %n", "Category", "Account", "description", "Budget");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");

                                for (Account acc : accountList) {
                                    String categoryNameParsed = acc.getCategoryName();
                                    String categoryNameMatched = null;
                                    for (Category cat : categoryList) {
                                        
                                        if (categoryNameParsed.equalsIgnoreCase(cat.getName())) {
                                            categoryNameMatched = categoryNameParsed;
                                            break;
                                        }
                                    
                                
                                    }
                                
                                if (categoryNameMatched ==null) {
                                            System.out.println("Category '" + categoryNameParsed + "' not found in the list, try again.");
                                            break; 
                                        }    

                                System.out.printf("| %-15s | %-25s | %-25s | %-15.2f |%n", 
                                categoryNameMatched, 
                                acc.getName(), 
                                acc.getDescription(), 
                                acc.getBudget()
                                );
                                 }

                            System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                            System.out.println("============= List Completed ==============\n");
                            }
                                case 3 -> {System.out.println("3 List Expenses.");
                               System.out.println("+-----------------+Expenses+------------+");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %n", "Account", "Amount", "Date", "Notes");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");

                                for (Expenses exp : expensesList) {
                                    String accountNameParsed = exp.getNameAccountExpenses();
                                    String expensesNameMatched = null;
                                    for (Account acc : accountList) {
                                        
                                        if (accountNameParsed.equalsIgnoreCase(acc.getName())) {
                                            expensesNameMatched = accountNameParsed;
                                            break;
                                        }
                                    
                                
                                    }
                                
                                if (expensesNameMatched ==null) {
                                            System.out.println("Expenses with account '" + accountNameParsed + "' not found in the list, try again.");
                                            break; 
                                        }    

                                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %n", expensesNameMatched, exp.getAmountExpenses(), exp.getDateExpenses(),  exp.getDateExpenses());
                                 }

                            System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                            System.out.println("============= List Completed ==============\n");
                            }
                                

   
                                case 4 -> {System.out.println("4 List Category Balance");
                                System.out.println("+-----------------+Categories Balance+------------+");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %n", "Category", "Budget", "Total Expenses", "Balance");
                                System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                                
                                for (Category cat : categoryList) {
                                    Double totalAmount = 0.00;
                                    Double balance = 0.00;
                                    String categoryNameParsed = cat.getName();
                                    Double budgetParsed = cat.getBudget();
                                    for (Expenses exp : expensesList) {
                                         // exp.getAccountObject().getCategoryName();
                                        
                                        if (categoryNameParsed.equalsIgnoreCase(exp.getAccountObject().getCategoryName())){
                                            totalAmount = totalAmount + exp.getAmountExpenses();
                                        }
                                    }
                                    balance = budgetParsed - totalAmount;
                                    String formattedBalance = String.format("%.2f", balance);
                                    // if (cat.getName().equalsIgnoreCase("Ingresos")) {
                                        
                                    // }   

                                    System.out.printf("| %-15s | %-15s | %-15s | %-15s| %n", categoryNameParsed, "£" + budgetParsed, "£" + totalAmount, "£" + formattedBalance);
                                 }


                            System.out.println("+-----------------+---------------------------+---------------------------+-----------------+");
                            System.out.println("============= List Category Completed ==============\n");
                                

                                   
                            }

                                case 5 -> {System.out.println("5 Return");}
                                
                                }

                        }
                    }
                    
                    
                    case 5 -> {System.out.println("5 Save selected.");
                        System.out.println("==========================Saving Data =====================================");
                         
                        try {
                            Category.saveCategoriesToCsv("Category.csv", categoryList);
                            System.out.println("* Categories saved.");
                        } catch (Exception e) {
                            System.out.println("Something went wrong when saving Categories, see error message ==>" + e.getMessage()); 
                        }

                        try {
                            Account.saveAccountToCsv("Account.csv", accountList);
                            System.out.println("* Account saved.");
                        } catch (Exception e) {
                            System.out.println("Something went wrong when saving Accounts, see error message ==>" + e.getMessage()); 
                        }

                        try {
                            Expenses.saveExpensesToCsv("Expenses.csv", expensesList);
                            System.out.println("* expenses saved.");
                        } catch (Exception e) {
                            System.out.println("Something went wrong when saving Accounts, see error message ==>" + e.getMessage()); 
                        }
                        
                        System.out.println("Saves completed. ");
                        System.out.println("==========================Saving Completed =====================================");
                    }
                    case 6 -> {System.out.println("6 Quitt selected.");
                        
                    }
                    
                    
                    
                    default -> System.out.println("Please choose an option from 1 to 6.");
                }
                
            } else {
                System.out.println("Please enter a number.");
                scanner.next();
            }
       }

        scanner.close();
    }
}
