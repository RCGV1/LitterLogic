package com.web.application.backEndStuff.depreciated;

import java.awt.image.BufferedImage;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OldLableGenerator {
    public String[] getLable (BufferedImage image) {



        return new String[]{};
    }

    public static void main (String[] args) throws IOException {
        detectLabels("/Users/benfaer/Desktop/Screen Shot 2023-03-27 at 7.49.37 AM.png");
    }

    // Detects labels in the specified local image.
    public static List<String> detectLabels(String filePath) throws IOException {
        List<String> out = new ArrayList<>();

        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString           imgBytes  =  ByteString.readFrom(new FileInputStream(filePath));
        Image                img       =  Image.newBuilder().setContent(imgBytes).build();
        Feature              feature   =  Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        AnnotateImageRequest request   =  AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(img).build();

        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return null;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    annotation
                            .getAllFields()
                            //.forEach((k, v) -> System.out.format("%s : %s%n", k, v.toString()));
                            .forEach((k, v) -> {
                                System.out.println((k.getName().equals("description") ? v.toString() : "     " + k.getName() + " : " + v.toString()));
                                if(k.getName().equals("description")) {
                                    out.add(v.toString());
                                }
                            });
                }
            }
        }

        return out;
    }
}
