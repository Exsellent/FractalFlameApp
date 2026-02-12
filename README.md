# Fractal Flame Generator

**Project implements the algorithm for generating fractal flame images based on the *Chaos Game* idea.**  
The program supports single-threaded and multi-threaded implementations for improved performance.

---

## Table of Contents

- [Description](#description)
- [Functional Features](#functional-features)
- [Non-Functional Requirements](#non-functional-requirements)
- [Input and Output Data](#input-and-output-data)
- [Implementation Instructions](#implementation-instructions)
- [Testing](#testing)
- [Usage](#usage)
- [Project Build](#project-build)
- [Configuration Setup](#configuration-setup)
- [Example Launch](#example-launch)
- [Single-Threaded Mode](#single-threaded-mode)
- [Multi-Threaded Mode](#multi-threaded-mode)
- [Project Structure](#project-structure)
- [Useful Maven Commands](#useful-maven-commands)
- [Image Examples](#image-examples)
- [Performance Comparison](#performance-comparison)

---

## Description

### Functional Features

1. Implementation of a color fractal flame generation algorithm.
2. Ability to run the program in single-threaded and multi-threaded modes.
3. Support for configuring generation parameters:
   - Image size.
   - Number of iterations.
   - Set of transformation functions.
4. More than 15 transformations implemented, including both basic ones from the original article and additional ones.

### Non-Functional Requirements

- The program is easily configurable via a settings file.
- Multi-threaded implementation demonstrates speedup compared to single-threaded.
- Published comparative results:
  - System configuration.
  - Execution time.
  - Number of threads used.

---

## Input and Output Data

### Input Data

1. Image dimensions (width and height).
2. Number of iterations for fractal generation.
3. List of transformation functions and their parameters.

### Output Data

- Generated fractal flame image in PNG format.

---

## Implementation Instructions

### Development Steps

1. Study of the theoretical foundations of fractal flame generation.
2. Implemented basic single-threaded version of the algorithm.
3. Added multi-threading support to accelerate computations.
4. Implemented configuration for managing parameters (e.g., number of threads, iterations).
5. Written tests for all methods and transformations.

---

## Testing

### Main Checks

1. Correctness of the algorithm on different parameter sets.
2. Writing tests for transformations.
3. Comparison of single-threaded and multi-threaded performance:
   - Measuring execution time.
   - Analyzing speedup depending on the number of threads.

---

## Usage

### Project Launch

1. Make sure you have installed:
   - **JDK 22**.
   - **Apache Maven 3.9.5** or higher.

2. Clone the project:
   ```shell
   git clone <repository-url>
   cd backend_academy_2024_project_4-java-Exsellent
   ```

### Project Build

To build and verify the project, use Maven:
```shell
mvn clean verify
```

### Configuration Setup

In the `config.properties` file, set generation parameters such as image size, number of threads, iterations, and transformations.

Example:
```properties
width=1920
height=1080
samples=1000000
iterations=100
threads=4
symmetry=3
outputDirectory=output
filename=fractal.png
```

---

## Example Launch

### Single-Threaded Mode

Run the application with minimal number of threads:
```shell
java -jar target/fractal-flame-generator.jar --threads 1
```

### Multi-Threaded Mode

Run the application, specifying the desired number of threads:
```shell
java -jar target/fractal-flame-generator.jar --threads 8
```

---

## Project Structure

- `src/main/java/backend/academy/FractalFlame`
    - `components` – basic components for representing the fractal.
    - `config` – classes for loading and managing settings.
    - `processors` – processors for post-processing images.
    - `renderers` – classes for image generation (single-threaded and multi-threaded implementations).
    - `transformations` – transformation functions (linear and non-linear).
    - `utils` – utility classes, including logging and testing.

---

## Useful Maven Commands

1. Project build:
   ```shell
   mvn compile
   ```

2. Run tests:
   ```shell
   mvn test
   ```

3. Code linting:
   ```shell
   mvn checkstyle:check
   ```

---

## Image Examples

- Examples of generated fractals can be found in the `output/` directory.

---

## Performance Comparison

### Results

| Mode            | Threads | Execution Time | Speedup |
|-----------------|---------|----------------|---------|
| Single-threaded | 1       | 45.943 sec     | 1x      |
| Multi-threaded  | 8       | 8.737 sec      | 5.26x   |
```
