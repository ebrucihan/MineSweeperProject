import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int row, column;

        // Kullanıcıdan satır ve sütun sayılarını alma

        do {
            System.out.print("Satır Sayısı:");
            row = scan.nextInt();
            System.out.print("Sütun Sayısı: ");
            column = scan.nextInt();
            System.out.println("Mayınların Konumu: ");


            // Girilen boyutların geçerliliğini kontrol etme
            if (row < 2 || column < 2) {
                System.out.println("Minimum boyut 2x2 olmalıdır! Lütfen tekrar giriniz.");
            }
        } while (row < 2 || column < 2);


        // Mayın tarlası nesnesini oluşturma ve oyunu başlatma

        MineSweeper mine = new MineSweeper(row, column);
        mine.run();
    }
}
