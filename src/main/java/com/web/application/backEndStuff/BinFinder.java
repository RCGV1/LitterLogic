package com.web.application.backEndStuff;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BinFinder {
    public String[] getBin(File image) throws IOException {
        LableGenerator LGen    =  new LableGenerator();
        BinMapper      BinMap  =  new BinMapper();
        BufferedImage  bImage  =  ImageIO.read(image);

        return BinMap.getBinFromLables(LGen.getLable(bImage));
    }

    enum OutIndex {
        BIN,
        EXPLANATION
    }
}
