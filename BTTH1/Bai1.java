import java.util.Random;
import java.util.Scanner;

class Bai1 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            System.out.print("Nhap ban kinh r: ");
            double r = scanner.nextDouble();

            int tongDiem = 1000000;
            int diemTrongTron = 0;

            for (int i = 0; i < tongDiem; i++) {
                double x = -r + 2 * r * random.nextDouble();
                double y = -r + 2 * r * random.nextDouble();

                if (x * x + y * y <= r * r) {
                    diemTrongTron++;
                }
            }

            double dienTich = ((double) diemTrongTron / tongDiem) * (2 * r) * (2 * r);

            System.out.println("Dien tich xap xi cua hinh tron: " + dienTich);
        }
    }
}