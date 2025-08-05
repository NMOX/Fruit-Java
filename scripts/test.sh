#!/bin/bash
# Test script for Java OOP Fruit Example
# Comprehensive testing with coverage analysis and reporting

set -euo pipefail

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
NC='\033[0m'

# Configuration
VERBOSE=false
COMPILE_FIRST=true
GENERATE_REPORT=false
TIMEOUT=30

# Function to print colored output
print_status() { echo -e "${BLUE}[INFO]${NC} $1"; }
print_success() { echo -e "${GREEN}[SUCCESS]${NC} $1"; }
print_warning() { echo -e "${YELLOW}[WARNING]${NC} $1"; }
print_error() { echo -e "${RED}[ERROR]${NC} $1"; }
print_test() { echo -e "${PURPLE}[TEST]${NC} $1"; }

show_help() {
    cat << EOF
Java OOP Fruit Example - Test Script

Usage: $0 [OPTIONS]

OPTIONS:
    -v, --verbose       Enable verbose test output
    -n, --no-compile    Skip compilation step
    -r, --report        Generate detailed test report
    -t, --timeout SEC   Set test timeout in seconds (default: 30)
    -h, --help          Show this help message

EXAMPLES:
    $0                  # Run all tests with compilation
    $0 -v -r            # Verbose tests with report generation
    $0 -n               # Run tests without recompiling
    $0 -t 60            # Run tests with 60-second timeout

EOF
}

# Parse arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -v|--verbose) VERBOSE=true; shift ;;
        -n|--no-compile) COMPILE_FIRST=false; shift ;;
        -r|--report) GENERATE_REPORT=true; shift ;;
        -t|--timeout) TIMEOUT="$2"; shift 2 ;;
        -h|--help) show_help; exit 0 ;;
        *) print_error "Unknown option: $1"; show_help; exit 1 ;;
    esac
done

# Check dependencies
check_dependencies() {
    if ! command -v java &> /dev/null; then
        print_error "Java runtime not found"
        exit 1
    fi
    
    if [ "$COMPILE_FIRST" = true ] && ! command -v javac &> /dev/null; then
        print_error "Java compiler not found"
        exit 1
    fi
    
    local java_version=$(java -version 2>&1 | head -1 | cut -d'"' -f2)
    print_status "Using Java version: $java_version"
}

# Compile if needed
compile_if_needed() {
    if [ "$COMPILE_FIRST" = true ]; then
        print_status "Compiling Java files..."
        if [ -f "scripts/compile.sh" ]; then
            chmod +x scripts/compile.sh
            if [ "$VERBOSE" = true ]; then
                ./scripts/compile.sh -v
            else
                ./scripts/compile.sh
            fi
        else
            javac *.java
        fi
        print_success "Compilation completed"
    else
        print_status "Skipping compilation (using existing class files)"
        
        # Verify class files exist
        if [ ! -f "FruitTests.class" ]; then
            print_error "FruitTests.class not found. Run with compilation or compile manually."
            exit 1
        fi
    fi
}

# Run main test suite
run_main_tests() {
    print_test "Running main test suite..."
    
    local test_output
    local exit_code=0
    
    # Capture test output
    if test_output=$(timeout "$TIMEOUT" java FruitTests 2>&1); then
        print_success "Main test suite completed"
    else
        exit_code=$?
        if [ $exit_code -eq 124 ]; then
            print_error "Test suite timed out after $TIMEOUT seconds"
        else
            print_error "Test suite failed with exit code $exit_code"
        fi
    fi
    
    # Parse test results
    local total_tests=$(echo "$test_output" | grep "Total Tests:" | awk '{print $3}' || echo "0")
    local passed_tests=$(echo "$test_output" | grep "Passed:" | awk '{print $2}' || echo "0")
    local failed_tests=$(echo "$test_output" | grep "Failed:" | awk '{print $2}' || echo "0")
    local success_rate=$(echo "$test_output" | grep "Success Rate:" | awk '{print $3}' || echo "0%")
    
    # Display results
    if [ "$VERBOSE" = true ]; then
        echo "$test_output"
    else
        # Show summary
        print_status "Test Results Summary:"
        echo "  Total Tests: $total_tests"
        echo "  Passed: $passed_tests"
        echo "  Failed: $failed_tests"
        echo "  Success Rate: $success_rate"
    fi
    
    # Check if all tests passed
    if echo "$test_output" | grep -q "All tests passed!"; then
        print_success "✅ All tests passed!"
        return 0
    else
        print_error "❌ Some tests failed"
        return $exit_code
    fi
}

