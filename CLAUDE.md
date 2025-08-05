# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a single-file Java educational project demonstrating Object-Oriented Programming concepts using fruits as an analogy. The project consists of one main file: `Fruit.java`.

## Development Commands

### Compilation
```bash
javac Fruit.java
```

### Running the Program
```bash
java FruitDemo
```

### Running Tests
```bash
java FruitTests
```

### Alternative: Compile and Run in One Command
```bash
javac *.java && java FruitDemo
```

### Compile and Test
```bash
javac *.java && java FruitTests
```

## Code Architecture

The codebase demonstrates core OOP principles through a fruit-based class hierarchy:

### Core Architecture Components

- **Abstract Base Class**: `Fruit` - Defines common properties (name, color, weight, ripeness) and abstract methods that subclasses must implement
- **Interfaces**: 
  - `Edible` - Defines eating behavior and ripeness checking
  - `Processable` - Defines processing capabilities for fruits that can be made into products
- **Concrete Classes**: `Apple`, `Orange`, `Banana` - Each with unique behaviors and properties
- **Composition Class**: `FruitBasket` - Manages collections of fruits and demonstrates composition relationships

### Key OOP Demonstrations

- **Encapsulation**: Private fields with controlled access through getters/setters
- **Inheritance**: All fruit classes extend the abstract `Fruit` class
- **Polymorphism**: Method overriding and interface implementations allow different fruit types to be treated uniformly
- **Abstraction**: Abstract methods force subclasses to implement specific behaviors

### Design Patterns

- **Template Method**: Base `Fruit` class defines the structure, subclasses implement specific details
- **Composition**: `FruitBasket` contains and manages `Fruit` objects

## Working with This Codebase

Since this is a single-file educational project, modifications typically involve:
- Adding new fruit types by extending the `Fruit` class
- Implementing additional interfaces for new behaviors
- Extending the `FruitBasket` functionality
- Adding new demonstration scenarios in the `main` method

## Testing

The project includes a comprehensive test suite in `FruitTests.java` that covers:

- **Unit Tests**: Individual fruit classes (Apple, Orange, Banana)
- **Integration Tests**: FruitBasket functionality and fruit interactions
- **Interface Tests**: Edible and Processable interface implementations
- **Polymorphism Tests**: Behavior verification across different fruit types

### Test Framework

Uses a custom `TestFramework.java` with assertion methods:
- `assertEquals(expected, actual, testName)`
- `assertTrue(condition, testName)`
- `assertFalse(condition, testName)`

### Test Coverage

- All public methods and constructors
- Abstract method implementations
- Interface compliance
- Inheritance relationships
- Edge cases (e.g., banana aging, overripeness)

## Extending the Codebase

When adding new fruit classes, ensure they:
1. Extend the abstract `Fruit` class
2. Implement all abstract methods (`getTaste()`, `getSeedCount()`)
3. Consider implementing `Processable` interface if applicable
4. Follow the established naming and structure patterns
5. **Add corresponding test methods** in `FruitTests.java`

When modifying existing classes:
1. Run the full test suite to ensure no regressions
2. Update tests if behavior changes
3. Add new tests for new functionality