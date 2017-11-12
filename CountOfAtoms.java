class Solution {
		char[] s;
		int pos, len;
		
	    public String countOfAtoms(String formula) {
	        s = formula.toCharArray();
	        pos = 0;
	        len = s.length;
	        TreeMap<String, java.math.BigInteger> res = go();
	        StringBuilder sb = new StringBuilder();
	        for(String key : res.keySet()){
	        	sb.append(key);
	        	java.math.BigInteger val = res.get(key);
	        	if(!val.equals(java.math.BigInteger.ONE)){
	        		sb.append(val);
	        	}
	        }
	        return sb.toString();
	    }
	    
	    TreeMap<String, java.math.BigInteger> go()
	    {
	    	TreeMap<String, java.math.BigInteger> ret = new TreeMap<>();
	    	while(pos < len && s[pos] != ')'){
		    	if(s[pos] == '('){
		    		pos++;
		    		TreeMap<String, java.math.BigInteger> res = go();
		    		pos++;
		    		java.math.BigInteger num = num();
		    		for(String key : res.keySet()){
		    			java.math.BigInteger val = res.get(key).multiply(num);
		    			if(ret.containsKey(key)){
		    				ret.put(key, ret.get(key).add(val));
		    			}else{
		    				ret.put(key, val);
		    			}
		    		}
		    	}else{
		    		String atom = atom();
		    		java.math.BigInteger num = java.math.BigInteger.ONE;
		    		if(pos < len && s[pos] >= '0' && s[pos] <= '9'){
		    			num = num();
		    		}
	    			if(ret.containsKey(atom)){
	    				ret.put(atom, ret.get(atom).add(num));
	    			}else{
	    				ret.put(atom, num);
	    			}
		    	}
	    	}
	    	return ret;
	    }
	    
	    String atom()
	    {
	    	int opos = pos++;
	    	while(pos < len && s[pos] >= 'a' && s[pos] <= 'z')pos++;
	    	return new String(s, opos, pos - opos);
	    }
	    
	    java.math.BigInteger num()
	    {
	    	int opos = pos;
	    	while(pos < len && s[pos] >= '0' && s[pos] <= '9')pos++;
	    	return new java.math.BigInteger(new String(s, opos, pos-opos));
	    }
	}	