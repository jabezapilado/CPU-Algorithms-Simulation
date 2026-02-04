package main.algorithms;

import java.util.Comparator;
import java.util.List;

import main.Process;
import main.Scheduler;

public class NPP implements Scheduler {
    public void schedule(List<Process> processes) {
        // Sort by arrival time, then by priority (lower priority value = higher priority)
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime)
                .thenComparingInt(p -> p.priority));
        
        int time = 0;
        for (Process p : processes) {
            if (time < p.arrivalTime) {
                time = p.arrivalTime;
            }
            p.waitingTime = time - p.arrivalTime;
            time += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
        }
    }
}