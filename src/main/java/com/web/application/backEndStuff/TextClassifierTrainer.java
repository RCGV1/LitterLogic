package com.web.application.backEndStuff;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.doccat.*;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TextClassifierTrainer {

    public static void main(String[] args) throws IOException {
        // Path to the training data file
        String trainingDataFilePath = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/trash_dataset.txt";

        // Create an InputStreamFactory that wraps a FileInputStream with a BufferedInputStream
        InputStreamFactory inputStreamFactory = new InputStreamFactory() {
            public InputStream createInputStream() throws IOException {
                return new BufferedInputStream(new FileInputStream(trainingDataFilePath));
            }
        };

        // Set up the training parameters
        TrainingParameters trainingParameters = new TrainingParameters();
        trainingParameters.put(TrainingParameters.ITERATIONS_PARAM, 10);
        trainingParameters.put(TrainingParameters.CUTOFF_PARAM, 0);

        // Set up the feature generator
        FeatureGenerator featureGenerator = new NGramFeatureGenerator(1, 2);

        // Set up the document categorizer
        DoccatFactory doccatFactory = new DoccatFactory(new FeatureGenerator[]{featureGenerator});
        DoccatModel doccatModel = null;
        try (ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
             ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream)) {
            doccatModel = DocumentCategorizerME.train("en", sampleStream, trainingParameters, doccatFactory);
        }


        // Save the trained model to a file
        String modelFilePath = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/models/modelTest.bin";
        doccatModel.serialize(new File(modelFilePath));
    }
}
