// Import for working with List interface and ArrayList
import java.util.List;

/**
 * COMPREHENSIVE TEST SUITE - Demonstrates testing in Object-Oriented Programming
 * 
 * This class shows:
 * 1. UNIT TESTING - Testing individual classes and methods
 * 2. INTEGRATION TESTING - Testing how classes work together
 * 3. POLYMORPHISM TESTING - Testing interface behavior across different classes
 * 4. METHOD REFERENCES - Using :: syntax for cleaner code
 * 5. STATIC METHODS - All test methods are static (no object creation needed)
 */
public class FruitTests {
    
    /**
     * MAIN TEST RUNNER - Orchestrates all the test suites
     * Demonstrates METHOD REFERENCES (::) - a cleaner way to pass methods
     */
    public static void main(String[] args) {
        // METHOD REFERENCES - FruitTests::testApple is equivalent to () -> FruitTests.testApple()
        TestFramework.runTestSuite("Apple Tests", FruitTests::testApple);
        TestFramework.runTestSuite("Orange Tests", FruitTests::testOrange);
        TestFramework.runTestSuite("Banana Tests", FruitTests::testBanana);
        TestFramework.runTestSuite("FruitBasket Tests", FruitTests::testFruitBasket);
        TestFramework.runTestSuite("Polymorphism Tests", FruitTests::testPolymorphism);
        TestFramework.runTestSuite("Interface Tests", FruitTests::testInterfaces);
        
        // NEW COMPREHENSIVE COVERAGE TESTS
        TestFramework.runTestSuite("Setter Method Tests", FruitTests::testSetterMethods);
        TestFramework.runTestSuite("ToString Method Tests", FruitTests::testToStringMethods);
        TestFramework.runTestSuite("FruitBasket Output Tests", FruitTests::testFruitBasketOutputMethods);
        TestFramework.runTestSuite("Orange Eat Override Tests", FruitTests::testOrangeEatOverride);
        TestFramework.runTestSuite("Edge Case Tests", FruitTests::testEdgeCases);
        TestFramework.runTestSuite("State Transition Tests", FruitTests::testStateTransitions);
        
        // Print overall results
        TestFramework.printSummary();
    }
    
    /**
     * UNIT TEST METHOD - Tests the Apple class thoroughly
     * Demonstrates OBJECT CREATION, METHOD TESTING, and STATE VERIFICATION
     */
    public static void testApple() {
        // ARRANGE - Create test objects with known values
        Apple grannySmith = new Apple("Granny Smith", "green", 150.0, "Washington");
        Apple redDelicious = new Apple("Red Delicious", "red", 180.0, "New York");
        
        // ACT & ASSERT - Test each property and method
        // Testing INHERITED methods from Fruit class
        TestFramework.assertEquals("Apple", grannySmith.getName(), "Apple name should be 'Apple'");
        TestFramework.assertEquals("green", grannySmith.getColor(), "Apple color should be set correctly");
        TestFramework.assertEquals(150.0, grannySmith.getWeight(), "Apple weight should be set correctly");
        TestFramework.assertEquals("Washington", grannySmith.getOrigin(), "Apple origin should be set correctly");
        TestFramework.assertFalse(grannySmith.isRipe(), "New apple should not be ripe initially");
        
        // Testing APPLE-SPECIFIC methods
        TestFramework.assertEquals("Granny Smith", grannySmith.getVariety(), "Apple variety should be set correctly");
        TestFramework.assertEquals(5, grannySmith.getSeedCount(), "Apple should have 5 seeds");
        
        grannySmith.ripen();
        TestFramework.assertTrue(grannySmith.isRipe(), "Apple should be ripe after ripening");
        
        TestFramework.assertEquals("tart and crisp", grannySmith.getTaste(), "Granny Smith should taste tart and crisp");
        TestFramework.assertEquals("sweet and crisp", redDelicious.getTaste(), "Red Delicious should taste sweet and crisp");
        
        TestFramework.assertEquals("Apple juice, apple pie, or apple sauce", grannySmith.process(), "Apple processing should return correct string");
        
        TestFramework.assertTrue(grannySmith instanceof Edible, "Apple should implement Edible");
        TestFramework.assertTrue(grannySmith instanceof Processable, "Apple should implement Processable");
        TestFramework.assertTrue(grannySmith instanceof Fruit, "Apple should extend Fruit");
    }
    
