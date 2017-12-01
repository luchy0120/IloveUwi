class Solution {
	    public List<Integer> selfDividingNumbers(int left, int right) {
	    	List<Integer> ret = new ArrayList<>();
	    	outer:
	        for(int i = left;i <= right;i++){
	        	for(int j = i;j > 0;j /= 10){
	        		int k = j % 10;
	        		if(k == 0)continue outer;
	        		if(i % k != 0)continue outer;
	        	}
	        	ret.add(i);
	        }
	    	return ret;
	    }
	}	