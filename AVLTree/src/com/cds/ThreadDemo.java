package com.cds;

import java.lang.Thread.State;
import java.util.Random;

class Task implements  Runnable {
	int counter;
	/*public Task(Integer counter){
		this.counter=counter;
	}*/
	public void run() {
		while(counter <= 100000000){
			Math.sqrt(2);
			counter++;
			if(counter %10000000 == 0){
				try {
					Random randomGenerator = new Random();
					Thread.sleep(randomGenerator.nextInt(100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

public class ThreadDemo {
	
	private static final int NUM_OF_THREADS=15;
	private static Integer counters[]=new Integer[NUM_OF_THREADS]; 
	
	public static void main(String []args) throws InterruptedException{
		Thread []workers=new Thread[NUM_OF_THREADS];
		for(int i=0; i<NUM_OF_THREADS; i++){
			counters[i]=1;
			workers[i]=new Thread(new Task(/*counters[i]*/));
		}
		for(int i=0; i < NUM_OF_THREADS; i++){
			workers[i].start();
		}
		int term=0,interval=1;
		
		while(term != NUM_OF_THREADS){
			term=0;
			for(int i=0;i< NUM_OF_THREADS; i++){
				if( workers[i].getState() == State.TERMINATED )
					term++;
				System.out.println("At interval "+ interval +" Thread "+ (i+1) +" is in " + workers[i].getState() +" status ");
			}
			System.out.println("num of threads terminated so far: "+term);
			Thread.sleep(200);
			interval++;
		}
	}	
}

