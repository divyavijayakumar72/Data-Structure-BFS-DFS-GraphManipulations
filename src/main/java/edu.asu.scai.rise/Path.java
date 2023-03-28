package edu.asu.scai.rise;

import java.util.List;

public class Path {
    private List<String> nodes;

    public Path(List<String> nodes) {
        this.nodes = nodes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            sb.append(nodes.get(i).toString());
            if (i != nodes.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}

