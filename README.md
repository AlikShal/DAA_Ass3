# MST Algorithm Implementation - City Transportation Network Optimization

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)]()

## 📋 Project Overview

Implementation of **Prim's** and **Kruskal's algorithms** for finding Minimum Spanning Trees (MST) to optimize city transportation networks. This project is completed as part of the **"Design and Analysis of Algorithms"** course.

### 🎯 Objective

Find the minimum set of roads connecting all city districts with the lowest total construction cost while ensuring all districts are reachable from each other.

---

## 🚀 Features

- ✅ Prim's Algorithm with Priority Queue implementation
- ✅ Kruskal's Algorithm with Union-Find data structure
- ✅ Object-Oriented Architecture (Graph and Edge classes - BONUS)
- ✅ Automated Testing with JUnit 5
- ✅ JSON Input/Output handling
- ✅ CSV Report generation for algorithm comparison
- ✅ Support for graphs of various sizes (5 to 1500+ vertices)

---

## 📁 Project Structure

```
JavaAsik3/
├── src/
│   ├── data/
│   │   ├── ass_3_input.json                    # Small graphs (5 vertices)
│   │   ├── medium_graphs.json                  # Medium graphs (50-100 vertices)
│   │   ├── large_graphs.json                   # Large graphs (400-700 vertices)
│   │   ├── extra_large_graphs.json             # Extra large graphs (1000-1500 vertices)
│   │   └── ass_3_output_generatedBy*.json      # Generated results
│   └── mst/
│       ├── Edge.java                           # Edge representation (BONUS)
│       ├── Graph.java                          # Graph data structure (BONUS)
│       ├── UnionFind.java                      # Union-Find data structure
│       ├── MSTResult.java                      # MST result storage
│       ├── PrimMST.java                        # Prim's algorithm implementation
│       ├── KruskalMST.java                     # Kruskal's algorithm implementation
│       ├── JSONHandler.java                    # JSON I/O handler
│       └── Main.java                           # Main application class
├── test/
│   └── mst/
│       └── MSTTest.java                        # JUnit test cases
├── comparison_results*.csv                     # CSV comparison reports
├── .gitignore
└── README.md
```

---

## 🛠️ Technology Stack

- **Language:** Java 11+
- **Libraries:**
    - Gson 2.10.1 - JSON processing
    - JUnit 5.9.3 - Unit testing framework
- **IDE:** IntelliJ IDEA
- **Build Tool:** Maven/Gradle (optional)

---

## ⚙️ Installation & Setup

### Prerequisites

- Java JDK 11 or higher
- IntelliJ IDEA (or any Java IDE)
- Gson library
- JUnit 5 (for running tests)

### Step 1: Clone Repository

```bash
git clone https://github.com/AlikShal/DAA_Ass3.git
cd mst-city-network
```

### Step 2: Add Dependencies

#### Using Maven (pom.xml):

```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### Using IntelliJ IDEA:

1. File → Project Structure → Libraries
2. Add → From Maven
3. Enter: `com.google.code.gson:gson:2.10.1`
4. Add: `org.junit.jupiter:junit-jupiter:5.9.3`

### Step 3: Compilation

```bash
javac -cp ".:libs/gson-2.10.1.jar" -d bin src/mst/*.java
```

### Step 4: Run

```bash
java -cp "bin:libs/gson-2.10.1.jar" mst.Main
```

**Or via IntelliJ IDEA:**
- Open `Main.java`
- Click the green arrow next to `public static void main`

---

## 🧪 Testing

### Run All Tests

**Via IntelliJ IDEA:**
- Open `MSTTest.java`
- Right-click → Run 'MSTTest'

### Test Coverage

- ✅ Algorithm correctness (identical MST cost)
- ✅ Edge count validation (E = V - 1)
- ✅ Cycle detection (no cycles in MST)
- ✅ Connectivity verification (all vertices connected)
- ✅ Disconnected graph handling
- ✅ Performance and reproducibility

---
## 🏗️ Architecture

### Core Components

#### 1. **Edge.java** (BONUS)
Represents an edge with two vertices and weight. Implements `Comparable` for sorting.

#### 2. **Graph.java** (BONUS)
Graph data structure with adjacency list, connectivity checking via BFS.

#### 3. **UnionFind.java**
Efficient disjoint-set data structure with path compression and union by rank for Kruskal's algorithm.

#### 4. **PrimMST.java**
Prim's algorithm implementation using priority queue. Time Complexity: O(E log V).

#### 5. **KruskalMST.java**
Kruskal's algorithm implementation with edge sorting. Time Complexity: O(E log E).

#### 6. **JSONHandler.java**
Handles JSON input/output using Gson library.

#### 7. **Main.java**
Main application class coordinating all components and generating reports.

#### 8. **MSTTest.java**
Automated test cases for correctness and performance validation.

---
## 👤 Author

**Your Name**  
Student - Design and Analysis of Algorithms

---

## 🙏 Acknowledgments

- Course instructor Sayakulova Zarina for the interesting assignment
- Open-source community for excellent libraries

---

## 📞 Contact

- GitHub: [@AlikShal](https://github.com/AlikShal)
- Email: alikmalik7492@gmail.com

---

**⭐ If this project was helpful, please star it!**