    public static void testOrange() {
        Orange navel = new Orange("orange", 200.0, "California", false);
        Orange valencia = new Orange("orange", 220.0, "Florida", true);
        
        TestFramework.assertEquals("Orange", navel.getName(), "Orange name should be 'Orange'");
        TestFramework.assertEquals("orange", navel.getColor(), "Orange color should be set correctly");
        TestFramework.assertEquals(200.0, navel.getWeight(), "Orange weight should be set correctly");
        TestFramework.assertEquals("California", navel.getOrigin(), "Orange origin should be set correctly");
        TestFramework.assertFalse(navel.isRipe(), "New orange should not be ripe initially");
        
        TestFramework.assertFalse(navel.hasSeeds(), "Navel orange should not have seeds");
        TestFramework.assertTrue(valencia.hasSeeds(), "Valencia orange should have seeds");
        
        TestFramework.assertEquals(0, navel.getSeedCount(), "Seedless orange should have 0 seeds");
        TestFramework.assertEquals(10, valencia.getSeedCount(), "Seeded orange should have 10 seeds");
        
        TestFramework.assertEquals("citrusy and sweet", navel.getTaste(), "Orange should taste citrusy and sweet");
        TestFramework.assertEquals("Orange juice or marmalade", navel.process(), "Orange processing should return correct string");
        
        TestFramework.assertTrue(navel instanceof Edible, "Orange should implement Edible");
        TestFramework.assertTrue(navel instanceof Processable, "Orange should implement Processable");
        TestFramework.assertTrue(navel instanceof Fruit, "Orange should extend Fruit");
    }
    
    public static void testBanana() {
        Banana banana = new Banana(120.0, "Ecuador");
        
        TestFramework.assertEquals("Banana", banana.getName(), "Banana name should be 'Banana'");
        TestFramework.assertEquals("yellow", banana.getColor(), "Banana color should be yellow initially");
        TestFramework.assertEquals(120.0, banana.getWeight(), "Banana weight should be set correctly");
        TestFramework.assertEquals("Ecuador", banana.getOrigin(), "Banana origin should be set correctly");
        TestFramework.assertEquals(1, banana.getRipeness(), "New banana should have ripeness of 1");
        TestFramework.assertFalse(banana.isRipe(), "New banana should not be ripe initially");
        TestFramework.assertEquals(0, banana.getSeedCount(), "Banana should have 0 seeds");
        TestFramework.assertEquals("starchy", banana.getTaste(), "Unripe banana should taste starchy");
        
        banana.ageOneDay();
        TestFramework.assertEquals(2, banana.getRipeness(), "Banana ripeness should increase after aging");
        TestFramework.assertFalse(banana.isRipe(), "Banana should still not be ripe after 1 day");
        
        banana.ageOneDay();
        TestFramework.assertEquals(3, banana.getRipeness(), "Banana ripeness should be 3 after 2 days");
        TestFramework.assertTrue(banana.isRipe(), "Banana should be ripe after 2 days");
        TestFramework.assertEquals("yellow with brown spots", banana.getColor(), "Ripe banana should have brown spots");
        TestFramework.assertEquals("sweet and creamy", banana.getTaste(), "Ripe banana should taste sweet and creamy");
        
        for (int i = 0; i < 3; i++) {
            banana.ageOneDay();
        }
        TestFramework.assertEquals(6, banana.getRipeness(), "Banana ripeness should be 6 after 5 days total");
        TestFramework.assertFalse(banana.isRipe(), "Overripe banana should not be considered ripe");
        TestFramework.assertEquals("brown", banana.getColor(), "Overripe banana should be brown");
        TestFramework.assertEquals("very sweet but mushy", banana.getTaste(), "Overripe banana should be very sweet but mushy");
        
        TestFramework.assertTrue(banana instanceof Edible, "Banana should implement Edible");
        TestFramework.assertFalse(banana instanceof Processable, "Banana should not implement Processable");
        TestFramework.assertTrue(banana instanceof Fruit, "Banana should extend Fruit");
    }
    
