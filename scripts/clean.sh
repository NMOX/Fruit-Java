#!/bin/bash
# Clean script for Java OOP Fruit Example
# Comprehensive cleanup with safety checks and verbose options

set -euo pipefail

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Configuration
VERBOSE=false
DRY_RUN=false
DEEP_CLEAN=false
INTERACTIVE=false

print_status() { echo -e "${BLUE}[INFO]${NC} $1"; }
print_success() { echo -e "${GREEN}[SUCCESS]${NC} $1"; }
print_warning() { echo -e "${YELLOW}[WARNING]${NC} $1"; }
print_error() { echo -e "${RED}[ERROR]${NC} $1"; }

show_help() {
    cat << EOF
Java OOP Fruit Example - Clean Script

Usage: $0 [OPTIONS]

OPTIONS:
    -v, --verbose       Enable verbose output
    -n, --dry-run       Show what would be cleaned without doing it
    -d, --deep          Deep clean (includes reports, temp files)
    -i, --interactive   Ask before cleaning each category
    -h, --help          Show this help message

CLEAN CATEGORIES:
    - Compiled class files (*.class)
    - Temporary files (*.tmp, *.temp)
    - Test reports (test-report-*.md)
    - Docker artifacts (containers, images)
    - IDE cache files
    - Log files (*.log)

EXAMPLES:
    $0                  # Basic clean (class files only)
    $0 -v               # Verbose basic clean
    $0 -d               # Deep clean everything
    $0 -n -d            # Show what deep clean would remove
    $0 -i               # Interactive clean

EOF
}

# Parse arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -v|--verbose) VERBOSE=true; shift ;;
        -n|--dry-run) DRY_RUN=true; shift ;;
        -d|--deep) DEEP_CLEAN=true; shift ;;
        -i|--interactive) INTERACTIVE=true; shift ;;
        -h|--help) show_help; exit 0 ;;
        *) print_error "Unknown option: $1"; show_help; exit 1 ;;
    esac
done

# Confirmation prompt
confirm() {
    if [ "$INTERACTIVE" = true ]; then
        read -p "$1 (y/N): " -n 1 -r
        echo
        [[ $REPLY =~ ^[Yy]$ ]]
    else
        return 0
    fi
}

# Execute command with dry-run support
execute() {
    local description="$1"
    shift
    local cmd="$*"
    
    if [ "$VERBOSE" = true ] || [ "$DRY_RUN" = true ]; then
        print_status "$description"
        echo "  Command: $cmd"
    fi
    
    if [ "$DRY_RUN" = false ]; then
        eval "$cmd"
    fi
}

# Clean compiled class files
clean_class_files() {
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean compiled class files?"; then
        return 0
    fi
    
    local class_files=$(find . -maxdepth 1 -name "*.class" 2>/dev/null)
    
    if [ -n "$class_files" ]; then
        print_status "Cleaning compiled class files..."
        if [ "$VERBOSE" = true ]; then
            echo "$class_files"
        fi
        execute "Remove class files" "rm -f *.class"
        print_success "Class files cleaned"
    else
        if [ "$VERBOSE" = true ]; then
            print_status "No class files found to clean"
        fi
    fi
}

# Clean temporary files
clean_temp_files() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean temporary files?"; then
        return 0
    fi
    
    print_status "Cleaning temporary files..."
    
    local temp_patterns=("*.tmp" "*.temp" "*~" ".#*" "*.swp" "*.swo")
    
    for pattern in "${temp_patterns[@]}"; do
        local files=$(find . -maxdepth 1 -name "$pattern" 2>/dev/null)
        if [ -n "$files" ]; then
            if [ "$VERBOSE" = true ]; then
                echo "Found: $files"
            fi
            execute "Remove $pattern files" "rm -f $pattern"
        fi
    done
    
    print_success "Temporary files cleaned"
}

# Clean test reports
clean_test_reports() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean test reports?"; then
        return 0
    fi
    
    local reports=$(find . -maxdepth 1 -name "test-report-*.md" 2>/dev/null)
    
    if [ -n "$reports" ]; then
        print_status "Cleaning test reports..."
        if [ "$VERBOSE" = true ]; then
            echo "$reports"
        fi
        execute "Remove test reports" "rm -f test-report-*.md"
        print_success "Test reports cleaned"
    else
        if [ "$VERBOSE" = true ]; then
            print_status "No test reports found to clean"
        fi
    fi
}

# Clean Docker artifacts
clean_docker() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if ! command -v docker &> /dev/null; then
        if [ "$VERBOSE" = true ]; then
            print_status "Docker not found, skipping Docker cleanup"
        fi
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean Docker artifacts?"; then
        return 0
    fi
    
    print_status "Cleaning Docker artifacts..."
    
    # Clean containers
    local containers=$(docker ps -a --filter "ancestor=java-oop-fruit" -q 2>/dev/null || true)
    if [ -n "$containers" ]; then
        execute "Remove containers" "docker rm -f $containers"
    fi
    
    # Clean images
    local images=$(docker images "java-oop-fruit" -q 2>/dev/null || true)
    if [ -n "$images" ]; then
        execute "Remove images" "docker rmi -f $images"
    fi
    
    print_success "Docker artifacts cleaned"
}

