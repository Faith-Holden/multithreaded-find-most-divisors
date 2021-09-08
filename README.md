# multithreaded-find-most-divisors
My solution for Chapter  Exercise  of “Introduction to Programming Using Java”.

Problem Description:
Exercise 3.2 asked you to find the integer in the range 1 to 10000 that has the largest
number of divisors. Now write a program that uses multiple threads to solve the same
problem, but for the range 1 to 100000. By using threads, your program will take less
time to do the computation when it is run on a multiprocessor computer. At the end of
the program, output the elapsed time, the integer that has the largest number of divisors,
and the number of divisors that it has. The program can be modeled on the sample
prime-counting program ThreadTest2.java from Subsection 12.1.3. For this exercise, you
should simply divide up the problem into parts and create one thread to do each part.
