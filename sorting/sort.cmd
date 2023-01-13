@echo off

javac -d ./classes %~dp0algorithms/Sort.java
java -cp %~dp0classes algorithms.Sort %*