/**
 * Round Robin (RR) scheduling algorithm
 */
export const scheduleRR = (processes, timeQuantum) => {
  const sorted = [...processes].sort((a, b) => a.arrivalTime - b.arrivalTime)
  const queue = []
  let time = 0
  let index = 0

  while (index < sorted.length || queue.length > 0) {
    while (index < sorted.length && sorted[index].arrivalTime <= time) {
      queue.push(sorted[index])
      index++
    }

    if (queue.length === 0) {
      if (index < sorted.length) {
        time = sorted[index].arrivalTime
        queue.push(sorted[index])
        index++
      }
    } else {
      const p = queue.shift()
      if (p.startTime < 0) p.startTime = time
      const executeTime = Math.min(timeQuantum, p.remainingTime)
      time += executeTime
      p.remainingTime -= executeTime

      while (index < sorted.length && sorted[index].arrivalTime <= time) {
        queue.push(sorted[index])
        index++
      }

      if (p.remainingTime > 0) {
        queue.push(p)
      } else {
        p.turnaroundTime = time - p.arrivalTime
        p.waitingTime = p.turnaroundTime - p.burstTime
        p.completionTime = time
      }
    }
  }

  return sorted
}
