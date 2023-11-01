import java.util.Scanner;

/**
 * main
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // array for items
        int latestItems = 0;
        String items[][] = new String[20][4];
        items[0][0] = "Ayam Bakar"; // nama item
        items[0][1] = "15000"; // harga
        items[0][2] = "40"; // stok
        items[0][3] = "10"; // diskon %

        items[1][0] = "Es teh"; // nama item
        items[1][1] = "3000"; // harga
        items[1][2] = "50"; // stok
        items[1][3] = "10"; // diskon %

        // array for users
        String[] username = { "haikal", "irsyad", "esa" };
        String[] password = { "123", "456", "789" };

        // array for orders
        int latestOrders = 0;
        String orders[][] = new String[20][5];
        orders[0][0] = "Adi"; // nama pemesan
        orders[0][1] = "Irsyad"; // nama kasir
        orders[0][2] = "150000"; // subtotal
        orders[0][3] = "50000"; // total diskon
        orders[0][4] = "100000"; // total

        // array for order details
        int latestOrder_details = 0;
        String order_details[][] = new String[100][7];
        order_details[0][0] = "0"; // id
        order_details[0][1] = "Ayam Bakar"; // nama item
        order_details[0][2] = "10"; // jumlah beli
        order_details[0][3] = "15000"; // harga item
        order_details[0][4] = "150000"; // subtotal per item
        order_details[0][5] = "50000"; // total diskon per item
        order_details[0][6] = "100000"; // total per item

        boolean session = true, access = false, ordering, stocking;
        String inputUsername, inputPassword;
        int choice1, choice2, choice3;

        String item[] = { "Ayam Bakar", "Es Teh" };
        int stok[] = { 40, 50 };

        while (session == true) {
            // login
            while (access == false) {
                System.out.println("LOGIN");
                System.out.print("Input username : ");
                inputUsername = sc.nextLine();
                System.out.print("Input password : ");
                inputPassword = sc.nextLine();

                // TODO: ganti menggunakan array username dan password

                for (int i = 0; i < username.length; i++) {
                    if (inputUsername.equals(username[i]) && inputPassword.equals(password[i])) {
                        System.out.println();
                        System.out.println("login berhasil");
                        access = true;
                        break;
                    }
                }

            }

            for (int i = 0; i < items.length; i++) {
                if (items[i][0] == null) {
                    latestItems = i;
                    break;
                }
            }

            // menu
            System.out.println();
            System.out.println("Cafe The Orange!");
            System.out.println("Choose 1 to make an order");
            System.out.println("Choose 2 to add stok");
            System.out.println("Choose 3 to exit the program");
            System.out.print("Input your answer : ");
            choice1 = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice1) {
                case 1:

                    for (int i = 0; i < orders.length; i++) {
                        if (orders[i][0] == null) {
                            latestOrders = i;
                            break;
                        }
                    }
                    System.out.println(latestOrders);

                    System.out.print("Nama Pembeli : ");
                    orders[latestOrders][0] = sc.nextLine();
                    orders[latestOrders][1] = "Irsyad";

                    ordering = true;
                    while (ordering == true) {

                        for (int i = 0; i < order_details.length; i++) {
                            if (order_details[i][0] == null) {
                                latestOrder_details = i;
                                break;
                            }
                        }

                        System.out.println("Cafe The Orange Menu!");

                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null) {
                                System.out.println("[" + (i + 1) + "] Order " + items[i][0] + " (" + items[i][2] + ")");
                            }
                        }

                        System.out.println("[" + items.length + "] to exit\n");
                        System.out.println("List pembelian :");

                        for (int i = 0; i < order_details.length; i++) {
                            if (order_details[i][0] != null
                                    && order_details[i][0].equals(Integer.toString(latestOrders))) {
                                System.out.print(order_details[i][0]);
                                System.out.println("> " + order_details[i][1] + " (" + order_details[i][2] +
                                        ")");
                            }
                        }
                        System.out.println();

                        // subtotal
                        orders[latestOrders][2] = "0";
                        for (int i = 0; i < order_details.length; i++) {
                            if (order_details[i][0] != null
                                    && order_details[i][0].equals(Integer.toString(latestOrders))) {
                                orders[latestOrders][2] = Integer.toString(Integer.parseInt(orders[latestOrders][2])
                                        + Integer.parseInt(order_details[i][4]));
                            }
                        }

                        // total diskon
                        orders[latestOrders][3] = "0";

                        // total
                        orders[latestOrders][4] = "0";
                        orders[latestOrders][4] = Integer.toString(Integer.parseInt(orders[latestOrders][2])
                                - Integer.parseInt(orders[latestOrders][3]));

                        System.out.println("Total : Rp " + orders[latestOrders][4] + "\n");
                        System.out.print("Choice : ");

                        choice2 = sc.nextInt();

                        if (items[choice2 - 1][0] != null) {

                            order_details[latestOrder_details][0] = Integer.toString(latestOrders); // id

                            order_details[latestOrder_details][1] = items[choice2 - 1][0]; // nama

                            System.out.print("Beli Berapa PCS " + items[choice2 - 1][1] + " ? : ");
                            order_details[latestOrder_details][2] = Integer.toString(sc.nextInt()); // jumlah

                            order_details[latestOrder_details][3] = items[choice2 - 1][1]; // harga

                            order_details[latestOrder_details][4] = Integer.toString(Integer
                                    .parseInt(order_details[latestOrder_details][3])
                                    * Integer.parseInt(order_details[latestOrder_details][2])); // subtotal

                            order_details[latestOrder_details][5] = "0"; // total diskon

                            order_details[latestOrder_details][6] = Integer
                                    .toString(Integer.parseInt(order_details[latestOrder_details][4])
                                            - Integer.parseInt(order_details[latestOrder_details][5])); // total

                            items[choice2 - 1][2] = Integer.toString(Integer.parseInt(items[choice2 - 1][2])
                                    - Integer.parseInt(order_details[latestOrder_details][2])); // pengurangan stok

                            continue;

                        } else if (choice2 == items.length) {

                            System.out.println("Thank you!");
                            System.out.println("Your total : " + orders[latestOrders][4]);
                            ordering = false;
                            break;

                        } else {
                            System.out.println("Menu tersebut tidak ada!");
                            System.out.println("Silahkan coba lagi!");
                            continue;

                        }
                    }

                    continue;

                case 2:
                    stocking = true;

                    while (stocking == true) {
                        System.out.println("Cafe The Orange Stok!");
                        // Todo: Gunakan perhitungan array
                        for (int i = 0; i < item.length; i++) {
                            System.out.println(
                                    "Pilih " + (i + 1) + " untuk tambah stok " + item[i] + " (" + stok[i] + ")");
                        }
                        System.out.println("Pilih 3 untuk selesai");
                        System.out.println("");
                        System.out.print("Pilihan : ");

                        choice3 = sc.nextInt();

                        switch (choice3) {
                            case 1:
                                System.out.print("Tambah stok Ayam Bakar : ");
                                int inputAyamBakar = sc.nextInt();
                                // Todo: Gunakan perhitungan array
                                stok[0] += inputAyamBakar;
                                break;
                            case 2:
                                System.out.print("Tambah stok Es Teh : ");
                                int inputEsTeh = sc.nextInt();
                                // Todo: Gunakan perhitungan array
                                stok[1] += inputEsTeh;
                                break;

                            case 3:
                                System.out.println("Thank you!");
                                stocking = false;
                                break;

                            default:
                                System.out.println("Item tersebut tidak tersedia!");
                                System.out.println("Silahkan coba lagi");
                                break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using this program!");
                    System.out.println("Goodbye ^-^");
                    session = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Your choice does not exist!");
                    System.out.println("Please try again!");
                    break;
            }
        }
    }
}