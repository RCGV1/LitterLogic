package com.web.application.backEndStuff.depreciated;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Deprecated
public class OldBinFinder {
    public String[] getBin(File image) throws IOException {
        OldLableGenerator LGen =  new OldLableGenerator();
        OldBinMapper BinMap    =  new OldBinMapper();
        BufferedImage  bImage  =  ImageIO.read(image);

        return BinMap.getBinFromLables(LGen.getLable(bImage));
    }

    enum OutIndex {
        BIN,
        EXPLANATION
    }
}
