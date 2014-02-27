package com.cds.locks;

import java.util.Random;

public class Backoff {

	private final int minDelay, maxDelay;
	private int limit;
	private final Random random;
	public Backoff(int minDelay, int maxDelay){
		this.minDelay=minDelay;
		this.maxDelay=maxDelay;
		limit=minDelay;
		random=new Random();
	}
	public void backoff()throws InterruptedException{
		int deplay=random.nextInt(limit);
		limit=Math.min(maxDelay, limit*2);
		Thread.sleep(deplay);
	}
	public void reset(){
		limit=minDelay;
	}
}
