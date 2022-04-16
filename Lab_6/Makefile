file = main.cpp
exe = main
asan_exe = asan

all: ${file}
        g++ -std=c++11 -fsanitize=address $(file) -o $(asan_exe)
        g++ -std=c++11 $(file) -o $(exe)

asan:
        ./$(asan_exe)

asan_return:
        ASAN_OPTIONS=detect_stack_use_after_return=1 ./$(asan_exe)

valg: $(exe)
        valgrind ./$(exe)

clean: 
        rm $(exe) $(asan_exe)