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

### Sample Web UI Session

1. Start the dev server with `npm run dev` and open the provided URL.
2. Add three processes through the panel: `(AT 0, BT 5, PR 1)`, `(AT 2, BT 3, PR 2)`, `(AT 4, BT 4, PR 3)`.
3. Select **Round Robin**, set the quantum to `2`, then press **Run**.
4. Review the rendered table and averages, similar to:

```
 PID | AT | BT | PR | Start | End | TAT | WT
-----|----|----|----|-------|-----|-----|----
 P1  |  0 |  5 |  1 |     0 |   9 |   9 |  4
 P2  |  2 |  3 |  2 |     2 |   8 |   6 |  3
 P3  |  4 |  4 |  3 |     6 |  12 |   8 |  4

Avg TAT: 7.67
Avg WT: 3.67
```

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
	- GeeksforGeeks. (2020, March 18). FCFS First Come First Serve CPU Scheduling. GeeksforGeeks. [geeksforgeeks.org/dsa/first-come-first-serve-cpu-scheduling-non-preemptive](https://www.geeksforgeeks.org/dsa/first-come-first-serve-cpu-scheduling-non-preemptive/)
	- GeeksforGeeks. (2025, January 13). Priority Scheduling in Operating System. GeeksforGeeks. [geeksforgeeks.org/operating-systems/priority-scheduling-in-operating-system](https://www.geeksforgeeks.org/operating-systems/priority-scheduling-in-operating-system/)
	- GeeksforGeeks. (2026, January 5). Shortest remaining time first (Preemptive SJF) scheduling algorithm. GeeksforGeeks. [geeksforgeeks.org/dsa/shortest-remaining-time-first-preemptive-sjf-scheduling-algorithm](https://www.geeksforgeeks.org/dsa/shortest-remaining-time-first-preemptive-sjf-scheduling-algorithm/)
	- GeeksforGeeks. (2026b, January 6). Round Robin scheduling in operating system. GeeksforGeeks. [geeksforgeeks.org/operating-systems/round-robin-scheduling-in-operating-system](https://www.geeksforgeeks.org/operating-systems/round-robin-scheduling-in-operating-system/)
	- GeeksforGeeks. (2018, November 13). Preemptive and NonPreemptive Scheduling. GeeksforGeeks. [geeksforgeeks.org/operating-systems/preemptive-and-non-preemptive-scheduling](https://www.geeksforgeeks.org/operating-systems/preemptive-and-non-preemptive-scheduling/)
	- GeeksforGeeks. (2023, January 17). Spooling in Operating System. GeeksforGeeks. [geeksforgeeks.org/operating-systems/spooling-in-operating-system](https://www.geeksforgeeks.org/operating-systems/spooling-in-operating-system/)
	- Operating Systems: CPU Scheduling. (2026). Uic.edu. [cs.uic.edu/~jbell/CourseNotes/OperatingSystems/5_CPU_Scheduling.html](https://www.cs.uic.edu/~jbell/CourseNotes/OperatingSystems/5_CPU_Scheduling.html)
	- Short, M. (2010). The Case For Non-preemptive, Deadline-driven Scheduling In Real-time Embedded Systems. IAENG International Journal of Computer Science, 1(1). [researchgate.net/publication/45534417_The_Case_For_Non-preemptive_Deadline-driven_Scheduling_In_Real-time_Embedded_Systems](https://www.researchgate.net/publication/45534417_The_Case_For_Non-preemptive_Deadline-driven_Scheduling_In_Real-time_Embedded_Systems)
	- sched(7) - Linux manual page. (2026). Man7.org. [man7.org/linux/man-pages/man7/sched.7.html](https://man7.org/linux/man-pages/man7/sched.7.html)
	- CFS Scheduler — The Linux Kernel documentation. (n.d.). [docs.kernel.org/scheduler/sched-design-CFS.html](https://docs.kernel.org/scheduler/sched-design-CFS.html)
	- Harchol-Balter, M., Schroeder, B., Bansal, N., & Agrawal, M. (2003). Size-based scheduling to improve web performance. ACM Transactions on Computer Systems, 21(2), 207–233. [doi.org/10.1145/762483.762486](https://doi.org/10.1145/762483.762486)

- **Frontend**
	- DeepSeek. (n.d.). DeepSeek. [deepseek.com](https://www.deepseek.com/)
	- Vitejs. (n.d.). GitHub - vitejs/vite-plugin-react: The all-in-one Vite plugin for React projects. GitHub. [github.com/vitejs/vite-plugin-react](https://github.com/vitejs/vite-plugin-react)
	- Css-Modules. (n.d.). GitHub - css-modules/css-modules: Documentation about css-modules. GitHub. [github.com/css-modules/css-modules](https://github.com/css-modules/css-modules)
	- Using CSS custom properties (variables) - CSS | MDN. (2025, December 16). [developer.mozilla.org/en-US/docs/Web/CSS/Guides/Cascading_variables/Using_custom_properties](https://developer.mozilla.org/en-US/docs/Web/CSS/Guides/Cascading_variables/Using_custom_properties)

## License

This project is released for educational use. Apply additional licensing as needed before redistribution.
