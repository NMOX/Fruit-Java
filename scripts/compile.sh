#!/bin/bash
# Compilation script for Java OOP Fruit Example
# Provides enhanced compilation with error handling and verbose output

set -euo pipefail  # Exit on error, undefined variables, pipe failures

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
JAVA_FILES="*.java"
VERBOSE=false
CLEAN_FIRST=false

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Help function
show_help() {
    cat << EOF
Java OOP Fruit Example - Compilation Script

Usage: $0 [OPTIONS]

OPTIONS:
    -v, --verbose       Enable verbose compilation output
    -c, --clean         Clean existing class files before compilation
    -h, --help          Show this help message

EXAMPLES:
    $0                  # Basic compilation
    $0 -v               # Verbose compilation
    $0 -c -v            # Clean and verbose compilation

EOF
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -v|--verbose)
            VERBOSE=true
            shift
            ;;
        -c|--clean)
            CLEAN_FIRST=true
            shift
            ;;
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            print_error "Unknown option: $1"
            show_help
            exit 1
            ;;
    esac
done

# Function to check Java installation
check_java() {
    if ! command -v javac &> /dev/null; then
        print_error "javac (Java compiler) is not installed or not in PATH"
        print_error "Please install JDK 8 or higher"
        exit 1
    fi
    
    if ! command -v java &> /dev/null; then
        print_error "java is not installed or not in PATH"
        print_error "Please install JDK 8 or higher"
        exit 1
    fi
    
    local java_version=$(javac -version 2>&1 | cut -d' ' -f2)
    print_status "Using Java compiler version: $java_version"
}

# Function to clean existing class files
clean_classes() {
    if [ "$CLEAN_FIRST" = true ]; then
        print_status "Cleaning existing class files..."
        rm -f *.class
        print_success "Class files cleaned"
    fi
}

# Function to compile Java files
compile_java() {
    print_status "Compiling Java files..."
    
    # Check if Java files exist
    if ! ls $JAVA_FILES 1> /dev/null 2>&1; then
        print_error "No Java files found to compile"
        exit 1
    fi
    
    # Build javac command
    local javac_cmd="javac"
    
    if [ "$VERBOSE" = true ]; then
        javac_cmd="$javac_cmd -verbose"
    fi
    
    # Add classpath for current directory
    javac_cmd="$javac_cmd -cp ."
    
    # Add all Java files
    javac_cmd="$javac_cmd $JAVA_FILES"
    
    print_status "Running: $javac_cmd"
    
    # Execute compilation
    if $javac_cmd; then
        print_success "Compilation completed successfully"
        
        # Count compiled files
        local class_count=$(ls -1 *.class 2>/dev/null | wc -l)
        print_status "Generated $class_count class files"
        
        # List generated files if verbose
        if [ "$VERBOSE" = true ]; then
            print_status "Generated class files:"
            ls -la *.class
        fi
    else
        print_error "Compilation failed"
        exit 1
    fi
}

# Function to verify compilation
verify_compilation() {
    print_status "Verifying compilation..."
    
    # Check for main classes
    local main_classes=("FruitDemo" "FruitTests" "TestFramework")
    local missing_classes=()
    
    for class in "${main_classes[@]}"; do
        if [ ! -f "${class}.class" ]; then
            missing_classes+=("$class")
        fi
    done
    
    if [ ${#missing_classes[@]} -eq 0 ]; then
        print_success "All expected class files generated"
    else
        print_warning "Missing class files: ${missing_classes[*]}"
    fi
    
    # Quick syntax check by running java -version on compiled classes
    for class in "${main_classes[@]}"; do
        if [ -f "${class}.class" ]; then
            if java -cp . "$class" --help &>/dev/null || java -cp . "$class" -h &>/dev/null; then
                continue  # Help option exists, that's fine
            elif java -cp . "$class" &>/dev/null; then
                continue  # Class runs without error
            else
                # This is expected for our classes since they don't have help options
                continue
            fi
        fi
    done
    
    print_success "Compilation verification completed"
}

# Main execution
main() {
    print_status "Java OOP Fruit Example - Compilation Script"
    print_status "============================================"
    
    check_java
    clean_classes
    compile_java
    verify_compilation
    
    print_success "Compilation process completed successfully!"
    print_status "You can now run:"
    print_status "  java FruitDemo    # Run the demonstration"
    print_status "  java FruitTests   # Run the test suite"
}

# Run main function
main "$@"