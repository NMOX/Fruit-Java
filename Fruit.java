// Import statements bring in classes from Java's standard library
import java.util.ArrayList;  // Dynamic array implementation
import java.util.List;       // Interface for ordered collections

/**
 * INTERFACE DEMONSTRATION - Interfaces define contracts (what classes MUST do)
 * 
 * The Edible interface represents anything that can be eaten.
 * This is an example of ABSTRACTION - we define what behavior is needed
 * without specifying how it's implemented.
 */
interface Edible {
    // Abstract method - classes implementing this interface MUST provide this behavior
    void eat();
    
    // Another required method - determines if the item is ready to eat
    boolean isRipe();
}

/**
 * The Processable interface represents items that can be processed into other products.
 * This demonstrates how interfaces can represent different capabilities.
 * A class can implement multiple interfaces (multiple inheritance of behavior).
 */
interface Processable {
    // Method that returns what this item can be processed into
    String process();
}

/**
 * ABSTRACT CLASS DEMONSTRATION - The base class for all fruits
 * 
 * This class demonstrates several key OOP concepts:
 * 1. ABSTRACTION - It's abstract, so you can't create a Fruit directly
 * 2. ENCAPSULATION - Private fields with controlled access via getters/setters
 * 3. INHERITANCE - Subclasses will inherit these properties and methods
 * 4. POLYMORPHISM - Subclasses can override methods for their specific behavior
 */
abstract class Fruit implements Edible {
    // ENCAPSULATION - Private fields can only be accessed through methods
    private String name;        // Only this class can directly modify the name
    private String color;       // Protected from outside interference
    private double weight;      // Data is hidden from external classes
    private boolean ripe;       // Internal state management
    
    // PROTECTED - Subclasses can access this field directly, but external classes cannot
    protected String origin;   // Shows different access levels in inheritance
    
    /**
     * CONSTRUCTOR - Called when creating a new Fruit object
     * This demonstrates encapsulation by setting up the initial state safely
     */
    public Fruit(String name, String color, double weight, String origin) {
        // 'this' keyword refers to the current object being created
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.origin = origin;
        this.ripe = false;  // All fruits start unripe
    }

    // GETTER METHODS - Provide controlled read access to private fields
    // This is ENCAPSULATION - we control how data is accessed
    public String getName() {
        return name;  // Return a copy of the private field
    }

    // SETTER METHODS - Provide controlled write access to private fields
    // We could add validation here if needed
    public void setName(String name) {
        this.name = name;  // 'this' distinguishes parameter from field
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        // In a real application, you might validate that weight > 0
        this.weight = weight;
    }

    public boolean isRipe() {
        return ripe;
    }

    /**
     * Method to make the fruit ripe
     * This demonstrates how we can control state changes
     */
    public void ripen() {
        this.ripe = true;
        System.out.println(name + " is now ripe!");
    }

    public String getOrigin() {
        return origin;  // This could also be modified by subclasses since it's protected
    }

    // ABSTRACT METHODS - These MUST be implemented by subclasses
    // This forces each fruit type to define its own taste and seed count
    public abstract String getTaste();    // Each fruit tastes different
    public abstract int getSeedCount();   // Each fruit has different seeds

    /**
     * INTERFACE IMPLEMENTATION - This implements the eat() method from Edible interface
     * The @Override annotation tells Java we're implementing an interface method
     */
    @Override
    public void eat() {
        if (isRipe()) {
            // Notice how we call getTaste() - this will use the subclass's implementation
            // This is POLYMORPHISM - the behavior changes based on the actual fruit type
            System.out.println("Eating delicious " + name + "! It tastes " + getTaste());
        } else {
            System.out.println(name + " is not ripe yet. It might be sour.");
        }
    }

