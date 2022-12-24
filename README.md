<!--suppress HtmlDeprecatedAttribute -->
<div align="center">

<a href="https://github.com/HttpMarco/Aeon"><img src="https://img.shields.io/github/stars/HttpMarco/Aeon?color=10c298" alt="Stars Badge"/></a>
<a href="https://github.com/HttpMarco/Aeon"><img src="https://img.shields.io/github/forks/HttpMarco/Aeon?color=10c298" alt="Forks Badge"/></a>
<a href="https://github.com/HttpMarco/Aeon"><img src="https://img.shields.io/github/issues-pr/HttpMarco/Aeon?color=10c298" alt="Pull Requests Badge"/></a>
<a href="https://github.com/HttpMarco/Aeon"><img src="https://img.shields.io/github/issues/HttpMarco/Aeon?color=10c298" alt="Issues Badge"/></a>
<a href="https://github.com/HttpMarco/Aeon"><img alt="GitHub contributors" src="https://img.shields.io/github/contributors/HttpMarco/Aeon?color=10c298"></a>
[![](https://jitpack.io/v/HttpMarco/Aeon.svg)](https://jitpack.io/#HttpMarco/Aeon)
<div>
    <a href="https://discord.gg/zacX9b2wCF">SUPPORT DISCORD</a>
</div>
</div>

****

Gradle Dependency

````groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.HttpMarco:Aeon:TAG'
}
````
The newest version: https://jitpack.io/#HttpMarco/Aeon
****

Maven Dependency

````xml

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
````

````xml

<dependency>
    <groupId>com.github.HttpMarco</groupId>
    <artifactId>Aeon</artifactId>
    <version>Tag</version>
</dependency>
````


The newest version: https://jitpack.io/#HttpMarco/Aeon
***

Examples
(Simple property object)

````java

@Getter
@Options(path = "src/test/java/net/http/aeon/", name = "config")
public class TestConfiguration {

    private final String name;
    private final int testValue;
    private final TestObject testObject;

    public TestConfiguration() {
        this.name = "test";
        this.testValue = 22;
        this.testObject = new TestObject();
    }
}
````

Save, read & auto manage of configuration

````java
public void handle(){
   TestConfiguration insert=Aeon.insert(new TestConfiguration());
   System.out.println(insert.getTestValue());
}
````

Result: 
````
testObject: [
   value: 20
]
name: test
testValue: 22
````

Add header or spaces for configuration field 
`````java 
@CommentedArgument(comment = "Test comment", type = Emphasizing.PRIMARY)
`````

****

Aeon vs Gson

- New fields are automatically added to the file. (Best option for updates)
- Rename simple fields
- Add comments or spacers.
- Work faster than Gson.
- Easy handling of configurations
- ignores upper and lower case for enumeration serialization

****

Todo 'Release':

- [ ] Handle of null parameters
- [ ] rename for configuration files
- [ ] Support Enumerations, Array, Map, Lists, Pair
- [ ] Add comments for fields
- [ ] remove duplicated renamed fields
- [ ] factory for default values
- [ ] Customize handler for object serialization