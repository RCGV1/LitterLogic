package com.web.application.backEndStuff.depreciated;

import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;
import java.util.List;

@Deprecated
public class OldBinMapper {

    static OldLableGenerator generator;
    public static void main(String[] args) throws IOException {
        determineIsTrash(generator.detectLabels("C:\\Users\\prate\\Downloads\\Coke-Cans-Recycled.jpg"));
    }

    public static boolean determineIsTrash(List<String> lables) {
        OpenAiService oAi = new OpenAiService("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");
        String lablesString = "";
        for(String lable : lables){
            lablesString += lable + ", ";
        }
        EditRequest request = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input(lablesString)
                .instruction("which value in the list of comma separated values seems like it might be household waste, an example being plastic bag if that was a given value")
                .build();

        System.out.println("\n\n\n\n\n");
        oAi.createEdit(request).getChoices().forEach(System.out::println);

        return true;
    }
    public String[] getBinFromLables (String[] Lables) {
        String binName = "",
                explanation = "";

        //TODO: add func

        return new String[]{binName, explanation};
    }

}
