ECHO OFF
SET X=150
SET Y=50
mode con: cols=%X% lines=%Y%
java TankideTorm %X% %Y%
pause