# Clean IDE cache files
clean_ide_cache() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean IDE cache files?"; then
        return 0
    fi
    
    print_status "Cleaning IDE cache files..."
    
    # VS Code
    if [ -d ".vscode" ] && [ -f ".vscode/.browse.VC.db" ]; then
        execute "Remove VS Code cache" "rm -f .vscode/.browse.VC.db"
    fi
    
    # IntelliJ IDEA
    if [ -d ".idea" ]; then
        execute "Remove IntelliJ cache" "rm -rf .idea/shelf .idea/workspace.xml"
    fi
    
    # Eclipse
    if [ -f ".metadata" ]; then
        execute "Remove Eclipse metadata" "rm -rf .metadata"
    fi
    
    print_success "IDE cache files cleaned"
}

# Clean log files
clean_logs() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean log files?"; then
        return 0
    fi
    
    local logs=$(find . -maxdepth 1 -name "*.log" 2>/dev/null)
    
    if [ -n "$logs" ]; then
        print_status "Cleaning log files..."
        if [ "$VERBOSE" = true ]; then
            echo "$logs"
        fi
        execute "Remove log files" "rm -f *.log"
        print_success "Log files cleaned"
    else
        if [ "$VERBOSE" = true ]; then
            print_status "No log files found to clean"
        fi
    fi
}

# Clean macOS specific files
clean_macos() {
    if [ "$DEEP_CLEAN" = false ]; then
        return 0
    fi
    
    if [ "$(uname)" != "Darwin" ]; then
        return 0
    fi
    
    if [ "$INTERACTIVE" = true ] && ! confirm "Clean macOS specific files?"; then
        return 0
    fi
    
    print_status "Cleaning macOS specific files..."
    
    local macos_files=(".DS_Store" "._*" ".AppleDouble" ".LSOverride")
    
    for pattern in "${macos_files[@]}"; do
        local files=$(find . -name "$pattern" 2>/dev/null)
        if [ -n "$files" ]; then
            if [ "$VERBOSE" = true ]; then
                echo "Found: $files"
            fi
            execute "Remove $pattern files" "find . -name '$pattern' -delete"
        fi
    done
    
    print_success "macOS files cleaned"
}

# Show what would be cleaned
show_cleanup_summary() {
    print_status "Cleanup Summary:"
    
    local class_count=$(find . -maxdepth 1 -name "*.class" 2>/dev/null | wc -l)
    echo "  Class files: $class_count"
    
    if [ "$DEEP_CLEAN" = true ]; then
        local temp_count=0
        local temp_patterns=("*.tmp" "*.temp" "*~" ".#*" "*.swp" "*.swo")
        for pattern in "${temp_patterns[@]}"; do
            temp_count=$((temp_count + $(find . -maxdepth 1 -name "$pattern" 2>/dev/null | wc -l)))
        done
        echo "  Temporary files: $temp_count"
        
        local report_count=$(find . -maxdepth 1 -name "test-report-*.md" 2>/dev/null | wc -l)
        echo "  Test reports: $report_count"
        
        local log_count=$(find . -maxdepth 1 -name "*.log" 2>/dev/null | wc -l)
        echo "  Log files: $log_count"
        
        if command -v docker &> /dev/null; then
            local container_count=$(docker ps -a --filter "ancestor=java-oop-fruit" -q 2>/dev/null | wc -l)
            local image_count=$(docker images "java-oop-fruit" -q 2>/dev/null | wc -l)
            echo "  Docker containers: $container_count"
            echo "  Docker images: $image_count"
        fi
    fi
}

# Main execution
main() {
    print_status "Java OOP Fruit Example - Clean Script"
    print_status "====================================="
    
    if [ "$DRY_RUN" = true ]; then
        print_warning "DRY RUN MODE - No files will be actually removed"
    fi
    
    if [ "$VERBOSE" = true ] || [ "$DRY_RUN" = true ]; then
        show_cleanup_summary
        echo
    fi
    
    # Always clean class files
    clean_class_files
    
    # Deep clean options
    if [ "$DEEP_CLEAN" = true ]; then
        clean_temp_files
        clean_test_reports
        clean_docker
        clean_ide_cache
        clean_logs
        clean_macos
    fi
    
    if [ "$DRY_RUN" = true ]; then
        print_status "Dry run completed - no files were actually removed"
    else
        print_success "🧹 Cleanup completed successfully!"
    fi
}

# Run main function
main "$@"