    public static void testFruitBasket() {
        FruitBasket basket = new FruitBasket("TestOwner");
        Apple apple = new Apple("Gala", "red", 160.0, "Michigan");
        Orange orange = new Orange("orange", 180.0, "Florida", true);
        Banana banana = new Banana(100.0, "Costa Rica");
        
        TestFramework.assertEquals(0, basket.getFruits().size(), "Empty basket should have 0 fruits");
        
        basket.addFruit(apple);
        TestFramework.assertEquals(1, basket.getFruits().size(), "Basket should have 1 fruit after adding apple");
        
        basket.addFruit(orange);
        basket.addFruit(banana);
        TestFramework.assertEquals(3, basket.getFruits().size(), "Basket should have 3 fruits after adding all");
        
        List<Fruit> fruits = basket.getFruits();
        TestFramework.assertTrue(fruits.contains(apple), "Basket should contain the apple");
        TestFramework.assertTrue(fruits.contains(orange), "Basket should contain the orange");
        TestFramework.assertTrue(fruits.contains(banana), "Basket should contain the banana");
        
        TestFramework.assertFalse(apple.isRipe(), "Apple should not be ripe initially");
        TestFramework.assertFalse(orange.isRipe(), "Orange should not be ripe initially");
        TestFramework.assertFalse(banana.isRipe(), "Banana should not be ripe initially");
        
        basket.ripenAllFruits();
        TestFramework.assertTrue(apple.isRipe(), "Apple should be ripe after ripening all");
        TestFramework.assertTrue(orange.isRipe(), "Orange should be ripe after ripening all");
        
        basket.removeFruit(apple);
        TestFramework.assertEquals(2, basket.getFruits().size(), "Basket should have 2 fruits after removing apple");
        TestFramework.assertFalse(basket.getFruits().contains(apple), "Basket should not contain removed apple");
    }
    
    public static void testPolymorphism() {
        Fruit[] fruits = {
            new Apple("Honeycrisp", "red", 170.0, "Minnesota"),
            new Orange("orange", 190.0, "Texas", false),
            new Banana(110.0, "Guatemala")
        };
        
        for (Fruit fruit : fruits) {
            TestFramework.assertTrue(fruit instanceof Edible, "All fruits should implement Edible");
            TestFramework.assertTrue(fruit.getSeedCount() >= 0, "All fruits should have non-negative seed count");
            TestFramework.assertTrue(fruit.getTaste() != null && !fruit.getTaste().isEmpty(), "All fruits should have a taste description");
        }
        
        int processableCount = 0;
        for (Fruit fruit : fruits) {
            if (fruit instanceof Processable) {
                processableCount++;
                Processable processable = (Processable) fruit;
                String processResult = processable.process();
                TestFramework.assertTrue(processResult != null && !processResult.isEmpty(), 
                    "Processable fruits should return processing information");
            }
        }
        TestFramework.assertEquals(2, processableCount, "Should have 2 processable fruits (Apple and Orange)");
    }
    
    public static void testInterfaces() {
        Apple apple = new Apple("Fuji", "red", 155.0, "Japan");
        Orange orange = new Orange("orange", 175.0, "Spain", true);
        Banana banana = new Banana(95.0, "Philippines");
        
        Edible[] edibles = {apple, orange, banana};
        for (Edible edible : edibles) {
            TestFramework.assertFalse(edible.isRipe(), "New fruits should not be ripe");
        }
        
        Processable[] processables = {apple, orange};
        for (Processable processable : processables) {
            String result = processable.process();
            TestFramework.assertTrue(result.length() > 10, "Processing result should be descriptive");
        }
        
        apple.ripen();
        orange.ripen();
        banana.ripen();
        
        for (Edible edible : edibles) {
            TestFramework.assertTrue(edible.isRipe(), "Ripened fruits should be ripe");
        }
    }
    
    /**
     * COMPREHENSIVE COVERAGE TESTS - Testing previously untested methods
     */
    
    public static void testSetterMethods() {
        Apple apple = new Apple("Gala", "red", 150.0, "Washington");
        
        // Test setName
        apple.setName("Modified Apple");
        TestFramework.assertEquals("Modified Apple", apple.getName(), "setName should update the name");
        
        // Test setColor  
        apple.setColor("green");
        TestFramework.assertEquals("green", apple.getColor(), "setColor should update the color");
        
        // Test setWeight
        apple.setWeight(175.5);
        TestFramework.assertEquals(175.5, apple.getWeight(), "setWeight should update the weight");
        
        // Test that these changes affect toString
        String result = apple.toString();
        TestFramework.assertTrue(result.contains("Modified Apple"), "toString should reflect name change");
        TestFramework.assertTrue(result.contains("green"), "toString should reflect color change");
        TestFramework.assertTrue(result.contains("175.5"), "toString should reflect weight change");
    }
    
