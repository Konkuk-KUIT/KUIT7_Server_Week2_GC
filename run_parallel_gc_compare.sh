#!/usr/bin/env bash
set -euo pipefail

# 실험 값만 바꾸면 파일명과 JVM 옵션이 같이 맞춰짐
PARALLEL_GC_THREADS=1
GC_TIME_RATIO=1
MAX_GC_PAUSE_MILLIS=1

ROOT="$(cd "$(dirname "$0")" && pwd)"
mkdir -p "$ROOT/logs"
LOG_DIR="$ROOT/logs/run-$(date +%Y%m%d_%H%M%S)"
mkdir -p "$LOG_DIR"
SRC="$ROOT/src"
cd "$SRC"

BASE_JAVA=(java -Xms256m -Xmx512m -XX:+UseParallelGC)

run() {
  local label="$1"
  local logfile="$2"
  shift 2
  echo "=== $label → $(basename "$logfile") ==="
  javac test/ParallelGCTest.java
  "${BASE_JAVA[@]}" "$@" \
    -Xlog:gc*:file="$logfile":time,uptime,level,tags \
    test.ParallelGCTest
  echo
}

LOG_BASELINE="$LOG_DIR/gc_parallel_1_baseline.log"
LOG_THREADS="$LOG_DIR/gc_parallel_2_parallel_gc_threads_${PARALLEL_GC_THREADS}.log"
LOG_RATIO="$LOG_DIR/gc_parallel_3_gc_time_ratio_${GC_TIME_RATIO}.log"
LOG_PAUSE="$LOG_DIR/gc_parallel_4_max_gc_pause_millis_${MAX_GC_PAUSE_MILLIS}.log"

run "1) Parallel GC (기본)" "$LOG_BASELINE"

run "2) +ParallelGCThreads만" "$LOG_THREADS" \
  -XX:ParallelGCThreads="${PARALLEL_GC_THREADS}"

run "3) +GCTimeRatio만" "$LOG_RATIO" \
  -XX:GCTimeRatio="${GC_TIME_RATIO}"

run "4) +MaxGCPauseMillis만" "$LOG_PAUSE" \
  -XX:MaxGCPauseMillis="${MAX_GC_PAUSE_MILLIS}"

echo "완료. 로그 디렉터리: $LOG_DIR"
echo "  $LOG_BASELINE"
echo "  $LOG_THREADS"
echo "  $LOG_RATIO"
echo "  $LOG_PAUSE"
