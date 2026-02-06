import { useState } from 'react'
import { ProcessInput, AlgorithmSelector, ResultsDisplay } from './components'
import { runScheduler } from './utils/scheduler'
import styles from './App.module.css'

function App() {
  const [processes, setProcesses] = useState([])
  const [algorithm, setAlgorithm] = useState('FCFS')
  const [timeQuantum, setTimeQuantum] = useState(2)
  const [results, setResults] = useState(null)

  const handleRun = () => {
    if (processes.length === 0) return
    const scheduled = runScheduler(processes, algorithm, timeQuantum)
    setResults(scheduled)
  }

  const handleClear = () => {
    setResults(null)
  }

  return (
    <div className={styles.app}>
      <header className={styles.header}>
        <h1 className={styles.logo}>CPU Algorithms Simulator</h1>
        <p className={styles.subtitle}>Apilado, Gurango, Maninang, Parungao, and Quilantang</p>
      </header>

      <main className={styles.calculator}>
        <div className={styles.display}>
          {results ? (
            <ResultsDisplay processes={results} />
          ) : (
            <div className={styles.placeholder}>
              Add processes, pick an algorithm, then press <strong>Run</strong>
            </div>
          )}
        </div>

        <div className={styles.controls}>
          <ProcessInput processes={processes} onProcessesChange={setProcesses} />
          <AlgorithmSelector
            selected={algorithm}
            onSelect={setAlgorithm}
            timeQuantum={timeQuantum}
            onQuantumChange={setTimeQuantum}
          />
        </div>

        <div className={styles.actions}>
          <button
            onClick={handleRun}
            disabled={processes.length === 0}
            className={styles.runBtn}
          >
            Run
          </button>
          <button
            onClick={handleClear}
            disabled={!results}
            className={styles.clearBtn}
          >
            Clear
          </button>
        </div>
      </main>

      <footer className={styles.footer}>
        FCFS • SRTF • Round Robin • NPP
      </footer>
    </div>
  )
}

export default App
