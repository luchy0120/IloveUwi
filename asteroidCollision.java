class Solution {
	    public int[] asteroidCollision(int[] as) {
	        int n = as.length;
	        LST lst = new LST(n);
	        lst.setRange(n);
	        Queue<Integer> q = new ArrayDeque<>();
	        for(int i = 0;i < n-1;i++){
	        	if(as[i] > 0 && as[i+1] < 0){
	        		q.add(i);
	        	}
	        }
	        while(!q.isEmpty()){
	        	int cur = q.poll();
	        	int l = lst.prev(cur);
	        	int r = lst.next(cur+1);
	        	if(l != -1 && r != -1 &&
	        			as[l] > 0 && as[r] < 0
	        			){
	        		boolean ch = false;
	        		if(Math.abs(as[l]) >= Math.abs(as[r])){
	        			lst.unset(r);
	        			ch = true;
	        		}
	        		if(Math.abs(as[r]) >= Math.abs(as[l])){
	        			lst.unset(l);
	        			ch = true;
	        		}
	        		if(ch){
	        			q.add(cur);
	        		}
	        	}
	        }
	        int[] ret = new int[n];
	        int p = 0;
	        for(int i = lst.next(0);i != -1;lst.unset(i), i = lst.next(0)){
	        	ret[p++] = as[i];
	        }
	        return Arrays.copyOf(ret, p);
	    }
	    
	    class LST {
	    	public long[][] set;
	    	public int n;
//	    	public int size;
	    	
	    	public LST(int n) {
	    		this.n = n;
	    		int d = 1;
	    		for(int m = n;m > 1;m>>>=6, d++);
	    		
	    		set = new long[d][];
	    		for(int i = 0, m = n>>>6;i < d;i++, m>>>=6){
	    			set[i] = new long[m+1];
	    		}
//	    		size = 0;
	    	}
	    	
	    	// [0,r)
	    	public LST setRange(int r)
	    	{
	    		for(int i = 0;i < set.length;i++, r=r+63>>>6){
	    			for(int j = 0;j < r>>>6;j++){
	    				set[i][j] = -1L;
	    			}
	    			if((r&63) != 0)set[i][r>>>6] |= (1L<<r)-1;
	    		}
	    		return this;
	    	}
	    	
	    	// [0,r)
	    	public LST unsetRange(int r)
	    	{
	    		if(r >= 0){
	    			for(int i = 0;i < set.length;i++, r=r+63>>>6){
	    				for(int j = 0;j < r+63>>>6;j++){
	    					set[i][j] = 0;
	    				}
	    				if((r&63) != 0)set[i][r>>>6] &= ~((1L<<r)-1);
	    			}
	    		}
	    		return this;
	    	}
	    	
	    	public LST set(int pos)
	    	{
	    		if(pos >= 0 && pos < n){
//	    			if(!get(pos))size++;
	    			for(int i = 0;i < set.length;i++, pos>>>=6){
	    				set[i][pos>>>6] |= 1L<<pos;
	    			}
	    		}
	    		return this;
	    	}
	    	
	    	public LST unset(int pos)
	    	{
	    		if(pos >= 0 && pos < n){
//	    			if(get(pos))size--;
	    			for(int i = 0;i < set.length && (i == 0 || set[i-1][pos] == 0L);i++, pos>>>=6){
	    				set[i][pos>>>6] &= ~(1L<<pos);
	    			}
	    		}
	    		return this;
	    	}
	    	
	    	public boolean get(int pos)
	    	{
	    		return pos >= 0 && pos < n && set[0][pos>>>6]<<~pos<0;
	    	}
	    	
	    	public int prev(int pos)
	    	{
	    		for(int i = 0;i < set.length && pos >= 0;i++, pos>>>=6, pos--){
	    			int pre = prev(set[i][pos>>>6], pos&63);
	    			if(pre != -1){
	    				pos = pos>>>6<<6|pre;
	    				while(i > 0)pos = pos<<6|63-Long.numberOfLeadingZeros(set[--i][pos]);
	    				return pos;
	    			}
	    		}
	    		return -1;
	    	}
	    	
	    	public int next(int pos)
	    	{
	    		for(int i = 0;i < set.length && pos>>>6 < set[i].length;i++, pos>>>=6, pos++){
	    			int nex = next(set[i][pos>>>6], pos&63);
	    			if(nex != -1){
	    				pos = pos>>>6<<6|nex;
	    				while(i > 0)pos = pos<<6|Long.numberOfTrailingZeros(set[--i][pos]);
	    				return pos;
	    			}
	    		}
	    		return -1;
	    	}
	    	
	    	private int prev(long set, int n)
	    	{
	    		long h = Long.highestOneBit(set<<~n);
	    		if(h == 0L)return -1;
	    		return Long.numberOfTrailingZeros(h)-(63-n);
	    	}
	    	
	    	private int next(long set, int n)
	    	{
	    		long h = Long.lowestOneBit(set>>>n);
	    		if(h == 0L)return -1;
	    		return Long.numberOfTrailingZeros(h)+n;
	    	}
	    	
	    	@Override
	    	public String toString()
	    	{
	    		List<Integer> list = new ArrayList<Integer>();
	    		for(int pos = next(0);pos != -1;pos = next(pos+1)){
	    			list.add(pos);
	    		}
	    		return list.toString();
	    	}
	    }

	}	