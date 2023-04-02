package com.web.application.backEndStuff;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.BeamSearch;
import opennlp.tools.ml.model.MaxentModel;
import opennlp.tools.ml.model.SequenceClassificationModel;
import opennlp.tools.ml.model.SequenceStream;
import opennlp.tools.ml.model.TwoPassDataIndexer;
import opennlp.tools.ml.perceptron.PerceptronTrainer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TrashClassifier {

    private static final String MODEL_FILE = "src/main/java/com/web/application/backEndStuff/models/trash_model.bin";

    public void trainModel(String trainingDataFile) throws IOException {
        // load training data
        List<DocumentSample> trainingSamples = new ArrayList<>();
        ObjectStream<String> lineStream = new PlainTextByLineStream((InputStreamFactory) new FileInputStream(trainingDataFile), StandardCharsets.UTF_8);
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
        while (true) {
            DocumentSample sample = sampleStream.read();
            if (sample == null) {
                break;
            }
            trainingSamples.add(sample);
        }
        sampleStream.close();

        // train model
        DoccatFactory factory = new DoccatFactory();
        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 100);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);
        DoccatModel model = DocumentCategorizerME.train("en", new DocumentSampleStream((ObjectStream<String>) trainingSamples), params, factory);

        // save model
        model.serialize(new File(MODEL_FILE));
    }

    public String classify(String text) throws IOException {
        // load model
        DoccatModel model = new DoccatModel(new FileInputStream(MODEL_FILE));
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

        // classify text
        double[] probabilities = categorizer.categorize(new String[]{text});
        String category = categorizer.getBestCategory(probabilities);
        return category;
    }
}
