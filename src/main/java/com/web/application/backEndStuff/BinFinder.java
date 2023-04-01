package com.web.application.backEndStuff;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

public class BinFinder {

    public static String frontTest (String lable) {
        return "testing testing 123...";
    }

    public static String getBinDisc (String bin) {
        switch (bin){
            case "ewaste":
                return "this category is less of a bin and more of a disposal style, which can vary from place to place. check your local guidelines for how to deal with ewaste";
            case "compost":
                return "this is typically the green garbage bin and is all about organic materials";
            case "recycling":
                return "this tends to be the blue bin, and hre things get fuzzy. because the type of plastics that can be recycled, among other things, can vary from region to region, we cannot be 100% sure that it is recyclable. make sre to check your local guidelines to be sure";
            case "trash":
                return "this is very simply the standard waste, and tends to be the black bin";
            case "other":
                return "this was waste that could not be categorized into the other types (ewaste, compost, recycling, trash). we don't know what to do here so you're on your own";
            default:
                throw new IllegalArgumentException("no recognised bin");
        }
    }

    public static String findBin (String input) {
        OpenAiService oAi = new OpenAiService("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");
        CompletionResult result = null;
        CompletionRequest prompt = null;

        //order: ewaste, compost, recycle, trash, other

//        prompt = CompletionRequest.builder()
//                .prompt("is a broken " + input + " typically ewaste?" +
//                        "ewaste is limited in scope to strictly electronic devices or parts. things such as computer chips, batteries, laptops, servers" +
//                        " (yes/no)")
//                .model("text-davinci-003")
//                .echo(false)
//                .build();
//        result = oAi.createCompletion(prompt);
        prompt = CompletionRequest.builder()
                .prompt("is a " + input + " an electronic device or part?(yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(prompt);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("yes") || choice.getText().contains("Yes")) {
                return "ewaste";
            }
        }

        prompt = CompletionRequest.builder()
                .prompt("is a " + input + " a plant, decomposable, OR an organic substance? (yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(prompt);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("yes") || choice.getText().contains("Yes")) {
                return "compost";
            }
        }

        prompt = CompletionRequest.builder()
                .prompt("is a " + input + " made of metal, plastic, wood, glass, cardboard, or parper, not organic material, and recyclable? (yes/no)\n")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(prompt);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("yes") || choice.getText().contains("Yes")) {
                return "recycle";
            }
        }

        prompt = CompletionRequest.builder()
                .prompt("could a " + input + " be garbage? (yes/no)\n")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(prompt);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("y") || choice.getText().contains("Y")) {
                return "trash";
            }
        }

        return "other";
    }
}
