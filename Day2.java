import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Day2 { 
    static int partOne(List<String> input) { 
        int depth = 0;
        int pos = 0;

        for (String e: input) { 
            int units = Integer.parseInt(e.substring(e.length()-1));
            //System.out.println(e);
            //System.out.printf("String: %s, Units: %d%n", e, units);

            if (e.startsWith("forward")) { 
                pos += units;
            } else if (e.startsWith("up")) { 
                depth -= units;
            } else { 
                depth += units;
            }
        }        
        
        System.out.printf("pos: %d, depth: %d%n", pos, depth);
        return pos * depth;
    }

    static int partTwo(List<String> input) {  
        int depth = 0;
        int pos = 0;
        int aim = 0;

        for (String e: input) { 
            int units = Integer.parseInt(e.substring(e.length()-1));

            if (e.startsWith("forward")) { 
                pos += units;
                depth += aim * units;
            } else if (e.startsWith("up")) { 
                aim -= units;
            } else { 
                aim += units;
            }
        }        
        
        System.out.printf("pos: %d, depth: %d%n", pos, depth);
        return pos * depth;
    }

    public static void main(String[] args) { 
        List<String> input = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) { 
            input.add(sc.nextLine());
        }
        
        System.out.println(partTwo(input));
    }
}
