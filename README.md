# 🖥️ System Monitor

A lightweight, extensible Java application for real-time system metrics collection, scheduling, and multi-channel logging — built with clean architecture principles.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Features](#features)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Modules](#modules)
- [Contributing](#contributing)

---

## Overview

**System Monitor** is a Java-based daemon that continuously collects CPU, memory, and disk usage metrics from the host system, persists them to a database, and dispatches alerts through multiple observer channels (console, file, and database loggers). It uses a scheduled executor to poll metrics at configurable intervals, keeping resource overhead minimal.

---

## Architecture

The project follows a layered, observer-driven architecture:

```
┌─────────────────────────────────────────┐
│              ApplicationConfig          │  ← Spring/DI wiring
└────────────────────┬────────────────────┘
                     │
         ┌───────────▼───────────┐
         │     MetricsScheduler  │  ← Scheduled polling (ScheduledExecutorService)
         └───────────┬───────────┘
                     │
         ┌───────────▼───────────┐
         │    MetricsCollector   │  ← Reads system stats (CommandExecutor)
         └───────────┬───────────┘
                     │
         ┌───────────▼───────────┐
         │      SystemMonitor    │  ← Orchestrates collection & notification
         └──────┬────────────────┘
                │
     ┌──────────▼──────────────────┐
     │       MetricsObserver       │  ← Observer interface
     ├──────────────────────────────┤
     │  AlertObserver              │
     │  ConsoleLogger              │
     │  DatabaseLogger             │
     │  FileLogger                 │
     └──────────────────────────────┘
                │
     ┌──────────▼──────────────────┐
     │     MetricsRepository       │  ← Persistence layer (DatabaseUtil)
     └─────────────────────────────┘
```

---

## Project Structure

```
com.gurukkalmath.monitor/
├── config/
│   └── ApplicationConfig.java       # Dependency injection & service wiring
├── model/
│   └── MetricsData.java             # Value object: CPU, memory, disk, timestamp
├── observer/
│   ├── AlertObserver.java           # Threshold-based alerting
│   ├── ConsoleLogger.java           # Stdout logging
│   ├── DatabaseLogger.java          # Persists events to DB
│   ├── FileLogger.java              # Writes metrics to log file
│   └── MetricsObserver.java         # Observer interface
├── repository/
│   └── MetricsRepository.java       # save(MetricsData) → DB abstraction
├── scheduler/
│   └── MetricsScheduler.java        # Periodic execution via ScheduledExecutorService
├── service/
│   ├── MetricsCollector.java        # Collects CPU/memory/disk from OS
│   └── SystemMonitor.java           # Core orchestrator
└── util/
    ├── CommandExecutor.java         # Runs shell commands to read system stats
    ├── DatabaseUtil.java            # DB connection management
    └── Main.java                    # Entry point
```

---

## Features

- **Real-time metrics** — Collects CPU usage, memory usage, disk usage, and timestamps
- **Scheduled polling** — Configurable interval via `ScheduledExecutorService`
- **Observer pattern** — Plug in any number of listeners without changing core logic
- **Multiple output channels** — Console, file, and database logging out of the box
- **Alert system** — Threshold-based alerts via `AlertObserver`
- **Clean persistence** — Repository abstraction for easy DB swapping

---

## Getting Started

### Prerequisites

- Java 11+
- Maven or Gradle
- A supported database (see [Configuration](#configuration))

### Build

```bash
# Maven
mvn clean package



### Run

```bash
java -jar target/system-monitor.jar
```

---

## Configuration

Edit `ApplicationConfig.java` (or an external `application.properties` if wired) to configure:

| Setting | Description | Default |
|---|---|---|
| Poll interval | How often metrics are collected | `60s` |
| Alert thresholds | CPU / memory / disk % triggers | `80%` |
| Database URL | JDBC connection string | `localhost` |
| Log file path | Output path for `FileLogger` | `./monitor.log` |

---

## Modules

### `MetricsData`
Immutable value object holding a single sample:
- `cpuUsage: double`
- `memoryUsage: double`
- `diskUsage: double`
- `timeStamp: long`

### `MetricsCollector`
Reads system statistics by executing OS-level commands via `CommandExecutor` and parsing their output into a `MetricsData` instance.

### `SystemMonitor`
Ties together collection and notification. On each tick, it requests a new sample from `MetricsCollector` and pushes it to all registered `MetricsObserver` implementations.

### `MetricsScheduler`
Wraps `ScheduledExecutorService` to invoke `SystemMonitor` at a fixed rate. Call `start(SystemMonitor)` to begin polling.

### Observer Implementations

| Class | Behavior |
|---|---|
| `ConsoleLogger` | Prints each sample to stdout |
| `FileLogger` | Appends samples to a rolling log file |
| `DatabaseLogger` | Delegates to `MetricsRepository` for DB persistence |
| `AlertObserver` | Fires an alert when any metric exceeds its threshold |

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push the branch: `git push origin feature/your-feature`
5. Open a Pull Request

Please ensure all new classes include unit tests under `test-classes/`.

---

