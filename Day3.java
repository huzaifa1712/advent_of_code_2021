import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Day3 { 
    static double partOne(List<String> input) { 
        int n = input.get(0).length();
        int lines = input.size();

        // count zeroes
        int[] common = new int[n];

        for (String s: input) { 
            for (int i = 0; i < n; i++) { 
                if (s.charAt(i) == '0') { 
                    common[i] += 1;
                }
            }
        } 
        
        double gamma = 0;
        int pow = n - 1;

        for (int num: common) { 
            int bit = (num < lines - num) ? 1 : 0; 
            gamma += Math.pow(2, pow) * bit;
            pow -= 1;
        }
        
        return gamma * (Math.pow(2,n) - gamma -1);
    }

    static long countZeroes(List<String> input, int place) { 
        return input.stream()
            .filter(s -> s.charAt(place) == '0')
            .count();
    }
    
    // for O2 rating
    static char mostCommonBit(List<String> input, int place) { 
        long zeroes = countZeroes(input, place);
        return (zeroes <= input.size() - zeroes) ? '1' : '0';
    }
    
    // for CO2 rating
    static char leastCommonBit(List<String> input, int place) {   
        long zeroes = countZeroes(input, place);
        return (zeroes <= input.size() - zeroes) ? '0' : '1';
    }

    static List<String> filterInput(List<String> input, char crit, int place) { 
        return input.stream()
            .filter(s -> s.charAt(place) == crit)
            .collect(Collectors.toList());
    }

    static int oxygenRating(List<String> input) { 
        int n = input.get(0).length();

        for (int place = 0; place < n; place++) { 
            if (input.size() == 1) break;

            char bit = mostCommonBit(input, place);
            input = filterInput(input, bit, place);
        }

        return Integer.parseInt(input.get(0), 2);
    }

    static int carbonRating(List<String> input) {  
        int n = input.get(0).length();

        for (int place = 0; place < n; place++) { 
            if (input.size() == 1) break;

            char bit = leastCommonBit(input, place);
            input = filterInput(input, bit, place);
        }

        return Integer.parseInt(input.get(0), 2);
    }

    static int partTwo(List<String> input) { 
        return oxygenRating(input) * carbonRating(input);
    }
    
    public static void main(String[] args) { 
        List<String> input = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) { 
            input.add(sc.next());
        }
        
        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }
}
