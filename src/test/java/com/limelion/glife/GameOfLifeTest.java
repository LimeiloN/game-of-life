/*
 * Copyright (c) 2019 Gui-Yôm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.limelion.glife;

import com.limelion.glife.input.Input;
import com.limelion.glife.output.OutputAdaptater;
import com.limelion.glife.output.OutputGLD;
import com.limelion.glife.output.OutputInfo;
import com.limelion.glife.output.OutputType;
import com.limelion.glife.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GameOfLifeTest {

    @Test
    public void test() throws IOException {

        //HERE WE GO !
        GameOfLife gol = new GameOfLife(500, Rule.Square2D.CONWAY);

        //gol.getUniverse().drawLine(20, 20, 20, 26);
        //gol.getUniverse().drawLine(500, 500, 500, 800);
        gol.drawLine(50, 250, 450, 250);

        gol.nextGen(50);

        OutputInfo oi = new OutputInfo(gol)
            .setDelay(250, TimeUnit.MILLISECONDS)
            .setRepeats(0);

        //OutputAdaptater outAPNG = OutputType.APNG.getImpl().init(Utils.cleanCreate("output/output.apng"), oi);

        OutputAdaptater outGIF = OutputType.GIF.getImpl().init(oi, Utils.cleanCreate("output/output.gif"));

        OutputAdaptater outPNG = OutputType.PNG.getImpl().init(oi.setBaseFileName("output/output_gen@gen@.png"));

        //OutputAdaptater outBMP = OutputType.BMP.getImpl().init(Utils.cleanCreate("output/output.bmp"), oi);

        OutputAdaptater outGLD = OutputType.GLD.getImpl().init(oi, Utils.cleanCreate("output/output.gld"));

        //gol.recordGen(outAPNG, 200, true);
        //gol.recordNext(outPNG);
        //gol.recordNext(outBMP);
        gol.record(outGLD);

        gol.recordGen(Arrays.asList(outPNG, outGIF), 5, false);
        gol.recordGen(outGIF, 395, false);

        //outAPNG.finish();
        outGIF.finish();
        outPNG.finish();
        //outBMP.finish();
        outGLD.finish();

        //Utils.createhtmlplayer("output/playerAPNG.html", "output.apng");
        Utils.createhtmlplayer("output/playerGIF.html", "output.gif");
        //Utils.createhtmlplayer("output/playerPNG.html", "output.png");
        //Utils.createhtmlplayer("output/playerBMP.html", "output.bmp");

        System.out.println("Generated files.");
        System.out.println(gol.getStats());

        GameOfLife gol2 = new GameOfLife(Input.fromGLD(new File("output/output.gld")));


        OutputAdaptater outGLD2 = new OutputGLD().init(new OutputInfo(gol2), Utils.cleanCreate("output/output2.gld"));

        gol2.record(outGLD2);

        outGLD2.finish();

        System.out.println("Generated second fileset.");
        System.out.println("Comparing ...");

        byte[] gld1 = Files.readAllBytes(new File("output/output.gld").toPath());
        byte[] gld2 = Files.readAllBytes(new File("output/output2.gld").toPath());

        Assert.assertArrayEquals(gld1, gld2);

        System.out.println("Files are similar. OK");
    }
}