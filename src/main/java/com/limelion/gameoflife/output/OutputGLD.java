/*
 * Copyright (c) 2019 Gui-Yôm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.limelion.gameoflife.output;

import com.limelion.gameoflife.GameOfLife;
import com.limelion.gameoflife.Utils;

import java.io.IOException;
import java.util.zip.Deflater;

public class OutputGLD extends OutputAdaptater {

    @Override
    public OutputAdaptater feed(byte[] data) throws IOException {

        if (isInited()) {

            output.write(GameOfLife.MAGIC);
            output.write(GameOfLife.VERSION);
            output.write(Utils.itoba(ei.getWidth()));
            output.write(Utils.itoba(ei.getHeight()));

            // First pass
            Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
            deflater.setInput(data);
            deflater.finish();

            byte[] buf = new byte[ei.getHeight() * ei.getWidth()];
            int comp = deflater.deflate(buf);
            assert comp != 0;
            deflater.end();

            // Finally write to file
            output.write(buf, 0, comp);

        } else
            throw new IllegalStateException("Please init the OutputAdaptater first !");

        return this;
    }

    @Override
    public OutputType getType() {

        return OutputType.GLD;
    }
}