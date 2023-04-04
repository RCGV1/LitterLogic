package com.web.application.backEndStuff;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;

public class TextClassifierTrainer {

    public static void main(String[] args) throws IOException {
        // Path to the training data file
        String trainingDataFilePath = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/trash_dataset.txt";
        String modelPATH = "/Users/benfaer/IdeaProjects/LitterLogic/src/main/java/com/web/application/backEndStuff/models/modelTest.bin";
        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(trainingDataFilePath));
        ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

        // Use CUT_OFF as zero since we will use very few samples.
        // BagOfWordsFeatureGenerator will treat each word as a feature. Since we have
        // few samples, each feature/word will have small counts, so it won't meet high
        // cutoff.
        TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
        params.put(TrainingParameters.CUTOFF_PARAM, 0);
        DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});

        // Train a model with classifications from above file.
        DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);

        // Serialize model to some file so that next time we don't have to again train a
        // model. Next time We can just load this file directly into model.
        model.serialize(new File(modelPATH));


        // Test the model
        System.out.println(TrashClassifier.findSmartBin("apple core"));
        System.out.println(TrashClassifier.findSmartBin("plastic bottle cap"));
        System.out.println(TrashClassifier.findSmartBin("disposable bag"));
        System.out.println(TrashClassifier.findSmartBin("rotten meat"));

    }
}