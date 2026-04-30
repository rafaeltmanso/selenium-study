# AGENTS.md

## Tech Stack
- Java 21 (maven.compiler.release=21)
- Maven 3.9+
- Selenium 4.27.0 with JUnit 5.11.4
- Tests use ChromeDriver directly (no WebDriverManager)

## Commands
- Run all tests: `mvn test`
- Run single test class: `mvn test -Dtest=HomePageTest`
- Run single method: `mvn test -Dtest=HomePageTest#testPageTitle`

## Critical Requirements
- **ChromeDriver must be in PATH** - tests instantiate `new ChromeDriver()` directly and fail if not found
- **Internet connection required** - tests navigate to external sites (rafaelmanso.dev, theinternet-tau.vercel.app)
- Tests run in headless Chrome by default

## Architecture: Page Object Model (POM)

```
src/test/java/com/example/study/
├── base/
│   └── BaseTest.java          # Shared setup/teardown, WebDriver initialization
├── pages/                   # Page Objects (one per page/section)
│   ├── HomePage.java        # rafaelmanso.dev
│   ├── CheckboxesPage.java
│   ├── FormAuthPage.java
│   ├── DragDropPage.java
│   ├── DropdownPage.java
│   ├── ContextMenuPage.java
│   ├── DynamicLoadingPage.java
│   ├── DynamicControlsPage.java
│   ├── FloatingMenuPage.java
│   ├── FramesPage.java
│   ├── HoversPage.java
│   ├── SortableTablesPage.java
│   ├── MultipleWindowsPage.java
│   ├── JsAlertsPage.java
│   ├── EntryAdPage.java
│   ├── FileUploadPage.java
│   ├── FileDownloadPage.java
│   └── AddRemovePage.java
├── tests/                   # Test Classes (one per page)
│   ├── HomePageTest.java
│   ├── CheckboxesTest.java
│   ├── FormAuthTest.java
│   ├── DragDropTest.java
│   ├── DropdownTest.java
│   ├── ContextMenuTest.java
│   ├── DynamicLoadingTest.java
│   ├── DynamicControlsTest.java
│   ├── FloatingMenuTest.java
│   ├── FramesTest.java
│   ├── HoversTest.java
│   ├── SortableTablesTest.java
│   ├── MultipleWindowsTest.java
│   ├── JsAlertsTest.java
│   ├── EntryAdTest.java
│   └── AddRemoveTest.java
└── utils/
    └── DriverFactory.java  # ChromeDriver configuration
```

## Pattern Guidelines
- **Pages**: encapsulate all UI logic (locators, actions, waits)
- **Tests**: contain only assertions and test flow
- **BaseTest**: manages WebDriver lifecycle
- **DriverFactory**: creates configured ChromeDriver instance
- Change UI locators in page objects only - tests should not reference By locators directly