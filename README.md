# CPU Algorithms Simulation

The **CPU Algorithms Simulation** project is a learning-focused toolkit for exploring core CPU scheduling strategies. It offers a Java command-line interface (CLI) for prompt-based experimentation and a React web dashboard for guided simulations, letting you compare First-Come-First-Served (FCFS), Shortest Remaining Time First (SRTF), Round Robin (RR), and Non-preemptive Priority (NPP) side by side.

## Overview

- Dual experience: interactive CLI and modern React UI built on a shared scheduling core.
- Displays per-process start, completion, turnaround, and waiting times plus averages.
- Validates burst durations and quantum inputs to prevent invalid runs.
- Modular design separates data, algorithms, and presentation layers for easy extension.

## Tech Stack

- **Java 11+** for the CLI simulator.
- **React 18 + Vite** for the web interface.
- **Node.js 18+** tooling for dependency management and builds.

## Prerequisites

Install or verify the following before running the project:

1. Java Development Kit (JDK) 11 or newer.
2. Node.js 18 or newer (npm ships with Node.js).
3. Git (optional) for cloning the repository.

## Getting Started

1. Clone or download the repository:
	```bash
	git clone https://github.com/jabezapilado/CPU-Algorithms-Simulation.git
	cd CPU-Algorithms-Simulation
	```
2. Open the folder in your preferred editor (e.g., Visual Studio Code or IntelliJ IDEA).

## CLI Workflow (Java)

1. Compile the sources:
	```bash
	javac -d out $(find src -name "*.java")
	```
2. Run the simulator:
	```bash
	java -cp out main.Main
	```
3. Enter the number of processes, then provide arrival, burst, and priority values when prompted.
4. Select an algorithm from the menu. For Round Robin, keep entering the quantum until a positive value is accepted.
5. Review the formatted table summarizing start, completion, turnaround (TAT), and waiting time (WT) for each process alongside averages.

### Sample CLI Session

```
Enter number of processes: 3

Process 1
Arrival Time: 0
Burst Time: 5
Priority: 1

...

Algorithms:
1 - FCFS
2 - SRTF
3 - Round Robin
4 - Non-preemptive Priority

Choose Algorithm: 3
Enter Time Quantum (ms > 0): 2

PID | AT | BT | PR | S | E | TAT | WT
-------------------------------------
P1  | 0  | 5  | 1  | 0 | 6 |  6  | 3
...
Average TAT: 5.67
Average WT: 3.00
```

## Web UI Workflow (React)

1. Install frontend dependencies:
	```bash
	cd gui
	npm install
	```
2. Start the development server:
	```bash
	npm run dev
	```
3. Open the printed localhost URL (usually http://localhost:5173).
4. Add processes through the Processes panel, selecting AT, BT, and PR values; burst time must be at least 1.
5. Choose an algorithm in the Algorithm panel. Selecting RR reveals a quantum input that defaults to 2 milliseconds.
6. Click **Run** to view the results table and averaged metrics. Use **Clear** to reset the output and **Clear All** in Processes to wipe your list.

### Web UI Highlights

- Process chips show entered values and provide one-click removal.
- Results table sorts by PID and gracefully handles processes that have not yet started or completed.
- Average TAT and WT are displayed below the table for quick comparison across algorithms.

## Project Structure

```
CPU-Algorithms-Simulation/
├── gui/                      # React + Vite web interface
│   ├── public/
│   └── src/
│       ├── algorithms/      # JavaScript FCFS, SRTF, RR, NPP implementations
│       ├── components/      # UI building blocks (inputs, selector, results)
│       ├── styles/          # Global styles and CSS modules
│       ├── types/           # Process factory and helpers
│       └── utils/           # Scheduler dispatcher used by the app
└── src/main/                # Java CLI simulator
	 ├── algorithms/          # Java FCFS, SRTF, RR, NPP strategies
	 ├── Main.java            # CLI entry point and workflow
	 ├── Process.java         # Process model with cloning helpers
	 └── Scheduler.java       # Scheduler contract implemented per algorithm
```

## Algorithms Implemented

- **FCFS (First Come First Served):** Runs jobs strictly in arrival order with no preemption.
- **SRTF (Shortest Remaining Time First):** Always selects the job with the least remaining time.
- **Round Robin:** Time-sliced execution with fixed quantum and queue rotation.
- **Non-preemptive Priority:** Executes the highest-priority job available; lower numbers mean higher priority.

## Extending the Project

- Add a new algorithm by implementing the `Scheduler` interface (Java) or adding a handler in `gui/src/algorithms` and registering it in `gui/src/utils/scheduler.js`.
- Adjust process metadata by updating the model in `Process.java` and the process factory in `gui/src/types/process.js`.
- Create automated tests by scripting sample runs and asserting output metrics, or add Jest/Vitest for the React layer.

## Troubleshooting

- **Compilation errors (Java):** Ensure you are using JDK 11+ and that the `out` directory exists or is created automatically by `javac`.
- **npm install failures:** Verify Node.js version with `node -v` and clear the cache using `npm cache clean --force` if necessary.
- **Blank web UI:** Confirm the dev server is running and the browser console shows no module import errors.
- **Unexpected results:** Double-check arrival, burst, and priority inputs; negative or zero burst times are rejected by design.

## Contributors

- Apilado, Jabez Timothy E. — Back-end lead and CLI implementation
- Maninang, Allein Dane G. — Scheduling algorithms (back end)
- Parungao, Nikko S. — Scheduling algorithms (back end)
- Gurango, Christine Francoise O. — React interface (front end)
- Quilantang, Grant Mihkael D. — React interface (front end)

## References

- **Backend**
	- GeeksforGeeks. "CPU Scheduling Algorithms." [geeksforgeeks.org/cpu-scheduling-algorithms](https://www.geeksforgeeks.org/cpu-scheduling-algorithms/)
	- TutorialsPoint. "Operating System - Process Scheduling." [tutorialspoint.com/operating_system/os_process_scheduling.htm](https://www.tutorialspoint.com/operating_system/os_process_scheduling.htm)
	- IBM Documentation. "Process scheduling." [ibm.com/docs/en/aix/7.3?topic=scheduling-process](https://www.ibm.com/docs/en/aix/7.3?topic=scheduling-process)
	- StudyTonight. "Scheduling Algorithms in Operating System." [studytonight.com/operating-system/scheduling-algorithms](https://www.studytonight.com/operating-system/scheduling-algorithms)
	- Javatpoint. "CPU Scheduling in Operating System." [javatpoint.com/os-cpu-scheduling](https://www.javatpoint.com/os-cpu-scheduling)
- **Frontend**
	- React Documentation. "Quick Start." [react.dev/learn](https://react.dev/learn)
	- Vite. "Getting Started." [vitejs.dev/guide](https://vitejs.dev/guide/)
	- MDN Web Docs. "Flexible Box Layout." [developer.mozilla.org/docs/Web/CSS/CSS_flexible_box_layout](https://developer.mozilla.org/docs/Web/CSS/CSS_flexible_box_layout)
	- CSS-Tricks. "A Complete Guide to CSS Modules." [css-tricks.com/css-modules-part-1-need](https://css-tricks.com/css-modules-part-1-need/)
	- TanStack Table (formerly react-table). "Table Examples." [tanstack.com/table/latest/docs/examples/react/basic](https://tanstack.com/table/latest/docs/examples/react/basic)

## License

This project is released for educational use. Apply additional licensing as needed before redistribution.
