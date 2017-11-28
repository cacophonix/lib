package lib.cacophonix.graph;

import lib.cacophonix.collections.Pair;
import lib.cacophonix.generated.collections.set.IntHashSet;
import lib.cacophonix.generated.collections.set.IntSet;

import java.util.Arrays;

/**
 * @author cacophonix
 */
public class StronglyConnectedComponents {
    private final Graph graph;
    private int[] order;
    private int[] lowlink;
    private int[] stack;
    private int stack_pos;
    private int color;
    private boolean[] visited;
    private int index = 0;
    private int vertexCount;
    private int[] condensed;
    private IntSet next;

    private StronglyConnectedComponents(Graph graph) {
        this.graph = graph;
        vertexCount = graph.vertexCount();
        order = new int[vertexCount];
        visited = new boolean[vertexCount];
        condensed = new int[vertexCount];
    }

    public static int[] tarjan(Graph graph) {
        return new StronglyConnectedComponents(graph).tarjan();
    }

    private int[] tarjan(){
        lowlink = new int[vertexCount];
        stack = new int[vertexCount];
        index  = 1;
        for(int i=0;i<vertexCount;i++){
            if(order[i]==0){
                calculate_tarjan(i);
            }
        }
        return condensed;
    }

    private void calculate_tarjan(int node){
        visited[node] = true;
        stack[stack_pos++] = node;
        lowlink[node] = index;
        order[node] = index;
        index++;
        int edge = graph.firstOutbound(node);
        while(edge!=-1){
            int next = graph.destination(edge);
            if(visited[next]) {
                lowlink[node] = Math.min(lowlink[node],order[next]);
            }
            else if(order[next]==0){
                calculate_tarjan(next);
                lowlink[node] = Math.min(lowlink[node],lowlink[next]);
            }
            edge=graph.nextOutbound(edge);
        }
        if(lowlink[node] == order[node]){
            int current;
            do{
                current =  stack[stack_pos-1];
                stack_pos--;
                condensed[current] = color;
                visited[current] = false;
            } while (current!=node);
            color++;
        }
    }


    public static Pair<int[], Graph> kosaraju(Graph graph) {
        return new StronglyConnectedComponents(graph).kosaraju();
    }

    private Pair<int[], Graph> kosaraju() {
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                firstDFS(i);
            }
        }
        Arrays.fill(visited, false);
        Graph result = new Graph(0);
        index = 0;
        for (int i = vertexCount - 1; i >= 0; i--) {
            if (!visited[order[i]]) {
                next = new IntHashSet();
                secondDFS(order[i]);
                result.addVertices(1);
                for (int set : next.toArray()) {
                    result.addSimpleEdge(set, index);
                }
                index++;
            }
        }
        return Pair.makePair(condensed, result);
    }

    private void secondDFS(int vertexID) {
        if (visited[vertexID]) {
            if (condensed[vertexID] != index) {
                next.add(condensed[vertexID]);
            }
            return;
        }
        condensed[vertexID] = index;
        visited[vertexID] = true;
        int edgeID = graph.firstInbound(vertexID);
        while (edgeID != -1) {
            secondDFS(graph.source(edgeID));
            edgeID = graph.nextInbound(edgeID);
        }
    }

    private void firstDFS(int vertexID) {
        if (visited[vertexID]) {
            return;
        }
        visited[vertexID] = true;
        int edgeID = graph.firstOutbound(vertexID);
        while (edgeID != -1) {
            firstDFS(graph.destination(edgeID));
            edgeID = graph.nextOutbound(edgeID);
        }
        order[index++] = vertexID;
    }
}