# Run demo to verify basic functionality
run_demo_test() {
    print_test "Running demo application test..."
    
    local demo_output
    if demo_output=$(timeout 10 java FruitDemo 2>&1); then
        print_success "Demo application runs successfully"
        
        # Verify demo output contains expected content
        if echo "$demo_output" | grep -q "Object-Oriented Programming with Fruits"; then
            print_success "Demo produces expected output"
        else
            print_warning "Demo output doesn't match expected format"
        fi
        
        return 0
    else
        print_error "Demo application failed"
        return 1
    fi
}

# Test individual components
run_component_tests() {
    print_test "Running component verification tests..."
    
    # Test individual class instantiation
    local temp_test_file="TempComponentTest.java"
    cat > "$temp_test_file" << 'EOF'
public class TempComponentTest {
    public static void main(String[] args) {
        try {
            // Test basic object creation
            Apple apple = new Apple("Test", "red", 100.0, "Test");
            Orange orange = new Orange("orange", 150.0, "Test", true);
            Banana banana = new Banana(120.0, "Test");
            FruitBasket basket = new FruitBasket("Test");
            
            // Basic functionality test
            basket.addFruit(apple);
            basket.addFruit(orange);
            basket.addFruit(banana);
            
            System.out.println("Component test passed");
        } catch (Exception e) {
            System.err.println("Component test failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
EOF
    
    if javac "$temp_test_file" && java TempComponentTest; then
        print_success "Component tests passed"
        rm -f "$temp_test_file" TempComponentTest.class
        return 0
    else
        print_error "Component tests failed"
        rm -f "$temp_test_file" TempComponentTest.class
        return 1
    fi
}

# Generate test report
generate_test_report() {
    if [ "$GENERATE_REPORT" = false ]; then
        return 0
    fi
    
    print_status "Generating test report..."
    
    local report_file="test-report-$(date +%Y%m%d-%H%M%S).md"
    
    cat > "$report_file" << EOF
# Test Report - Java OOP Fruit Example

**Generated:** $(date)
**Environment:** $(uname -s) $(uname -r)
**Java Version:** $(java -version 2>&1 | head -1)

## Test Execution Summary

EOF
    
    # Add test results to report
    java FruitTests 2>&1 | while read -r line; do
        echo "$line" >> "$report_file"
    done
    
    cat >> "$report_file" << EOF

## Project Statistics

- **Source Files:** $(ls *.java | wc -l)
- **Total Lines:** $(wc -l *.java | tail -1 | awk '{print $1}')
- **Comment Lines:** $(grep -c '^[[:space:]]*//\|^[[:space:]]*\*' *.java | awk -F: '{sum += $2} END {print sum}')

## Class Files Generated

EOF
    
    ls -la *.class >> "$report_file" 2>/dev/null || echo "No class files found" >> "$report_file"
    
    print_success "Test report generated: $report_file"
}

# Main test execution
main() {
    print_status "Java OOP Fruit Example - Test Runner"
    print_status "===================================="
    
    local overall_result=0
    
    # Setup
    check_dependencies
    compile_if_needed
    
    # Run tests
    if ! run_main_tests; then
        overall_result=1
    fi
    
    if ! run_demo_test; then
        overall_result=1
    fi
    
    if ! run_component_tests; then
        overall_result=1
    fi
    
    # Generate report if requested
    generate_test_report
    
    # Final result
    if [ $overall_result -eq 0 ]; then
        print_success "🎉 All tests completed successfully!"
    else
        print_error "❌ Some tests failed"
    fi
    
    return $overall_result
}

# Run main function
main "$@"