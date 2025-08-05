# Java OOP Fruit Example 🍎🍊🍌

A comprehensive educational project demonstrating Object-Oriented Programming (OOP) concepts using fruits as an intuitive analogy. This project is designed for newcomers to Java and OOP, featuring extensive comments, complete test coverage, and practical examples.

## 🎯 Learning Objectives

By studying this project, you will understand:
- **The Four Pillars of OOP**: Encapsulation, Inheritance, Polymorphism, and Abstraction
- **Java Programming Fundamentals**: Classes, objects, interfaces, and method overriding
- **Software Testing**: Unit testing, integration testing, and test-driven development
- **Code Organization**: Clean code practices, documentation, and project structure

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [OOP Concepts Demonstrated](#-oop-concepts-demonstrated)
- [Core Classes](#-core-classes)
- [Testing](#-testing)
- [Installation & Development Environment](#-installation--development-environment)
- [Developer Experience (DX)](#-developer-experience-dx)
- [Usage Examples](#-usage-examples)
- [Learning Path](#-learning-path)
- [Contributing](#-contributing)
- [License](#-license)

## 🚀 Quick Start

```bash
# Clone or download the project
cd Fruit

# Compile all Java files
javac *.java

# Run the main demonstration
java FruitDemo

# Run the comprehensive test suite
java FruitTests
```

## 📁 Project Structure

```
Fruit/
├── README.md              # This comprehensive guide
├── CLAUDE.md              # Development guidance for AI assistants
├── Fruit.java             # Main source file with all classes
├── FruitTests.java        # Comprehensive test suite (136 tests)
├── TestFramework.java     # Custom testing framework
└── *.class               # Compiled Java bytecode files
```

### Key Files

| File | Purpose | Lines of Code |
|------|---------|---------------|
| `Fruit.java` | Main source with all OOP examples | ~550 |
| `FruitTests.java` | Complete test coverage | ~400 |
| `TestFramework.java` | Simple testing framework | ~115 |
| `Makefile` | Task automation and workflows | ~200 |
| `scripts/*.sh` | Enhanced development scripts | ~600 |
| **Total Project** | **All files (code + config + docs)** | **~3,300** |

## 🎓 OOP Concepts Demonstrated

### 1. **Encapsulation** 🔒
- **Private fields** with controlled access
- **Getter/setter methods** for data protection
- **Defensive copying** in collections

```java
private String name;        // Hidden from outside access
private double weight;      // Controlled through setters

public String getName() {   // Controlled read access
    return name;
}

public void setWeight(double weight) {  // Controlled write access
    this.weight = weight;
}
```

### 2. **Inheritance** 🌳
- **Abstract base class** (`Fruit`) defining common behavior
- **Concrete subclasses** (`Apple`, `Orange`, `Banana`) with specific implementations
- **Constructor chaining** with `super()`
- **Method inheritance** and access to parent functionality

```java
abstract class Fruit implements Edible {
    protected String origin;  // Accessible to subclasses
    public abstract String getTaste();  // Must be implemented
}

class Apple extends Fruit {
    public Apple(String variety, String color, double weight, String origin) {
        super("Apple", color, weight, origin);  // Call parent constructor
    }
}
```

### 3. **Polymorphism** 🔄
- **Method overriding** for different behaviors
- **Interface implementation** across multiple classes
- **Runtime type resolution** with `instanceof`
- **Collections of different types** treated uniformly

```java
// Same method call, different behaviors
Fruit[] fruits = {new Apple(...), new Orange(...), new Banana(...)};
for (Fruit fruit : fruits) {
    fruit.eat();  // Each fruit eats differently!
}
```

### 4. **Abstraction** 📋
- **Abstract classes** that cannot be instantiated
- **Interfaces** defining contracts
- **Hidden implementation details** with public APIs

```java
interface Edible {
    void eat();           // What to do, not how
    boolean isRipe();     // Contract definition
}

// Multiple classes implement the same interface differently
class Apple implements Edible { /* specific implementation */ }
class Orange implements Edible { /* different implementation */ }
```

## 🏗️ Core Classes

### Interface Layer
- **`Edible`**: Defines eating behavior and ripeness checking
- **`Processable`**: Defines food processing capabilities

### Abstract Base Class
- **`Fruit`**: Common properties and behaviors for all fruits
  - Encapsulated fields: name, color, weight, ripeness, origin
  - Abstract methods: `getTaste()`, `getSeedCount()`
  - Concrete methods: `eat()`, `ripen()`, `toString()`

### Concrete Implementations

#### 🍎 `Apple`
- **Variety-based behavior**: Different tastes based on apple variety
- **Implements `Processable`**: Can be made into juice, pie, sauce
- **Demonstrates**: Multiple interface implementation

#### 🍊 `Orange`
- **Seed variation**: Seedless vs seeded oranges
- **Method overriding**: Custom `eat()` method with peeling instruction
- **Demonstrates**: Conditional logic and method customization

#### 🍌 `Banana`
- **Complex state management**: 6-level ripeness system
- **Unique behavior**: `ageOneDay()` method for time simulation
- **Advanced overriding**: Custom `ripen()` and `isRipe()` methods
- **Demonstrates**: State-dependent behavior and complex inheritance

### Composition Example
- **`FruitBasket`**: Manages collections of fruits
  - Demonstrates "HAS-A" relationships
  - Polymorphic operations on mixed fruit types
  - Defensive copying for encapsulation

### Demo Application
- **`FruitDemo`**: Interactive demonstration of all OOP concepts
  - Object creation and manipulation
  - Polymorphic method calls
  - Interface usage and type checking

## 🧪 Testing

This project features **100% test coverage** with **136 comprehensive tests**.

### Test Categories

| Test Suite | Tests | Purpose |
|------------|-------|---------|
| Apple Tests | 14 | Core Apple functionality |
| Orange Tests | 14 | Core Orange functionality |
| Banana Tests | 21 | Complex Banana state management |
| FruitBasket Tests | 14 | Collection operations |
| Polymorphism Tests | 7 | Interface and inheritance behavior |
| Interface Tests | 8 | Contract compliance |
| Setter Method Tests | 7 | Encapsulation verification |
| ToString Method Tests | 9 | String representation |
| FruitBasket Output Tests | 4 | Output method behavior |
| Orange Eat Override Tests | 3 | Method overriding |
| Edge Case Tests | 8 | Boundary conditions |
| State Transition Tests | 27 | Complex state changes |

### Custom Test Framework

The project includes a simple but effective testing framework:

```java
// Example test usage
TestFramework.assertEquals("Apple", apple.getName(), "Name should be set correctly");
TestFramework.assertTrue(apple.isRipe(), "Apple should be ripe after ripening");
TestFramework.assertFalse(banana instanceof Processable, "Banana should not be processable");
```

### Running Tests

```bash
# Run all tests with detailed output
java FruitTests

# Expected output: 136/136 tests passing (100% success rate)
```

## 💻 Installation & Development Environment

### Prerequisites
- **Java Development Kit (JDK) 11 or higher**
- **Optional**: Docker, Make, Git
- **IDE Support**: VS Code, IntelliJ IDEA, Eclipse

### Quick Setup Options

#### Option 1: Make-based Development (Recommended)
```bash
# Clone the repository
git clone <repository-url>
cd Fruit

# One-command setup and verification
make setup && make dev
```

#### Option 2: Docker Development Environment
```bash
# Build and run in isolated environment
docker build -t java-oop-fruit .
docker run --rm java-oop-fruit

# Or use Docker Compose for full development stack
docker-compose up java-dev
docker-compose exec java-dev bash
```

#### Option 3: Manual Setup
```bash
# Traditional approach
javac *.java
java FruitDemo
java FruitTests
```

### Development Tools Integration

#### Make Commands (Recommended Workflow)
```bash
make help           # Show all available commands
make dev            # Full development cycle (clean, compile, test)
make quick          # Quick compile and run (skip tests)
make test           # Run comprehensive test suite
make clean          # Clean compiled files
make docker-dev     # Docker-based development
make watch          # Auto-recompile on file changes
make stats          # Show project statistics
```

#### Development Scripts
```bash
# Enhanced compilation with error handling
./scripts/compile.sh -v -c    # Verbose compilation with cleanup

# Comprehensive testing with reporting
./scripts/test.sh -v -r       # Verbose tests with report generation

# Advanced cleanup options
./scripts/clean.sh -d -i      # Deep clean with interactive prompts
```

#### Docker Compose Services
```bash
# Start development environment
docker-compose up -d java-dev

# Run tests in isolated environment
docker-compose run test-runner

# Code quality analysis
docker-compose run code-quality

# File watching for auto-recompilation
docker-compose --profile watch up file-watcher
```

## 🛠️ Developer Experience (DX)

This project includes comprehensive developer tooling for a professional development experience.

### IDE Support

#### VS Code (Recommended)
- **Auto-setup**: Open folder in VS Code, install recommended extensions
- **Tasks**: Predefined build/test/run tasks (Ctrl+Shift+P → "Tasks: Run Task")
- **Debugging**: F5 to debug FruitDemo or FruitTests
- **Settings**: Consistent formatting, Java language support

#### IntelliJ IDEA
- **Import**: Open folder as existing project
- **Run Configurations**: Pre-configured for FruitDemo and FruitTests
- **Project Structure**: Proper module setup and dependencies

### Code Quality & Consistency

#### EditorConfig Support
- **Automatic formatting** across all editors
- **Consistent indentation** (4 spaces for Java)
- **Line ending normalization** (LF)
- **Character encoding** (UTF-8)

#### Git Integration
- **Comprehensive .gitignore** for Java, IDE, and OS files
- **GitHub Actions CI/CD** with multiple Java versions (11, 17, 21)
- **Automated code quality checks** and security scanning

### Containerized Development

#### Multi-stage Docker Build
```bash
# Development container with full toolchain
docker build --target builder -t java-oop-fruit:dev .

# Production container with minimal runtime
docker build --target runtime -t java-oop-fruit:prod .
```

#### Docker Compose Services
| Service | Purpose | Command |
|---------|---------|---------|
| `java-dev` | Development environment | `docker-compose up java-dev` |
| `test-runner` | Automated testing | `docker-compose run test-runner` |
| `code-quality` | Static analysis | `docker-compose run code-quality` |
| `file-watcher` | Auto-recompilation | `docker-compose --profile watch up` |

### Continuous Integration

#### GitHub Actions Workflow
- **Multi-version testing** (Java 11, 17, 21)
- **Docker build verification**
- **Code quality analysis**
- **Educational content validation**
- **Security scanning** with Trivy

#### Local CI Simulation
```bash
# Run the same checks as CI
make ci                    # Compile and test
./scripts/test.sh -v -r    # Comprehensive testing
docker-compose run code-quality  # Quality analysis
```

### Enhanced Scripts

#### Compilation (`scripts/compile.sh`)
```bash
./scripts/compile.sh -v -c    # Verbose output, clean first
./scripts/compile.sh --help   # Show all options
```

#### Testing (`scripts/test.sh`)
```bash
./scripts/test.sh -v -r -t 60  # Verbose, generate report, 60s timeout
./scripts/test.sh --no-compile # Test without recompiling
```

#### Cleanup (`scripts/clean.sh`)
```bash
./scripts/clean.sh -d         # Deep clean (logs, cache, Docker)
./scripts/clean.sh -n         # Dry run (show what would be cleaned)
./scripts/clean.sh -i         # Interactive cleanup
```

### File Organization

```
Fruit/
├── 📁 .github/workflows/     # CI/CD automation
├── 📁 .idea/                 # IntelliJ IDEA configuration
├── 📁 .vscode/               # VS Code workspace settings
├── 📁 scripts/               # Enhanced development scripts
├── 📄 .editorconfig          # Code formatting rules
├── 📄 .gitignore             # Git ignore patterns
├── 📄 Dockerfile             # Multi-stage container build
├── 📄 docker-compose.yml     # Development services
├── 📄 Makefile               # Task automation
└── 📄 README.md              # This documentation
```

### Performance & Optimization

#### Build Optimization
- **Incremental compilation** with dependency tracking
- **Docker layer caching** for faster rebuilds
- **Parallel test execution** where possible

#### Development Workflow
```bash
# Recommended daily workflow
make dev          # Clean, compile, and test everything
make watch        # Auto-recompile during development
make quick        # Fast compile and run for demos
make docker-dev   # Full containerized testing
```

## 📖 Usage Examples

### Basic Object Creation
```java
// Creating different types of fruits
Apple apple = new Apple("Granny Smith", "green", 150.0, "Washington");
Orange orange = new Orange("orange", 200.0, "Florida", true);  // has seeds
Banana banana = new Banana(120.0, "Ecuador");

// Demonstrating polymorphism
Fruit[] fruits = {apple, orange, banana};
for (Fruit fruit : fruits) {
    System.out.println(fruit.getName() + " tastes " + fruit.getTaste());
}
```

### Interface Usage
```java
// Working with interfaces
List<Processable> processableFruits = new ArrayList<>();
if (apple instanceof Processable) {
    processableFruits.add(apple);  // Apples can be processed
}
if (orange instanceof Processable) {
    processableFruits.add(orange); // Oranges can be processed
}
// Note: Bananas don't implement Processable

for (Processable fruit : processableFruits) {
    System.out.println("Can make: " + fruit.process());
}
```

### Collection Management
```java
// Using composition with FruitBasket
FruitBasket basket = new FruitBasket("Alice");
basket.addFruit(apple);
basket.addFruit(orange);
basket.addFruit(banana);

basket.displayFruits();     // Show all fruits
basket.ripenAllFruits();    // Make all fruits ripe
basket.eatAllRipeFruits();  // Eat the ripe ones
```

### State Management
```java
// Demonstrating complex state with Banana
Banana banana = new Banana(100.0, "Ecuador");
System.out.println("Day 0: " + banana.getTaste());  // "starchy"

banana.ageOneDay();  // Day 1
banana.ageOneDay();  // Day 2
System.out.println("Day 2: " + banana.getTaste());  // "sweet and creamy"
System.out.println("Ripe? " + banana.isRipe());     // true

// Continue aging...
for (int i = 0; i < 4; i++) {
    banana.ageOneDay();
}
System.out.println("Day 6: " + banana.getTaste());  // "very sweet but mushy"
System.out.println("Ripe? " + banana.isRipe());     // false (overripe)
```

## 🎯 Learning Path

### For Complete Beginners
1. **Start with `FruitDemo.java`** - Run it and observe the output
2. **Read the comments in `Fruit.java`** - Each concept is explained
3. **Experiment with object creation** - Try creating your own fruits
4. **Run simple tests** - See how testing validates behavior

### For Intermediate Learners
1. **Study inheritance patterns** - Compare Apple, Orange, and Banana implementations
2. **Analyze polymorphism** - Trace method calls through the inheritance hierarchy
3. **Examine the test suite** - Understand comprehensive testing strategies
4. **Try modifications** - Add new fruit types or behaviors

### For Advanced Students
1. **Design patterns** - Identify Template Method, Factory, and other patterns
2. **Extend the system** - Add new interfaces, create fruit categories
3. **Refactor for packages** - Split into multiple files and packages
4. **Add persistence** - Save/load fruit data from files or databases

### Suggested Exercises

#### Beginner Exercises
1. Create a `Grape` class that extends `Fruit`
2. Add a `color` parameter to the `toString()` method output
3. Make a fruit basket that holds only ripe fruits

#### Intermediate Exercises
1. Implement a `Seasonal` interface with availability months
2. Create a `CitrusFruit` abstract class between `Fruit` and citrus fruits
3. Add a `NutritionFacts` class and composition relationship

#### Advanced Exercises
1. Implement the Visitor pattern for fruit operations
2. Add generic type parameters for specialized baskets
3. Create a fruit processing factory with different product types
4. Implement custom serialization for saving/loading fruits

## 🤝 Contributing

This is an educational project, and contributions that enhance learning are welcome!

### Types of Contributions
- **Bug fixes** in the existing code
- **Additional OOP examples** that fit the fruit theme
- **More comprehensive tests** for edge cases
- **Documentation improvements** and clearer explanations
- **New fruit types** demonstrating different OOP concepts
- **Alternative implementations** showing different design approaches

### Contribution Guidelines
1. **Maintain educational focus** - All code should teach OOP concepts
2. **Include comprehensive comments** - Explain the "why" not just the "what"
3. **Add corresponding tests** - Maintain 100% test coverage
4. **Follow existing patterns** - Keep consistency with current code style
5. **Update documentation** - Ensure README and CLAUDE.md stay current

### Getting Started with Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-fruit-type`)
3. Make your changes with full comments and tests
4. Verify all tests pass (`java FruitTests`)
5. Submit a pull request with detailed description

## 📝 License

This project is created for educational purposes. Feel free to use, modify, and distribute for learning and teaching OOP concepts.

---

## 🎉 Conclusion

This Fruit OOP example provides a solid foundation for understanding Object-Oriented Programming in Java. The extensive comments, complete test coverage, and practical examples make it an ideal learning resource for students, educators, and anyone looking to master OOP concepts.

**Happy Learning! 🚀**

---

*For questions, suggestions, or educational use, please refer to the documentation or create an issue in the project repository.*