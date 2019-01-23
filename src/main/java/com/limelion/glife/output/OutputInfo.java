/*
 * Copyright (c) 2018 Gui-Yôm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.limelion.glife.output;

import com.limelion.glife.GameOfLife;
import com.limelion.glife.Metadata;

import java.util.concurrent.TimeUnit;

/**
 * A class holding data about the encoding process.
 */
public class OutputInfo {

    private int delay;
    private int repeats;
    // Used when creating multiples files
    // @gen@ to include generation
    // @n@ to include the img number
    private String baseFileName = null;
    private Metadata metadata;

    public OutputInfo(GameOfLife gol) {

        delay = 0;
        repeats = 0;
        this.metadata = gol.getMetadata();
    }

    public String getBaseFileName() {

        return baseFileName;
    }

    public OutputInfo setBaseFileName(String baseFileName) {

        this.baseFileName = baseFileName;
        return this;
    }

    public Metadata getMetadata() {

        return metadata;
    }

    public int getRepeats() {

        return repeats;
    }

    public OutputInfo setRepeats(int repeats) {

        this.repeats = repeats;
        return this;
    }

    public OutputInfo setDelay(int time, TimeUnit timeUnit) {

        delay = (int) TimeUnit.MILLISECONDS.convert(time, timeUnit);
        return this;
    }

    public int getDelay() {

        return delay;
    }

}