# Lab 7: Fuzz Testing
1. We provide a small program that converts bmp from color to grayscale.
	- Use **AFL++** to find the file that can trigger the vulnerability.
	- Use **test.bmp** as init seed.
2. Deliverables shall include the following:
	- PoC: the file that can trigger the vulnerability
	- Screenshot of AFL++ running (with triggered crash): STUDENT_ID.png

**Do not compress the files and plagiarism!**

## Building and installing AFL++
https://github.com/AFLplusplus/AFLplusplus/blob/stable/docs/INSTALL.md

```
docker pull aflplusplus/aflplusplus
docker run -ti -v /location/of/your/target:/src aflplusplus/aflplusplus
```

## Execute
```
git clone https://github.com/a4865g/NYCU-Software-Testing-2022.git
cd NYCU-Software-Testing-2022/Lab_7
export CC=$(which afl-cc)
export AFL_USE_ASAN=1
make
mkdir in
cp test.bmp in/
$(which afl-fuzz) -i in -o out -m none -- ./bmpgrayscale @@ a.bmp
```

## Find crash files
```
# get id_file
ls ./out/default/crashes
./bmpgrayscale ./out/default/crashes/${id_file} a.bmp
```