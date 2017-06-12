package br.com.jbugbrasil.urbandictionary.client.helper;

/**
 * Created by fspolti on 5/15/17.
 */
public class CustomTermResponse {

    private String term;
    private String definition;
    private String example;

    public CustomTermResponse(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public CustomTermResponse(String term, String definition, String example) {
        this.term = term;
        this.definition = definition;
        this.example = example;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "CustomTermResponse{" +
                "term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
