package com.cds.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TTASLock implements Lock {
	AtomicBoolean state=new AtomicBoolean();

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		while(true){
			while(state.get()){}
			if(!state.getAndSet(true))
				return;
		}
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
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
		while(true){
			while(state.get()){ 
				if( System.nanoTime() - start > delay){
					return false;
				}
			}
			if(!state.getAndSet(true))
				return true;
			if( System.nanoTime() - start > delay){
				return false;
			}		
		}
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