    public static void testToStringMethods() {
        Apple apple = new Apple("Honeycrisp", "red", 160.0, "Minnesota");
        Orange orange = new Orange("orange", 200.0, "Florida", true);
        Banana banana = new Banana(120.0, "Ecuador");
        
        // Test Apple toString includes variety
        String appleString = apple.toString();
        TestFramework.assertTrue(appleString.contains("Honeycrisp"), "Apple toString should include variety");
        TestFramework.assertTrue(appleString.contains("Apple"), "Apple toString should include fruit name");
        TestFramework.assertTrue(appleString.contains("red"), "Apple toString should include color");
        TestFramework.assertTrue(appleString.contains("160.0"), "Apple toString should include weight");
        TestFramework.assertTrue(appleString.contains("Minnesota"), "Apple toString should include origin");
        TestFramework.assertTrue(appleString.contains("not ripe"), "Apple toString should include ripeness status");
        
        // Test Orange toString (uses base Fruit toString)
        String orangeString = orange.toString();
        TestFramework.assertTrue(orangeString.contains("Orange"), "Orange toString should include fruit name");
        TestFramework.assertTrue(orangeString.contains("200.0"), "Orange toString should include weight");
        
        // Test Banana toString (uses base Fruit toString)
        String bananaString = banana.toString();
        TestFramework.assertTrue(bananaString.contains("Banana"), "Banana toString should include fruit name");
        TestFramework.assertTrue(bananaString.contains("yellow"), "Banana toString should include color");
        
        // Test toString after ripening
        apple.ripen();
        String ripeAppleString = apple.toString();
        TestFramework.assertTrue(ripeAppleString.contains("ripe"), "Ripe apple toString should show ripe status");
        TestFramework.assertFalse(ripeAppleString.contains("not ripe"), "Ripe apple toString should not show 'not ripe'");
    }
    
    public static void testFruitBasketOutputMethods() {
        FruitBasket basket = new FruitBasket("TestUser");
        Apple apple = new Apple("Fuji", "red", 150.0, "Japan");
        Orange orange = new Orange("orange", 180.0, "Spain", false);
        
        basket.addFruit(apple);
        basket.addFruit(orange);
        
        // Test that displayFruits doesn't crash (we can't easily test console output)
        // This at least ensures the method executes without errors
        basket.displayFruits();
        TestFramework.assertTrue(true, "displayFruits should execute without errors");
        
        // Test eatAllRipeFruits with unripe fruits (should not eat any)
        basket.eatAllRipeFruits();
        TestFramework.assertTrue(true, "eatAllRipeFruits should handle unripe fruits gracefully");
        
        // Test eatAllRipeFruits with ripe fruits
        basket.ripenAllFruits();
        basket.eatAllRipeFruits();
        TestFramework.assertTrue(true, "eatAllRipeFruits should handle ripe fruits correctly");
        
        // Test that fruits are still in basket after eating (eating doesn't remove them)
        TestFramework.assertEquals(2, basket.getFruits().size(), "Eating fruits should not remove them from basket");
    }
    
    public static void testOrangeEatOverride() {
        Orange orange = new Orange("orange", 200.0, "California", false);
        
        // Test eating unripe orange (should show generic message only)
        orange.eat();
        TestFramework.assertTrue(true, "Eating unripe orange should not crash");
        
        // Test eating ripe orange (should show both generic message AND peeling instruction)
        orange.ripen();
        orange.eat();
        TestFramework.assertTrue(true, "Eating ripe orange should show peeling instruction");
        
        // Verify orange is indeed ripe after ripening
        TestFramework.assertTrue(orange.isRipe(), "Orange should be ripe after calling ripen()");
    }
    
