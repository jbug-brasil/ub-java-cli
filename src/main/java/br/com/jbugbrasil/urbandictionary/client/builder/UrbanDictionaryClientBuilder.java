package br.com.jbugbrasil.urbandictionary.client.builder;

import br.com.jbugbrasil.urbandictionary.client.UrbanDictionaryClient;

/**
 * The client builder
 * It should have at least the term parameter
 *  term - term to be searched
 *  numberOfResults - the number of term definitions that will be shown
 *      in its absence, the number should be 1
 *  showExample - if specified, return a example of the term
 */
public class UrbanDictionaryClientBuilder {

    public String term;
    public int numberOfResults = 1;
    public boolean showExample;

    public UrbanDictionaryClientBuilder() {
    }

    public UrbanDictionaryClientBuilder numberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
        return this;
    }

    public UrbanDictionaryClientBuilder term(String term) {
        this.term = term;
        return this;
    }

    public UrbanDictionaryClientBuilder showExample() {
        this.showExample = true;
        return this;
    }

    public UrbanDictionaryClient build() {
        if (null == term || "".equals(term)) {
            throw new IllegalArgumentException("The term parameter cannot be null.");
        }
        return new UrbanDictionaryClient(this);
    }
}

