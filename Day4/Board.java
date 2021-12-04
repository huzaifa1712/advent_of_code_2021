import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;

// use hash table to update marked and check for win in O(1) time (if no duplicates) 
class Board { 
    private final HashMap<Integer, Pair<ArrayList<Integer>, Boolean>> map; // ArrayList<Integer> of indices w that num, in case duplicates
    private final int[] marked; // 0 to 4: rows, 5 to 9: cols. first to n 
    private boolean won;
    private int score;
    private int n;

    Board(ArrayList<ArrayList<Integer>> input) { 
        n = input.size();
        won = false;
        score = -1;
        marked = new int[n * 2];
        
        map = new HashMap<Integer, Pair<ArrayList<Integer>, Boolean>>();

        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < n; j++) { 
                int index = n * i + j;
                int num = input.get(i).get(j);

                if (map.containsKey(num)) { 
                    map.get(num).first.add(index);
                } else { 
                    Pair<ArrayList<Integer>,Boolean> p = new Pair<ArrayList<Integer>,Boolean>(
                            new ArrayList<Integer>(List.of(index)), false);

                    map.put(num, p);
                }
            }
        }

    }

    boolean hasWon() { 
        return won;
    }

    private int unmarkedSum() { 
       int sum = 0;
       for (Map.Entry<Integer, Pair<ArrayList<Integer>, Boolean>> m: map.entrySet()) { 
            if (m.getValue().second == false) { 
                sum += m.getValue().first.size() * m.getKey();
            }
       }

       return sum;
    }
    
    // look up entry with num, mark as true (if not already true)
    // calculate row and col for each index in indices, update appropriate index in marked 
    // unless: if updated sum == n, won = true and score = num * sum of unmarked
    Board mark(int num) { 
        if (this.won || !map.containsKey(num)) return this;
        
        Pair<ArrayList<Integer>, Boolean> p = map.get(num);

        if (p.second == true) return this;

        p.second = true;
        boolean won = false;

        for (int i: p.first) { 
            int rowIndex = i / n;
            int colIndex = (i % n) + n;

            marked[rowIndex] += 1;
            marked[colIndex] += 1;

            if (marked[rowIndex] == n || marked[colIndex] == n) { 
                won = true;
            }
        }

        if (won) { 
            this.won = true;
            this.score = num * this.unmarkedSum();
        }

        return this;

    }

    int getScore() { return score; }

    @Override
    public String toString() { 
        return map.toString() + "\n" + Arrays.toString(marked);
    }
}
