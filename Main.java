import java.util.Scanner;

/**
 * main
 */
public class Main {

    // Declarations
    static Scanner sc = new Scanner(System.in);

    // array and variables for items
    static int latestItems = 0;
    static int jumlahMasuk;
    static String items[][];

    // array and variables for users
    static int latestUsers = 0;
    static int user_id = 0;
    static String users[][];

    // array and variables for orders
    static int latestOrders = 0;
    static int memberDiskon = 10; // %
    static String orders[][];

    // array and variables for order details
    static int latestOrder_details = 0;
    static String order_details[][];

    // array and variables for membership
    static String noMembership[][];

    // other variables
    static boolean session = true, access = false, ordering, stocking, memberValid, paying, managingItem;
    static String inputUsername, inputPassword, inputUsernameHistory;
    static int mainChoice, orderChoice, stockChoice, removeItemChoice, paymentChoice, historyChoice, manageItemChoice;
    static char isMember;

    static void Init() {
        items = new String[20][5];
        items[0][0] = "Ayam Bakar"; // nama item
        items[0][1] = "15000"; // harga
        items[0][2] = "40"; // stok
        items[0][3] = "10"; // diskon %
        items[0][4] = "Makanan"; // tipe

        items[1][0] = "Es teh"; // nama item
        items[1][1] = "3000"; // harga
        items[1][2] = "50"; // stok
        items[1][3] = "10"; // diskon %
        items[1][4] = "Minuman"; // tipe

        users = new String[20][3];
        users[0][0] = "haikal";
        users[0][1] = "123";
        users[1][0] = "irsyad";
        users[1][1] = "456";
        users[2][0] = "esa";
        users[2][1] = "789";

        orders = new String[20][11];
        orders[0][0] = "Adi"; // nama pemesan
        orders[0][1] = "Irsyad"; // nama kasir
        orders[0][2] = "150000"; // subtotal
        orders[0][3] = "50000"; // total diskon
        orders[0][4] = "100000"; // total
        orders[0][5] = "100000"; // jumlah pembayaran
        orders[0][6] = "0"; // jumlah kembalian
        orders[0][7] = "Completed"; // status pembayaran
        orders[0][8] = "member"; // status membership
        orders[0][9] = "10"; // diskon membership
        orders[0][10] = "10000"; // total diskon membership

        order_details = new String[100][7];
        order_details[0][0] = "0"; // id
        order_details[0][1] = "Ayam Bakar"; // nama item
        order_details[0][2] = "10"; // jumlah beli
        order_details[0][3] = "15000"; // harga item
        order_details[0][4] = "150000"; // subtotal per item
        order_details[0][5] = "50000"; // total diskon per item
        order_details[0][6] = "100000"; // total per item

        noMembership = new String[100][2];
        noMembership[0][0] = "234172"; // id
        noMembership[0][1] = "Irsyad"; // nama
        noMembership[1][0] = "234173"; // id
        noMembership[1][1] = "Esa"; // nama
        noMembership[2][0] = "234174"; // id
        noMembership[2][1] = "Haikal"; // nama
    }

    static void GetLatestItems() {
        for (int i = 0; i < items.length; i++) {
            if (items[i][0] == null) {
                latestItems = i;
                break;
            }
        }
    }

    static void GetLatestUsers() {
        for (int i = 0; i < users.length; i++) {
            if (users[i][0] == null) {
                latestUsers = i;
                break;
            }
        }
    }

