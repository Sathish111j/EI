# EI Study Coding Exercise 




- [EI Study Coding Exercise](#ei-study-coding-exercise)
  - [Behavioral Design Patterns](#behavioral-design-patterns)
    - [1. Observer Pattern – News Publisher](#1-observer-pattern--news-publisher)
    - [2. Strategy Pattern – Sorting Algorithm](#2-strategy-pattern--sorting-algorithm)
  - [Creational Design Patterns](#creational-design-patterns)
    - [3. Singleton Pattern – Logger](#3-singleton-pattern--logger)
    - [4. Factory Pattern – Shape Factory](#4-factory-pattern--shape-factory)
  - [Structural Design Patterns](#structural-design-patterns)
    - [5. Adapter Pattern – Media Player](#5-adapter-pattern--media-player)
    - [6. Decorator Pattern – Coffee Shop](#6-decorator-pattern--coffee-shop)
- [EI Study Coding Exercise – Real-Time Chat Application](#ei-study-coding-exercise--real-time-chat-application)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Design Patterns](#design-patterns)
---

## Behavioral Design Patterns

### 1. Observer Pattern – News Publisher

The Observer Pattern defines a one-to-many dependency between objects. When the state of the subject changes, all its dependents (observers) are notified and updated automatically.

**Files:**
- `NewsPublisher.java` – Manages the list of subscribers and notifies them when a new article is published.
- `Subscriber.java` – Interface that defines the update method for observers.
- `EmailSubscriber.java` – Concrete implementation of the observer that receives updates.
- `Main.java` – Runs the news publishing system, allowing users to subscribe/unsubscribe and publish articles.

### 2. Strategy Pattern – Sorting Algorithm

The Strategy Pattern allows selecting an algorithm's behavior at runtime. Different strategies are encapsulated in separate classes and can be switched dynamically.

**Files:**
- `SortStrategy.java` – Interface that defines the sort method.
- `BubbleSortStrategy.java` – Implements the bubble sort algorithm.
- `QuickSortStrategy.java` – Implements the quick sort algorithm.
- `Sorter.java` – Context class that uses a strategy to sort the list.
- `Main.java` – Runs the sorting application, allowing users to choose a sorting strategy and input a list of numbers.

---

## Creational Design Patterns

### 3. Singleton Pattern – Logger

The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance.

**Files:**
- `Logger.java` – Singleton class that provides a global logging mechanism.
- `Main.java` – Demonstrates the usage of the singleton logger.

### 4. Factory Pattern – Shape Factory

The Factory Pattern provides a way to instantiate objects based on input, without specifying the exact class of the object to be created.

**Files:**
- `Shape.java` – Interface that defines the draw method for shapes.
- `Circle.java` – Concrete class that implements the Shape interface.
- `Square.java` – Concrete class that implements the Shape interface.
- `ShapeFactory.java` – Factory class that creates instances of shapes.
- `Main.java` – Demonstrates the usage of the ShapeFactory to create different shapes.

---

## Structural Design Patterns

### 5. Adapter Pattern – Media Player

The Adapter Pattern allows incompatible interfaces to work together by wrapping one interface in another.

**Files:**
- `MediaPlayer.java` – Target interface for playing audio files.
- `AdvancedMediaPlayer.java` – Interface for advanced media formats.
- `VlcPlayer.java` – Plays VLC media files.
- `Mp4Player.java` – Plays MP4 media files.
- `MediaAdapter.java` – Adapts `AdvancedMediaPlayer` to `MediaPlayer`.
- `AudioPlayer.java` – Client class that uses `MediaAdapter` to play different media formats.
- `Main.java` – Demonstrates the usage of the adapter to play different audio types.

### 6. Decorator Pattern – Coffee Shop

The Decorator Pattern dynamically adds new functionality to objects by wrapping them in a decorator class.

**Files:**
- `Beverage.java` – Interface that defines the getDescription and getCost methods.
- `Coffee.java` – Concrete class that implements the base beverage.
- `CondimentDecorator.java` – Abstract class that wraps a beverage and adds functionality.
- `Milk.java` – Concrete decorator that adds milk to the beverage.
- `Sugar.java` – Concrete decorator that adds sugar to the beverage.
- `Main.java` – Demonstrates adding condiments (milk, sugar) dynamically to the coffee.

---

# EI Study Coding Exercise – Real-Time Chat Application

This project is a terminal-based real-time chat application implemented in Java. It allows users to join or create chat rooms, send messages, and interact in real time. The application is built using key design patterns such as Singleton, Observer, and Adapter. It adheres to global coding standards, best practices, and focuses on performance optimization, logging, and error handling.

## Table of Contents

- [Features](#features)
- [Design Patterns](#design-patterns)


## Features

- Create or join chat rooms by entering a unique room ID.
- Send and receive messages in real time within a chat room.
- Display a list of active users in the chat room.
- Private messaging between users.
- Message history persists even if the user leaves and rejoins.
- Default setup with 3 users and 2 chat rooms.
- Logging and exception handling throughout the application.
- Follows SOLID principles, optimized for performance, and structured for long-term execution.

## Design Patterns

The project leverages the following design patterns:
1. **Singleton**: To manage the state of the chat server (`ChatServer` class) and ensure a single instance.
2. **Observer**: To notify users in the chat room when new messages are received (`User` and `ChatRoom` classes).
3. **Adapter**: To allow the chat server to support different message formats or clients (`MessageAdapter`).
