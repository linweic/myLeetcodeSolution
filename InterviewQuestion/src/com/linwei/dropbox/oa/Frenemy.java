package com.linwei.dropbox.oa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Frenemy {
	/**
	 * First construct a graph representing relations of different people.
	 * Individual people is vertex in the graph and relation "E" or "F" is edge.
	 * Use dfs to check whether vertex s can reach t through the path given by relation chain.
	 * @author linweic
	 *
	 */
	private class Graph{
		private int v;
		private List<Integer>[] adj;
		@SuppressWarnings("unchecked")
		public Graph(char[][] matrix, int ppl){
			v = ppl;
			adj = (List<Integer>[]) new ArrayList[v];
			for(int i = 0; i<v; i++){
				for(int j = i+1; j<v; j++){
					if(matrix[i][j] != '-'){
						if(adj[i] == null) adj[i] = new ArrayList<Integer>();
						adj[i].add(j);
						if(adj[j] == null) adj[j] = new ArrayList<Integer>();
						adj[j].add(i);
					}
				}
			}
		}
	}
	Graph graph;
	char[][] matrix;
	public Frenemy(int ppl, char[][] matrix){
		graph = new Graph(matrix, ppl);
		this.matrix = matrix;
	}
	public boolean isRelation(int s, int t, String chain){
		int index = -1;
		return dfs(s, t, index, chain);
	}
	private boolean dfs(int s, int t, int index, String chain){  
		index++;
		for(int p : graph.adj[s]){
			if(chain.charAt(index) == matrix[s][p]){
				if(index < chain.length()-1){
					if(dfs(p, t, index, chain)==true) return true;
					else continue;
				}
				if(index == chain.length()-1 && p == t) return true;
				else continue;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ppl = 3;
		int s = 0;
		int t = 2;
		String chain = new String("EFFFFE");
		char[][] matrix = new char[][]{
			{'-','F','E'},
			{'F','-','F'},
			{'E','F','-'}};
		Frenemy frenemy = new Frenemy(ppl, matrix);
		frenemy.new Graph(matrix, ppl);
		System.out.println(frenemy.isRelation(s, t, chain));
	}

}