/**
 * Non-Preemptive Priority (NPP) scheduling algorithm
 * Lower priority number = higher priority
 */
export const scheduleNPP = (processes) => {
  const sorted = [...processes].sort((a, b) => a.arrivalTime - b.arrivalTime)
  const total = sorted.length
  let completed = 0
  let time = total > 0 ? sorted[0].arrivalTime : 0

  while (completed < total) {
    let current = null

    for (const p of sorted) {
      if (p.remainingTime <= 0 || p.arrivalTime > time) continue

      if (
        !current ||
        p.priority < current.priority ||
        (p.priority === current.priority && p.arrivalTime < current.arrivalTime) ||
        (p.priority === current.priority && p.arrivalTime === current.arrivalTime && p.pid < current.pid)
      ) {
        current = p
      }
    }

    if (!current) {
      let nextArrival = Infinity
      for (const p of sorted) {
        if (p.remainingTime > 0 && p.arrivalTime > time) {
          nextArrival = Math.min(nextArrival, p.arrivalTime)
        }
      }
      if (nextArrival === Infinity) break
      time = nextArrival
      continue
    }

    if (current.startTime < 0) current.startTime = time
    time += current.remainingTime
    current.remainingTime = 0
    completed++
    current.turnaroundTime = time - current.arrivalTime
    current.waitingTime = current.turnaroundTime - current.burstTime
    current.completionTime = time
  }

  return sorted
}
