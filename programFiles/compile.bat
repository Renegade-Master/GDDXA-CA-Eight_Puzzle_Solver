::	@author			Ciaran Bent [K00221230]
::	@creationDate	2018/24/11
::	@description	...

@set JAVAC="C:\Program Files\Java\jdk1.8.0_172\bin\javac.exe"
@set CLASS_PATH=".;utils/algs4.jar;utils/stdlib.jar"

%JAVAC% -cp %CLASS_PATH% Solver.java

@if not %errorlevel% == 0 (
	echo There was an error.
)

java Solver < "puzzles\puzzle_01.txt"

::pause