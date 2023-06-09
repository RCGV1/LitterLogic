package com.web.application.backEndStuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.Span;

public class TextClassifierTester {

    public static void main(String[] args) throws IOException {
        // Path to the trained model file
        String modelFilePath = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/models/modelTest.bin";
        DoccatModel model = new DoccatModel(new FileInputStream(modelFilePath));

        // Create a DocumentCategorizerME object with the trained model
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

        // Get user input from the console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a description of the trash: ");
        String inputText = scanner.nextLine();

        // Categorize the input text
        double[] probabilities = categorizer.categorize(inputText.split(" "));
        String allResults = categorizer.getAllResults(probabilities);

        // Print all the results
        System.out.println("The trash is categorized as:");
        System.out.println(allResults);

        // Get the top category
        String bestCategory = categorizer.getBestCategory(probabilities);
        System.out.println("The most likely category is: " + bestCategory);
    }
}
