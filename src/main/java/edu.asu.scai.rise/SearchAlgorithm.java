package edu.asu.scai.rise;

import java.util.List;
import java.util.Map;

public interface SearchAlgorithm {
    Path search(String src, String dst, Map<String, List<String>> map);
}