    /**
     * OVERRIDING OBJECT'S toString() METHOD
     * Every class in Java inherits from Object class, which has a toString() method
     * We override it to provide a meaningful string representation of our fruit
     */
    @Override
    public String toString() {
        // String.format() is like printf - %s for strings, %.1f for decimals with 1 digit
        return String.format("%s: %s, %.1fg, from %s, %s", 
            name, color, weight, origin, ripe ? "ripe" : "not ripe");
    }
}

/**
 * CONCRETE CLASS EXAMPLE - Apple extends Fruit and implements Processable
 * 
 * This demonstrates:
 * 1. INHERITANCE - Apple inherits all properties and methods from Fruit
 * 2. MULTIPLE INTERFACE IMPLEMENTATION - Implements both Edible (through Fruit) and Processable
 * 3. METHOD OVERRIDING - Provides specific implementations for abstract methods
 * 4. POLYMORPHISM - Can be treated as Fruit, Edible, or Processable
 */
class Apple extends Fruit implements Processable {
    // ENCAPSULATION - Apple has its own private data in addition to inherited data
    private String variety;  // Specific to apples (Granny Smith, Red Delicious, etc.)

    /**
     * CONSTRUCTOR CHAINING - Calls parent constructor with super()
     * This shows how inheritance works in constructors
     */
    public Apple(String variety, String color, double weight, String origin) {
        // super() calls the parent class (Fruit) constructor
        // Notice we hard-code "Apple" as the name since all apples are apples
        super("Apple", color, weight, origin);
        this.variety = variety;  // Set apple-specific data
    }

    // GETTER for apple-specific data
    public String getVariety() {
        return variety;
    }

    /**
     * IMPLEMENTING ABSTRACT METHOD - We must provide this since Fruit declared it abstract
     * This demonstrates POLYMORPHISM - different fruits have different tastes
     */
    @Override
    public String getTaste() {
        // Conditional logic based on variety - shows how subclasses can have complex behavior
        return variety.toLowerCase().contains("granny") ? "tart and crisp" : "sweet and crisp";
    }

    /**
     * IMPLEMENTING ABSTRACT METHOD - Required by parent class
     */
    @Override
    public int getSeedCount() {
        return 5;  // Most apples have about 5 seeds
    }

    /**
     * IMPLEMENTING INTERFACE METHOD - Required by Processable interface
     * This shows how a class can implement multiple interfaces
     */
    @Override
    public String process() {
        return "Apple juice, apple pie, or apple sauce";
    }

    /**
     * METHOD OVERRIDING - Customizing the string representation
     * Notice how we call super.toString() to reuse parent logic
     */
    @Override
    public String toString() {
        return variety + " " + super.toString();  // Add variety to the standard fruit info
    }
}

/**
 * ANOTHER CONCRETE CLASS - Orange demonstrates different inheritance patterns
 * 
 * Notice the differences from Apple:
 * 1. Different private fields (hasSeeds vs variety)
 * 2. Different constructor parameters
 * 3. Overrides a method that wasn't abstract (eat method)
 * 4. Shows conditional logic in getSeedCount()
 */
class Orange extends Fruit implements Processable {
    // Different private field than Apple - shows how subclasses can vary
    private boolean hasSeeds;  // Some oranges are seedless, others aren't

    /**
     * CONSTRUCTOR - Shows different parameter approach than Apple
     */
    public Orange(String color, double weight, String origin, boolean hasSeeds) {
        super("Orange", color, weight, origin);  // All oranges are "Orange"
        this.hasSeeds = hasSeeds;  // Store the seed information
    }

    // GETTER for orange-specific property
    public boolean hasSeeds() {
        return hasSeeds;
    }

    /**
     * IMPLEMENTING ABSTRACT METHOD - Required implementation
     * All oranges have the same basic taste profile
     */
    @Override
    public String getTaste() {
        return "citrusy and sweet";
    }

    /**
     * IMPLEMENTING ABSTRACT METHOD with CONDITIONAL LOGIC
     * Shows how implementation can depend on object state
     */
    @Override
    public int getSeedCount() {
        // Ternary operator: condition ? valueIfTrue : valueIfFalse
        return hasSeeds ? 10 : 0;  // Seeded oranges have ~10 seeds, seedless have 0
    }

