package com.web.application.backEndStuff;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TrashClassifier {
    public static String findSmartBin(String inputText){
        // Path to the trained model file
        String modelFilePath = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/models/modelTest.bin";
        DoccatModel model = null;
        try {
            model = new DoccatModel(new FileInputStream(modelFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a DocumentCategorizerME object with the trained model
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

        inputText = inputText.toLowerCase();

        // Categorize the input text
        double[] probabilities = categorizer.categorize(inputText.split(" "));
        String allResults = categorizer.getAllResults(probabilities);

        // Print all the results
        System.out.println("The trash is categorized as:");
        System.out.println(allResults);

        // Get the top category
        String bestCategory = categorizer.getBestCategory(probabilities);
        System.out.println("The most likely category is: " + bestCategory);

        return bestCategory;
    }
}
