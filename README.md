# Formal Languages & Finite Automata Projects

This repository contains projects created throughout my undergraduate **Formal Languages & Finite Automata** (FLFA) course. 
The projects focus on **Deterministic Finite Automata (DFAs)**, **Parsing**, and **Turing Machines**. 
Other topics were covered in this course, but either did not require a project or have incomplete implementations.

## Projects Summary

Projects are primarily written in **Java**, with one file written in **Python**.
- **DFAs**
  - *Man-Wolf-Goat-Cabbage*
  - *Mod 3, No Remainder*
  - *Mod 7, Remainder 2*
- **Parsing and String Processing**
  - *RegexFilter*
    - Demonstrates string processing using regular expressions.
  - *D2R*
    - Implements the D2R algorithm to convert a **Non-Deterministic Finite Automata (NFA)** to a **regular expression**. 
  - *Parser*
    - An **LL(1) parser** for infix mathematical expressions. Demonstrates string processing and parsing strategies.
- **Turing Machines**
  - *TM.py*
    - Turing Machine implemented in Python that accepts the language **L = {1<sup>n</sup>0<sup>n</sup> | n ∈ ℕ}** (equal number of 1's followed by 0's).

## Cloning and Running

To clone this repository, run:
```
git clone https://github.com/closecw/MSCS-271_Formal-Languages_Main.git
cd MSCS-271_Formal-Languages_Main
```

### Java Files

To run the Java source files, each independent of each other, compile and run them (using standalone Java):
```
javac FileName.java
java FileName
```

Alternatively, run them in an IDE designed to run Java files natively such as IntelliJ IDEA or Eclipse.

### Python Script

Any recent version of Python 3+ is recommended for maximum compatibility.
To run the singular Python script:
```
python TM.py
```

If calling python does not work, you may find better luck calling python3:
```
python3 TM.py
```

## Note

This repository is mostly my individual work and is provided for educational and portfolio purposes. 
