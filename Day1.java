import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Day1 { 

    static int partTwo(List<Integer> lst) { 
        int sum = lst.get(0) + lst.get(1) + lst.get(2);
        int count = 0;

        for (int i = 1; i <= lst.size() - 3; i++) { 
            int newSum = lst.get(i) + lst.get(i + 1) + lst.get(i + 2);
            if (newSum > sum) { 
                count++;
            }

            sum = newSum;
        }

        return count;
    }

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        List<Integer> lst = new ArrayList<Integer>();

        while(sc.hasNextInt()) { 
            lst.add(sc.nextInt());
        }

       // int count = 0;

       // for (int i = 1; i < lst.size(); i++) { 
       //     if (lst.get(i) > lst.get(i - 1)) { 
       //         count++;
       //     } 
       // }
       
       System.out.println(partTwo(lst));

    }
}
