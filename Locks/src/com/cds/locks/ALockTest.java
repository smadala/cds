package com.cds.locks;

import java.lang.Thread.State;

public class ALockTest {
	private static int NUM_OF_THREADS=10;
	private static int counter=0;
	private static final int COUNTER_MAX=1000;
	private static ALock lock=new ALock(NUM_OF_THREADS);
	private static class Task implements Runnable{

		@Override
		public void run() {
			while(true){
			lock.lock();
			if(counter > COUNTER_MAX){
				lock.unlock();
				break;
			}
			counter++;
			//System.out.println(counter+ " "+ Thread.currentThread().getName());
			lock.unlock();
			//break;
			}
		}
		
	}
	
	public static void main(String args[]){
		Thread workers[] = new Thread[NUM_OF_THREADS];
		for(int i=0; i < NUM_OF_THREADS;i++ ){
			workers[i] = new Thread( new ALockTest.Task());
			workers[i].setName(""+i);
		}
		for(int i=0; i<NUM_OF_THREADS; i++){
			workers[i].start();
		}
		for(int i=0;i<NUM_OF_THREADS;i++){
			if(workers[i].getState() != State.TERMINATED){
				i--;
				System.out.println(i+  "thread");
			}
		}
		
	}

}
