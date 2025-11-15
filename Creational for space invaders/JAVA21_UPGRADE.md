# Java 21 LTS Upgrade Guide

## Overview
This project has been configured to use Java 21 LTS (Long-Term Support). Java 21 is the latest LTS release and includes performance improvements, new language features, and enhanced stability.

## What Changed

### 1. Build Configuration Files Added
- **pom.xml**: Maven configuration with Java 21 source/target compatibility
- **build.gradle**: Gradle configuration with Java 21 source/target compatibility

Both files are provided for flexibility - you can use either Maven or Gradle as your build tool.

### 2. Java Version Configuration
Both build files specify:
- Source compatibility: Java 21
- Target compatibility: Java 21
- Release version: Java 21

## Installation & Setup

### Prerequisites
- **Java 21 JDK** must be installed on your system

### Option 1: Using Windows Package Manager (Recommended)
```powershell
winget install Oracle.JDK.21
```

### Option 2: Manual Installation
Download from one of these sources:
- [Oracle JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Eclipse Adoptium (OpenJDK 21)](https://adoptium.net/)

### Option 3: Using SDKMAN (if installed)
```bash
sdk install java 21-lts
```

## Building the Project

### Using Maven
```powershell
mvn clean compile
mvn package
mvn exec:java -Dexec.mainClass="SpaceInvaders"
```

### Using Gradle
```powershell
gradle clean build
gradle run
```

### Using javac directly
```powershell
javac -d out src/main/java/*.java
java -cp out SpaceInvaders
```

## Verification

To verify Java 21 is properly installed:
```powershell
java -version
```

Should output something like:
```
openjdk version "21.0.1" 2023-10-17
OpenJDK Runtime Environment (build 21.0.1+12-LTS)
```

## Java 21 Features Your Code Can Use

Your project can now optionally use Java 21 features such as:
- **Virtual Threads** for concurrent programming
- **Records** for immutable data carriers
- **Pattern Matching** enhancements
- **Sealed Classes** for better inheritance control
- **Text Blocks** for multi-line strings

## Compatibility Notes

✅ Your current code is fully compatible with Java 21 LTS
- All standard Swing components are supported
- No breaking changes affecting your project
- No deprecation warnings expected

## Configuration Files Location
- Maven: `pom.xml` in project root
- Gradle: `build.gradle` in project root

## Source Code Structure
```
src/main/java/
├── SpaceInvaders.java    (Main entry point)
├── Board.java
├── Player.java
├── Alien.java
├── Bomb.java
├── Shot.java
├── GameOver.java
├── Won.java
├── Commons.java
├── Sprite.java
├── PushPanel.java
├── AlienFactory.java
├── ShotFactory.java
└── img/
```

## IDE Configuration

### VS Code
Add to `.vscode/settings.json`:
```json
{
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-21",
            "path": "/path/to/jdk-21"
        }
    ],
    "java.compile.nullAnalysis.mode": "automatic"
}
```

### IntelliJ IDEA
1. Go to File → Project Structure
2. Select Project
3. Set SDK to Java 21
4. Set Language level to 21

### Eclipse
1. Project → Properties
2. Java Compiler
3. Set Compiler compliance level to 21

## Next Steps

1. Install Java 21 JDK using one of the methods above
2. Choose your preferred build tool (Maven or Gradle)
3. Build and run the project
4. Test the Space Invaders game

## Support

If you encounter any issues:
- Ensure Java 21 is in your PATH
- Run `java -version` to verify installation
- Check IDE language level settings
- Review build output for compilation errors

---
**Last Updated**: November 14, 2025
