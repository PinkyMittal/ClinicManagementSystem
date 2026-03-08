# JVM Report

This report covers key components of the Java Virtual Machine (JVM) as used by the Java platform.

---

## 1) Class Loader

The JVM uses a **class loader subsystem** to load `.class` files into memory. There are three primary class loaders:

- **Bootstrap (Primordial) Class Loader**: Loads core Java classes from `rt.jar` (e.g., `java.lang.*`).
- **Platform (Extension) Class Loader**: Loads platform-specific classes from the `jre/lib/ext` directory.
- **Application (System) Class Loader**: Loads application classes from the classpath (e.g., `-cp` or `CLASSPATH`).

Class loaders follow a **delegation model**: a child loader first delegates loading to its parent. This prevents core classes from being overridden by untrusted application code.

---

## 2) Runtime Data Areas

When the JVM runs a Java program, it creates several runtime memory areas:

### Heap

- Stores all **objects** and **arrays** that are created with `new`.
- Shared across all threads.
- Garbage collected automatically.

### Stack

- Each thread has its own **Java stack**.
- Stores **stack frames** for method calls, including local variables, operand stack, and return addresses.
- Frames are pushed when a method is invoked and popped when it returns.

### Method Area (Part of the Heap in HotSpot)

- Stores class-level data including **bytecode**, **static variables**, **method metadata**, **constant pool**, and **field information**.

### Program Counter (PC) Register

- Each thread has a **PC register** that points to the current instruction being executed.
- For Java methods, the PC holds the address of the next bytecode instruction.
- For native methods, the PC value is undefined.

---

## 3) Execution Engine

The JVM execution engine reads and executes bytecode instructions.

Key components:

- **Bytecode Interpreter**: Reads bytecode one instruction at a time and executes it.
- **Just-In-Time (JIT) Compiler**: Compiles bytecode into native machine code for performance.
- **Garbage Collector (GC)**: Reclaims unused objects from the heap.

---

## 4) JIT Compiler vs Interpreter

### Interpreter

- Executes bytecode **instruction by instruction**.
- Fast startup, but slower execution due to repeated interpretation.
- Useful for quickly running small programs or when compilation overhead is not worth it.

### Just-In-Time (JIT) Compiler

- Compiles frequently executed code paths (hot methods) into native machine code at runtime.
- Improves performance by avoiding repeated interpretation.
- The JVM can **profile** running code and optimize based on runtime information.

> The JVM typically starts by interpreting and then compiles hot code paths.

---

## 5) "Write Once, Run Anywhere"

Java bytecode is platform-independent. The same `.class` files can run on any platform with a compatible JVM, because the JVM abstracts away underlying OS/hardware differences.

Key points:

- Java source (`.java`) is compiled into bytecode (`.class`).
- Bytecode is executed by the JVM, which is implemented per platform.
- This allows the same application binary to run on Windows, macOS, Linux, etc., without recompilation.
