package com.web.application.backEndStuff;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

public class BinFinder {

    public static void main (String[] args) {
        System.out.println(findBin("plastic bottle"));
    }
    public static String findBin (String input) {
        OpenAiService oAi = new OpenAiService("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");
        CompletionResult result = null;
        CompletionRequest completionRequest = null;

        //order: ewaste, compost, recycle, trash, other

        completionRequest = CompletionRequest.builder()
                .prompt("is a " + input + " typically ewaste?" +
                        "ewaste is limited in scope to strictly electronics. if it is not an electronic, it is not ewaste" +
                        " (yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(completionRequest);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("y") || choice.getText().contains("Y")) {
                return "ewaste";
            }
        }

        completionRequest = CompletionRequest.builder()
                .prompt("is a " + input + " compostable? " +
                        "compostables typically are limited to organic things, and something like a wrapper would not count" + "(yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(completionRequest);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("y") || choice.getText().contains("Y")) {
                return "compost";
            }
        }

        completionRequest = CompletionRequest.builder()
                .prompt("is a " + input + " recyclable? " +
                        "recyclables tend to include plastic, but typically food wrappers or containers cannot be recycled because they are considered contaminated, and mut be thrown away" +
                        "(yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(completionRequest);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("y") || choice.getText().contains("Y")) {
                return "recycle";
            }
        }

        completionRequest = CompletionRequest.builder()
                .prompt("can a " + input + " be thrown in the trash? (yes/no)")
                .model("text-davinci-003")
                .echo(false)
                .build();
        result = oAi.createCompletion(completionRequest);
        for(CompletionChoice choice : result.getChoices()) {
            if(choice.getText().contains("y") || choice.getText().contains("Y")) {
                return "trash";
            }
        }

        return "other";
    }
}
