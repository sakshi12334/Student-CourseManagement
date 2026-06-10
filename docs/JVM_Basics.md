# JVM Basics - Understanding Java Execution

## What is Java?

Java is a high-level, object-oriented programming language created by Sun Microsystems (now Oracle) in 1995. It is designed to be platform-independent and runs on the Java Virtual Machine (JVM), which is available for various operating systems.

## The Java Technology Stack

The Java technology stack consists of three main components:

### 1. JDK (Java Development Kit)

**Definition**: The complete software development environment for building Java applications.

**What it includes**:
- **Java Compiler** (`javac`): Translates Java source code (.java files) into bytecode (.class files)
- **Java Runtime** (JRE): Runtime environment to execute Java programs
- **Development Tools**: Debugger, documentation generator, and other utilities
- **Standard Library**: Comprehensive set of classes and APIs for development

**When to use**: Use JDK when developing Java applications. Developers need JDK installed on their machines.

**Example**:
```bash
javac HelloWorld.java      # Compiles source code using JDK
java HelloWorld            # Runs the compiled program using JRE
```

### 2. JRE (Java Runtime Environment)

**Definition**: The environment required to run compiled Java applications.

**What it includes**:
- Java Virtual Machine (JVM)
- Class libraries and runtime support
- Java plug-in (for browser applets - older versions)

**What it does NOT include**:
- Compiler (`javac`)
- Development tools
- Source code

**When to use**: End users need JRE installed to run Java applications. JRE is lighter than JDK and sufficient for execution only.

**Key Point**: JRE is included within JDK. When you install JDK, you automatically have JRE.

### 3. JVM (Java Virtual Machine)

**Definition**: An abstract computing machine that enables a computer to run Java programs and programs written in other languages that are compiled to Java bytecode.

**Key Characteristics**:
- **Virtual Machine**: Software-based, not physical hardware
- **Platform-Independent**: Runs on Windows, Linux, macOS, etc.
- **Abstract Computing Machine**: Defines an instruction set, memory model, and other aspects of an abstract computer
- **Bytecode Interpreter**: Reads and executes bytecode

## The Java Execution Model

### Step 1: Source Code (.java files)

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Step 2: Compilation (javac)

The Java compiler converts readable Java source code into bytecode:

```bash
javac HelloWorld.java
```

This creates: `HelloWorld.class` (bytecode file)

### Step 3: Bytecode (.class files)

Bytecode is an intermediate, platform-neutral representation of your Java program. It's not machine code specific to any processor.

```
Compiled bytecode is stored in .class files
```

### Step 4: JVM Execution

```bash
java HelloWorld
```

The JVM reads the bytecode and executes it:

1. **Class Loader**: Loads the .class file into memory
2. **Bytecode Verifier**: Checks bytecode for security and validity
3. **Execution Engine**: 
   - **Interpreter**: Line-by-line bytecode execution (slower)
   - **JIT Compiler**: Just-in-Time compilation converts bytecode to native machine code (faster for repeated execution)
4. **Runtime System**: Manages memory, garbage collection, exception handling

## Write Once, Run Anywhere (WORA)

### The Philosophy

Java's key principle is **"Write Once, Run Anywhere"** (WORA):

- **Write Once**: You write your Java code once on your computer
- **Compile Once**: Compile it to bytecode once
- **Run Anywhere**: The same bytecode runs on any platform that has a JVM installed

### Why This Works

1. **Source Code** (.java) → Written once
2. **Bytecode** (.class) → Compiled once (platform-independent)
3. **Execution** → Can run on any JVM (Windows, Linux, macOS, etc.)

### Visual Representation

```
Windows Machine          Linux Machine           macOS Machine
    ↓                         ↓                        ↓
  JVM                       JVM                      JVM
    ↓                         ↓                        ↓
Executes HelloWorld.class     Executes HelloWorld.class     Executes HelloWorld.class
    ↓                         ↓                        ↓
Output: "Hello, World!"  Output: "Hello, World!"  Output: "Hello, World!"
```

### Example

1. Compile on Windows:
```bash
javac MyProgram.java
```

2. Copy `MyProgram.class` to Linux

3. Run on Linux:
```bash
java MyProgram
```

It works the same because bytecode is platform-independent!

## Bytecode Explained

### What is Bytecode?

Bytecode is an intermediate code between source code and machine code:

- **Human-Readable**: More readable than machine code
- **Platform-Independent**: Not specific to any processor architecture
- **Compact**: More compact than source code
- **Structured**: Follows a strict format

### Viewing Bytecode

You can view bytecode using the `javap` command:

```bash
javap -c HelloWorld
```

This shows the bytecode instructions.

### JVM Instructions

Common bytecode instructions:
- `bipush`: Push byte onto stack
- `ldc`: Push constant
- `invokestatic`: Invoke static method
- `return`: Return from method
- `areturn`: Return object reference

### Example

Source Code:
```java
public class Simple {
    public static void main(String[] args) {
        int x = 5;
        int y = 10;
        int z = x + y;
    }
}
```

Compiled to bytecode (simplified):
```
0: bipush        5          // Push 5 onto stack
2: istore_1                 // Store in local variable x
3: bipush        10         // Push 10 onto stack
5: istore_2                 // Store in local variable y
6: iload_1                  // Load variable x
7: iload_2                  // Load variable y
8: iadd                     // Add x and y
9: istore_3                 // Store in local variable z
10: return                  // Return
```

## JVM Architecture Overview

