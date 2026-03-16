import java.util.Random;

class Bai2 {

    public static void main(String[] args) {

        Random random = new Random();

        int tongDiem = 1000000;
        int diemTrongTron = 0;

        for (int i = 0; i < tongDiem; i++) {

            double x = -1 + 2 * random.nextDouble();
            double y = -1 + 2 * random.nextDouble();

            if (x * x + y * y <= 1) {
                diemTrongTron++;
            }
        }

        double pi = 4.0 * diemTrongTron / tongDiem;

        System.out.println("Gia tri xap xi cua PI: " + pi);
    }
}
