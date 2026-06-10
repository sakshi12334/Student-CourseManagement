# Setup Instructions for LearnTrack

## Prerequisites

Before running LearnTrack, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher
- **Command Line Interface**: Terminal (Linux/Mac) or Command Prompt/PowerShell (Windows)
- **Text Editor or IDE**: Any text editor or IDE (Visual Studio Code, IntelliJ IDEA, Eclipse, NetBeans, etc.)

## JDK Installation

### Windows

1. **Download JDK**
   - Visit: https://www.oracle.com/java/technologies/downloads/
   - Download the Windows installer for JDK 8 or later
   - Choose the appropriate version (x64 or x86)

2. **Install JDK**
   - Run the installer executable (.exe)
   - Follow the installation wizard
   - Note the installation path (typically `C:\Program Files\Java\jdk1.8.0_xxx`)

3. **Set Environment Variables**
   - Right-click "This PC" or "My Computer" → Properties
   - Click "Advanced system settings" → "Environment Variables"
   - Click "New" under System variables
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk1.8.0_xxx` (your installation path)
   - Click OK

4. **Update PATH Variable**
   - In Environment Variables, find "Path" in System variables
   - Click "Edit"
   - Click "New" and add: `%JAVA_HOME%\bin`
   - Click OK and close all dialogs

5. **Verify Installation**
   - Open Command Prompt or PowerShell
   - Type: `java -version`
   - You should see the Java version information

### macOS

1. **Using Homebrew (Recommended)**
   ```bash
   brew install openjdk@11
   ```

2. **Manual Installation**
   - Visit: https://www.oracle.com/java/technologies/downloads/
   - Download macOS installer
   - Run the installer and follow the wizard

3. **Verify Installation**
   ```bash
   java -version
   ```

### Linux (Ubuntu/Debian)

1. **Using apt Package Manager**
   ```bash
   sudo apt update
   sudo apt install default-jdk
   ```

2. **Verify Installation**
   ```bash
   java -version
   ```

## Project Setup

### Step 1: Extract Project Files

Ensure all project files are extracted to a directory:
```
Student&CourseManagement/
├── src/
│   └── com/airtribe/learntrack/
│       ├── Main.java
│       ├── entity/
│       ├── repository/
│       ├── service/
│       ├── exception/
│       ├── util/
│       ├── constants/
│       └── enums/
├── docs/
├── README.md
└── Setup_Instructions.md
```

### Step 2: Navigate to Project Directory

```bash
# Windows
cd C:\path\to\Student&CourseManagement

# Linux/Mac
cd /path/to/Student&CourseManagement
```

### Step 3: Compile the Project

Create a `bin` directory for compiled files:

```bash
# Windows
mkdir bin

