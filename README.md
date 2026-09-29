# Overview


The software is a personal budget management system designed to track expenses across three simple classes: Categories, Accounts, and Expenses. Users navigate menus in the terminal to create budgets, record daily expenses, view balance reports, and safely delete or edit records. 


I wrote this software to learn the basics of Java, specifically handling nested control flow, managing memory with standard collections, and building file input/output using CSV files.


[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

The application was developed and tested using Visual Studio Code as the primary source code editor, along with the Extension Pack for Java (Oracle version as it allowed me to use Junyper notebooks for testing and practicing purposes). Version control was managed through Git.

The project is built entirely in standard Java (JDK 21) without external third-party dependencies. It relies on standard Java standard library utilities:

    java.util.Scanner for parsing user input from the console.

    java.util.ArrayList for dynamic, in-memory object storage.

    java.io.PrintWriter and java.io.FileWriter for reading and writing persistent CSV records.

# Useful Websites

    Oracle Java Documentation - Scanner: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Scanner.html
                                         https://medium.com/@zakariafarih142/mastering-csv-parsing-in-java-comprehensive-methods-and-best-practices-a3b8d0514edf
    
    Encapsulation: https://www.youtube.com/watch?v=pTB0EiLXUC8 THis video helps me clarify the bacics concept of Java how is different from Python (The lenguague I fell more familiarised)

    Google gemini. https://gemini.google.com/.  I used it to clarify concepts like how Java deal with memory, like Stack and Heap, which helped me undertand better why Java beahaves in certain manners. I learn about the stack and sintax of unknown commands like while, switch and for loop. 
    


# Future Work

Whilst the main functionality is complete, there are a few areas I'd like to improve:

    * Implement a better validation for user input to gracefully catch formatting errors without interrupting the menu loop.

    * A UI for a better user friendly experience. Although I love the terminal, I reckon it is not for everydody. 
    
    * Add advanced reporting filters, allowing the user to view expenses filtered by custom date ranges and specific categories.
