package solution;

import java.util.Scanner;

public class MultiThreadFindMostDivisors {
    static int numWithMaxDivisors;
    static int maxNumOfDivisors;

    public static void main(String[] args){
        final int UPPER_LIMIT = 100000;
        Scanner scanner = new Scanner(System.in);
        int numOfThreads=-1;
        while(numOfThreads<1){
            System.out.println("How many threads would you like? Please choose a number greater than 0.");
            numOfThreads = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Finding the number between 1 and 100,000 with the most divisors...");
        long startTime = System.currentTimeMillis();
        findDivisorsWithThreads(UPPER_LIMIT, numOfThreads);

        long endTime = System.currentTimeMillis();
        long computationTime = endTime-startTime;

        System.out.println("The number between 1 and "+ UPPER_LIMIT + " with the most divisors was " + numWithMaxDivisors);
        System.out.println("The maximum number of divisors was " + maxNumOfDivisors);
        System.out.println("Computation time for " + numOfThreads + " threads was " + computationTime + " milliseconds.");
    }


    public static void findDivisorsWithThreads(int UPPER_LIMIT, int numOfThreads){
        int increment = UPPER_LIMIT/numOfThreads;
        Thread[] divisorFinders = new DivisorFinderThread[numOfThreads];
        for (int i = 0; i<numOfThreads; i++){
            divisorFinders[i]=new DivisorFinderThread(i*increment+1, (i+1)*increment);
            divisorFinders[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
            while (divisorFinders[i].isAlive()) {
                try {
                    divisorFinders[i].join();
                }
                catch (InterruptedException e) {
                }
            }
        }
    }

    synchronized public static void adjustCounters(int maxNum, int divisors){
        if(divisors>maxNumOfDivisors){
            numWithMaxDivisors = maxNum;
            maxNumOfDivisors = divisors;
        }
    }

    static class DivisorFinderThread extends Thread{
        int minNum;
        int maxNum;
        int numWithMax;
        int maxDivisors;
        DivisorFinderThread(int minNum, int maxNum){
            this.minNum = minNum;
            this.maxNum = maxNum;
        }
        public void run(){
            for (int i=minNum; i<=maxNum; i++){
                int divisorCounter = 0;
                for (int j = 1; j<=i; j++){
                    if(i%j==0){
                        divisorCounter++;
                    }
                }
                if(divisorCounter>maxDivisors){
                    maxDivisors = divisorCounter;
                    numWithMax = i;
                }
            }
            adjustCounters(numWithMax, maxDivisors);
        }
    }
}
