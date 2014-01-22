package com.cds.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TASLock implements Lock {
	AtomicBoolean state=new AtomicBoolean(false);

	@Override
	public void lock() {
		while(state.getAndSet(true)){}
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		if(state.getAndSet(true))
			return false;
		return true;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		long start=System.nanoTime();
		long delay=TimeUnit.NANOSECONDS.convert(time, unit);
		while( state.getAndSet(true)){ 
				 if(System.nanoTime() - start > delay){
					 return false;
				 }
		}
		return true;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		state.set(false);
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}	
}
