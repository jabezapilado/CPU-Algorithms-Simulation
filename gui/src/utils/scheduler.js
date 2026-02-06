import { copyProcess } from '../types/process'
import { scheduleFCFS, scheduleSRTF, scheduleRR, scheduleNPP } from '../algorithms'

export const ALGORITHMS = {
  FCFS: { id: 'FCFS', name: 'FCFS', label: 'First Come First Served', needsQuantum: false },
  SRTF: { id: 'SRTF', name: 'SRTF', label: 'Shortest Remaining Time First', needsQuantum: false },
  RR: { id: 'RR', name: 'Round Robin', label: 'Round Robin', needsQuantum: true },
  NPP: { id: 'NPP', name: 'NPP', label: 'Non-Preemptive Priority', needsQuantum: false },
}

export const runScheduler = (processes, algorithmId, timeQuantum = 2) => {
  const copies = processes.map((p) => copyProcess(p))

  switch (algorithmId) {
    case 'FCFS':
      return scheduleFCFS(copies)
    case 'SRTF':
      return scheduleSRTF(copies)
    case 'RR':
      return scheduleRR(copies, timeQuantum)
    case 'NPP':
      return scheduleNPP(copies)
    default:
      return copies
  }
}