    static void GetLatestOrders() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i][0] == null) {
                latestOrders = i;
                break;
            }
        }
    }

    static void GetLatestOrderDetails() {
        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] == null) {
                latestOrder_details = i;
                break;
            }
        }
    }

    // * Order Feature
    static void CreateOrder() {
        // mencari baris yang kosong di orders
        GetLatestOrders();

        // nama kasir
        orders[latestOrders][1] = users[user_id][0];

        // status pembayaran
        orders[latestOrders][7] = "incomplete";

        ordering = true;
        while (ordering == true) {

            // mencari baris yang kosong di detail order
            GetLatestOrderDetails();

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
            for (int i = 0; i < order_details.length; i++) {
                if (order_details[i][0] != null
                        && order_details[i][0].equals(Integer.toString(latestOrders))) {
                    orders[latestOrders][3] = Integer.toString(Integer.parseInt(orders[latestOrders][3])
                            + Integer.parseInt(order_details[i][5]));
                }
            }

            // total
            orders[latestOrders][4] = "0"; // to reset the value after each item added
            orders[latestOrders][4] = Integer.toString(Integer.parseInt(orders[latestOrders][2])
                    - Integer.parseInt(orders[latestOrders][3]));

            System.out.println("Total : Rp " + orders[latestOrders][4] + "\n");
            System.out.print("Choice : ");

            orderChoice = sc.nextInt();
            sc.nextLine();

            if (orderChoice < items.length && items[orderChoice - 1][0] != null) {
                CreateOrderDetail();
                continue;

            } else if (orderChoice == items.length) {
                CancelOrderDetail();

            } else if (orderChoice == items.length + 1) {
                FinishOrder();
                break;

            } else if (orderChoice == items.length + 2) {
                CancelOrder();
                break;

            } else {
                System.out.println("Menu tersebut tidak ada!");
                System.out.println("Silahkan coba lagi!\n");
                continue;

            }
        }
    }

    // ! Fix it
    static void CreateOrderDetail() {
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
        order_details[latestOrder_details][5] = Integer.toString(
                Integer.parseInt(order_details[latestOrder_details][4]) * Integer.parseInt(items[orderChoice - 1][3])
                        / 100);

        // total
        order_details[latestOrder_details][6] = Integer
                .toString(Integer.parseInt(order_details[latestOrder_details][4])
                        - Integer.parseInt(order_details[latestOrder_details][5]));

        // pengurangan stok
        items[orderChoice - 1][2] = Integer.toString(Integer.parseInt(items[orderChoice - 1][2])
                - Integer.parseInt(order_details[latestOrder_details][2]));
    }

    static void CancelOrderDetail() {
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
                    order_details[removeItemChoice][i] = null;
                }
            }

            // mengurutkan barisan array setelah penghapusan
            for (int i = 0; i < order_details.length - 1; i++) {
                if (order_details[i][0] == null && order_details[i + 1][0] != null) {
                    for (int j = 0; j < order_details[i].length; j++) {
                        order_details[i][j] = order_details[i + 1][j];
                        order_details[i + 1][j] = null;
                    }
                }
            }
        }
    }

    static void CheckMembership() {
        memberValid = false;

        while (memberValid != true) {
            System.out.println();
            System.out.print("Apakah punya kartu member? (y/t) :");
            isMember = sc.next().charAt(0);
            if (isMember == 'y' || isMember == 'Y') {
                System.out.print("Masukkan nomer member : ");
                String memberNumber = sc.next();
                boolean memberExists = false;
                String memberName = "";

                // Check if the member number exists in the noMembership array
                for (int i = 0; i < noMembership.length; i++) {
                    if (noMembership[i][0] != null && noMembership[i][0].equals(memberNumber)) {
                        memberExists = true;
                        memberName = noMembership[i][1];
                        break;
                    }
                }
                // print member names
                if (memberExists) {
                    System.out.println("Member name: " + memberName);
                    orders[latestOrders][8] = "member";
                    memberValid = true;
                } else {
                    System.out.println("Member number not found!");
                    break;
                }
            } else if (isMember == 't' || isMember == 'T') {
                orders[latestOrders][8] = "not member";
                memberValid = true;
            } else {
                System.out.println("Input invalid!");
                System.out.println("Please try again!");
            }
        }
    }

    static void FinishOrder() {

        CheckMembership();

        while (true) {
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
                    System.out.println("Total Awal : " + orders[latestOrders][2]);
                    System.out.println("Total Diskon : " + orders[latestOrders][3]);
                    // ! Fix this
                    if (orders[latestOrders][8] != null && orders[latestOrders][8].equals("member")) {
                        if (orders[latestOrders][4] != null) {
                            orders[latestOrders][10] = Integer
                                    .toString(Integer.parseInt(orders[latestOrders][4]) * memberDiskon / 100);
                            System.out.println("Member discount " + memberDiskon + "% : "
                                    + orders[latestOrders][10]);
                        }
                    } else {
                        orders[latestOrders][10] = "0";
                    }
                    System.out.println("---------------------------");
                    orders[latestOrders][4] = Integer.toString(
                            Integer.parseInt(orders[latestOrders][2]) - Integer.parseInt(orders[latestOrders][3])
                                    - Integer.parseInt(orders[latestOrders][10]));
                    System.out.println("Total : " + orders[latestOrders][4]);
                    System.out.print("Bayar : ");
                    orders[latestOrders][5] = Integer.toString(sc.nextInt());

                    if (orders[latestOrders][5] != null
                            && Integer.parseInt(orders[latestOrders][5]) >= Integer
                                    .parseInt(orders[latestOrders][4])) {

                        // kembalian
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

            break;
        }
    }

    static void CancelOrder() {
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
    }

    // * Manage Item Menu Feature
    static void ManageItems() {
        managingItem = true;
        while (managingItem == true) {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║        MANAGE ITEM MENU        ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("[1] View all");
            System.out.println("[2] Create new Item Menu");
            System.out.println("[3] Edit Item Menu");
            System.out.println("[4] Delete Item Menu");
            System.out.println("[5] Back");

            System.out.print("Masukkan pilihan anda : ");
            manageItemChoice = sc.nextInt();
            sc.nextLine();

            switch (manageItemChoice) {
                case 1:
                    ViewItemList();
                    break;

                case 2:
                    CreateItem();
                    break;

                case 3:
                    EditItem();
                    break;

                case 4:
                    DeleteItem();
                    break;

                case 5:
                    managingItem = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    static void ViewItemList() {
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                      Item Menu List                                      ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Name       |       Tipe       |    Price     |    Stock   |    Discount (%)  ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < items.length; i++) {
            if (items[i][0] != null) {
                System.out.println(String.format(
                        "║ %3d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║",
                        i + 1,
                        items[i][0],
                        items[i][4],
                        items[i][1],
                        items[i][2],
                        items[i][3]));
            }
        }
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void CreateItem() {
        boolean active = true;

        while (active) {
            // getting latest items index
            GetLatestItems();

            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║               Add New Item Menu              ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.println("[1] Add new food");
            System.out.println("[2] Add new drink");
            System.out.println("[3] Back");
            System.out.print("Masukkan pilihan anda : ");
            int createItemChoice = sc.nextInt();
            sc.nextLine();

            switch (createItemChoice) {
                case 1:
                    // tipe menu
                    items[latestItems][4] = "Makanan";

                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 Add New Food                 ║");
                    System.out.println("╚══════════════════════════════════════════════╝");
                    System.out.print("Insert food name : ");
                    items[latestItems][0] = sc.nextLine();

                    System.out.print("Insert food price : ");
                    items[latestItems][1] = Integer.toString(sc.nextInt());

                    System.out.print("Insert food stock : ");
                    items[latestItems][2] = Integer.toString(sc.nextInt());

                    System.out.print("Insert food discount : ");
                    items[latestItems][3] = Integer.toString(sc.nextInt());

                    System.out.println("New food has been successfully added!");
                    break;

                case 2:
                    // tipe menu
                    items[latestItems][4] = "Minuman";

                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 Add New Drink                ║");
                    System.out.println("╚══════════════════════════════════════════════╝");
                    System.out.print("Insert drink name : ");
                    items[latestItems][0] = sc.nextLine();

                    System.out.print("Insert drink price : ");
                    items[latestItems][1] = Integer.toString(sc.nextInt());

                    System.out.print("Insert drink stock : ");
                    items[latestItems][2] = Integer.toString(sc.nextInt());

                    System.out.print("Insert drink discount : ");
                    items[latestItems][3] = Integer.toString(sc.nextInt());

                    System.out.println("New drink has been successfully added!");
                    break;

                case 3:
                    active = false;
                    break;

                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }
    }

    static void EditItem() {

        String temp;

        ViewItemList();

        System.out.print("Input the number to edit the item : ");
        int editItemChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (editItemChoice < items.length && items[editItemChoice][0] != null) {
            System.out.println("Input (-) to not edit the data!");
            System.out.print("Input new name (" + items[editItemChoice][0] + ") : ");
            temp = sc.nextLine();
            if (!temp.equals("-")) {
                items[editItemChoice][0] = temp;
            }

            while (true) {
                System.out.print("Input new type (" + items[editItemChoice][4] + ") : ");
                temp = sc.nextLine();
                if (temp.equals("Makanan") || temp.equals("Minuman")) {
                    items[editItemChoice][4] = temp;
                    break;
                } else if (temp.equals("-")) {
                    break;
                } else {
                    System.out.println("Invalid type!");
                    continue;
                }
            }

            System.out.print("Input new price (" + items[editItemChoice][1] + ") : ");
            temp = Integer.toString(sc.nextInt());
            if (!temp.equals("-")) {
                items[editItemChoice][1] = temp;
            }

            System.out.println("Item has been successfully edited!");
        }
    }

    static void DeleteItem() {

        ViewItemList();

        System.out.print("Input the number to delete the item : ");
        int deleteItemChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (deleteItemChoice < items.length && items[deleteItemChoice][0] != null) {
            for (int i = 0; i < items[deleteItemChoice].length; i++) {
                items[deleteItemChoice][i] = null;
            }

            for (int i = 0; i < items.length - 1; i++) {
                if (items[i][0] == null & items[i + 1][0] != null) {
                    for (int j = 0; j < items[i].length; j++) {
                        items[i][j] = items[i + 1][j];
                        items[i + 1][j] = null;
                    }
                }
            }

            System.out.println("Item successfully deleted!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // * Manage Stock Feature
    static void ManageStock() {
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
    }

    // * History Feature
    static void ViewSalesHistory() {
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
    }

    // * Profit Report Feature
    static void ViewProfitReport() {
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
                        totalIncome += Double.parseDouble(orders[i][5]) - Double.parseDouble(orders[i][6]);
                    }
                }
                System.out.println("Total Pendapatan: " + totalIncome);
                System.out.println("=========================");
                System.out.println();
                break;

            default:
                System.out.println("Invalid choice!");
                System.out.println("Please try again!");
                break;
        }
    }

    // * Exit Program
    static void ExitProgram() {
        System.out.println("Thank you for using this program!");
        System.out.println("Goodbye ^-^");
        session = false;
        sc.close();
    }

    // login
    static void Login() {
        while (!access) {
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

            if (!access) {
                System.out.println("Username dan password salah!");
                System.out.println("Silahkan coba lagi!\n");
            }
        }
    }

    public static void main(String[] args) {

        Init();

        while (session) {

            Login();

            // menu
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║       Selamat Datang di Cafe The Orange!       ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("[1] Buat Pesanan");
            System.out.println("[2] Manajemen Item Menu");
            System.out.println("[3] Manajemen Stok");
            System.out.println("[null] Manajemen Diskon");
            System.out.println("[4] Lihat Riwayat Penjualan");
            System.out.println("[5] Lihat Laporan Pendapatan");
            System.out.println("[null] Manajemen User");
            System.out.println("[6] Keluar dari Program");
            System.out.print("Masukkan pilihan Anda: ");
            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                case 1:
                    CreateOrder();
                    continue;

                case 2:
                    ManageItems();
                    break;

                case 3:
                    ManageStock();
                    break;

                case 4:
                    ViewSalesHistory();
                    break;

                case 5:
                    ViewProfitReport();
                    break;
                case 6:
                    ExitProgram();
                    break;

                default:
                    System.out.println("Your choice does not exist!");
                    System.out.println("Please try again!");
                    break;
            }
        }
    }
}