    /**
     * INTERFACE IMPLEMENTATION - Processable interface method
     */
    @Override
    public String process() {
        return "Orange juice or marmalade";
    }

    /**
     * METHOD OVERRIDING - Overriding a non-abstract method from parent
     * This shows you can override ANY method, not just abstract ones
     * Notice how we call super.eat() first, then add orange-specific behavior
     */
    @Override
    public void eat() {
        super.eat();  // Call the parent's eat method first
        if (isRipe()) {
            // Add orange-specific eating instructions
            System.out.println("Don't forget to peel the orange first!");
        }
    }
}

/**
 * COMPLEX INHERITANCE EXAMPLE - Banana demonstrates advanced OOP concepts
 * 
 * Key learning points:
 * 1. DOES NOT implement Processable - shows optional interface implementation
 * 2. OVERRIDES multiple parent methods (ripen, isRipe) - shows method customization
 * 3. ADDS new behavior (ageOneDay) - shows how subclasses can extend functionality
 * 4. STATE-DEPENDENT BEHAVIOR - behavior changes based on ripeness level
 */
class Banana extends Fruit {
    // CUSTOM STATE MANAGEMENT - banana-specific ripeness tracking
    private int ripeness;  // 1-5+ scale, different from parent's boolean 'ripe'

    /**
     * CONSTRUCTOR - Notice different parameters than Apple/Orange
     * Shows how different fruits can have different creation patterns
     */
    public Banana(double weight, String origin) {
        // Hard-code color as yellow since all bananas start yellow
        super("Banana", "yellow", weight, origin);
        this.ripeness = 1;  // Start at ripeness level 1 (green/unripe)
    }

    // GETTER for banana-specific state
    public int getRipeness() {
        return ripeness;
    }

    /**
     * NEW METHOD - Not inherited, specific to Banana
     * This shows how subclasses can add completely new behavior
     * Demonstrates STATE CHANGES and SIDE EFFECTS
     */
    public void ageOneDay() {
        ripeness++;  // Increase ripeness level
        
        // STATE-DEPENDENT BEHAVIOR - different actions at different ripeness levels
        if (ripeness >= 3) {
            ripen();  // Call parent's ripen method
            setColor("yellow with brown spots");  // Change visual appearance
        }
        if (ripeness >= 6) {
            setColor("brown");  // Overripe bananas turn brown
            System.out.println("Banana is overripe!");
        }
    }

    /**
     * IMPLEMENTING ABSTRACT METHOD with COMPLEX LOGIC
     * Shows how taste changes based on internal state
     */
    @Override
    public String getTaste() {
        // CONDITIONAL LOGIC based on state - demonstrates polymorphism
        if (ripeness < 3) return "starchy";           // Green bananas
        if (ripeness < 6) return "sweet and creamy";  // Ripe bananas
        return "very sweet but mushy";                // Overripe bananas
    }

    /**
     * SIMPLE IMPLEMENTATION - Bananas are seedless
     */
    @Override
    public int getSeedCount() {
        return 0;  // Commercial bananas don't have seeds
    }

    /**
     * OVERRIDING PARENT METHOD - Customizing the ripen behavior
     * This shows how you can override ANY method to add custom logic
     */
    @Override
    public void ripen() {
        if (ripeness < 3) {
            ripeness = 3;  // Force banana to ripe stage
            setColor("yellow with brown spots");
        }
        super.ripen();  // Still call parent's ripen method
    }

    /**
     * OVERRIDING PARENT METHOD - Custom ripeness logic
     * This completely replaces the parent's simple boolean logic
     * with complex state-based logic
     */
    @Override
    public boolean isRipe() {
        // Ripe means ripeness is 3, 4, or 5 (not too green, not too brown)
        return ripeness >= 3 && ripeness < 6;
    }
}

