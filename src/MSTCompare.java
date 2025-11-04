/*
Prim's and Kruskal's algorithms for Minimum Spanning Tree (using adjacency matrix / union-find)
Compare total weights produced and run times roughly
Time: Prim O(V^2) naive, better with heap O(E log V). Kruskal O(E log E)
Space: O(V^2) for matrix or O(E) for edge list
CO3
*/
import java.util.*;


public class MSTCompare {
static class Edge { int u,v; int w; Edge(int u,int v,int w){this.u=u;this.v=v;this.w=w;} }


// Kruskal
static int find(int[] p, int x){ return p[x]==x?x:(p[x]=find(p,p[x])); }
static int kruskal(int V, List<Edge> edges){
Collections.sort(edges, Comparator.comparingInt(e->e.w));
int[] p = new int[V]; for (int i=0;i<V;i++) p[i]=i;
int total=0, cnt=0;
for (Edge e: edges){
int a=find(p,e.u), b=find(p,e.v);
if (a!=b){ p[a]=b; total+=e.w; if (++cnt==V-1) break; }
}
return total;
}


// Prim (adjacency list)
static int prim(int V, List<List<Edge>> adj){
boolean[] vis = new boolean[V];
int total = 0;
PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e->e.w));
pq.add(new Edge(0,0,0));
while (!pq.isEmpty()){
Edge e = pq.poll();
if (vis[e.v]) continue;
vis[e.v]=true; total += e.w;
for (Edge ne: adj.get(e.v)) if (!vis[ne.v]) pq.add(ne);
}
return total;
}


public static void main(String[] args){
int V = 4;
List<Edge> edges = new ArrayList<>();
edges.add(new Edge(0,1,10)); edges.add(new Edge(0,2,6)); edges.add(new Edge(0,3,5));
edges.add(new Edge(1,3,15)); edges.add(new Edge(2,3,4));


// build adj
List<List<Edge>> adj = new ArrayList<>(); for (int i=0;i<V;i++) adj.add(new ArrayList<>());
for (Edge e: edges){ adj.get(e.u).add(new Edge(e.u,e.v,e.w)); adj.get(e.v).add(new Edge(e.v,e.u,e.w)); }


int k = kruskal(V, edges);
int p = prim(V, adj);
System.out.println("Kruskal MST weight: " + k);
System.out.println("Prim MST weight: " + p);
}
}