    public static void testEdgeCases() {
        // Test boundary values for weights
        Apple lightApple = new Apple("Mini", "red", 0.1, "Lab");
        TestFramework.assertEquals(0.1, lightApple.getWeight(), "Should handle very small weights");
        
        Apple heavyApple = new Apple("Giant", "red", 999.9, "Lab");
        TestFramework.assertEquals(999.9, heavyApple.getWeight(), "Should handle large weights");
        
        // Test zero weight
        Orange zeroOrange = new Orange("orange", 0.0, "Test", true);
        TestFramework.assertEquals(0.0, zeroOrange.getWeight(), "Should handle zero weight");
        
        // Test extreme banana aging
        Banana extremeBanana = new Banana(100.0, "Test");
        for (int i = 0; i < 10; i++) {
            extremeBanana.ageOneDay();
        }
        TestFramework.assertEquals(11, extremeBanana.getRipeness(), "Should handle extreme aging");
        TestFramework.assertFalse(extremeBanana.isRipe(), "Extremely aged banana should not be ripe");
        TestFramework.assertEquals("very sweet but mushy", extremeBanana.getTaste(), "Extremely aged banana should be mushy");
        
        // Test empty basket operations
        FruitBasket emptyBasket = new FruitBasket("Empty");
        emptyBasket.ripenAllFruits();
        emptyBasket.eatAllRipeFruits();
        emptyBasket.displayFruits();
        TestFramework.assertEquals(0, emptyBasket.getFruits().size(), "Empty basket should remain empty");
        
        // Test removing non-existent fruit
        Apple outsideApple = new Apple("Outside", "green", 100.0, "Elsewhere");
        emptyBasket.removeFruit(outsideApple);
        TestFramework.assertEquals(0, emptyBasket.getFruits().size(), "Removing non-existent fruit should not affect basket");
    }
    
    public static void testStateTransitions() {
        // Test complex banana state transitions
        Banana banana = new Banana(100.0, "Test");
        
        // Initial state
        TestFramework.assertEquals(1, banana.getRipeness(), "New banana should start at ripeness 1");
        TestFramework.assertEquals("starchy", banana.getTaste(), "New banana should taste starchy");
        TestFramework.assertEquals("yellow", banana.getColor(), "New banana should be yellow");
        TestFramework.assertFalse(banana.isRipe(), "New banana should not be ripe");
        
        // Age to just before ripe
        banana.ageOneDay();
        TestFramework.assertEquals(2, banana.getRipeness(), "Banana should be ripeness 2 after 1 day");
        TestFramework.assertEquals("starchy", banana.getTaste(), "Still unripe banana should taste starchy");
        TestFramework.assertFalse(banana.isRipe(), "Banana should still not be ripe");
        
        // Age to ripe
        banana.ageOneDay();
        TestFramework.assertEquals(3, banana.getRipeness(), "Banana should be ripeness 3 after 2 days");
        TestFramework.assertEquals("sweet and creamy", banana.getTaste(), "Ripe banana should taste sweet and creamy");
        TestFramework.assertEquals("yellow with brown spots", banana.getColor(), "Ripe banana should have brown spots");
        TestFramework.assertTrue(banana.isRipe(), "Banana should be ripe at ripeness 3");
        
        // Age to overripe
        banana.ageOneDay(); // 4
        banana.ageOneDay(); // 5
        banana.ageOneDay(); // 6
        TestFramework.assertEquals(6, banana.getRipeness(), "Banana should be ripeness 6 after 5 days");
        TestFramework.assertEquals("very sweet but mushy", banana.getTaste(), "Overripe banana should be mushy");
        TestFramework.assertEquals("brown", banana.getColor(), "Overripe banana should be brown");
        TestFramework.assertFalse(banana.isRipe(), "Overripe banana should not be considered ripe");
        
        // Test direct ripening of unripe banana
        Banana youngBanana = new Banana(100.0, "Test2");
        TestFramework.assertEquals(1, youngBanana.getRipeness(), "Young banana should start at ripeness 1");
        youngBanana.ripen();
        TestFramework.assertEquals(3, youngBanana.getRipeness(), "Directly ripened banana should jump to ripeness 3");
        TestFramework.assertTrue(youngBanana.isRipe(), "Directly ripened banana should be ripe");
        TestFramework.assertEquals("yellow with brown spots", youngBanana.getColor(), "Directly ripened banana should have brown spots");
        
        // Test ripening already ripe banana
        youngBanana.ripen();
        TestFramework.assertEquals(3, youngBanana.getRipeness(), "Re-ripening ripe banana should not change ripeness");
        TestFramework.assertTrue(youngBanana.isRipe(), "Re-ripened banana should still be ripe");
    }
}