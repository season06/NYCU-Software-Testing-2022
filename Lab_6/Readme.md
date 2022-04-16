# Software Testing Lab6

- compiler: `g++ (GCC) 8.5.0 20210514 (Red Hat 8.5.0-10)`  
- install & compile  
```bash
sudo yum install valgrind
sudo yum install libasan
g++ -fsanitize=address -o main main.cpp
```

## ● Heap out-of-bounds read/write

### Source Code
```c++
#include <iostream>
int main()
{
        int *ptr = (int*)malloc(2 * sizeof(int));
        return ptr[2];
}
```
### ASan report
<img src="./images/heap-asan.png">

### Valgrind report
<img src="./images/heap-valg.png">

### Compare
- ASan: yes
- Valgrind: yes

---

## ● Stack out-of-bounds read/write

### Source Code
```c++
#include <iostream>
int main()
{
        int arr[2];
        arr[1] = 1;
        return arr[2];
}
```

### ASan report
<img src="./images/stack-asan.png">

### Valgrind report
<img src="./images/stack-valg.png">

### Compare
- ASan: yes
- Valgrind: no

---

## ● Global out-of-bounds read/write

### Source Code
```c++
#include <iostream>
int global_arr[2] = {0};
int main()
{
        return global_arr[2];
}
```

### ASan report
<img src="./images/global-asan.png">

### Valgrind report
<img src="./images/global-valg.png">

### Compare
- ASan: yes
- Valgrind: no

---

## ● Use-after-free

### Source Code
```c++
#include <iostream>
int main()
{
        int *ptr = (int*)malloc(2 * sizeof(int*));
        free(ptr);
        return ptr[2];
}
```

### ASan report
<img src="./images/after-free-asan.png">

### Valgrind report
<img src="./images/after-free-valg.png">

### Compare
- ASan: yes
- Valgrind: yes

---

## ● Use-after-return

### Source Code
```c++
#include <iostream>
int *x;
void foo()
{
    int num[2];
    x = &num[1];
}
int main()
{
    foo();
    *x = 3;
    return 0;
}
```

### ASan report
<img src="./images/after-return-asan.png">

### Valgrind report
<img src="./images/after-return-valg.png">

### Compare
- ASan: yes (sometimes)
- Valgrind: no
 
 
## ● Redzone with Stack Buffer Overflow

### Source Code
```c++
#include <iostream>
int main()
{
        int redzone_size = 2;
        int a[2], b[4];
        
        for (int i = 1; i <= 4; i++)
                *(&a[1] + 7*redzone_size + i) = 2; // jump to `b` address

        for (int i = 0; i < 4; i++)
                std::cout << b[i] << "\n";

        *(&a[1] + 15*redzone_size + 1) = 100;  // jump outside the redzone 

        return 0;
}
```

## result
ASan can't find the error when the stack buffer overflow crosses the RedZone.
