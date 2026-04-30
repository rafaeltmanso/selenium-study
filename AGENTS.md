# AGENTS.md

## Tech Stack
- Java 21 (maven.compiler.release=21)
- Maven 3.9+
- Selenium 4.27.0 with JUnit 5.11.4
- Tests use ChromeDriver directly (no WebDriverManager)

## Commands
- Run all tests: `mvn test`
- Run single test class: `mvn test -Dtest=FirstTest`
- Run single method: `mvn test -Dtest=FirstTest#verifyPageSaysRafaelManso`

## Critical Requirements
- **ChromeDriver must be in PATH** - tests instantiate `new ChromeDriver()` directly and fail if not found
- **Internet connection required** - tests navigate to external sites (rafaelmanso.dev, theinternet-tau.vercel.app)
- Tests launch real Chrome browser windows during execution

## Project Structure
- `src/test/java/` - all code lives here (study project, no main sources)
- Two test classes: `FirstTest.java` (personal site) and `TheInternet.java` (the-internet practice site)
- No README or documentation exists
