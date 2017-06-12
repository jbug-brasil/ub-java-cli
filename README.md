# Urban Dictionary Java Client
Urban Dictionary Client to use within your Java applications


Supports:
- org.codehaus.jackson
- com.fasterxml.jackson

### How to use it?
First you need to build it until it is not available in any public manve repository (we are working on it).

```bash
git clone https://github.com/jbug-brasil/urban-dictionary-client-api.git
cd urban-dictionary-client-api && mvn clean install
```
PS: the tests needs internet connection (it does a real query on Urban Dictionary API), if you don't have it, just disable the tests using the -DskipTests flag.

Onde built, add the following dependency on your pom.xml:

```java
<!-- Urban Dictionary client -->
<dependency>
    <groupId>br.com.jbugbrasil</groupId>
    <artifactId>urban-dictionary-client-api</artifactId>
    <version>1.1.Final</version>
</dependency>
```

Then

```java
UrbanDictionaryClient client = new UrbanDictionaryClientBuilder().term("lol").numberOfResults(1).showExample().build();
List<CustomTermResponse> response = client.execute();
```

Where:
- **term** is the word that you want to search.
- **numberOfResults** in it absence the default value is 1, you can add more results to your search by setting this parameter.
- **showExample** by default it is false, if you set it the result will bring a exemple about the term usage.

### Issues or suggestions?
Please submit your suggestion, we really appreciate it.
