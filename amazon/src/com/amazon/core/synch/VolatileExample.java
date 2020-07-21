package com.amazon.core.synch;

public class VolatileExample {
	static int a = 0, b = 0;
	
	static void method_one() {
		a++;
		b++;
		
	}
	
	static void method_two() {
		System.out.println("a=" + a + " b=" +b);
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			public void run() {
				for(int i = 0; i < 1000; i++)
					method_one();
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				for(int i = 0; i < 1000; i++)
					method_two();
			}
		};
		
		t1.start();
		t2.start();
	}
}