/**
 * COMPOSITION EXAMPLE - FruitBasket demonstrates "HAS-A" relationship
 * 
 * This class shows:
 * 1. COMPOSITION - FruitBasket HAS fruits (not IS-A fruit)
 * 2. COLLECTION MANAGEMENT - Working with Lists of objects
 * 3. POLYMORPHISM - Can hold any type of Fruit (Apple, Orange, Banana)
 * 4. ENCAPSULATION - Private List with controlled access methods
 * 5. ITERATION - Looping through collections of objects
 */
class FruitBasket {
    // COMPOSITION - FruitBasket contains Fruits (HAS-A relationship)
    private List<Fruit> fruits;  // List can hold any Fruit subtype
    private String owner;        // Who owns this basket

    /**
     * CONSTRUCTOR - Initialize the basket with an owner
     * Notice how we create the ArrayList in the constructor
     */
    public FruitBasket(String owner) {
        this.owner = owner;
        // POLYMORPHISM - List is interface, ArrayList is implementation
        this.fruits = new ArrayList<>();  // Empty list to start
    }

    /**
     * ADDING OBJECTS - Demonstrates collection management
     * POLYMORPHISM - Can accept ANY type of Fruit (Apple, Orange, Banana)
     */
    public void addFruit(Fruit fruit) {
        fruits.add(fruit);  // ArrayList's add method
        // Using the fruit's inherited getName() method
        System.out.println("Added " + fruit.getName() + " to " + owner + "'s basket");
    }

    /**
     * REMOVING OBJECTS - Shows object comparison and removal
     */
    public void removeFruit(Fruit fruit) {
        // List.remove() returns true if object was found and removed
        if (fruits.remove(fruit)) {
            System.out.println("Removed " + fruit.getName() + " from basket");
        }
    }

    /**
     * ITERATION and POLYMORPHISM - Loop through different fruit types
     * Enhanced for loop (for-each) syntax
     */
    public void displayFruits() {
        System.out.println("\n" + owner + "'s Fruit Basket:");
        System.out.println("========================");
        // ENHANCED FOR LOOP - cleaner than traditional for loop
        for (Fruit fruit : fruits) {
            // POLYMORPHISM - fruit.toString() calls the appropriate subclass version
            System.out.println("- " + fruit);  // Implicitly calls toString()
        }
    }

    /**
     * POLYMORPHIC METHOD CALLS - Calling methods on different fruit types
     * Each fruit type might ripen differently (see Banana's custom ripen method)
     */
    public void ripenAllFruits() {
        System.out.println("\nRipening all fruits in the basket...");
        for (Fruit fruit : fruits) {
            if (!fruit.isRipe()) {
                // POLYMORPHISM - fruit.ripen() calls the appropriate version
                // (Fruit.ripen() for Apple/Orange, Banana.ripen() for Banana)
                fruit.ripen();
            }
        }
    }

    /**
     * MORE POLYMORPHISM - Different eat behaviors for different fruits
     */
    public void eatAllRipeFruits() {
        System.out.println("\nEating all ripe fruits...");
        for (Fruit fruit : fruits) {
            if (fruit.isRipe()) {
                // POLYMORPHISM - Different fruits have different eat() behaviors
                // Orange.eat() adds peeling instruction, others use Fruit.eat()
                fruit.eat();
            }
        }
    }

    /**
     * DEFENSIVE COPYING - Return a copy of the list, not the original
     * This prevents external code from modifying our private list directly
     * This is an important ENCAPSULATION technique
     */
    public List<Fruit> getFruits() {
        return new ArrayList<>(fruits);  // Return a copy, not the original
    }
}

