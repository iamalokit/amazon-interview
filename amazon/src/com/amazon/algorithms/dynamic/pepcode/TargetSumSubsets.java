package com.amazon.algorithms.dynamic.pepcode;

import java.util.Scanner;

public class TargetSumSubsets {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = scn.nextInt();
		}
		
		int target = scn.nextInt();
		boolean[][] dp = new boolean[n+1][target+1];
		
		for(int i = 0; i< dp.length; i++) {
			for(int j = 0; j < dp[0].length; j++) {
				if(i == 0 && j == 0) {
					dp[i][j] = true;
				}
				else if(i == 0) {
					dp[i][j] = false;
				}else if(j == 0) {
					dp[i][j] = true;
				} else {
					if(dp[i-1][j] == true) {
						dp[i][j] = true;
					} else {
						int val = arr[i-1];
						if(j >= val) {
							if(dp[i-1][j-val] == true) {
								dp[i][j] = true;
							}
						}
					}
				}
			}
		}
		
		System.out.println(dp[arr.length][target]);
	}
}
