# Makefile for Java OOP Fruit Example
# Provides convenient commands for common development tasks

# Variables
JAVA_FILES = $(wildcard *.java)
CLASS_FILES = $(JAVA_FILES:.java=.class)
MAIN_CLASS = FruitDemo
TEST_CLASS = FruitTests
DOCKER_IMAGE = java-oop-fruit
DOCKER_TAG = latest

# Default target
.PHONY: help
help: ## Show this help message
	@echo "Java OOP Fruit Example - Available Commands:"
	@echo "==========================================="
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

# Compilation targets
.PHONY: compile
compile: $(CLASS_FILES) ## Compile all Java source files
	@echo "✅ Compilation complete"

%.class: %.java
	@echo "🔨 Compiling $<..."
	@javac $<

.PHONY: compile-verbose
compile-verbose: ## Compile with verbose output
	javac -verbose *.java

# Execution targets
.PHONY: run
run: compile ## Compile and run the main demo
	@echo "🚀 Running $(MAIN_CLASS)..."
	@java $(MAIN_CLASS)

.PHONY: test
test: compile ## Compile and run all tests
	@echo "🧪 Running tests..."
	@java $(TEST_CLASS)

.PHONY: demo
demo: run ## Alias for run command

# Development targets
.PHONY: clean
clean: ## Remove all compiled class files
	@echo "🧹 Cleaning compiled files..."
	@rm -f *.class
	@echo "✅ Clean complete"

.PHONY: clean-all
clean-all: clean ## Remove all generated files (including Docker images)
	@echo "🧹 Removing Docker images..."
	@docker rmi $(DOCKER_IMAGE):$(DOCKER_TAG) 2>/dev/null || true
	@echo "✅ Deep clean complete"

.PHONY: rebuild
rebuild: clean compile ## Clean and recompile everything
	@echo "✅ Rebuild complete"

# Code quality targets
.PHONY: check
check: compile test ## Compile and run tests (quality check)
	@echo "✅ All checks passed!"

.PHONY: verify
verify: check ## Alias for check command

# Docker targets
.PHONY: docker-build
docker-build: ## Build Docker image
	@echo "🐳 Building Docker image..."
	@docker build -t $(DOCKER_IMAGE):$(DOCKER_TAG) .
	@echo "✅ Docker image built: $(DOCKER_IMAGE):$(DOCKER_TAG)"

.PHONY: docker-run
docker-run: ## Run the application in Docker container
	@echo "🐳 Running in Docker container..."
	@docker run --rm $(DOCKER_IMAGE):$(DOCKER_TAG)

.PHONY: docker-test
docker-test: ## Run tests in Docker container
	@echo "🐳 Running tests in Docker container..."
	@docker run --rm $(DOCKER_IMAGE):$(DOCKER_TAG) java FruitTests

.PHONY: docker-shell
docker-shell: ## Open shell in Docker container
	@echo "🐳 Opening shell in Docker container..."
	@docker run --rm -it $(DOCKER_IMAGE):$(DOCKER_TAG) /bin/bash

.PHONY: docker-dev
docker-dev: docker-build docker-test ## Build image and run tests in Docker

# Documentation targets
.PHONY: docs
docs: ## Display project documentation
	@echo "📚 Java OOP Fruit Example Documentation"
	@echo "======================================"
	@echo "Main files:"
	@echo "  README.md     - Comprehensive project documentation"
	@echo "  CLAUDE.md     - Development guidance for AI assistants"
	@echo "  Fruit.java    - Main source code with extensive comments"
	@echo ""
	@echo "Quick start:"
	@echo "  make compile  - Compile the code"
	@echo "  make run      - Run the demo"
	@echo "  make test     - Run all tests"

.PHONY: stats
stats: ## Show project statistics
	@echo "📊 Project Statistics"
	@echo "===================="
	@echo "Java files:    $(words $(JAVA_FILES))"
	@echo "Total lines:   $$(wc -l *.java *.md | tail -1 | awk '{print $$1}')"
	@echo "Code lines:    $$(grep -v '^[[:space:]]*$$' *.java | grep -v '^[[:space:]]*//' | wc -l)"
	@echo "Test count:    $$(grep -o 'TestFramework\.' FruitTests.java | wc -l)"

# Development workflow targets
.PHONY: dev
dev: clean compile test ## Full development cycle (clean, compile, test)
	@echo "🎉 Development cycle complete!"

.PHONY: ci
ci: compile test ## Continuous integration workflow
	@echo "✅ CI workflow complete!"

.PHONY: quick
quick: compile run ## Quick compile and run (skip tests)

# File watching (requires entr - install with: brew install entr)
.PHONY: watch
watch: ## Watch files and auto-recompile on changes
	@echo "👀 Watching Java files for changes (Ctrl+C to stop)..."
	@echo "$(JAVA_FILES)" | tr ' ' '\n' | entr make compile

.PHONY: watch-test
watch-test: ## Watch files and auto-run tests on changes
	@echo "👀 Watching Java files for changes and running tests..."
	@echo "$(JAVA_FILES)" | tr ' ' '\n' | entr make test

# Setup targets
.PHONY: setup
setup: ## Setup development environment
	@echo "🔧 Setting up development environment..."
	@echo "Checking Java installation..."
	@java -version
	@javac -version
	@echo "✅ Environment setup complete!"

.PHONY: install-tools
install-tools: ## Install recommended development tools (macOS)
	@echo "🔧 Installing development tools..."
	@command -v brew >/dev/null 2>&1 || { echo "❌ Homebrew not found. Install from https://brew.sh/"; exit 1; }
	@brew install entr docker
	@echo "✅ Tools installed!"

# Force rebuild of specific targets
.PHONY: force-compile
force-compile: ## Force recompilation of all files
	@rm -f *.class
	@$(MAKE) compile

# List targets
.PHONY: list
list: ## List all available targets
	@$(MAKE) -pRrq -f $(lastword $(MAKEFILE_LIST)) : 2>/dev/null | awk -v RS= -F: '/^# File/,/^# Finished Make data base/ {if ($$1 !~ "^[#.]") {print $$1}}' | sort | egrep -v -e '^[^[:alnum:]]' -e '^$@$$'

# Ensure intermediate files are not deleted
.PRECIOUS: %.class