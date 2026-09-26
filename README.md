# University Student Management System

## Project Overview

The University Student Management System is a Java console-based application developed to demonstrate the practical use of fundamental data structures.

The system manages university student records and represents connections between campus locations.

## Data Structures

The project will implement:

- Linked List
- Stack
- Queue
- Binary Search Tree (BST)
- Hashing
- Graph
- BFS/DFS Traversal

## Student Information

Each student record contains:

- Student ID
- Name
- Programme
- Marks

## Team Members

| Name                         | Student ID | Role                  | Responsibility                                |
|------------------------------|------------|------------------  ---|-----------------------------------------------|
| M. K. S. S. Ananda           | 22UG3-0184 | Project Coordinator   | Integration, GitHub documentation and testing |
| M.P.J.S.S.Jayasooriya        | 22UG3-0051 | Software Developer 01 | Student Records and Linked List               |
| S. D. D. A. SIYAMBALAPITIYA  | 22UG3-0270 | Software Developer 02 | Stack, Queue, BST and Hashing                 |
| T. Dhammika Thero            | 22UG3-0570 | Software Developer 03 | Graph and BFS/DFS                             |

## Technologies

- Java
- Git
- GitHub
- Visual Studio Code

## Individual Contributions

### Member 2 Contribution

**S. D. D. A. Siyambalapitiya (22UG3-0270)** is responsible for the following components:

#### 1. Stack (ActionStack.java)
- Maintains recent actions (Undo/History feature)
- Uses LIFO (Last In First Out) principle
- Operations: push, pop, peek, isEmpty, displayAll
- Records every student operation (add, update, delete)

#### 2. Queue (ServiceQueue.java)
- Manages student service requests in order of arrival
- Uses FIFO (First In First Out) principle
- Operations: enqueue, dequeue, peek, isEmpty, displayAll

#### 3. Binary Search Tree (StudentBST.java)
- Organizes and searches student records by Student ID
- Operations: insert, search, delete, displayInOrder, count
- Displays students in sorted order by Student ID
- Handles all three deletion cases (leaf, one child, two children)

#### 4. Hash Table (StudentHashTable.java)
- Efficient Student ID searching
- Uses chaining (Linked List) for collision handling
- Hash function: (hashValue * 31 + char) % capacity
- Operations: insert, search, delete, display
- Provides O(1) average time complexity for searching

#### 5. Integration
- Integrated all four components into Main.java
- Connected Stack, Queue, BST, and Hash Table with student operations
- Tested all menu options successfully

---

### Member 1 Contribution

**M.P.J.S.S. Jayasooriya (22UG3-0051)** is responsible for the shared `Student` model, the manually implemented singly linked list, student CRUD operations, input validation, and the linked-list test harness.

---

### Member 3 Contribution

**T. Dhammika Thero (22UG3-0570)** is responsible for the Graph implementation, campus locations, connections, and BFS/DFS traversal.
