# CPU Scheduling Algorithms Simulator

A small toolkit for experimenting with classic CPU scheduling algorithms.

The repository contains two entry points:

- **CLI simulator (Java)** under `src/main`. Offers an interactive prompt to enter processes and run FCFS, SRTF, Round Robin, or Non-preemptive Priority scheduling.
- **Web UI (React + Vite)** under `gui/`. Provides a friendly interface for adding processes, choosing an algorithm, and viewing results.

## Prerequisites

- Java 11+ for the CLI.
- Node.js 18+ and npm for the web UI.

## Run the CLI

```bash
# Compile
javac -d out $(find src -name "*.java")

# Execute
java -cp out main.Main
```

## Run the Web UI

```bash
cd gui
npm install
npm run dev
```

Use the displayed localhost URL to open the app.

## Features

- Supports FCFS, SRTF, Round Robin (validated quantum input in CLI), and Non-preemptive Priority scheduling.
- Displays per-process start, completion, turnaround, and waiting times, plus averages.
- Separates algorithm logic from input/output for easier experimentation.

## Housekeeping

- Build outputs are ignored via `.gitignore` (`out/`, `bin/`, `*.class`).
- Temporary planning files were removed; keep documentation in this README going forward.
