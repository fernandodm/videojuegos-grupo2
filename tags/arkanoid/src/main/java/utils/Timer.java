package utils;

import java.util.concurrent.Semaphore;

public class Timer extends Thread{
	
	private int seconds;
	private Task task;
	
	private Semaphore semaphore=new Semaphore(1);
	
	
	public Timer(int init,Task task){
		super();
		this.seconds=init;
		this.task=task;
		this.start();
	}
	
	@Override
	public void run(){
		while(seconds != 0){
			semaphore.acquireUninterruptibly();
			try {
				Thread.sleep(1000);
				seconds--;
			} catch (InterruptedException e) {
				
			}
			semaphore.release();
		}
		semaphore.acquireUninterruptibly();
		task.execute();
		semaphore.release();
		
	}
	
	public void resetSeconds(){
		semaphore.acquireUninterruptibly();
		seconds=0;
		semaphore.release();
	}
	
	public void addSeconds(int seconds){
		semaphore.acquireUninterruptibly();
		seconds+=0;
		semaphore.release();
	}
	
	public int getSeconds(){
		return this.seconds;
	}

}
