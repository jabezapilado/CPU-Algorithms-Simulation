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
        System.out.println("\nAlgorithms:");
        System.out.println("1 - FCFS");
        System.out.println("2 - SRTF");
        System.out.println("3 - Round Robin");
        System.out.println("4 - Non-preemptive Priority");

        System.out.print("\nChoose Algorithm: "); int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new FCFS();
            case 2:
                return new SRTF();
            case 3:
                System.out.print("Enter Time Quantum (ms): ");
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
        processes.sort((a, b) -> Integer.compare(a.pid, b.pid));

        String[] headers = {"PID", "AT", "BT", "PR", "S", "E", "TAT", "WT"};
        int columns = headers.length;
        int[] widths = new int[columns];
        for (int i = 0; i < columns; i++) {
            widths[i] = headers[i].length();
        }

        List<String[]> rows = new ArrayList<>();

        int totalWT = 0;
        int totalTAT = 0;

        for (Process p : processes) {
            String pid = "P" + p.pid;
            String at = Integer.toString(p.arrivalTime);
            String bt = Integer.toString(p.burstTime);
            String pr = Integer.toString(p.priority);
            String start = p.startTime < 0 ? "-" : Integer.toString(p.startTime);
            String end = p.completionTime == 0 ? "-" : Integer.toString(p.completionTime);
            String tat = Integer.toString(p.turnaroundTime);
            String wt = Integer.toString(p.waitingTime);

            String[] row = {pid, at, bt, pr, start, end, tat, wt};
            rows.add(row);

            for (int i = 0; i < columns; i++) {
                widths[i] = Math.max(widths[i], row[i].length());
            }

            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            formatBuilder.append("%").append(widths[i]).append("s");
            if (i < columns - 1) {
                formatBuilder.append(" | ");
            }
        }
        formatBuilder.append("\n");
        String rowFormat = formatBuilder.toString();

        int lineLength = 0;
        for (int width : widths) {
            lineLength += width;
        }
        lineLength += (columns - 1) * 3;
        String separator = "-".repeat(lineLength);

        System.out.println();
        System.out.printf(rowFormat, (Object[]) headers);
        System.out.println(separator);
        for (String[] row : rows) {
            System.out.printf(rowFormat, (Object[]) row);
        }

        double avgWT = processes.isEmpty() ? 0 : (double) totalWT / processes.size();
        double avgTAT = processes.isEmpty() ? 0 : (double) totalTAT / processes.size();

        System.out.printf("Average TAT: %.2f\n", avgTAT);
        System.out.printf("Average WT: %.2f\n", avgWT);
    }
}
