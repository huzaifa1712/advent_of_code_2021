import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;

class Day4 { 
    private static int N_SMALL = 27;
    private static int N_BIG = 100;
    private static int BOARD_SIZE = 5;

    static int partOne(List<Integer> input, ArrayList<Board> boards) {  
       // for (int i: input) { 
       //     System.out.printf("%d ", i);
       // }

       // System.out.println();

       // for (Board board: boards) { 
       //     System.out.println(board);
       //     System.out.println("");
       // }
       
       for (int i: input) { 
           Optional<Board> win = boards.stream()
               .filter(b -> b.hasWon())
               .findFirst();

           if (win.isPresent()) { 
               return win.get().getScore();
           }

           boards = boards.stream()
               .map(b -> b.mark(i))
               .collect(Collectors.toCollection(ArrayList::new));
       }

        return 0;
    }


    static int partTwo(List<Integer> input, ArrayList<Board> boards) {  
       // for (int i: input) { 
       //     System.out.printf("%d ", i);
       // }

       // System.out.println();

       // for (Board board: boards) { 
       //     System.out.println(board);
       //     System.out.println("");
       // }
       
       for (int i: input) { 
           ArrayList<Board> win = boards.stream()
               .filter(b -> !b.hasWon())
               .collect(Collectors.toCollection(ArrayList::new));

           if (win.size() == 1) { 
               return win.get(0).getScore() ;
           }

           boards = boards.stream()
               .map(b -> b.mark(i))
               .collect(Collectors.toCollection(ArrayList::new));
       }

        return 0;
    }

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        ArrayList<Board> boards = new ArrayList<Board>(); 
        List<Integer> input = new ArrayList<Integer>();
 
        for (int i = 0; i < N_BIG; i++) { 
            //System.out.printf("%d ", i);
            input.add(sc.nextInt());
            
        }

        while (sc.hasNextInt()) { 
            ArrayList<ArrayList<Integer>> boardInp = new ArrayList<ArrayList<Integer>>();

             for (int i = 0; i < BOARD_SIZE; i++) { 
                ArrayList<Integer> row = new ArrayList<Integer>();
                for (int j = 0; j < BOARD_SIZE; j++) { 
                   row.add(sc.nextInt()); 
                }
    
                boardInp.add(row);
             }

             boards.add(new Board(boardInp));
        }

        System.out.println(partOne(input, boards));

    }
}
