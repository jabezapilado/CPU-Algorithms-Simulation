package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.algorithms.FCFS;
import main.algorithms.NPP;
import main.algorithms.RR;
import main.algorithms.SRTF;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1));

            System.out.print("Arrival Time: ");
            int at = scanner.nextInt();

            System.out.print("Burst Time: ");
            int bt = scanner.nextInt();

            System.out.print("Priority: ");
            int pr = scanner.nextInt();

            processes.add(new Process(i + 1, at, bt, pr));
        }

        Scheduler scheduler = selectScheduler(scanner);

        if (scheduler != null) {
            List<Process> scheduled = cloneList(processes);
            scheduler.schedule(scheduled);
            printResults(scheduled);
        }
    }

    static Scheduler selectScheduler(Scanner scanner) {
        System.out.println("\nChoose Algorithm:");
        System.out.println("1 - FCFS");
        System.out.println("2 - SRTF");
        System.out.println("3 - Round Robin");
        System.out.println("4 - Non-preemptive Priority");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new FCFS();
            case 2:
                return new SRTF();
            case 3:
                System.out.print("Enter Time Quantum: ");
                int quantum = scanner.nextInt();
                return new RR(quantum);
            case 4:
                return new NPP();
            default:
                System.out.println("Invalid choice. No scheduling performed.");
                return null;
        }
    }

    static List<Process> cloneList(List<Process> original) {
        List<Process> copy = new ArrayList<>();
        for (Process p : original) {
            copy.add(p.copy());
        }
        return copy;
    }

    static void printResults(List<Process> processes) {
        System.out.println("\nPID | AT | BT | PR | WT | TAT");

        for (Process p : processes) {
            System.out.printf("P%d | %d | %d | %d | %d | %d\n",
                    p.pid, p.arrivalTime, p.burstTime,
                    p.priority, p.waitingTime, p.turnaroundTime);
        }
    }
}
