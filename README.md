### About:
high level class diagram of domain model classes [here](https://app.creately.com/diagram/V7wQ3LqHsi6/edit) 

SonarQube report [here](https://github.com/alturany/DiscountsService/blob/master/DiscountServiceSonarQube.pdf)
### Assumptions:
1) Assumed that calculating the bill cost need not to update the DB even though in real projects it should, so accordingly the REST service used the method GET
2) No need to track the price history of items.
3) No need to track the discount history of user categories.
4) Even though the discount percentages were fixed in requirements but the design allowed to change the percentages in DB
5) If this were a real project price of items before and after discount needs to be displayed.
6) User can be one of categories employee, affiliate or regular and can't belong to multi-category
7) The cost calculation algorithm has been extracted into it's own class as per gang of four strategy patten to allow the flexibility of changing the algorithm, for example suppose it's sales period then there would be different business for discounts calculation.
### To run the application on Linux or Bash shell on Windows:
1) Install [java 11 JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2) define JAVA_HOME path variable [here](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)
3) Install [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
4) Download the project from GitHub 
    ```
        git clone https://github.com/alturany/DiscountsService.git
    ```
5) Execute this Maven command to run the server 
    ```
        ./mvnw spring-boot:run
    ```
    Checkstyle static code analysis runs as part of build, and the report can be found in
    ```
        target/checkstyle-result.xml
    ```
6) To run Unit tests and generate JoCoCo coverage report
    ```
        ./mvnw clean verify
    ```
    The report will be located in 
    ```
        target/jacoco-report/index.html
    ```
