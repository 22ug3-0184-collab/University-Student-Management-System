# University Student Management System

## Project Overview

The University Student Management System is a Java console-based application developed to demonstrate the practical use of fundamental data structures.

The system manages university student records and represents connections between campus locations.

## Data Structures

The project will implement:
This Java console application implements the data structures and features defined in the project timeline:

- Linked List
- Stack
- Queue
- Binary Search Tree (BST)
- Hashing
- Graph
- BFS/DFS Traversal
- Student records using a singly linked list
- Recent actions using a stack
- Service requests using a queue
- Student search/indexing using a Binary Search Tree (BST)
- Student indexing using a hash table
- Campus connections using a graph represented by an adjacency list
- BFS and DFS graph traversal
- Integrated console menu in `Main.java`

## Student Information

Each student record contains:

- Student ID
- Name
- Programme
- Marks

## Team Members

| M. K. S. S. Ananda           | 22UG3-0184 | Project Coordinator   | Integration, GitHub documentation and testing |
| M.P.J.S.S.Jayasooriya        | 22UG3-0051 | Software Developer 01 | Student Records and Linked List               |
| S. D. D. A. SIYAMBALAPITIYA  | 22UG3-0270 | Software Developer 02 | Stack, Queue, BST and Hashing                 |
| T. Dhammika Thero            | 22UG3-0570 | Software Developer 03 | Graph and BFS/DFS                             |

## Technologies

- Java
- Git
- GitHub
- Visual Studio Code

## Member 2 Contribution
## Java Files

All Java source files are in the `src` folder:

- `Main.java`
- `Student.java`
- `StudentNode.java`
- `StudentLinkedList.java`
- `Action.java`
- `ActionStack.java`
- `ServiceRequest.java`
- `ServiceQueue.java`
- `BSTNode.java`
- `StudentBST.java`
- `StudentHashTable.java`
- `Graph.java`

There is intentionally only one shared `Student.java` class.

## How to Compile

Open PowerShell/Terminal in the `src` directory:

```powershell
javac *.java
```

## How to Run

```powershell
java Main
```

## Main Features

1. Add, display, search, update and delete student records.
2. Linked List stores the main student records.
3. Stack records recent system actions.
4. Queue manages student service requests.
5. BST supports insert/search/delete/in-order traversal.
6. Hash Table supports insert/search/delete using Student ID.
7. Graph manages campus locations and connections.
8. BFS and DFS demonstrate graph traversal.
