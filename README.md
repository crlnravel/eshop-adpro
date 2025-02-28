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
- Code becomes easier to read, understand, and modify.
- Reduces the chances of introducing bugs when making changes.

### 2. **Enhanced Scalability**
- The system can be extended with new features without affecting existing functionality.
- Encourages modular design, making it easier to grow the codebase.

### 3. **Better Code Reusability**
- Encourages designing reusable components that can be used across multiple projects.
- Reduces duplication, leading to a cleaner codebase.

### 4. **Easier Debugging and Testing**
- Promotes writing loosely coupled code, making unit testing more effective.
- Reduces dependencies, leading to faster and more reliable test execution.

### 5. **Encourages Separation of Concerns**
- Each class and module has a single responsibility, making code more organized.
- Reduces the risk of unintended side effects when modifying a part of the system.

### 6. **Enhances Code Flexibility & Adaptability**
- Changes in one part of the system don’t cascade into unrelated areas.
- Encourages dependency injection, making it easier to swap implementations.

### 7. **Facilitates Team Collaboration**
- Code becomes more understandable for team members, improving communication.
- Allows multiple developers to work on different components independently.

### 8. **Reduces Technical Debt**
- Well-structured code prevents hacks and workarounds that accumulate technical debt.
- Leads to a more stable and maintainable long-term codebase.

By following SOLID principles, developers can create software that is easier to manage, scale, and extend while minimizing risks associated with changes and new requirements.

## Disadvantages of Not Applying SOLID Principles

Not applying **SOLID** principles in software development can lead to several disadvantages, including:

### 1. **Tightly Coupled Code**
- Without **Single Responsibility Principle (SRP)** and **Dependency Inversion Principle (DIP)**, different parts of the system become highly dependent on each other.
- This makes it difficult to modify or extend a part of the system without affecting others.

### 2. **Difficult Maintenance & Scalability**
- Lack of **Open/Closed Principle (OCP)** means modifying existing code for new features rather than extending it.
- This increases the risk of introducing bugs and makes maintenance more complex.

### 3. **Code Reusability is Reduced**
- Without **Interface Segregation Principle (ISP)**, classes often have unnecessary dependencies on methods they don’t use.
- This makes it harder to reuse components across different parts of the application.

### 4. **Harder Unit Testing**
- Without **Dependency Inversion Principle (DIP)**, code depends on concrete implementations rather than abstractions.
- This makes it difficult to mock dependencies for testing, leading to unreliable or difficult-to-write tests.

### 5. **Increased Risk of Bugs**
- Violating **Liskov Substitution Principle (LSP)** can lead to unexpected behavior when subclass instances replace superclass instances.
- This can cause runtime errors and subtle bugs that are hard to track.

### 6. **Poor Readability & Maintainability**
- Lack of **SRP** leads to bloated classes that handle multiple responsibilities.
- This makes the codebase harder to understand and maintain, especially for new developers.

### 7. **Refactoring Becomes Expensive**
- Without a well-structured architecture, small changes may require major modifications across multiple files.
- This increases development time and cost.

Ignoring SOLID principles leads to **fragile, tightly coupled, and difficult-to-maintain code**. While not always necessary in very small projects, following SOLID is essential for building scalable, testable, and maintainable software systems.
