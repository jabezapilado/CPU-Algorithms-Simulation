/**
 * First Come First Served (FCFS) scheduling algorithm
 */
export const scheduleFCFS = (processes) => {
  const sorted = [...processes].sort((a, b) => a.arrivalTime - b.arrivalTime)
  let time = 0

  for (const p of sorted) {
    if (time < p.arrivalTime) time = p.arrivalTime
    if (p.startTime < 0) p.startTime = time
    p.waitingTime = time - p.arrivalTime
    time += p.burstTime
    p.completionTime = time
    p.turnaroundTime = p.waitingTime + p.burstTime
  }

  return sorted
}
