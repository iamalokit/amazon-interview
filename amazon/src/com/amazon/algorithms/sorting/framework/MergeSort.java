package com.amazon.algorithms.sorting.framework;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
	protected T[] aux;

	@Override
	public void sort(T[] nums) {
		aux = (T[]) new Comparable[nums.length];
		sort(nums, 0, nums.length - 1);
	}

	private void sort(T[] nums, int l, int h) {
		if (h <= l) {
			return;
		}

		int mid = l + (h - l) / 2;
		sort(nums, l, mid);
		sort(nums, mid + 1, h);
		merge(nums, l, mid, h);
	}

	private void merge(T[] nums, int l, int m, int h) {
		int i = l, j = m + 1;
		for (int k = l; k <= h; k++) {
			aux[k] = nums[k];
		}

		for (int k = l; k <= h; k++) {
			if (i > m) {
				nums[k] = aux[j++];
			} else if (j > h) {
				nums[k] = aux[i++];
			} else if (aux[i].compareTo(aux[j]) <= 0) {
				nums[k] = aux[i++]; // Do this first to ensure stability
			} else {
				nums[k] = aux[j++];
			}
		}
	}
	
	public static void main(String[] args) {
        Integer[] nums = new Integer[]{3, 5, 1, 2, 4, 6};
        new MergeSort<Integer>().sort(nums);
    }

}
