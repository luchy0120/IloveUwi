class Solution {
	    public String minWindow(String S, String T) {
	        char[] s = S.toCharArray();
	        int[][] next = makeFatNext(s, 'a', 'z');
	        int minlen = 9999999;
	        int best = -1;
	        outer:
	        for(int i = 0;i < s.length;i++){
	        	int j = i;
	        	for(char c : T.toCharArray()){
	        		int ne = next[c-'a'][j];
	        		if(ne > s.length)continue outer;
	        		j = ne;
	        	}
	        	if(j-i < minlen){
	        		minlen = j-i;
	        		best = i;
	        	}
	        }
	        if(best == -1)return "";
	        return S.substring(best, best + minlen);
	    }
	    
		public int[][] makeFatNext(char[] s, char inf, char sup)
		{
			int n = s.length;
			int[][] next = new int[sup-inf+1][n+1];
			for(int i = 0;i < sup-inf+1;i++)next[i][n] = n+1;
			for(int i = s.length-1;i >= 0;i--){
				for(int j = 0;j < sup-inf+1;j++)next[j][i] = next[j][i+1];
				next[s[i]-inf][i] = i+1;
			}
			return next;
		}
	}	