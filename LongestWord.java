class Solution {
	    public String longestWord(String[] words) {
	        Arrays.sort(words, new Comparator<String>() {
				public int compare(String a, String b) {
					if(a.length() != b.length())return a.length() - b.length();
					return -a.compareTo(b);
				}
			});
	        int n = words.length;
	        boolean[] dp = new boolean[n];
	        String last = "";	
	        for(int i = 0;i < words.length;i++){
	        	String w = words[i];
	        	if(w.length() == 1){
	        		dp[i] = true;
	        		last = w;
	        		continue;
	        	}
	        	for(int j = 0;j < i;j++){
	        		if(dp[j] && w.length() == words[j].length() + 1 && w.startsWith(words[j])){
	        			dp[i] = true;
		        		last = w;
	        			break;
	        		}
	        	}
	        }
	        return last;
	    }
	}	