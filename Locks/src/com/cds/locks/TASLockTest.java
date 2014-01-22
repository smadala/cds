package com.cds.locks;

import java.lang.Thread.State;
import java.util.concurrent.locks.Lock;

public  class TASLockTest {

	static Lock lock=new TASLock();
	static  int val=0;
	static final int NUM_OF_THREADS=100;
	private static class Task implements Runnable {
		public void run() {
			
			try {
				while(true){
				Thread.sleep(1);
				lock.lock();
				if(val > 100000){
					lock.unlock();
					break;
				}
				val++;
				System.out.println(val);
				lock.unlock();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread [] threads=new Thread[NUM_OF_THREADS];
		System.out.println("Started ...");
		for(int i=0;i<NUM_OF_THREADS; i++){
			threads[i]=new Thread(new Task());
		}
		long start=System.currentTimeMillis();
		for(int i=0;i<NUM_OF_THREADS;i++){
			threads[i].start();
		}
		
		for(int i=0;i<NUM_OF_THREADS;i++){
			 if(threads[i].getState() != State.TERMINATED ){
				 i=0;
				 Thread.sleep(1);
			 }
		}
		System.out.println("Time in ms : "+( System.currentTimeMillis() - start) );
	}

}
