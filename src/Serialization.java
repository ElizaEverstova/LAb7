import java.io.*;
import java.util.Scanner;

class Lake implements Serializable {
    String name;
    String location;
    int area;
}

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in,"cp1251");
        // создается файл на диске
        File f1 = new File("E:\\LAb77_serialization_out1.txt");
        f1.createNewFile();
        File f2 = new File("E:\\LAb77_serialization_out2.txt");
        f2.createNewFile();
               FileOutputStream fos = new FileOutputStream(f1);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println("Введите количество озер => ");
        int count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
                    Lake lake = new Lake();
            System.out.println("Введите информацию о озере №" + (i + 1) + ": ");
            System.out.print("Название озера => ");
            lake.name = sc.nextLine();
            System.out.print("Место расположения => ");
            lake.location = sc.nextLine();
            System.out.print("Площадь => ");
            lake.area = sc.nextInt();
            sc.nextLine();
            oos.writeObject(lake);
        }
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream(f1);
        ObjectInputStream oin = new ObjectInputStream(fis);
        fos = new FileOutputStream(f2);
        oos = new ObjectOutputStream(fos);
        try {
            while (true) {
                              Lake lake = (Lake) oin.readObject();
                if (lake.location.equals("Russia")) {
                    oos.writeObject(lake);
                }
            }
        }
        catch (EOFException e) {

        }

        oin.close();
        oos.flush();
        oos.close();
    }
}
