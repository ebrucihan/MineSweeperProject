import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    // Oyunun satır, sütun ve alan boyutu
    int rowNumber, colNumber, size;
    // Mayınların konumunu temsil eden harita
    char[][] map;
    // Oyun tahtası
    char[][] board;
    // Oyun durumu
    boolean game = true;
    // İlk hamle kontrolü
    boolean firstMove = true;



    // Rastgele sayı üretme ve kullanıcı girişi oluşturma
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);


    MineSweeper(int row, int col) {
        // Satır ve sütun sayılarını atama
        this.rowNumber = row;
        this.colNumber = col;
        // Mayın haritası ve oyun tahtasını oluşturma
        this.map = new char[row][col];
        this.board = new char[row][col];
        // Alan boyutunu hesaplama
        this.size = rowNumber * colNumber;
        // Oyun hazırlığı
        prepareGame();
    }



    // Oyunu çalıştırma metodu
    public void run() {
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");
        while (game) {
            System.out.println("===========================");
            print(board);
            System.out.print("Satır Giriniz : ");
            int row = scan.nextInt();
            System.out.print("Sütun Giriniz : ");
            int col = scan.nextInt();

            // Geçerli hamle olup olmadığını kontrol etme
            if (!isValidMove(row, col)) {
                continue;
            }

            // Mayına denk gelip gelmediğini kontrol etme
            if (map[row][col] == '*') {
                game = false;
                System.out.println("===========================\nGame Over!!");
                break;
            } else {
                // İlk hamlede olup olmadığını kontrol etme
                if (firstMove) {
                    openEmptyCoordinates(row, col);
                    firstMove = false;
                } else {
                    board[row][col] = map[row][col];
                    adjacentEmptyCoordinates(row, col);
                }
                // Kazanma kontrolü
                if (checkWin()) {
                    System.out.println("===========================\nOyunu Kazandınız !");
                    break;
                }
            }
        }
    }



    // Boş koordinatları açma metodu
    private void openEmptyCoordinates(int row, int col) {
        if (map[row][col] != '-') {
            return;
        }
        int count = countAdjacentMines(row, col);
        board[row][col] = (char) (count + '0');
        if (count == 0) {
            adjacentEmptyCoordinates(row, col);
        }
    }


    // Komşu boş koordinatları açma metodu
    private void adjacentEmptyCoordinates(int row, int col) {
        if (map[row][col] != '*') {
            int count = countAdjacentMines(row, col);
            board[row][col] = (char) (count + '0');
        }
    }



    // Hamle geçerliliğini kontrol etme metodu
    private boolean isValidMove(int row, int col) {
        if (row >= 0 && row < rowNumber && col >= 0 && col < colNumber) {
            if (board[row][col] == '-') {
                return true;
            } else {
                System.out.println("Bu koordinat daha önce seçildi, başka bir koordinat girin.");
                return false;
            }
        } else {
            System.out.println("Geçersiz koordinat! Lütfen tekrar deneyin.");
            return false;
        }
    }



    // Oyun hazırlık metodu
    private void prepareGame() {
        int mineCount = size / 4;
        int count = 0;
        while (count < mineCount) {
            int randomRow = rand.nextInt(rowNumber);
            int randomCol = rand.nextInt(colNumber);
            if (map[randomRow][randomCol] != '*') {
                map[randomRow][randomCol] = '*';
                count++;
            }
        }

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (map[i][j] != '*') {
                    map[i][j] = '-';
                }
                board[i][j] = '-';
            }
        }

        print(map);
    }



    // Komşu mayınları sayma metodu
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rowNumber && j >= 0 && j < colNumber && map[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }


    // Kazanma durumunu kontrol etme metodu
    private boolean checkWin() {
        int openCoordinates = 0;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (board[i][j] != '-' && board[i][j] != '*') {
                    openCoordinates++;
                }
            }
        }
        return openCoordinates == (size - (size / 4));
    }


    // Tahtayı yazdırma metodu
    public void print(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

