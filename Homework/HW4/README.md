# Homework 4: Mutation Based Testing

Download and install the Java mutation tool: [muJava](https://cs.gmu.edu/~offutt/mujava/). Use muJava to test [cal()](https://cs.gmu.edu/~offutt/softwaretest/java/Cal.java). Use all the operators. Design tests to kill all non-equivalent mutants. Note that a test case is a method call to cal().

(a) How many mutants are there?

(b) How many test cases do you need to kill the non-equivalent mutants?

(c) What mutation score were you able to achieve before analyzing for equivalent mutants?

(d) How many equivalent mutants are there?


## Install & Set Environments
```bash
sudo apt-get install openjdk-11-jdk

mkdir ~/hw4 && cd ~/hw4
wget https://cs.gmu.edu/~offutt/mujava/mujava.jar
wget https://cs.gmu.edu/~offutt/mujava/openjava.jar
wget https://cs.gmu.edu/~offutt/mujava/mujava.config
wget https://repo1.maven.org/maven2/junit/junit/4.12/junit-4.12.jar
wget https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

export CLASSPATH=~/hw4/mujava.jar:~/hw4/openjava.jar:~/hw4/junit-4.12.jar:~/hw4/hamcrest-core-1.3.jar
echo "MuJava_HOME=~/hw4" > mujava.config
```

- check
    ```bash
    java --version
    echo $CLASSPATH
    cat mujava.config
    ```

## Create File
```bash
# Create subdirectories 
java mujava.makeMuJavaStructure

# Create source and test file
vim ./src/Cal.java
vim ./testset/CalTest.java

# Compile
javac ./testset/CalTest.java ./src/Cal.java
```

## Run
```bash
# Generate the mutants
java mujava.gui.GenMutantsMain
cp ./src/Cal.class ./classes

# Run the mutants
java mujava.gui.RunTestMain
```