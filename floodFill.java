class Solution {
	    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
	        return floodFillc(image, newColor, sr, sc);
	    }
	    
		public int[][] floodFillc(int[][] map, int x, int sr, int sc)
		{
			if(map[sr][sc] == x)return map;
			int[] dr = {1, 0, -1, 0};
			int[] dc = {0, 1, 0, -1};
			int l = dr.length;
			
			int n = map.length;
			int m = map[0].length;
			
			int mark = map[sr][sc];
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.add(new int[]{sr, sc});
			map[sr][sc] = x;
			while(q.size() > 0) {
				int[] cur = q.poll();
				int r = cur[0], c = cur[1];
				for(int k = 0;k < l;k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if(nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == mark) {
						map[nr][nc] = x;
						q.add(new int[]{nr, nc});
					}
				}
			}
			return map;
		}
	}	