Nama: Carleano Ravelza Wongso

NPM: 2306213022

Kelas: Adpro-B

# Reflection 1: Coding Standard and Secure Coding
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code

##  Coding Standard

### Separation of Concerns

I have structured the application following the layered architecture, ensuring that responsibilities are well-separated across different components:

- Controller Layer: Handles HTTP requests and responses. 
- Service Layer: Contains business logic and interacts with the repository.
- Repository Layer: Manages entities

### Meaningful Naming

I use descriptive and intuitive names for variables, methods, classes, and packages to improve readability.

### Consistent Formatting and Indentation

I maintain a uniform coding style by:
- Using proper indentation, spacing, and line breaks.
- Following Java conventions (e.g., class names in PascalCase, variables in camelCase).
- Keeping method length manageable and avoiding long parameter lists.

## Secure Coding Practices

### Input Validation

I ensure that user input is validated to prevent security vulnerabilities.

### Error Handling

I implement structured exception handling to prevent system crashes and information leakage.

# Reflection 2: Testing

## Unit Testing

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

After writing unit tests, I feel more confident about my code's reliability. Unit tests help ensure that individual components of the program function as expected and make debugging easier by pinpointing failures early. They also provide a safety net when making changes, reducing the risk of unintended side effects.

### Number of Unit Tests

The number of unit tests in a class depends on the complexity of the class and its methods. Generally, each method should have at least one test case, but multiple tests should be written to cover different input scenarios, edge cases, and error handling. 

### Ensuring Unit Tests Are Sufficient

To ensure that unit tests adequately verify the program, I considered the following:
- Code Coverage
- Edge Cases
- Mutation Testing

### Does 100% Code Coverage Mean No Bugs?

No, 100% code coverage does not guarantee that the code is free of bugs or errors. Code coverage only indicates which lines of code were executed during testing but does not ensure logical correctness. Therefore, while high code coverage is beneficial, it should be complemented with integration testing, end-to-end testing, and real-world testing to ensure software reliability.

## Functional Testing

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. <br><br> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

The new functional test suite for verifying product list items raises several clean code concerns:

- Code Duplication
- Hard Coded Values

To improve the functional test, we can do the following:
- Create a base class for the tests
- Making sure we get our constants values (e.g. Base URL, product ID, product name) come from one source (e.g. env file).

# Reflection 3: CI/CD & DevOps

> You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.
> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

## Code Quality Improvement

During the CI/CD implementation, I encountered and fixed several code quality issues to ensure a smoother automated workflow. Below are the key issues I addressed and my approach to resolving them:

1. Token-Permission Issue
Problem: The GitHub Actions workflow lacked appropriate permission settings, leading to potential security risks and failures in accessing necessary resources.
Solution: I added top-level permission settings in the GitHub Actions script to explicitly define the required access level. This ensured that workflows had the correct permissions to execute securely while preventing unauthorized actions.

2. Dependency Management and Updates
Problem: Keeping dependencies updated manually was inefficient and posed security risks due to outdated libraries.
Solution: I introduced multiple automated dependency management bots:
Renovatebot: Automates dependency updates and creates pull requests with version changes.
Dependabot: Provides security updates for dependencies.

4. Version Pinning in GitHub Actions
Problem: Using floating versions for GitHub Actions scripts posed a risk of unexpected behavior due to updates.
Solution: I configured Renovatebot to automatically pin versions using commit hashes instead of tags. This ensures that the workflow runs on a fixed, tested version, improving reliability.

5. Branch Protection Rules
Problem: The repository allowed direct pushes to the main branch, which could lead to unreviewed changes being merged.
Solution: I enforced branch protection rules, requiring:
No direct pushes to main.
Merge restrictions so that changes require approval before being merged.
CI/CD checks to pass before merging, ensuring quality control.
By implementing these fixes, I enhanced the security, maintainability, and reliability of the CI/CD pipeline while enforcing best practices in code quality and dependency management.

## CI/CD Implementation

My current CI/CD implementation meets the definition of Continuous Integration and Continuous Deployment. My CI process ensures that every code change is automatically tested, analyzed for code quality, and checked for security vulnerabilities before being merged. This guarantees that only well-tested and high-quality code reaches the main branch.

For CD, my deployment to Koyeb is fully automated, meaning every successful change is immediately deployed without manual intervention. This aligns with the principles of Continuous Deployment, where new updates are pushed to production as soon as they pass all necessary checks. With this setup, I have achieved an efficient workflow that minimizes delays and ensures rapid, reliable software releases. 

# Reflection 4: SOLID Principle