/**
 * MAIN DEMONSTRATION CLASS - Shows all OOP concepts in action
 * 
 * This class demonstrates:
 * 1. OBJECT CREATION - Creating instances of different classes
 * 2. POLYMORPHISM - Treating different objects uniformly
 * 3. COMPOSITION - Using FruitBasket to manage fruits
 * 4. INTERFACE USAGE - Checking and using Processable interface
 * 5. METHOD CALLS - Calling various methods on objects
 */
class FruitDemo {
    /**
     * MAIN METHOD - Entry point of the Java program
     * The JVM calls this method when you run 'java FruitDemo'
     */
    public static void main(String[] args) {
        System.out.println("=== Object-Oriented Programming with Fruits ===\n");

        // OBJECT CREATION - Creating a FruitBasket instance (COMPOSITION)
        FruitBasket basket = new FruitBasket("Alice");

        // CREATING DIFFERENT FRUIT OBJECTS - Each with different constructors
        // Notice how each class has different constructor parameters
        Apple grannySmith = new Apple("Granny Smith", "green", 150.0, "Washington");
        Apple redDelicious = new Apple("Red Delicious", "red", 180.0, "New York");
        Orange navel = new Orange("orange", 200.0, "California", false);  // seedless
        Orange valencia = new Orange("orange", 220.0, "Florida", true);   // has seeds
        Banana banana = new Banana(120.0, "Ecuador");  // Different constructor pattern

        // ADDING OBJECTS TO COLLECTION - Demonstrating composition
        // All these different fruit types can be added because they're all Fruits
        basket.addFruit(grannySmith);
        basket.addFruit(redDelicious);
        basket.addFruit(navel);
        basket.addFruit(valencia);
        basket.addFruit(banana);

        // CALLING METHODS ON OBJECTS
        basket.displayFruits();

        // DEMONSTRATING POLYMORPHISM and INTERFACE USAGE
        System.out.println("\n=== Demonstrating Polymorphism ===");
        System.out.println("Processing fruits that can be processed:");
        for (Fruit fruit : basket.getFruits()) {
            // INSTANCEOF OPERATOR - Checking if object implements an interface
            if (fruit instanceof Processable) {
                // TYPE CASTING - Converting Fruit to Processable interface
                Processable processableFruit = (Processable) fruit;
                // INTERFACE METHOD CALL - Using the process() method
                System.out.println(fruit.getName() + " can be made into: " + processableFruit.process());
            }
        }

        // DEMONSTRATING UNIQUE SUBCLASS BEHAVIOR
        System.out.println("\n=== Aging the banana ===");
        for (int day = 1; day <= 4; day++) {
            System.out.println("Day " + day + ":");
            banana.ageOneDay();  // Method only available on Banana, not other fruits
            // Calling methods specific to Banana class
            System.out.println("Banana ripeness: " + banana.getRipeness() + ", Color: " + banana.getColor());
        }

        // POLYMORPHIC METHOD CALLS - Same method name, different behaviors
        basket.ripenAllFruits();  // Calls ripen() on each fruit - different behavior for Banana
        basket.displayFruits();   // Shows the updated state
        basket.eatAllRipeFruits(); // Different eat() behavior for Orange

        // DEMONSTRATING ENCAPSULATION - Accessing private data through public methods
        System.out.println("\n=== Demonstrating Encapsulation ===");
        System.out.println("Apple variety: " + grannySmith.getVariety());  // Apple-specific method
        System.out.println("Orange has seeds: " + valencia.hasSeeds());     // Orange-specific method
        System.out.println("Banana seed count: " + banana.getSeedCount()); // Inherited method

        // DEMONSTRATING INHERITANCE - All fruits share common interface
        System.out.println("\n=== Demonstrating Inheritance ===");
        System.out.println("All fruits inherit from the Fruit class:");
        for (Fruit fruit : basket.getFruits()) {
            // POLYMORPHISM - Each fruit implements these methods differently
            System.out.println(fruit.getName() + " has " + fruit.getSeedCount() + " seeds and tastes " + fruit.getTaste());
        }

        System.out.println("\nOOP Demo Complete!");
    }
}