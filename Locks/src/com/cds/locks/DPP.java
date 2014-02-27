package com.cds.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DPP {

	private static final int NUM_OF_PHLP = 5;
	private static final long DURATION = 1000 * 10;
	private static long endTime;
	static volatile Lock []locks;
	public static String numberOfTimesString[]=new String[NUM_OF_PHLP];
	public static Random random=new Random();

	private static class Philosopher implements Runnable {
		Lock leftLock;
		Lock rightLock;
		int numOftimes;
		int id;
		
		public Philosopher(Lock leftLock,Lock rightLock,int id){
			this.id=id;
			this.leftLock=leftLock;
			this.rightLock=rightLock;
		}

		@Override
		public void run() {
			try {
				while(endTime > System.currentTimeMillis()){
					if(leftLock.tryLock(10, TimeUnit.MILLISECONDS)){
						if(rightLock.tryLock(10, TimeUnit.MILLISECONDS)){
							numOftimes++;
							System.out.println("Philosopher "+id+ " eating ");
							Thread.sleep(random.nextInt(10));
							rightLock.unlock();
						}else{
							leftLock.unlock();
						}
					}
					System.out.println("Philosopher "+id+ " thinking ");
					Thread.sleep(random.nextInt(10));
				}
				Thread.sleep(300);
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//		numberTimesOfString="Philosopher "+id+ " ate " +numOftimes;
			System.out.println("Philosopher "+id+ " ate " +numOftimes+" times");
		}
	}
	
	public static void main(String args[]) throws InterruptedException{
		locks=new ReentrantLock[NUM_OF_PHLP];
		for(int i=0;i<NUM_OF_PHLP;i++){
			locks[i]=new ReentrantLock();
		}
		
		Thread [] workers=new Thread[NUM_OF_PHLP];
		for(int i=0;i<NUM_OF_PHLP;i++){
			workers[i]=new Thread( new DPP.Philosopher(locks[i], locks[i%NUM_OF_PHLP], i));
		}
		endTime=System.currentTimeMillis()+DURATION;
		
		for(int i=0;i<NUM_OF_PHLP;i++){
			workers[i].start();
		}
		for(int i=0;i<NUM_OF_PHLP;i++){
			workers[i].join();
		}
	}
	
}
