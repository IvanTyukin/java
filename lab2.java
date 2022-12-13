import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // количество IP адресов


        Scanner in = new Scanner(System.in);
        int[][] ipArray = new int[n][4];
        String array[];
        for (int i = 0; i < n; i++) {
            String ip = in.nextLine();
            array = ip.split("\\.");
            for (int j = 0; j < 4; j++) {
                ipArray[i][j] = Integer.parseInt(array[j]);
            }
        }

        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(ipArray[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
