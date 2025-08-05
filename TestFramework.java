/**
 * CUSTOM TEST FRAMEWORK - A simple testing framework for educational purposes
 * 
 * This demonstrates:
 * 1. STATIC METHODS - Methods that belong to the class, not instances
 * 2. CLASS VARIABLES - Static fields shared across all method calls
 * 3. UTILITY CLASS PATTERN - A class with only static methods
 * 4. TESTING CONCEPTS - Assertions, test tracking, and reporting
 */
public class TestFramework {
    // STATIC VARIABLES - Shared across all test method calls
    // These keep track of test statistics across the entire test run
    private static int totalTests = 0;   // Count of all tests run
    private static int passedTests = 0;  // Count of successful tests
    private static int failedTests = 0;  // Count of failed tests
    
    /**
     * ASSERTION METHOD - Tests if two objects are equal
     * This is a STATIC METHOD - called on the class, not an instance
     * 
     * @param expected The value we expect
     * @param actual The actual value from our code
     * @param testName Description of what we're testing
     */
    public static void assertEquals(Object expected, Object actual, String testName) {
        totalTests++;  // Increment static counter
        
        // COMPLEX CONDITION - Handle null values and use .equals() for comparison
        if ((expected == null && actual == null) || 
            (expected != null && expected.equals(actual))) {
            passedTests++;
            System.out.println("✓ PASS: " + testName);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + actual);
        }
    }
    
    /**
     * BOOLEAN ASSERTION - Tests if a condition is true
     * Demonstrates METHOD OVERLOADING (different parameter types than assertEquals)
     */
    public static void assertTrue(boolean condition, String testName) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println("✓ PASS: " + testName);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Expected: true");
            System.out.println("  Actual: false");
        }
    }
    
    /**
     * NEGATIVE BOOLEAN ASSERTION - Tests if a condition is false
     * Shows how to test negative conditions
     */
    public static void assertFalse(boolean condition, String testName) {
        totalTests++;
        if (!condition) {  // NOT operator (!) flips the boolean
            passedTests++;
            System.out.println("✓ PASS: " + testName);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Expected: false");
            System.out.println("  Actual: true");
        }
    }
    
    /**
     * REPORTING METHOD - Prints a summary of all test results
     * Demonstrates MATHEMATICAL OPERATIONS and CONDITIONAL LOGIC
     */
    public static void printSummary() {
        System.out.println("\n" + "=".repeat(50));  // String.repeat() creates repeated characters
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        
        // TERNARY OPERATOR and ARITHMETIC - Calculate success percentage
        System.out.println("Success Rate: " + (totalTests > 0 ? (passedTests * 100 / totalTests) : 0) + "%");
        
        // CONDITIONAL LOGIC - Different messages based on results
        if (failedTests == 0) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️  " + failedTests + " test(s) failed.");
        }
    }
    
    /**
     * TEST ORGANIZATION METHOD - Runs a group of related tests
     * 
     * This demonstrates:
     * 1. FUNCTIONAL PROGRAMMING - Using Runnable interface
     * 2. LAMBDA EXPRESSIONS - The testSuite parameter can be a lambda
     * 3. CALLBACK PATTERN - We call the provided test method
     * 
     * @param suiteName Name of the test suite for organization
     * @param testSuite A Runnable that contains the tests to run
     */
    public static void runTestSuite(String suiteName, Runnable testSuite) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Running Test Suite: " + suiteName);
        System.out.println("=".repeat(50));
        testSuite.run();  // CALLBACK - Execute the provided test suite
    }
}