> Apply the SOLID principles you have learned. You are allowed to modify the source code according to the principles you want to implement. Please answer the following questions: 
> 1) Explain what principles you apply to your project!
> 2) Explain the advantages of applying SOLID principles to your project with examples.
> 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

## Applied SOLID Principles

### Single Responsibility Principle

The Single Responsibility Principle states that a class should have only one reason to change, meaning it should have only one responsibility.
This principle are reflected by the MVC architecture.

1. Models (Product, Car) are responsible only for data representation
2. Repositories (ProductRepository, CarRepository) handle only data persistence operations
3. Services (ProductService, CarService) contain only business logic
4. Controllers (ProductController, CarController) are only responsible for handling HTTP requests

### Open/Closed Principle (OCP)

The Open/Closed Principle states that software entities should be open for extension but closed for modification.

1. Abstract Base Classes (e.g. Item) that can be extended without modifying existing code
2. Interfaces that define contracts (e.g. ItemService, ProductService, ItemRepository) which can be implemented as long as it fits the implemented interface criteria.
3. Inheritance Hierarchies that allow new functionality to be added through extension

### Liskov Subtitution Principle (LSP)

The Liskov Substitution Principle states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

1. Subclasses like Product and Car extend Item (abstract class) without breaking its behavior
2. ItemService and ItemRepository define contracts that are correctly implemented by their subclasses
3. Each layer can work with any Item (Car or Product) implementation without knowing its concrete type and still going to work as expected

### Interface Segregation Principle (ISP)

The Interface Segregation Principle states that clients should not be forced to depend on interfaces they do not use.
This is implemented by interface hierarchies that allow clients to depend only on what they need (e.g. ProductController only uses ProductService without using CarService)

### Dependency Inversion Principle (DIP)

The Dependency Inversion Principle states that high-level modules should not depend on low-level modules; both should depend on abstractions.

1. Spring already implemented IoC by default, that's why we do not have to connect each layer by ourselves 
2. Each layer only uses interfaces instead of concrete implementation
3. Interfaces that abstract away implementation details

## Advantages of Applying SOLID Principles

Applying SOLID principles in software development provides several advantages, including:

### 1. **Improved Maintainability**
By following the SRP, classes have a single responsibility, making it easier to understand, modify, and extend the codebase. For example, the ProductService class is responsible for product-related logic, while the ProductController class handles HTTP requests and responses.

### 2. **Enhanced Flexibility**
By adhering to the OCP, classes are open for extension but closed for modification, allowing new features to be added without altering existing code. For example, new models can be easily added by extending the ModelAbstract class without changing the base functionality.

### 3. **Increased Reusability**
By following the LSP, subclasses can be substituted for their base class without affecting the program's behavior, promoting code reusability and consistency. For example, the Car and Product classes can be used interchangeably in the ModelAbstract class.

## Disadvantages of Not Applying SOLID Principles

Not applying **SOLID** principles in software development can lead to several disadvantages, including:

### 1. **Increased Complexity**
Ignoring the SRP can lead to classes with multiple responsibilities, making the code harder to understand and maintain. For example, a class handling both business logic and data access can become overly complex and difficult to debug.

### 2. **Tight Coupling**
Disregarding the OCP can result in tightly coupled classes, making it challenging to add new features or modify existing ones without affecting other parts of the code. For instance, changes to a base class might require updates in multiple subclasses.

### 3. **Inconsistent Behavior**
Neglecting the LSP can cause subclasses to behave inconsistently with their base class, reducing code reliability. For example, if subclasses do not adhere to the base class's contract, they may introduce unexpected behavior or errors.

Ignoring SOLID principles leads to **fragile, tightly coupled, and difficult-to-maintain code**. While not always necessary in very small projects, following SOLID is essential for building scalable, testable, and maintainable software systems.

# Reflection 4: Refactoring and TDD

## Reflections on Tutorial

### Reflection on TDD Flow
> Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

The TDD workflow has been helpful in guiding development, ensuring early bug detection, and maintaining code quality. However, there are areas for improvement, such as refining test coverage, handling more edge cases, and improving the structure of test cases. In future tests, I need to plan better test scenarios and ensure that tests truly drive the design rather than just verifying existing implementations.

### Reflection on F.I.R.S.T. Principle
> You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

My tests generally follow the F.I.R.S.T. principle, as they are fast, independent, and repeatable. However, there are some aspects that need improvement, such as better isolation of dependencies and ensuring tests remain self-contained. In future tests, I will focus on using proper mocking techniques, improving test organization, and maintaining clear and concise assertions to enhance reliability and maintainability.
