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



        int testArray[][] = new int[n][4];

        for (int k = 8; k > 0; k--) {
            for (int i = 0 ; i < n ; i++) { // k -количество разрядов
                int m = Integer.parseInt("11111111",2) - Integer.parseInt(Integer.toBinaryString(255 >> k),2);
                testArray[i][4] = m & ipArray[i][4];
                int p = 1;
                for (int t = 0 ; t < n ; t++) {
                    System.out.print("Подсеть" + p + ": " + ipArray[0][0] + "." + ipArray[0][1] + "." + ipArray[0][2] + "." + m + " Маска: " +
                                        255 + "." + 255 + "." + 255 + "." + testArray[i][4] + " ");
                    for (int f = t+1 ; f < n ; f++) {

                    }
                    p += 1;
                }

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