# Linux/Mac
mkdir -p bin
```

Compile all Java source files:

```bash
# Windows
javac -d bin src/com/airtribe/learntrack/*.java
javac -d bin src/com/airtribe/learntrack/entity/*.java
javac -d bin src/com/airtribe/learntrack/repository/*.java
javac -d bin src/com/airtribe/learntrack/service/*.java
javac -d bin src/com/airtribe/learntrack/exception/*.java
javac -d bin src/com/airtribe/learntrack/util/*.java
javac -d bin src/com/airtribe/learntrack/constants/*.java
javac -d bin src/com/airtribe/learntrack/enums/*.java
```

Or use a single command to compile all files recursively:

```bash
javac -d bin -sourcepath src src/com/airtribe/learntrack/**/*.java
```

### Step 4: Run the Application

```bash
java -cp bin com.airtribe.learntrack.Main
```

### Step 5: Test with a Simple Example

Once the application starts, follow these steps:

1. **Add a Student** (Option 1)
   - First Name: `John`
   - Last Name: `Doe`
   - Email: `john@example.com`
   - Batch: `Batch-2024-Q1`

2. **Add a Course** (Option 5)
   - Course Name: `Core Java Fundamentals`
   - Description: `Learn Java basics and OOP concepts`
   - Duration: `8` weeks

3. **Enroll Student** (Option 9)
   - Student ID: `1001` (from step 1)
   - Course ID: `2001` (from step 2)

4. **View Student Enrollments** (Option 10)
   - Student ID: `1001`

5. **Exit** (Option 0)

## Troubleshooting

### Java Command Not Found

**Problem**: Command 'java' is not recognized

**Solution**:
- Verify JDK installation: `java -version`
- Add JAVA_HOME to PATH environment variable
- Restart command prompt/terminal after setting environment variables

### Compilation Errors

**Problem**: `error: cannot find symbol` or similar compilation errors

**Solution**:
- Ensure all source files are in correct package directories
- Use `-sourcepath` flag when compiling: `javac -d bin -sourcepath src ...`
- Check for typos in class names and file names

### Runtime ClassNotFoundException

**Problem**: `Exception in thread "main" java.lang.NoClassDefFoundError`

**Solution**:
- Ensure compiled files are in the `bin` directory
- Check the classpath: `java -cp bin com.airtribe.learntrack.Main`
- Recompile all files

### Port Already in Use (if running multiple instances)

**Solution**:
- Close all running instances of the application
- Wait a few seconds before starting a new instance

## IDE Setup (Optional)

### Visual Studio Code

1. **Install Extensions**
   - Extension Pack for Java (by Microsoft)
   - Debugger for Java

2. **Create `.vscode/settings.json`**
   ```json
   {
       "java.project.sourcePaths": ["src"],
       "java.project.outputPath": "bin"
   }
   ```

3. **Run from Terminal**
   - Open integrated terminal (Ctrl+`)
   - Execute: `java -cp bin com.airtribe.learntrack.Main`

### IntelliJ IDEA

1. **Import Project**
   - File → Open → Select project folder
   - Select "Create project from existing sources"

2. **Configure Source Roots**
   - Right-click `src` folder → Mark Directory as → Sources Root

3. **Run Application**
   - Right-click `Main.java` → Run 'Main.main()'

### Eclipse

1. **Create Project**
   - File → New → Java Project
   - Name: `LearnTrack`
   - Create separate source and output folders

2. **Import Source Files**
   - Copy all files to `src` folder maintaining package structure

3. **Run**
   - Right-click `Main.java` → Run As → Java Application

## Project Structure Explanation

- **src/**: Contains all Java source files organized by package
- **bin/**: Contains compiled .class files (created during compilation)
- **docs/**: Contains documentation files
- **README.md**: Project overview and usage guide

## Building and Running Script (Optional)

### Windows Batch Script (`build_and_run.bat`)

```batch
@echo off
echo Compiling LearnTrack...
mkdir bin
javac -d bin -sourcepath src src/com/airtribe/learntrack/**/*.java
echo Compilation complete!
echo Running LearnTrack...
java -cp bin com.airtribe.learntrack.Main
pause
```

### Linux/Mac Shell Script (`build_and_run.sh`)

```bash
#!/bin/bash
echo "Compiling LearnTrack..."
mkdir -p bin
javac -d bin -sourcepath src src/com/airtribe/learntrack/**/*.java
echo "Compilation complete!"
echo "Running LearnTrack..."
java -cp bin com.airtribe.learntrack.Main
```

Run the script:
```bash
chmod +x build_and_run.sh
./build_and_run.sh
```

## Next Steps

After successful setup:
1. Read the [README.md](../README.md) for detailed project information
2. Review [JVM_Basics.md](JVM_Basics.md) to understand Java execution
3. Check [Design_Notes.md](Design_Notes.md) for architectural decisions
4. Explore the source code and run the application
5. Experiment with different menu options

## Additional Resources

- **Official Java Documentation**: https://docs.oracle.com/en/java/
- **Java Tutorials**: https://docs.oracle.com/javase/tutorial/
- **JDK Download**: https://www.oracle.com/java/technologies/downloads/
- **OpenJDK**: https://openjdk.java.net/

## Support

For issues or questions:
1. Check the Troubleshooting section above
2. Verify all files are in correct locations
3. Ensure JDK is properly installed and PATH is set
4. Review the error messages carefully
