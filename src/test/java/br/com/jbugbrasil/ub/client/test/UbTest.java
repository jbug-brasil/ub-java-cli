/*
 MIT License

 Copyright (c) 2017 JBUG:Brasil

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package br.com.jbugbrasil.ub.client.test;

import br.com.jbugbrasil.ub.client.UBClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fspolti on 5/12/17.
 */
public class UbTest {

    @Test
    public void testUbClientBuilderDefaultValues() {
        UBClient o = new UBClient.UBClientBuilder().term("lol").build();
        Assert.assertEquals("lol", o.getTerm());
        Assert.assertEquals(1, o.getNumberOfResults());
    }

    @Test
    public void testUbClientBuilder() {
        UBClient o = new UBClient.UBClientBuilder().term("rofl").numberOfResults(2).showExample(true).build();
        Assert.assertEquals("rofl", o.getTerm());
        Assert.assertEquals(2, o.getNumberOfResults());
        Assert.assertEquals(true, o.isShowExample());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testNullTerm() {
        UBClient o = new UBClient.UBClientBuilder().build();
    }


    @Test
    public void testDefaultNumberOfresults() {
        UBClient o = new UBClient.UBClientBuilder().term("lol").build();
        Assert.assertEquals(1, o.execute().size());
    }

    @Test
    public void testNumberOfresultsTo1() {
        UBClient o = new UBClient.UBClientBuilder().term("lol").numberOfResults(1).build();
        Assert.assertEquals(1, o.execute().size());
    }

    @Test
    public void testNumberOfresultsTo2() {
        UBClient o = new UBClient.UBClientBuilder().term("lol").numberOfResults(2).build();
        Assert.assertEquals(2, o.execute().size());
    }

    @Test
    public void testShowExample() {
        UBClient o = new UBClient.UBClientBuilder().term("lol").numberOfResults(1).showExample(true).build();
        o.execute().stream().forEach(e -> {
            Assert.assertTrue(e.getExample() != null);
        });
    }
}