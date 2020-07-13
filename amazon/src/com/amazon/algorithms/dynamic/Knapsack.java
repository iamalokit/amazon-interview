package com.amazon.algorithms.dynamic;

public class Knapsack {
	public static void main(String[] args) {
		int wt[] = new int[] {10, 20, 30};
		int val[] = new int[] {60, 100, 120};
		int W = 50;
		int n = val.length;
		
		System.out.println(knapSack(W, n, wt, val));
	}

	private static int knapSack(int W, int n, int[] wt, int[] val) {
		int i , w;
		int K[][] = new int[n+1][W+1];
		for(i = 0; i <= n; i++) {
			for(w = 0; w <= W; w++) {
				if(i == 0 || w == 0)
					K[i][w] = 0;
				else if(wt[i-1] <= w)
					K[i][w] = Integer.max(val[i-1] + K[i-1][w-wt[i - 1]], K[i-1][w]);
				else
					K[i][w] = K[i-1][w];
			}
		}
		
		return K[n][W];
	}
}
