#!/usr/bin/env bash
set -euo pipefail

# 실험 값만 바꾸면 파일명과 JVM 옵션이 같이 맞춰짐
MAX_GC_PAUSE_MILLIS=100
G1_HEAP_REGION_SIZE=4m
INITIATING_HEAP_OCCUPANCY_PERCENT=30

ROOT="$(cd "$(dirname "$0")" && pwd)"
mkdir -p "$ROOT/logs"
LOG_DIR="$ROOT/logs/run-g1-$(date +%Y%m%d_%H%M%S)"
mkdir -p "$LOG_DIR"
SRC="$ROOT/src"
cd "$SRC"

BASE_JAVA=(java -Xms256m -Xmx512m -XX:+UseG1GC)

run() {
  local label="$1"
  local logfile="$2"
  shift 2
  echo "=== $label → $(basename "$logfile") ==="
  javac test/G1GCTest.java
  "${BASE_JAVA[@]}" "$@" \
    -Xlog:gc*:file="$logfile":time,uptime,level,tags \
    test.G1GCTest
  echo
}

LOG_BASELINE="$LOG_DIR/gc_g1_1_baseline.log"
LOG_PAUSE="$LOG_DIR/gc_g1_2_max_gc_pause_millis_${MAX_GC_PAUSE_MILLIS}.log"
LOG_REGION="$LOG_DIR/gc_g1_3_g1_heap_region_size_${G1_HEAP_REGION_SIZE}.log"
LOG_IHOP="$LOG_DIR/gc_g1_4_initiating_heap_occupancy_percent_${INITIATING_HEAP_OCCUPANCY_PERCENT}.log"

run "1) G1 GC (기본)" "$LOG_BASELINE"

run "2) +MaxGCPauseMillis만" "$LOG_PAUSE" \
  -XX:MaxGCPauseMillis="${MAX_GC_PAUSE_MILLIS}"

run "3) +G1HeapRegionSize만" "$LOG_REGION" \
  -XX:G1HeapRegionSize="${G1_HEAP_REGION_SIZE}"

run "4) +InitiatingHeapOccupancyPercent만" "$LOG_IHOP" \
  -XX:InitiatingHeapOccupancyPercent="${INITIATING_HEAP_OCCUPANCY_PERCENT}"

echo "완료. 로그 디렉터리: $LOG_DIR"
echo "  $LOG_BASELINE"
echo "  $LOG_PAUSE"
echo "  $LOG_REGION"
echo "  $LOG_IHOP"
