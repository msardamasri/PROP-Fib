MAIN_PATH =	./main/subgrup14_1/mastermind/domini/controladors/*.java \
				./main/subgrup14_1/mastermind/domini/enums/*.java \
				./main/subgrup14_1/mastermind/domini/interficies/*.java \
				./main/subgrup14_1/mastermind/domini/iteradors/*.java \
				./main/subgrup14_1/mastermind/domini/models/*.java \
				./main/subgrup14_1/mastermind/excepcions/*.java \
				./main/subgrup14_1/mastermind/utils/*.java \
				./main/subgrup14_1/mastermind/presentacio/controladors/*.java \
				./main/subgrup14_1/mastermind/presentacio/vistes/*.java \
				./main/subgrup14_1/mastermind/*.java \
				./main/subgrup14_1/mastermind/persistencia/*.java

JARS_OUTPUT =	../../../EXE/

CLASS_OUTPUT =	../../../EXE/output/

JARS = ../libs/byte-buddy-1.12.16.jar:../libs/byte-buddy-agent-1.12.16.jar:../libs/hamcrest-core-1.3.jar

clean:
	rm -r ../../../EXE/out/*

distclean:
	rm -r ../../../EXE/*

code:
	javac -cp $(JARS) -d $(CLASS_OUTPUT) $(MAIN_PATH)

executable:
	jar cevf main.subgrup14_1.mastermind.Main $(JARS_OUTPUT)Mastermind.jar -C $(CLASS_OUTPUT) .

all: code executable

executa: all
	java -jar $(JARS_OUTPUT)Mastermind.jar