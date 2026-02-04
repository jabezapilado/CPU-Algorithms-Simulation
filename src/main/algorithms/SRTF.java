package main.algorithms;

import java.util.Comparator;
import java.util.List;

import main.Process;    
import main.Scheduler;

public class SRTF implements Scheduler {
    public void schedule(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        
        int time = 0;
        while (!processes.isEmpty()) {
            // Find process with shortest remaining time that has arrived
            Process shortestProcess = null;
            int shortestIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;
            
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= time && processes.get(i).burstTime > 0) {
                    if (processes.get(i).burstTime < minBurstTime) {
                        minBurstTime = processes.get(i).burstTime;
                        shortestProcess = processes.get(i);
                        shortestIndex = i;
                    }
                }
            }
            
            if (shortestProcess != null) {
                shortestProcess.burstTime--;
                time++;
                
                if (shortestProcess.burstTime == 0) {
                    shortestProcess.turnaroundTime = time - shortestProcess.arrivalTime;
                    shortestProcess.waitingTime = shortestProcess.turnaroundTime - shortestProcess.burstTime - 1;
                    processes.remove(shortestIndex);
                }
            } else {
                time++;
            }
        }
    }
}