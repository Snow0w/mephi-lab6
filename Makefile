PATH	=	src/edu/mephi/lab5
NAME	=	Lab5.java
FULL_NAME	=	$(PATH)/$(NAME)
OBJ	=	edu.mephi.lab5.Lab5

all: compile run

compile: makeobj
	javac -sourcepath ./src -d obj $(FULL_NAME)

c: compile

makeobj:
	@mkdir -p obj/

clean:
	rm -rf obj/

run:
	java -classpath ./obj $(OBJ) 
