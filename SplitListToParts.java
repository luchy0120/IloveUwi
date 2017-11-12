class Solution {
	    public ListNode[] splitListToParts(ListNode root, int k) {
	        int n = 0;
	        for(ListNode x = root;x != null;x = x.next, n++);
	        int f = n/k+1, fn = n % k;
	        int l = n/k, ln = k - n % k;
	        ListNode[] ret = new ListNode[k];
	        ListNode prev = null;
	        for(int i = 0;i < fn;i++){
	        	ret[i] = root;
	        	for(int j = 0;j < f;j++){
	        		prev = root;
	        		root = root.next;
	        	}
	        	if(prev != null)prev.next = null;
	        }
	        for(int i = fn;i < fn+ln;i++){
	        	ret[i] = root;
	        	for(int j = 0;j < l;j++){
	        		prev = root;
	        		root = root.next;
	        	}
	        	if(prev != null)prev.next = null;
	        }
	        return ret;
	    }
	}	