### Memory Areas

1. **Heap**: Shared memory for object allocation (garbage collected)
2. **Stack**: Thread-specific memory for method execution
3. **Method Area**: Stores class structures, method data, and code
4. **Program Counter (PC) Register**: Current instruction being executed
5. **Native Method Stack**: Contains native methods

### Execution Process

```
.java File (Source Code)
    ↓
[Compilation Phase]
    ↓
.class File (Bytecode)
    ↓
[Loading Phase]
    ↓
JVM Memory (Class Loader, Memory Areas)
    ↓
[Verification Phase]
    ↓
[Execution Phase: Bytecode Interpreter + JIT Compiler]
    ↓
[Runtime System: Garbage Collector, Exception Handler]
    ↓
Program Output
```

## Comparison: JDK vs JRE vs JVM

| Feature | JDK | JRE | JVM |
|---------|-----|-----|-----|
| **Purpose** | Development | Running Apps | Execution Engine |
| **Contains Compiler** | Yes | No | No |
| **Contains Tools** | Yes | No | No |
| **Contains JVM** | Yes | Yes | N/A |
| **File Size** | ~300-500 MB | ~150-200 MB | Varies |
| **Use Case** | Developers | End Users | Core Engine |
| **Can Compile** | Yes | No | No |
| **Can Run Programs** | Yes | Yes | Yes |

## Installation Hierarchy

```
JDK
├── JRE
│   └── JVM
│   └── Class Libraries
│   └── Runtime Support
├── Compiler (javac)
├── Development Tools
├── Documentation
└── Source Code
```

When you install JDK, you automatically get everything.

## The Compilation and Execution Flow

### Detailed Example: LearnTrack Application

1. **Write Source Code** (`.java` files)
```
src/com/airtribe/learntrack/Main.java
src/com/airtribe/learntrack/entity/Student.java
src/com/airtribe/learntrack/service/StudentService.java
```

2. **Compile with JDK**
```bash
javac -d bin src/com/airtribe/learntrack/**/*.java
```

Output: Bytecode files in `bin/` directory
```
bin/com/airtribe/learntrack/Main.class
bin/com/airtribe/learntrack/entity/Student.class
bin/com/airtribe/learntrack/service/StudentService.class
```

3. **Run with JRE (contains JVM)**
```bash
java -cp bin com.airtribe.learntrack.Main
```

Process:
- JVM starts
- Class Loader loads `Main.class` and dependent classes
- Bytecode Verifier validates bytecode
- Execution Engine executes bytecode
- Application runs and produces output

## Key JVM Features

### 1. Garbage Collection
- Automatic memory management
- Frees unused objects automatically
- Programmers don't need to manually free memory

### 2. Exception Handling
- Try-catch-finally blocks
- Automatic stack unwinding
- Consistent error handling across platforms

### 3. Type Safety
- Strong type checking at compile time
- Runtime type checking
- Prevents type-related errors

### 4. Security
- Bytecode verification ensures safety
- Security manager controls resource access
- Class loader enforces security policies

### 5. Multithreading Support
- Built-in multithreading capabilities
- Thread synchronization primitives
- Concurrent programming support

## Important Concepts

### Classpath
The path where JVM looks for compiled classes:
```bash
java -cp bin:lib/* com.airtribe.learntrack.Main
```

### Package Declaration
Classes are organized in packages for namespacing:
```java
package com.airtribe.learntrack;
```

### Import Statements
Imports declare which classes are used:
```java
import java.util.ArrayList;
```

### main() Method
Entry point for a Java application:
```java
public static void main(String[] args) {
    // Program starts here
}
```

## Common Java Commands

### Compilation
```bash
javac [options] source_files
javac MyProgram.java              # Compile single file
javac -d bin src/**/*.java        # Compile all files to bin directory
```

### Execution
```bash
java [options] class_name
java HelloWorld                   # Run class with main method
java -cp bin HelloWorld           # Specify classpath
java -cp bin:lib.jar MyApp        # Include multiple classpath entries
```

### Bytecode Inspection
```bash
javap [options] class_name
javap HelloWorld                  # Show class structure
javap -c HelloWorld               # Show bytecode
javap -p HelloWorld               # Show all members
```

## Performance: Interpreter vs JIT Compiler

### Interpreter
- Translates bytecode line by line
- Slower execution (immediate but slower)
- Lower startup time
- No optimization

### JIT (Just-In-Time) Compiler
- Compiles frequently used bytecode to native machine code
- Faster execution (after compilation)
- Higher startup time (compilation overhead)
- Runtime optimizations

### JVM Optimization
Modern JVMs use a hybrid approach:
1. Start with interpreter for quick startup
2. Monitor which methods are called frequently
3. Compile hot methods using JIT compiler
4. Optimize based on runtime information

## Summary

| Component | Role | Output |
|-----------|------|--------|
| **JDK** | Development toolkit | Bytecode (.class files) |
| **JRE** | Runtime environment | Running program |
| **JVM** | Virtual machine | Executed bytecode results |

### Three Magic Words in Java

1. **Compile Once**: `javac MyProgram.java`
2. **Run Anywhere**: `java MyProgram` (on any JVM)
3. **Write Once**: Your code works unchanged on different platforms

## References

- [Oracle JDK Documentation](https://docs.oracle.com/en/java/)
- [JVM Specification](https://docs.oracle.com/javase/specs/)
- [Java Bytecode Instruction Set](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-6.html)
