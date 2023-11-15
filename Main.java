import java.util.Scanner;

/**
 * main
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // array and variables for items
        int latestItems = 0;
        int jumlahMasuk;
        String items[][] = new String[20][4];
        items[0][0] = "Ayam Bakar"; // nama item
        items[0][1] = "15000"; // harga
        items[0][2] = "40"; // stok
        items[0][3] = "10"; // diskon %

        items[1][0] = "Es teh"; // nama item
        items[1][1] = "3000"; // harga
        items[1][2] = "50"; // stok
        items[1][3] = "10"; // diskon %

        // array and variables for users
        int latestUsers = 0;
        int user_id = 0;
        String users[][] = new String[20][3];
        users[0][0] = "haikal";
        users[0][1] = "123";
        users[1][0] = "irsyad";
        users[1][1] = "456";
        users[2][0] = "esa";
        users[2][1] = "789";

        // array and variables for orders
        int latestOrders = 0;
        int memberDiskon = 10; // %
        String orders[][] = new String[20][9];
        orders[0][0] = "Adi"; // nama pemesan
        orders[0][1] = "Irsyad"; // nama kasir
        orders[0][2] = "150000"; // subtotal
        orders[0][3] = "50000"; // total diskon
        orders[0][4] = "100000"; // total
        orders[0][5] = "100000"; // jumlah pembayaran
        orders[0][6] = "0"; // jumlah kembalian
        orders[0][7] = "Completed"; // status pembayaran
        orders[0][8] = "member"; // status membership

        // array and variables for order details
        int latestOrder_details = 0;
        String order_details[][] = new String[100][7];
        order_details[0][0] = "0"; // id
        order_details[0][1] = "Ayam Bakar"; // nama item
        order_details[0][2] = "10"; // jumlah beli
        order_details[0][3] = "15000"; // harga item
        order_details[0][4] = "150000"; // subtotal per item
        order_details[0][5] = "50000"; // total diskon per item
        order_details[0][6] = "100000"; // total per item

        boolean session = true, access = false, ordering, stocking, memberValid;
        String inputUsername, inputPassword, inputUsernameHistory;
        int mainChoice, orderChoice, stockChoice, removeItemChoice, paymentChoice, historyChoice;
        char isMember;

        while (session == true) {
            // login
            while (access == false) {
                System.out.println("╔══════════════════════════════╗");
                System.out.println("║        SILAHKAN LOGIN        ║");
                System.out.println("╚══════════════════════════════╝");

                System.out.print("Input username : ");
                inputUsername = sc.nextLine();
                System.out.print("Input password : ");
                inputPassword = sc.nextLine();

                for (int i = 0; i < users.length; i++) {
                    if (inputUsername.equals(users[i][0]) && inputPassword.equals(users[i][1])) {
                        user_id = i;
                        System.out.println();
                        System.out.println("Login berhasil!");
                        access = true;
                        break;
                    }
                }

                if (access != true) {
                    System.out.println("\u001B[31mUsername dan password salah!");
                    System.out.println("Silahkan coba lagi!\n\u001B[0m");
                }

            }

            for (int i = 0; i < items.length; i++) {
                if (items[i][0] == null) {
                    latestItems = i;
                    break;
                }
            }

            // menu
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║       Selamat Datang di Cafe The Orange!       ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("[1] Buat Pesanan");
            System.out.println("[null] Manajemen Item Menu");
            System.out.println("[2] Manajemen Stok");
            System.out.println("[null] Manajemen Diskon");
            System.out.println("[3] Lihat Riwayat Penjualan");
            System.out.println("[4] Lihat Laporan Pendapatan");
            System.out.println("[5] Keluar dari Program");
            System.out.print("Masukkan pilihan Anda: ");
            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                // Membuat order
                case 1:

                    // mencari baris yang kosong di orders
                    for (int i = 0; i < orders.length; i++) {
                        if (orders[i][0] == null) {
                            latestOrders = i;
                            break;
                        }
                    }

                    // nama kasir
                    orders[latestOrders][1] = users[user_id][0];

                    // status pembayaran
                    orders[latestOrders][7] = "incomplete";

                    ordering = true;
                    while (ordering == true) {

                        // mencari baris yang kosong di detail order
                        for (int i = 0; i < order_details.length; i++) {
                            if (order_details[i][0] == null) {
                                latestOrder_details = i;
                                break;
                            }
                        }

                        System.out.println("╔═════════════════════════════════════════════════╗");
                        System.out.println("║              Cafe The Orange Menu!              ║");
                        System.out.println("╚═════════════════════════════════════════════════╝");
                        if (orders[latestOrders][0] == null) {
                            System.out.print("Nama Pembeli : ");
                            orders[latestOrders][0] = sc.nextLine();
                        } else {
                            System.out.print("Nama Pembeli : " + orders[latestOrders][0]);
                        }
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.println("Daftar menu :");

                        // looping menu
                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null) {
                                System.out.println("[" + (i + 1) + "] Order " + items[i][0] + " (" + items[i][2]
                                        + ")    --  Rp " + items[i][1]);
                            }
                        }

                        System.out.println("[" + items.length + "] to cancel an item");
                        System.out.println("[" + (items.length + 1) + "] to finish order");
                        System.out.println("[" + (items.length + 2) + "] to exit\n");
                        System.out.println("List pembelian :");

                        // looping daftar pesanan
                        for (int i = 0; i < order_details.length; i++) {
                            if (order_details[i][0] != null
                                    && order_details[i][0].equals(Integer.toString(latestOrders))) {
                                System.out.println(i + "> " + order_details[i][1] + " (" + order_details[i][2] +
                                        ")    Rp" + order_details[i][4]);
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
                        orders[latestOrders][4] = "0"; // to reset the value after each item added
                        orders[latestOrders][4] = Integer.toString(Integer.parseInt(orders[latestOrders][2])
                                - Integer.parseInt(orders[latestOrders][3]));

                        System.out.println("Total : Rp " + orders[latestOrders][4] + "\n");
                        System.out.print("Choice : ");

                        orderChoice = sc.nextInt();
                        sc.nextLine();

                        if (orderChoice < items.length && items[orderChoice - 1][0] != null) {

                            // id
                            order_details[latestOrder_details][0] = Integer.toString(latestOrders);

                            // nama
                            order_details[latestOrder_details][1] = items[orderChoice - 1][0];

                            // jumlah
                            System.out.print("Beli Berapa PCS " + items[orderChoice - 1][0] + " ? : ");
                            order_details[latestOrder_details][2] = Integer.toString(sc.nextInt());
                            sc.nextLine();

                            // harga
                            order_details[latestOrder_details][3] = items[orderChoice - 1][1];

                            // subtotal
                            order_details[latestOrder_details][4] = Integer.toString(Integer
                                    .parseInt(order_details[latestOrder_details][3])
                                    * Integer.parseInt(order_details[latestOrder_details][2]));

                            // total diskon
                            order_details[latestOrder_details][5] = "0";

                            // total
                            order_details[latestOrder_details][6] = Integer
                                    .toString(Integer.parseInt(order_details[latestOrder_details][4])
                                            - Integer.parseInt(order_details[latestOrder_details][5]));

                            // pengurangan stok
                            items[orderChoice - 1][2] = Integer.toString(Integer.parseInt(items[orderChoice - 1][2])
                                    - Integer.parseInt(order_details[latestOrder_details][2]));

                            continue;

                        } else if (orderChoice == items.length) {
                            System.out.println("Pilih item yang ingin di cancel!");
                            System.out.println("List pembelian :");

                            // looping daftar pesanan
                            for (int i = 0; i < order_details.length; i++) {
                                if (order_details[i][0] != null
                                        && order_details[i][0].equals(Integer.toString(latestOrders))) {
                                    System.out.println(i + "> " + order_details[i][1] + " (" + order_details[i][2] +
                                            ")    Rp" + order_details[i][4]);
                                }
                            }

                            System.out.print("Choice : ");
                            removeItemChoice = sc.nextInt();

                            if (removeItemChoice < order_details.length && order_details[removeItemChoice][0]
                                    .equals(Integer.toString(latestOrders))) {

                                // pengembalian stok
                                for (int i = 0; i < items.length; i++) {
                                    if (items[i][0].equals(order_details[removeItemChoice][1])) {
                                        items[i][2] = Integer.toString(Integer.parseInt(items[i][2])
                                                + Integer.parseInt(order_details[removeItemChoice][2]));
                                        break;
                                    }
                                }

                                // membuat satu baris menjadi null untuk dicancel
                                for (int i = 0; i < order_details[removeItemChoice].length; i++) {
                                    if (order_details[removeItemChoice][0] != null
                                            && order_details[removeItemChoice][0]
                                                    .equals(Integer.toString(latestOrders))) {
                                        order_details[removeItemChoice][i] = order_details[removeItemChoice + 1][i];
                                        order_details[removeItemChoice + 1][i] = null;
                                    }
                                }
                            }

                        } else if (orderChoice == items.length + 1) {

                            do {
                                memberValid = false;
                                System.out.println();
                                System.out.print("Apakah punya kartu member? (y/t) :");
                                isMember = sc.next().charAt(0);

                                if (isMember == 'y' || isMember == 'Y') {
                                    orders[latestOrders][8] = "member";
                                    memberValid = true;
                                } else if (isMember == 't' || isMember == 'T') {
                                    orders[latestOrders][8] = "not member";
                                    memberValid = true;
                                } else {
                                    System.out.println("Input invalid!");
                                    System.out.println("Please try again!");
                                }
                            } while (memberValid != true);

                            System.out.println();
                            System.out.println("╔══════════════════════════════════════╗");
                            System.out.println("║           Payment methods            ║");
                            System.out.println("╚══════════════════════════════════════╝");
                            System.out.println("[1] Cash");
                            System.out.println("[2] Debit Card");
                            System.out.println("[3] Credit Card");
                            System.out.print("Choose your payment method : ");
                            paymentChoice = sc.nextInt();
                            sc.nextLine();

                            // !! other payment methods
                            switch (paymentChoice) {
                                case 1:
                                    System.out.println("Cafe The Orange");
                                    System.out.println("---------------------------");
                                    System.out.println("Order ke-" + latestOrders);
                                    System.out.println("Pelanggan : " + orders[latestOrders][0]);
                                    System.out.println("Kasir : " + orders[latestOrders][1]);
                                    System.out.println("---------------------------");
                                    System.out.println("Daftar pesanan : ");

                                    // looping daftar pesanan
                                    for (int i = 0; i < order_details.length; i++) {
                                        if (order_details[i][0] != null
                                                && order_details[i][0].equals(Integer.toString(latestOrders))) {
                                            System.out.println(
                                                    i + "> " + order_details[i][1] + " (" + order_details[i][2] +
                                                            ")");
                                        }
                                    }

                                    System.out.println("Cash payment : ");
                                    System.out.println("Total Awal : " + orders[latestOrders][4]);
                                    if (orders[latestOrders][8].equals("member")) {
                                        orders[latestOrders][4] = Integer
                                                .toString(Integer.parseInt(orders[latestOrders][4])
                                                        - (Integer.parseInt(orders[latestOrders][4]) * memberDiskon
                                                                / 100));
                                        System.out.println("Member discount " + memberDiskon + "% : "
                                                + orders[latestOrders][4]);
                                    }
                                    System.out.println("---------------------------");
                                    System.out.println("Total : " + orders[latestOrders][4]);
                                    System.out.print("Bayar : ");
                                    orders[latestOrders][5] = Integer.toString(sc.nextInt());

                                    if (Integer.parseInt(orders[latestOrders][5]) >= Integer
                                            .parseInt(orders[latestOrders][4])) {
                                        orders[latestOrders][6] = Integer
                                                .toString(Integer.parseInt(orders[latestOrders][5])
                                                        - Integer.parseInt(orders[latestOrders][4]));
                                        System.out.println("Kembalian : " + orders[latestOrders][6]);

                                        break;
                                    } else {
                                        System.out.println("Uang tidak cukup!");
                                        System.out.println("Proses Pembayaran gagal!");
                                        System.out.println();
                                        continue;
                                    }

                                default:
                                    System.out.println("Invalid method!");
                                    continue;
                            }

                            ordering = false;
                            break;

                        } else if (orderChoice == items.length + 2) {

                            // menghapus seluruh order item dan order
                            for (int i = 0; i < order_details.length; i++) {
                                if (order_details[i][0] != null
                                        && Integer.parseInt(order_details[i][0]) == latestOrders) {
                                    for (int j = 0; j < order_details[i].length; j++) {
                                        order_details[i][j] = null;
                                    }
                                }
                            }

                            // menghapus order
                            for (int i = 0; i < orders[latestOrders].length; i++) {
                                orders[latestOrders][i] = null;
                            }

                            System.out.println("Ordering has been cancelled!");

                            break;

                        } else {
                            System.out.println("Menu tersebut tidak ada!");
                            System.out.println("Silahkan coba lagi!\n");
                            continue;

                        }
                    }

                    continue;

                case 2:
                    stocking = true;

                    while (stocking == true) {
                        System.out.println();
                        System.out.println("╔══════════════════════════════════════════════╗");
                        System.out.println("║              Cafe the orange stok            ║");
                        System.out.println("╚══════════════════════════════════════════════╝");
                        for (int i = 0; i < items.length; i++) {
                            if (items[i][0] != null) {
                                System.out.println(
                                        "[" + (i + 1) + "] " + " tambah stok " + items[i][0] + " (" + items[i][2]
                                                + ")");
                            }
                        }

                        System.out.println("[" + items.length + "] to exit\n");

                        System.out.print("Pilihan : ");

                        stockChoice = sc.nextInt();

                        System.out.println();

                        if (items[stockChoice - 1][0] != null) {
                            System.out.print("Tambah stok " + items[stockChoice - 1][0] + " : ");
                            jumlahMasuk = sc.nextInt();
                            items[stockChoice - 1][2] = Integer
                                    .toString(Integer.parseInt(items[stockChoice - 1][2]) + jumlahMasuk);
                        } else if (stockChoice == items.length) {
                            System.out.println("Thank you!");
                            stocking = false;
                            break;
                        } else {
                            System.out.println("Item tersebut tidak tersedia!");
                            System.out.println("Silahkan coba lagi");
                            continue;
                        }

                        continue;
                    }

                    break;

                case 3:
                    // ! history feature
                    System.out.println();
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println("║            Sales History             ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    System.out.println("[1] All time");
                    System.out.println("[2] Sort by cashier");
                    System.out.println("[3] Sort by date");
                    System.out.print("Your choice : ");
                    historyChoice = sc.nextInt();
                    sc.nextLine();

                    switch (historyChoice) {
                        case 1:
                            System.out.println("History by all time :");
                            System.out.println();
                            for (int i = 0; i < orders.length; i++) {
                                if (orders[i][0] != null) {
                                    System.out.println("=========================");
                                    System.out.println("Order ke-" + i);
                                    System.out.println("-------------------------");
                                    System.out.println("Cashier : " + orders[i][1]);
                                    System.out.println("Pelanggan : " + orders[i][0]);
                                    System.out.println("-------------------------");

                                    for (int j = 0; j < order_details.length; j++) {
                                        if (order_details[j][0] != null && Integer.parseInt(order_details[j][0]) == i) {
                                            System.out
                                                    .println(j + "> " + order_details[j][1] + " -- "
                                                            + order_details[0][2]);
                                        }
                                    }

                                    System.out.println("-------------------------");
                                    System.out.println("Subtotal : " + orders[i][2]);
                                    System.out.println("Total Diskon : " + orders[i][3]);
                                    System.out.println("Total : " + orders[i][4]);
                                    System.out.println("Pembayaran : " + orders[i][5]);
                                    System.out.println("Kembalian : " + orders[i][6]);

                                    System.out.println("=========================");
                                    System.out.println();
                                }
                            }
                            break;

                        case 2:
                            System.out.print("Masukkan username kasir : ");
                            inputUsernameHistory = sc.nextLine();

                            System.out.println("History by cashier :");
                            System.out.println();
                            for (int i = 0; i < orders.length; i++) {
                                if (orders[i][0] != null && orders[i][1].equals(inputUsernameHistory)) {

                                    System.out.println("Order ke-" + i);
                                    System.out.println("--------------------");
                                    System.out.println("Cashier : " + orders[i][1]);
                                    System.out.println("Pelanggan : " + orders[i][0]);
                                    System.out.println("--------------------");

                                    for (int j = 0; j < order_details.length; j++) {
                                        if (order_details[j][0] != null && Integer.parseInt(order_details[j][0]) == i) {
                                            System.out
                                                    .println(j + "> " + order_details[j][1] + " -- "
                                                            + order_details[0][2]);
                                        }
                                    }

                                    System.out.println("=========================");
                                    System.out.println();
                                }
                            }
                            break;
                        default:
                            break;
                    }

                    break;
                case 4:
                    System.out.println();
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println("║         Laporan Pendapatan           ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    System.out.println("[1] Laporan pendapatan per tanggal");
                    System.out.println("[2] Laporan pendapatan all time");
                    System.out.print("Your choice : ");
                    int reportChoice = sc.nextInt();
                    sc.nextLine();

                    switch (reportChoice) {
                        case 1:
                            System.out.print("Masukkan tanggal: ");
                            String date = sc.nextLine();
                            System.out.println("Laporan pendapatan per tanggal: " + date);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Laporan pendapatan all time: ");
                            System.out.println();
                            double totalIncome = -100000;
                            for (int i = 0; i < orders.length; i++) {
                                if (orders[i][0] != null) {
                                    totalIncome += Double.parseDouble(orders[i][5]);
                                }
                            }
                            System.out.println("Total Pendapatan Rp: " + totalIncome);
                            System.out.println("=========================");
                            System.out.println();
                            break;

                        default:
                            System.out.println("Invalid choice!");
                            System.out.println("Please try again!");
                            break;
                    }
                    break;
                case 5:
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