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

package br.com.jbugbrasil.ub.client;

import br.com.jbugbrasil.ub.client.helper.CustomTermResponse;
import br.com.jbugbrasil.ub.client.term.Term;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fspolti on 5/12/17.
 */
public class UBClient implements IUBClient {

    private static final String UB_ENDPOINT = "http://api.urbandictionary.com/v0/define";

    private final String term;
    private final int numberOfResults;
    private final boolean showExample;

    public UBClient(UBClientBuilder builder) {
        this.term = builder.term;
        this.numberOfResults = builder.numberOfResults;
        this.showExample = builder.showExample;
    }

    public String getTerm() {
        return term;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public boolean isShowExample() {
        return showExample;
    }

    @Override
    public String toString() {
        return "UBClientBuilder{" +
                "term='" + term + '\'' +
                ", numberOfResults=" + numberOfResults +
                '}';
    }

    /**
     * The request builder
     * It should have at least the term parameter
     *  term - term to be searched
     *  numberOfResults - the number of term definitions that will be shown
     *      in its absence, the number should be 1
     *  showExample - if true, return a example of the term
     */
    public static class UBClientBuilder {

        private String term;
        private int numberOfResults = 1;
        private boolean showExample;

        public UBClientBuilder() {
        }

        public UBClientBuilder numberOfResults(int numberOfResults) {
            this.numberOfResults = numberOfResults;
            return this;
        }

        public UBClientBuilder term(String term) {
            this.term = prepareTerm(term);
            return this;
        }

        public UBClientBuilder showExample(boolean show) {
            this.showExample = show;
            return this;
        }

        public UBClient build() {
            if (null == term || "".equals(term)) {
                throw new IllegalArgumentException("The term parameter cannot be null.");
            }
            return new UBClient(this);
        }

        private String prepareTerm (String term) {
            return term.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");
        }
    }

    /**
     * Execute the request
     * @return {@link CustomTermResponse}
     */
    public List<CustomTermResponse> execute() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(this.UB_ENDPOINT + "?term=" + this.term);

        Response response = target.request().get();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to connect in the ub endpoint " + this.UB_ENDPOINT + ", status code is: " + response.getStatus());
        }

        List<CustomTermResponse> c = new ArrayList<>();

        response.readEntity(Term.class)
                .getTermDefinitions()
                .stream().limit(this.numberOfResults)
                .forEach(entry -> {
                    if (this.showExample) {
                        c.add(new CustomTermResponse(entry.getWord(), entry.getDefinition(), entry.getExample()));
                    } else {
                        c.add(new CustomTermResponse(entry.getWord(), entry.getDefinition()));
                    }
                });

        return c;
    }
}