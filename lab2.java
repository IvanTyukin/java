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
            int m = Integer.parseInt("11111111",2) - Integer.parseInt(Integer.toBinaryString(255 >> k),2);
            for (int i = 0 ; i < n ; i++) { // k -количество разрядов
                testArray[i][3] = m & ipArray[i][3];
            }
            
            int p = 1;
            for (int t = 0 ; t < n ; t++) {
                
                for (int f = t+1 ; f < n ; f++) {
                    if (testArray[t][3] == testArray[f][3]) {
                        System.out.print("Подсеть" + p + ": " + ipArray[0][0] + "." + ipArray[0][1] + "." + ipArray[0][2] + "." + testArray[t][3] + " Маска: " +
                                    255 + "." + 255 + "." + 255 + "." + m + " входят адреса ");
                        System.out.print(ipArray[t][0] + "." + ipArray[t][1] + "." + ipArray[t][2] + "." + ipArray[t][3] + "," +
                                        ipArray[f][0] + "." + ipArray[f][1] + "." + ipArray[f][2] + "." + ipArray[f][3]);
                    }
                }
                p += 1;
            }

        }


        
    }
}
