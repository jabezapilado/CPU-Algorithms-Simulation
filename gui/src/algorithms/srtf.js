/**
 * Shortest Remaining Time First (SRTF) scheduling algorithm
 */
export const scheduleSRTF = (processes) => {
  const sorted = [...processes].sort((a, b) => a.arrivalTime - b.arrivalTime)
  const total = sorted.length
  let completed = 0
  let time = total > 0 ? sorted[0].arrivalTime : 0

  while (completed < total) {
    let current = null
    let minRemaining = Infinity

    for (const p of sorted) {
      if (p.arrivalTime <= time && p.remainingTime > 0 && p.remainingTime < minRemaining) {
        minRemaining = p.remainingTime
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
      time = nextArrival === Infinity ? time + 1 : nextArrival
      continue
    }

    if (current.startTime < 0) current.startTime = time
    current.remainingTime--
    time++

    if (current.remainingTime === 0) {
      completed++
      current.turnaroundTime = time - current.arrivalTime
      current.waitingTime = current.turnaroundTime - current.burstTime
      current.completionTime = time
    }
  }

  return sorted
}
