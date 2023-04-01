package com.web.application.backEndStuff;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

public class BinFinder {

    String[] ewasteKeywords = {
            "battery",
            "laptop",
            "computer",
            "chip",
            "electric",
            "auto",
            "speaker",
            "ipad",
            "phone",
            "microphone",
            "keyboard",
            "mouse",
            "electronic",
            "android",
            "tech",
            "desktop"
    };

    String[] trashKeyword = {
            "wrapper",
            "ceramic",
            "plastic bag",
            "straw",
            "takeout",
            "plastic utensil",
            "bubble wrap",
            "brush",
            "plastic container",
            "trash",
            "rubbish",
            "garbage"
    };

    String[] recycleKeywords = {
            "carton",
            "bottle",
            "paper",
            "cardboard",
            "metal",
            "metallic",
            "can",
            "plastic",
            "wooden",
            "glass",
            "aluminum",
            "tin",
            "foil",
            "table",
            "chair",
            "jar",
            "magazine",
            "hdpe",
            "cup",
            "polyethylene",
            "pet",
            "pp"
    };

    String[] compostKeyWord = {
            "putrid",
            "rot",
            "expire",
            "rind",
            "cheese",
            "rice",
            "bread",
            "egg",
            "apple",
            "pear",
            "peel",
            "fish",
            "meat",
            "plant",
            "tree",
            "grass",
            "fern",
            "chicken",
            "beef",
            "ham",
            "food",
            "branch"
    };


    public String frontTest (String lable) {
        return "testing testing 123...";
    }

    public String getBinDisc (String bin) {
        switch (bin){
            case "ewaste":
                return "this category is less of a bin and more of a disposal style, which can vary from place to place. check your local guidelines for how to deal with ewaste";
            case "compost":
                return "this is typically the green garbage bin and is all about organic materials";
            case "recycling":
                return "this tends to be the blue bin, and hre things get fuzzy. because the type of plastics that can be recycled, among other things, can vary from region to region, we cannot be 100% sure that it is recyclable. make sre to check your local guidelines to be sure";
            case "trash":
                return "this is very simply the standard waste, and tends to be the black bin";
            case "No keywords detected":
                return "this was waste that could not be categorized into the other types (ewaste, compost, recycling, trash). we don't know what to do here so you're on your own";
            default:
                throw new IllegalArgumentException("no recognised bin");
        }
    }

    public String findBin (String input) {
        input = input.toLowerCase();

        for (String keyword : ewasteKeywords) {
            if(input.contains(keyword)){
                return "ewaste";
            }
        }

        for (String keyword : trashKeyword) {
            if(input.contains(keyword)){
                return "trash";
            }
        }

        for (String keyword : recycleKeywords) {
            if(input.contains(keyword)){
                return "recycle";
            }
        }

        for (String keyword : compostKeyWord) {
            if(input.contains(keyword)){
                return "compost";
            }
        }

        return "No keywords detected";
    }
}
