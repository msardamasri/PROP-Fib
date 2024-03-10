MAIN_PATH =	./main/subgrup14_1/mastermind/domini/controladors/*.java \
				./main/subgrup14_1/mastermind/domini/enums/*.java \
				./main/subgrup14_1/mastermind/domini/interficies/*.java \
				./main/subgrup14_1/mastermind/domini/iteradors/*.java \
				./main/subgrup14_1/mastermind/domini/models/*.java \
				./main/subgrup14_1/mastermind/excepcions/*.java \
				./main/subgrup14_1/mastermind/utils/*.java
						
TESTS_PATH = 	./test/subgrup14_1/mastermind/*.java \
			  	./test/subgrup14_1/mastermind/testsUnitaris/*.java \
			  	./test/subgrup14_1/mastermind/drivers/*.java

DRIVER_PATH = test/subgrup14_1/mastermind/drivers/

JARS_OUTPUT =	../../../EXE/

CLASS_OUTPUT =	../../../EXE/output/

JARS_JUNIT = ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar

clean:
	rm -r ../../../EXE/out/*

distclean:
	rm -r ../../../EXE/*

all: code tests jars

code:
	javac -d $(CLASS_OUTPUT) $(MAIN_PATH)
	
tests:
	javac -cp $(JARS_JUNIT) -d $(CLASS_OUTPUT) $(MAIN_PATH) $(TESTS_PATH)

jars:
	javac -d $(CLASS_OUTPUT) $(MAIN_PATH) ./test/subgrup14_1/mastermind/drivers/DriverPerfil.java ./test/subgrup14_1/mastermind/drivers/DriverPartida.java ./test/subgrup14_1/mastermind/drivers/DriverEstadistiques.java
	jar cmvf ./test/subgrup14_1/mastermind/drivers/DriverPerfil.mf $(JARS_OUTPUT)DriverPerfil.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./test/subgrup14_1/mastermind/drivers/DriverPartida.mf $(JARS_OUTPUT)DriverPartida.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./test/subgrup14_1/mastermind/drivers/DriverEstadistiques.mf $(JARS_OUTPUT)DriverEstadistiques.jar -C $(CLASS_OUTPUT) .

exeDriverPerfil: all
	java -cp $(JARS_OUTPUT)DriverPerfil.jar $(DRIVER_PATH)DriverPerfil

exeDriverPartida: all
	java -cp $(JARS_OUTPUT)DriverPartida.jar $(DRIVER_PATH)DriverPartida

exeDriverEstadistiques: all
	java -cp $(JARS_OUTPUT)DriverEstadistiques.jar $(DRIVER_PATH)DriverEstadistiques


testTotal: all
	java -cp $(JARS_JUNIT):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.subgrup14_1.mastermind.TestSuiteTotal
	
testEEDD: all
	java -cp $(JARS_JUNIT):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.subgrup14_1.mastermind.TestSuiteEEDD

testModels: all
	java -cp $(JARS_JUNIT):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.subgrup14_1.mastermind.TestSuiteModels