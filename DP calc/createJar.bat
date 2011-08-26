cd source
javac *.java
jar cmf mainclass.txt ./../DP_Calc.jar *.class
cd ..
cd changer
javac *.java
jar cmf mainclass.txt ./../stat_changer.jar *.class
