class Solution {
		int pos;
		int len;
		String[] sp;
		
	    public int evaluate(String expression) {
	    	if(expression == null)return 0;
	    	sp = expression.replace("(", "( ").replace(")", " )").trim().split(" ");
	    	len = sp.length;
	    	pos = 0;
	        int ret = (int)expr(new HashMap<String, Long>());
	        assert pos == len;
	        return ret;
	    }
	    
	    long expr(Map<String, Long> assign)
	    {
	    	if(sp[pos].equals("(")){
		    	pos++;
	    		if(sp[pos].equals("let")){
	    			pos++;
	    			Map<String, Long> scope = new HashMap<>(assign);
	    			while(true){
		    			if(sp[pos].equals("(") || sp[pos+1].equals(")")){
		    				long ret = expr(scope);
		    				pos++;
		    				return ret;
		    			}
		    			scope.put(sp[pos++], expr(scope));
	    			}
	    		}else if(sp[pos].equals("add")){
	    			pos++;
	    			long r1 = expr(assign);
	    			long r2 = expr(assign);
	    			pos++;
	    			return r1+r2;
	    		}else if(sp[pos].equals("mult")){
	    			pos++;
	    			long r1 = expr(assign);
	    			long r2 = expr(assign);
	    			pos++;
	    			return r1*r2;
	    		}else{
	    			throw new RuntimeException();
	    		}
	    	}else{
	    		try {
					long x = Long.parseLong(sp[pos]);
					pos++;
					return x;
				} catch (NumberFormatException e) {
					long ret = assign.get(sp[pos]);
					pos++;
					return ret;
				}
	    	}
	    }
	}