# Lab_5 - LLVM

## Build Environment

```
docker build -t llvm .
docker run -v $PWD/share:/home/llvm-lab/share -it llvm /bin/bash
```

## Run Code

```
cd /home/llvm-lab/share
make
./target 1
```