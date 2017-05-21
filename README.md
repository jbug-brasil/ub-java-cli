# Urban Dictionary Java Client
Urban Dictionary Client to use within your Java applications


Supports:
- org.codehaus.jackson
- com.fasterxml.jackson

### How to use it?
First you need to build it until it is not available in any public manve repository (we are working on it).

```bash
git clone https://github.com/jbug-brasil/ub-java-client.git
cd ub-java-client && mvn clean install
```
PS: the tests needs internet connection (it does a real query on Urban Dictionary API), if you don't have it, just disable the tests using the -DskipTests flag.

Onde built, add the following dependency on your pom.xml:

```java
<!-- Urban Dictionary client -->
<dependency>
    <groupId>br.com.jbugbrasil</groupId>
    <artifactId>urban-dictionary-client-api</artifactId>
    <version>1.0.Final</version>
</dependency>
```

Then

```java
UBClient client = new UBClient.UBClientBuilder().term("lol").numberOfResults(1).showExample(true).build();
List<CustomTermResponse> ubResponse = client.execute();
```

Where:
- **term** is the word that you want to search.
- **numberOfResults** in it absence the default value is 1, you can add more results to your search by setting this parameter.
- **showExample** by default it is false, if you set it to true the search will bring a exemple about the term usage.

### Issues or suggestions?
Please submit your suggestion, we really appreciate it.
