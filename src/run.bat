@echo off
chcp 65001

echo ===== 컴파일 =====
javac DataChunk.java ParallelGCTest.java G1GCTest.java

echo.
echo ===== [1/4] Parallel GC - 튜닝 전 =====
java -Xms256m -Xmx512m ^
     -XX:+UseParallelGC ^
     -Xlog:gc*:file=gc_parallel_before.log:time,uptime,level,tags ^
     ParallelGCTest

echo.
echo ===== [2/4] Parallel GC - 튜닝 후 =====
java -Xms256m -Xmx512m ^
     -XX:+UseParallelGC ^
     -XX:ParallelGCThreads=4 ^
     -XX:GCTimeRatio=9 ^
     -XX:MaxGCPauseMillis=200 ^
     -Xlog:gc*:file=gc_parallel_after.log:time,uptime,level,tags ^
     ParallelGCTest

echo.
echo ===== [3/4] G1 GC - 튜닝 전 =====
java -Xms256m -Xmx512m ^
     -XX:+UseG1GC ^
     -Xlog:gc*:file=gc_g1_before.log:time,uptime,level,tags ^
     G1GCTest

echo.
echo ===== [4/4] G1 GC - 튜닝 후 =====
java -Xms256m -Xmx512m ^
     -XX:+UseG1GC ^
     -XX:MaxGCPauseMillis=100 ^
     -XX:G1HeapRegionSize=4m ^
     -XX:InitiatingHeapOccupancyPercent=35 ^
     -Xlog:gc*:file=gc_g1_after.log:time,uptime,level,tags ^
     G1GCTest

echo.
echo ===== 완료! =====
dir gc_*.log