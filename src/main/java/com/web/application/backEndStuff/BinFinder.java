package com.web.application.backEndStuff;

public class BinFinder {

    String[] ewasteKeywords = {
            "chromebook",
            "battery",
            "batteries",
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
            "desktop",
            "samsung",
            "lithium",
            "alkaline",
            "zinc",
            "lead",
            "electronic",
            "tv"
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
            "garbage",
            "styrofoam",
            "vase",
            "glove",
            "dish",
            "pot",



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
            "shampoo"
    };

    String[] compostKeyWord = {
            "putrid",
            "rotting",
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
            "branch",
            "tree",
            "organic",
            "pulp",
            "scrap",
            "coffee",
            "bone",
            "flower",
            "plant"
    };


    public String frontTest (String lable) {
        return "testing testing 123...";
    }

    public String getBinDisc (String bin) {
        switch (bin){
            case "E-Waste":
                return "This category contains all electronics, like computers, speakers, and the like.\n" +
                       "Depending on where you are and what it is, there may be different ways of disposing of it, such as bulk pickup for a TV screen.\n"+
                        "Please dispose of these items properly as they contain toxic and flammable batteries which can explode (DO NOT THROW IN TRASH).";
            case "Compost":
                return "Compost includes all things organic, which is to say alive or previously alive, like moldy toast or cut grass.\n" +
                       "Compost can be disposed of in your own household compost container or in the green bin.";
            case "Recycling":
                return "The goal of recycling is to reuse old material. Recyclable items can be quite picky, especially since they are \n" +
                       "compacted into huge cubes before shipping, and a single contaminant can cause the whole cube to go to the landfill.\n" +
                       "Thus recycling can be very picky, with different types of plastic being allowed in different rejoins. Especially for \n" +
                       "plastic, it is important to check local guidelines to see whether it is okay to recycle. Recycling can be handled in \n" +
                        "much the same way as compost, except it's bin color is blue";
            case "Trash":
                return "Before throwing something away in the trash consider if you can reuse the object. Trash can be almost anything, though not\n" +
                        "E-Waste. However, the fact that it typically ends up either burned or in landfills means that it is not that great for\n" +
                        "the environment, or the economy, as any material thrown away tends to be taken out of circulation forever";
            case "No keywords detected":
                return "Nothing stood ot to us from the prompt. Perhaps try again with a broad discription of what your item is, like \"plastic \n" +
                        "bag\" or \"egg carton\"";
            default:
                throw new IllegalArgumentException("No recognised bin.");
        }
    }

    public String findBin (String input) {
        input = input.toLowerCase();

        for (String keyword : ewasteKeywords) {
            if(input.contains(keyword)){
                return "E-Waste";
            }
        }

        for (String keyword : trashKeyword) {
            if(input.contains(keyword)){
                return "Trash";
            }
        }

        for (String keyword : recycleKeywords) {
            if(input.contains(keyword)){
                System.out.println(keyword);
                return "Recycling";
            }
        }

        for (String keyword : compostKeyWord) {
            if(input.contains(keyword)){
                return "Compost";
            }
        }

        return "No keywords detected";
    }
}
