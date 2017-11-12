	class Solution {
	    public int pivotIndex(int[] a) {
	        int n = a.length;
	        int[] cum = new int[n+1];
	        for(int i = 0;i < n;i++){
	        	cum[i+1] = cum[i] + a[i];
	        }
	        for(int i = 0;i < n;i++){
	        	if(cum[i+1] == cum[n] - cum[i]){
	        		return i;
	        	}
	        }
	        return -1;
	    }
	}	