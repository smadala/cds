package com.cds.locks;

import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CompositeLockTest {

	private static int NUM_OF_THREADS=100;
	private static int counter=0;
	private static final int COUNTER_MAX=2000;
	private static Lock lock=new CompositeLock();
	private static class Task implements Runnable{

		@Override
		public void run() {
			int times=0;
			try{
				while(true){
					if(!lock.tryLock(10, TimeUnit.MILLISECONDS)){
						continue;
					}
			
					if(counter >= COUNTER_MAX){
						lock.unlock();
						break;
					}
					counter++;
					System.out.println(counter+ " "+ Thread.currentThread().getName() + " times " + ++times);
					lock.unlock();
					//	break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[]){
		Thread workers[] = new Thread[NUM_OF_THREADS];
		for(int i=0; i < NUM_OF_THREADS;i++ ){
			workers[i] = new Thread( new Task());
		}
		for(int i=0; i<NUM_OF_THREADS; i++){
			workers[i].start();
		}
		for(int i=0;i<NUM_OF_THREADS;i++){
			if(workers[i].getState() != State.TERMINATED){
				i--;
//				System.out.println(i+  "thread");
			}
		}
		
	}
}
