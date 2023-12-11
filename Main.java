import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

/**
 * main
 */
public class Main {

    // Declarations
    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
    static String memberName = " ";

    static void Init() {
        items = new String[20][6];
        items[0][0] = "Ayam Bakar"; // nama item
        items[0][1] = "15000"; // harga
        items[0][2] = "40"; // stok
        items[0][3] = "10"; // diskon %
        items[0][4] = "Makanan"; // tipe
        items[0][5] = "12000"; // harga beli

        items[1][0] = "Es teh"; // nama item
        items[1][1] = "3000"; // harga
        items[1][2] = "50"; // stok
        items[1][3] = "10"; // diskon %
        items[1][4] = "Minuman"; // tipe
        items[1][5] = "1000"; // harga beli

        users = new String[20][3];
        users[0][0] = "haikal";
        users[0][1] = "123";
        users[0][2] = "admin";
        users[1][0] = "irsyad";
        users[1][1] = "456";
        users[1][2] = "manager";
        users[2][0] = "esa";
        users[2][1] = "789";
        users[2][2] = "kasir";

        orders = new String[20][16];
        orders[0][0] = "Adi"; // nama pemesan
        orders[0][1] = "Irsyad"; // nama kasir
        orders[0][2] = "200000"; // subtotal
        orders[0][3] = "20000"; // total diskon
        orders[0][4] = "180000"; // total
        orders[0][5] = "180000"; // jumlah pembayaran
        orders[0][6] = "0"; // jumlah kembalian
        orders[0][7] = "completed"; // status pembayaran
        orders[0][8] = "member"; // status membership
        orders[0][9] = "10"; // diskon membership
        orders[0][10] = "10000"; // total diskon membership
        orders[0][11] = "cash"; // metode pembayaran
        orders[0][12] = "234172000"; // nomer rekening
        orders[0][13] = "BCA"; // bank
        orders[0][14] = "12-12-2023"; // created_at
        orders[0][15] = "150000"; // harga beli stock total

        order_details = new String[100][9];
        order_details[0][0] = "0"; // id
        order_details[0][1] = "Ayam Bakar"; // nama item
        order_details[0][2] = "10"; // jumlah beli
        order_details[0][3] = "15000"; // harga item
        order_details[0][4] = "150000"; // subtotal per item
        order_details[0][5] = "50000"; // total diskon per item
        order_details[0][6] = "100000"; // total per item
        order_details[0][7] = "10"; // diskon item
        order_details[0][8] = "150000"; // harga beli stock

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
    static void ViewMenuList() {
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                        Menu List                                         ║");
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
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |                                   Other Choices                                    ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(String.format("║ %3d ║ %82s ║", items.length, "Cancel an item"));
        System.out.println(String.format("║ %3d ║ %82s ║", items.length + 1, "Finish order"));
        System.out.println(String.format("║ %3d ║ %82s ║", items.length + 2, "Cancel and exit"));
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewOrderDetailList() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                                        Order Detail List                                                      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Name       |       Price      |  Qty  |     Subtotal     |    Discount (%)  |  Discount Total  |       Total      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(latestOrders))) {
                System.out.println(String.format(
                        "║ %3d ║ %16s ║ %16s ║ %5s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        order_details[i][3],
                        order_details[i][2],
                        order_details[i][4],
                        order_details[i][7],
                        order_details[i][5],
                        order_details[i][6]));
            }
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      |     Subtotal     |  Discount Total  |       Total      ║");
        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");
        if (orders[latestOrders][2] != null) {
            System.out.println(String.format(
                    "║ %68s ║ %16s ║ %16s ║ %16s ║",
                    " ",
                    orders[latestOrders][2],
                    orders[latestOrders][3],
                    orders[latestOrders][4]));
        }
        System.out.println(
                "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static int SetBankDetails() {
        while (true) {
            if (orders[latestOrders][11].equals("card")) {
                System.out.println("╔═════════════════════════════════╗");
                System.out.println("║        BANK CARD PAYMENT        ║");
                System.out.println("╚═════════════════════════════════╝");

                System.out.println("[1] BCA");
                System.out.println("[2] MANDIRI");
                System.out.println("[3] BRI");
                System.out.println("[4] Back");
                System.out.print("> Choice : ");
                int bankChoice = sc.nextInt();
                sc.nextLine();

                switch (bankChoice) {
                    case 1:
                        orders[latestOrders][13] = "BCA";
                        break;

                    case 2:
                        orders[latestOrders][13] = "MANDIRI";
                        break;

                    case 3:
                        orders[latestOrders][13] = "BRI";
                        break;

                    case 4:
                        return 400;

                    default:
                        System.out.println("Invalid Choice!");
                        continue;
                }

                System.out.print("> Input account number : ");
                orders[latestOrders][12] = sc.nextLine();
                break;
            }
        }

        return 200;
    }

    static int PaymentReceipt() {

        if (orders[latestOrders][11].equals("card")) {
            switch (SetBankDetails()) {
                case 200:
                    System.out.println("Bank verified!");
                    System.out.println();
                    break;

                case 400:
                    return 400;
            }
        }

        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf(
                "║        Cafe The Orange Receipt                                                               Order - %04d                     ║\n",
                latestOrders);
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║        Pelanggan      : %-16s                                                     Kasir : %-16s         ║\n",
                orders[latestOrders][0], orders[latestOrders][1]);
        System.out.println(
                "║                                                                                                                               ║");
        if (orders[latestOrders][11].equals("card")) {
            System.out.printf(
                    "║        Payment Method : %-26s                                           Date  : %-16s         ║\n",
                    orders[latestOrders][13] + " " + orders[latestOrders][11] + " (" + orders[latestOrders][12] + ")",
                    orders[latestOrders][14]);
        } else {
            System.out.printf(
                    "║        Payment Method : %-26s                                           Date  : %-16s         ║\n",
                    orders[latestOrders][11],
                    orders[latestOrders][14]);
        }
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        Order Detail List                                                                                                      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Name       |       Price      |  Qty  |     Subtotal     |    Discount (%)  |  Discount Total  |       Total      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(latestOrders))) {
                System.out.println(String.format(
                        "║ %3d ║ %16s ║ %16s ║ %5s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        order_details[i][3],
                        order_details[i][2],
                        order_details[i][4],
                        order_details[i][7],
                        order_details[i][5],
                        order_details[i][6]));
            }
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      |     Subtotal     |  Discount Total  |       Total      ║");
        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");
        if (orders[latestOrders][2] != null) {
            System.out.println(String.format(
                    "║ %68s ║ %16s ║ %16s ║ %16s ║",
                    " ",
                    orders[latestOrders][2],
                    orders[latestOrders][3],
                    orders[latestOrders][4]));
        }

        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      |          Member Discount %          |       Total      ║");
        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");

        if (orders[latestOrders][8] != null && orders[latestOrders][8].equals("member")) {
            if (orders[latestOrders][4] != null) {
                orders[latestOrders][10] = Integer
                        .toString(Integer.parseInt(orders[latestOrders][4]) * memberDiskon / 100);
                System.out.println(String.format(
                        "║ %68s ║ %35s ║ %16s ║",
                        " ",
                        orders[latestOrders][9],
                        orders[latestOrders][10]));
            }
        } else {
            orders[latestOrders][10] = "0";
            System.out.println(String.format(
                    "║ %68s ║ %35s ║ %16s ║",
                    " ",
                    "0", "0"));
        }

        orders[latestOrders][4] = Integer.toString(
                Integer.parseInt(orders[latestOrders][2]) - Integer.parseInt(orders[latestOrders][3])
                        - Integer.parseInt(orders[latestOrders][10]));

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║ Total :                                                                                                      %16s ║\n",
                orders[latestOrders][4]);
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        if (orders[latestOrders][11].equals("cash")) {
            System.out.print(
                    "║ Bayar :                                                                                                             ");
            orders[latestOrders][5] = Integer.toString(sc.nextInt());
        } else {
            orders[latestOrders][5] = orders[latestOrders][4];
            System.out.printf(
                    "║ Bayar :                                                                                                      %16s ║\n",
                    orders[latestOrders][5]);
        }

        if (orders[latestOrders][5] != null
                && Integer.parseInt(orders[latestOrders][5]) >= Integer
                        .parseInt(orders[latestOrders][4])) {

            // kembalian
            orders[latestOrders][6] = Integer
                    .toString(Integer.parseInt(orders[latestOrders][5])
                            - Integer.parseInt(orders[latestOrders][4]));
            System.out.println(
                    "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf(
                    "║ Kembalian :                                                                                                  %16s ║\n",
                    orders[latestOrders][6]);
            System.out.println(
                    "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

            return 200;

        } else {
            orders[latestOrders][5] = "0";
            return 401;
        }
    }

    static void CreateOrder() {
        // mencari baris yang kosong di orders
        if (!orders[latestOrders][5].equals("0")) {
            GetLatestOrders();
        }

        // nama kasir
        orders[latestOrders][1] = users[user_id][0];

        // status pembayaran
        orders[latestOrders][7] = "incomplete";

        // tanggal order
        orders[latestOrders][14] = LocalDate.now().format(dateFormat);

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

            // harga beli
            orders[latestOrders][15] = "0";
            for (int i = 0; i < order_details.length; i++) {
                if (order_details[i][0] != null
                        && order_details[i][0].equals(Integer.toString(latestOrders))) {
                    orders[latestOrders][15] = Integer.toString(Integer.parseInt(orders[latestOrders][15])
                            + Integer.parseInt(order_details[i][8]));
                }
            }

            System.out.println();

            ViewOrderDetailList();

            System.out.println();

            ViewMenuList();

            System.out.println();

            System.out.print("> Choice : ");

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

    static void CreateOrderDetail() {
        // id
        order_details[latestOrder_details][0] = Integer.toString(latestOrders);

        // nama
        order_details[latestOrder_details][1] = items[orderChoice - 1][0];

        // jumlah
        System.out.print("> Beli Berapa PCS " + items[orderChoice - 1][0] + " ? : ");
        order_details[latestOrder_details][2] = Integer.toString(sc.nextInt());
        sc.nextLine();

        // harga
        order_details[latestOrder_details][3] = items[orderChoice - 1][1];

        // subtotal
        order_details[latestOrder_details][4] = Integer.toString(Integer
                .parseInt(order_details[latestOrder_details][3])
                * Integer.parseInt(order_details[latestOrder_details][2]));

        // discount %
        order_details[latestOrder_details][7] = items[orderChoice - 1][3];

        // total diskon
        order_details[latestOrder_details][5] = Integer.toString(
                Integer.parseInt(order_details[latestOrder_details][4]) * Integer.parseInt(items[orderChoice - 1][3])
                        / 100);

        // total
        order_details[latestOrder_details][6] = Integer
                .toString(Integer.parseInt(order_details[latestOrder_details][4])
                        - Integer.parseInt(order_details[latestOrder_details][5]));

        // harga beli
        order_details[latestOrder_details][8] = Integer.toString(Integer
                .parseInt(order_details[latestOrder_details][3])
                * Integer.parseInt(items[orderChoice - 1][5]));

        // pengurangan stok
        items[orderChoice - 1][2] = Integer.toString(Integer.parseInt(items[orderChoice - 1][2])
                - Integer.parseInt(order_details[latestOrder_details][2]));
    }

    static void CancelOrderDetail() {
        System.out.println("Pilih item yang ingin di cancel!");

        ViewOrderDetailList();

        System.out.print("> Choice : ");
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

        while (!memberValid) {
            System.out.println();
            System.out.print("Apakah punya kartu member? (y/t) :");
            isMember = sc.next().charAt(0);

            if (isMember == 'y' || isMember == 'Y') {
                System.out.print("Masukkan nomer member : ");
                String memberNumber = sc.next();
                boolean memberExists = false;

                // Check if the member number exists in the noMembership array
                for (int i = 0; i < noMembership.length; i++) {
                    if (noMembership[i][0] != null && noMembership[i][0].equals(memberNumber)) {
                        memberExists = true;
                        memberName = noMembership[i][1];
                        break;
                    }
                }

                if (memberExists) {
                    // Set orders[latestOrders][8] to "member" if member number is found
                    orders[latestOrders][8] = "member";
                    orders[latestOrders][9] = Integer.toString(memberDiskon);
                } else {
                    System.out.println("Member number not found!");
                    break;
                }
            } else if (isMember == 't' || isMember == 'T') {
                orders[latestOrders][8] = "not member";
            } else {
                System.out.println("Input invalid!");
                System.out.println("Please try again!");
                continue;
            }

            // Assign default values if orders[latestOrders][8] is still null
            if (orders[latestOrders][8] == null) {
                orders[latestOrders][8] = "not member";
                orders[latestOrders][9] = "0";
            }

            // Print membership status
            System.out.println(
                    "╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println(
                    "║                                      Membership Status                                         ║");
            System.out.println(
                    "╠════════════════════════════════════════════════════════════════════════════════════════════════╣");
            String membershipStatus = orders[latestOrders][8].equals("member") ? "Active Member" : "Non-Member";
            System.out.printf(
                    "║        Member Name    : %-16s                  Status         : %-16s    ║\n",
                    memberName,
                    "Active Member");
            System.out.printf(
                    "║        Member Discount : %-16s                                                      ║\n",
                    orders[latestOrders][9] + "%");
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════╝");

            memberValid = true;

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
            System.out.println("[2] Bank Card");
            System.out.println("[3] Back");
            System.out.print("Choose your payment method : ");
            paymentChoice = sc.nextInt();
            sc.nextLine();

            switch (paymentChoice) {
                case 1:
                    orders[latestOrders][11] = "cash";
                    if (PaymentReceipt() == 200) {
                        System.out.println("Transaction Successful! \n");
                        orders[latestOrders][7] = "completed";
                        break;

                    } else if (PaymentReceipt() == 401) {
                        System.out.println("Uang tidak cukup!");
                        System.out.println("Proses Pembayaran gagal! \n");
                        System.out.println();
                        continue;

                    } else if (PaymentReceipt() == 400) {
                        System.out.println("Transaction cancelled! \n");
                    }

                case 2:
                    orders[latestOrders][11] = "card";
                    if (PaymentReceipt() == 200) {
                        System.out.println("Transaction Successful! \n");
                        orders[latestOrders][7] = "completed";
                        break;

                    } else if (PaymentReceipt() == 401) {
                        System.out.println("Uang tidak cukup!");
                        System.out.println("Proses Pembayaran gagal! \n");
                        System.out.println();
                        continue;

                    } else if (PaymentReceipt() == 400) {
                        System.out.println("Transaction cancelled! \n");
                    }

                case 3:
                    CreateOrder();
                    break;

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
                "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                                Item Menu List                                                ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Name       |       Tipe       |    Price     |    Stock   |    Discount (%)   |   Buying Price   ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < items.length; i++) {
            if (items[i][0] != null) {
                System.out.println(String.format(
                        "║ %3d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║ %17s ║",
                        i + 1,
                        items[i][0],
                        items[i][4],
                        items[i][1],
                        items[i][2],
                        items[i][3],
                        items[i][5]));
            }
        }
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewSimpleItemList() {
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

                    System.out.print("Insert food buying price : ");
                    items[latestItems][5] = Integer.toString(sc.nextInt());

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

                    System.out.print("Insert drink buying price : ");
                    items[latestItems][5] = Integer.toString(sc.nextInt());

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

            System.out.print("Input new buying price (" + items[editItemChoice][1] + ") : ");
            temp = Integer.toString(sc.nextInt());
            if (!temp.equals("-")) {
                items[editItemChoice][5] = temp;
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

    // * Manage Discount Feature
    static void manageDiscount() {
        boolean continueManaging = true;

        while (continueManaging) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║             Manage Discount          ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.println("1. Diskon item");
            System.out.println("2. Diskon Member" + " (" + memberDiskon + "%)");
            System.out.println("3. Kembali");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Diskon item
                    System.out.println("1. Edit Diskon");

                    if (choice == 1) { // Edit Diskon
                        ViewSimpleItemList();
                        System.out.print("Pilih item: ");
                        int editDiscountChoice = sc.nextInt();
                        if (editDiscountChoice > 0 && editDiscountChoice <= items.length
                                && items[editDiscountChoice - 1][0] != null) {
                            System.out.print(
                                    "Masukkan diskon baru " + items[editDiscountChoice - 1][0] + ": ");
                            int newDiscount = sc.nextInt();
                            items[editDiscountChoice - 1][3] = Integer.toString(newDiscount);
                            System.out.println("Diskon " + items[editDiscountChoice - 1][0] + " diubah menjadi "
                                    + items[editDiscountChoice - 1][3] + "%");
                        }
                    }
                    break;

                case 2: // Diskon Member
                    System.out.println("1. Edit Diskon");
                    System.out.println("2. Hapus Diskon");
                    System.out.print("Pilihan: ");
                    int memberDiscountChoice = sc.nextInt();
                    sc.nextLine();

                    switch (memberDiscountChoice) {
                        case 1: // Edit Diskon
                            // Implement logic for editing member discount
                            System.out.println("Edit diskon member ");
                            System.out.print("Masukkan diskon baru: ");
                            int newDiscount = sc.nextInt();
                            memberDiskon = newDiscount;
                            break;
                        case 2: // Hapus Diskon
                            // Implement logic for removing member discount
                            System.out
                                    .println(
                                            "Member discount removal functionality not implemented in this example.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 3: // Back/Exit
                    continueManaging = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // * History Feature
    static void ViewSalesHistoryDetails(int OrderIndex) {
        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf(
                "║        Cafe The Orange Receipt                                                               Order - %04d                     ║\n",
                OrderIndex + 1);
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║        Pelanggan      : %-16s                                                     Kasir : %-16s         ║\n",
                orders[OrderIndex][0], orders[OrderIndex][1]);
        System.out.println(
                "║                                                                                                                               ║");
        if (orders[OrderIndex][11].equals("card")) {
            System.out.printf(
                    "║        Payment Method : %-26s                                           Date  : %-16s         ║\n",
                    orders[OrderIndex][13] + " " + orders[OrderIndex][11] + " (" + orders[OrderIndex][12] + ")",
                    orders[OrderIndex][14]);
        } else {
            System.out.printf(
                    "║        Payment Method : %-26s                                           Date  : %-16s         ║\n",
                    orders[OrderIndex][11],
                    orders[OrderIndex][14]);
        }
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        Order Detail List                                                                                                      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Name       |       Price      |  Qty  |     Subtotal     |    Discount (%)  |  Discount Total  |       Total      ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(OrderIndex))) {
                System.out.println(String.format(
                        "║ %3d ║ %16s ║ %16s ║ %5s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        order_details[i][3],
                        order_details[i][2],
                        order_details[i][4],
                        order_details[i][7],
                        order_details[i][5],
                        order_details[i][6]));
            }
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      |     Subtotal     |  Discount Total  |       Total      ║");
        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");
        if (orders[OrderIndex][2] != null) {
            System.out.println(String.format(
                    "║ %68s ║ %16s ║ %16s ║ %16s ║",
                    " ",
                    orders[OrderIndex][2],
                    orders[OrderIndex][3],
                    orders[OrderIndex][4]));
        }

        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      |          Member Discount %          |       Total      ║");
        System.out.println(
                "║                                                                       ════════════════════════════════════════════════════════╣");

        if (orders[OrderIndex][8] != null && orders[OrderIndex][8].equals("member")) {
            if (orders[OrderIndex][4] != null) {
                System.out.println(String.format(
                        "║ %68s ║ %35s ║ %16s ║",
                        " ",
                        orders[OrderIndex][9],
                        orders[OrderIndex][10]));
            }
        } else {

            System.out.println(String.format(
                    "║ %68s ║ %35s ║ %16s ║",
                    " ",
                    "0", "0"));
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║ Total :                                                                                                      %16s ║\n",
                orders[OrderIndex][4]);
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        System.out.printf(
                "║ Bayar :                                                                                                      %16s ║\n",
                orders[OrderIndex][5]);

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║ Kembalian :                                                                                                  %16s ║\n",
                orders[OrderIndex][6]);
        System.out.println(
                "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewSalesHistory(int day, int month, int year, String type) {

        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        while (true) {
            System.out.println();
            System.out.println(
                    "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");

            if (type.equals("today")) {
                System.out.println(
                        "║                                                                                     Sales History Today                                                                                           ║");
            } else {
                System.out.printf(
                        "║                                                                               Sales History %-8s %-4s                                                                                         ║\n",
                        monthName, year);
            }

            System.out.println(
                    "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println(
                    "║ No  |   Cashier   |   Customer   |  Membership  |    Subtotal    |  Discount Total  |  Membership (%)  |  Member Discount  |      Total      |  Payment Status  |  Payment Method  |  Created At  ║");
            System.out.println(
                    "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

            for (int i = 0; i < orders.length; i++) {
                if (type.equals("today") || type.equals("day")) {
                    if (orders[i][0] != null && (LocalDate.parse(orders[i][14], dateFormat).getDayOfMonth() == day)
                            && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)
                            && (LocalDate.parse(orders[i][14], dateFormat).getYear() == year)) {
                        System.out.println(String.format(
                                "║ %3d ║ %11s ║ %12s ║ %12s ║ %14s ║ %16s ║ %16s ║ %17s ║ %15s ║ %16s ║ %16s ║ %12s ║",
                                i + 1,
                                orders[i][1],
                                orders[i][0],
                                orders[i][8],
                                orders[i][2],
                                orders[i][3],
                                orders[i][9],
                                orders[i][10],
                                orders[i][4],
                                orders[i][7],
                                orders[i][11],
                                orders[i][14]));
                    }
                } else {
                    if (orders[i][0] != null && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)
                            && (LocalDate.parse(orders[i][14], dateFormat).getYear() == year)) {
                        System.out.println(String.format(
                                "║ %3d ║ %11s ║ %12s ║ %12s ║ %14s ║ %16s ║ %16s ║ %17s ║ %15s ║ %16s ║ %16s ║ %12s ║",
                                i + 1,
                                orders[i][1],
                                orders[i][0],
                                orders[i][8],
                                orders[i][2],
                                orders[i][3],
                                orders[i][9],
                                orders[i][10],
                                orders[i][4],
                                orders[i][7],
                                orders[i][11],
                                orders[i][14]));
                    }
                }
            }

            System.out.println(
                    "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

            System.out.println();
            System.out.println("Input the number to view history details");
            System.out.println("[" + orders.length + "] Back");
            System.out.print("> Your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < orders.length && orders[choice - 1][0] != null) {
                ViewSalesHistoryDetails(choice - 1);
                continue;

            } else if (choice == orders.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    static void SalesHistoryByToday() {
        ViewSalesHistory(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear(), "today");
    }

    static void SalesHistoryByMonth(int year) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║            Sales History             ║");
            System.out.println("╚══════════════════════════════════════╝");
            for (int i = 0; i < months.length; i++) {
                System.out.println("[" + (i + 1) + "] " + months[i]);
            }

            System.out.println("[" + (months.length + 1) + "] Back");

            System.out.println();
            System.out.print("> Your choice : ");
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {
                ViewSalesHistory(1, month, year, "month");
                break;

            } else if (month == months.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    static void SalesHistoryByDay(int year) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║            Sales History             ║");
            System.out.println("╚══════════════════════════════════════╝");
            for (int i = 0; i < months.length; i++) {
                System.out.println("[" + (i + 1) + "] " + months[i]);
            }

            System.out.println("[" + (months.length + 1) + "] Back");

            System.out.println();
            System.out.print("> Your choice : ");
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {

                System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║                         Days of %-8s %-4d                        ║\n",
                        Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH), year);
                System.out.println("╠══════════════════════════════════════════════════════════════════════╣");

                DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("dd");
                LocalDate startDate = LocalDate.of(year, month, 1);
                LocalDate endDate = startDate.plusMonths(1).minusDays(1);

                LocalDate currentDay = startDate;
                int spaceCount = 0;

                if (currentDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                    spaceCount = 1;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.TUESDAY) {
                    spaceCount = 2;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                    spaceCount = 3;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    spaceCount = 4;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    spaceCount = 5;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    spaceCount = 6;
                }

                if (currentDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    System.out.print("║");
                }

                while (!currentDay.isAfter(endDate)) {
                    if (currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        System.out.print("║");
                    }
                    System.out.printf("  %-2s %-2d  ",
                            currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                            Integer.parseInt(dayFormat.format(currentDay)));

                    if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                        while (spaceCount != 0) {
                            System.out.print("          ");
                            spaceCount--;
                        }
                        System.out.println("║");
                    }
                    currentDay = currentDay.plusDays(1);
                }

                if (currentDay.isAfter(endDate)) {

                    if (currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        spaceCount = 6;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                        spaceCount = 5;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.TUESDAY) {
                        spaceCount = 4;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                        spaceCount = 3;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        spaceCount = 2;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.FRIDAY) {
                        spaceCount = 1;
                    }

                    while (spaceCount != 0) {
                        System.out.print("            ");
                        spaceCount--;
                    }

                    System.out.print("║");
                }

                System.out.println();
                System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

                System.out.println();
                System.out.print("> Your choice : ");

                int day = sc.nextInt();
                sc.nextLine();

                ViewSalesHistory(day, month, year, "day");
                break;

            } else if (month == months.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    static void SalesHistory(int selectedYear) {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║            Sales History             ║");
            System.out.println("╚══════════════════════════════════════╝");
            if (selectedYear == LocalDate.now().getYear()) {
                System.out.println("[0] Today");
            }

            System.out.println("[1] By month");
            System.out.println("[2] By day");
            System.out.println("[3] Back");
            System.out.print("> Your choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    SalesHistoryByToday();
                    break;

                case 1:
                    SalesHistoryByMonth(selectedYear);
                    break;

                case 2:
                    SalesHistoryByDay(selectedYear);
                    break;

                case 3:
                    PickHistoryYear();
                    break;

                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            break;
        }
    }

    static void PickHistoryYear() {

        while (true) {

            Set<Integer> uniqueYears = new HashSet<>();

            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║            Sales History             ║");
            System.out.println("╚══════════════════════════════════════╝");

            for (int i = 0; i < orders.length; i++) {
                if (orders[i][0] != null) {
                    LocalDate date = LocalDate.parse(orders[i][14], dateFormat);
                    int year = date.getYear();
                    uniqueYears.add(year);
                }
            }

            int uniqueYearsArray[] = uniqueYears.stream().mapToInt(Integer::intValue).toArray();

            for (int i = 0; i < uniqueYears.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + uniqueYearsArray[i]);
            }

            System.out.println("[" + (uniqueYearsArray.length + 1) + "] Back");

            System.out.print("> Your choice : ");
            int choice = sc.nextInt() - 1;

            if (choice < uniqueYearsArray.length) {
                SalesHistory(uniqueYearsArray[choice]);
                break;

            } else if (choice == uniqueYearsArray.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    // * Profit Report Feature
    static void ViewProfitReport(int day, int month, int year, String type) {

        double totalPenjualan = 0, totalDiskon = 0, totalDiskonMember = 0, totalPembelian = 0, totalKeuntungan,
                totalKerugian;

        for (int i = 0; i < orders.length; i++) {
            if (orders[i][0] != null && orders[i][7].equals("Completed")) {
                if (type.equals("all time")) {
                    totalPenjualan += Integer.parseInt(orders[i][2]);
                    totalDiskon += Integer.parseInt(orders[i][3]);
                    totalDiskonMember += Integer.parseInt(orders[i][10]);
                    totalPembelian += Integer.parseInt(orders[i][15]);
                } else if (type.equals("year")) {
                    if (LocalDate.parse(orders[i][14], dateFormat).getYear() == year) {
                        totalPenjualan += Integer.parseInt(orders[i][2]);
                        totalDiskon += Integer.parseInt(orders[i][3]);
                        totalDiskonMember += Integer.parseInt(orders[i][10]);
                        totalPembelian += Integer.parseInt(orders[i][15]);
                    }
                } else if (type.equals("month")) {
                    if ((LocalDate.parse(orders[i][14], dateFormat).getYear() == year)
                            && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)) {
                        totalPenjualan += Integer.parseInt(orders[i][2]);
                        totalDiskon += Integer.parseInt(orders[i][3]);
                        totalDiskonMember += Integer.parseInt(orders[i][10]);
                        totalPembelian += Integer.parseInt(orders[i][15]);
                    }
                } else {
                    if ((LocalDate.parse(orders[i][14], dateFormat).getYear() == year)
                            && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)
                            && (LocalDate.parse(orders[i][14], dateFormat).getDayOfMonth() == day)) {
                        totalPenjualan += Integer.parseInt(orders[i][2]);
                        totalDiskon += Integer.parseInt(orders[i][3]);
                        totalDiskonMember += Integer.parseInt(orders[i][10]);
                        totalPembelian += Integer.parseInt(orders[i][15]);
                    }
                }
            }
        }

        totalKerugian = totalDiskon + totalDiskonMember + totalPembelian;
        totalKeuntungan = totalPenjualan - totalKerugian;

        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                                                                          ║");

        if (type.equals("all time")) {
            System.out.println(
                    "║        Profit Report                                                      All Time       ║");

        } else if (type.equals("year")) {
            System.out.printf(
                    "║        Profit Report                                                          %4d       ║\n",
                    year);

        } else if (type.equals("month")) {
            String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            System.out.printf(
                    "║        Profit Report                                                 %8s %4d       ║\n",
                    monthName, year);

        } else {
            String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            System.out.printf(
                    "║        Profit Report                                              %2d %8s %4d       ║\n",
                    day, monthName, year);

        }

        System.out.println(
                "║                                                                                          ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        Pendapatan :                                                                      ║");
        System.out.printf(
                "║              Penjualan                %44s       ║\n", totalPenjualan);
        System.out.println(
                "║                                                                                          ║");

        System.out.println(
                "║        Beban      :                                                                      ║");
        System.out.printf(
                "║              Harga Pokok Pembelian    %-44s       ║\n", totalPembelian);
        System.out.printf(
                "║              Beban Diskon             %-44s       ║\n", totalDiskon);
        System.out.printf(
                "║              Beban Diskon Membership  %-44s       ║\n", totalPembelian);
        System.out.printf(
                "║                                       %44s       ║\n", totalKerugian);
        System.out.println(
                "║        ---------------------------------------------------------------------------       ║");
        System.out.println(
                "║                                                                                          ║");
        System.out.printf(
                "║        TOTAL KEUNTUNGAN               %44s       ║\n", totalKeuntungan);
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ProfitReportByAllTime() {
        ViewProfitReport(0, 0, 0, "all time");
    }

    static void ProfitReportByMonth(int year) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Laporan Pendapatan           ║");
            System.out.println("╚══════════════════════════════════════╝");
            for (int i = 0; i < months.length; i++) {
                System.out.println("[" + (i + 1) + "] " + months[i]);
            }

            System.out.println("[" + (months.length + 1) + "] Back");

            System.out.println();
            System.out.print("> Your choice : ");
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {
                ViewProfitReport(0, month, year, "month");
                break;

            } else if (month == months.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    static void ProfitReportByDay(int year) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Laporan Pendapatan           ║");
            System.out.println("╚══════════════════════════════════════╝");
            for (int i = 0; i < months.length; i++) {
                System.out.println("[" + (i + 1) + "] " + months[i]);
            }

            System.out.println("[" + (months.length + 1) + "] Back");

            System.out.println();
            System.out.print("> Your choice : ");
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {

                System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║                         Days of %-8s %-4d                        ║\n",
                        Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH), year);
                System.out.println("╠══════════════════════════════════════════════════════════════════════╣");

                DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("dd");
                LocalDate startDate = LocalDate.of(year, month, 1);
                LocalDate endDate = startDate.plusMonths(1).minusDays(1);

                LocalDate currentDay = startDate;
                int spaceCount = 0;

                if (currentDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                    spaceCount = 1;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.TUESDAY) {
                    spaceCount = 2;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                    spaceCount = 3;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    spaceCount = 4;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    spaceCount = 5;
                } else if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    spaceCount = 6;
                }

                if (currentDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    System.out.print("║");
                }

                while (!currentDay.isAfter(endDate)) {
                    if (currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        System.out.print("║");
                    }
                    System.out.printf("  %-2s %-2d  ",
                            currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                            Integer.parseInt(dayFormat.format(currentDay)));

                    if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                        while (spaceCount != 0) {
                            System.out.print("          ");
                            spaceCount--;
                        }
                        System.out.println("║");
                    }
                    currentDay = currentDay.plusDays(1);
                }

                if (currentDay.isAfter(endDate)) {

                    if (currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        spaceCount = 6;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                        spaceCount = 5;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.TUESDAY) {
                        spaceCount = 4;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                        spaceCount = 3;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        spaceCount = 2;
                    } else if (currentDay.getDayOfWeek() == DayOfWeek.FRIDAY) {
                        spaceCount = 1;
                    }

                    while (spaceCount != 0) {
                        System.out.print("            ");
                        spaceCount--;
                    }

                    System.out.print("║");
                }

                System.out.println();
                System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

                System.out.println();
                System.out.print("> Your choice : ");

                int day = sc.nextInt();
                sc.nextLine();

                ViewProfitReport(day, month, year, "day");
                break;

            } else if (month == months.length) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    static void ProfitReport(int year) {

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Laporan Pendapatan           ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.println("[1] This year");
            System.out.println("[2] By month");
            System.out.println("[3] By day");
            System.out.println("[4] Back");

            System.out.println();
            System.out.print("> Your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    ViewProfitReport(0, 0, year, "year");
                    break;

                case 2:
                    ProfitReportByMonth(year);
                    break;

                case 3:
                    ProfitReportByDay(year);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            break;
        }
    }

    static void PickReportYear() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Laporan Pendapatan           ║");
            System.out.println("╚══════════════════════════════════════╝");

            Set<Integer> uniqueYears = new HashSet<>();

            for (int i = 0; i < orders.length; i++) {
                if (orders[i][0] != null) {
                    LocalDate date = LocalDate.parse(orders[i][14], dateFormat);
                    int year = date.getYear();
                    uniqueYears.add(year);
                }
            }

            int uniqueYearsArray[] = uniqueYears.stream().mapToInt(Integer::intValue).toArray();

            for (int i = 0; i < uniqueYears.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + uniqueYearsArray[i]);
            }

            System.out.println("[" + (uniqueYearsArray.length + 1) + "] All time");
            System.out.println("[" + (uniqueYearsArray.length + 2) + "] Back");
            System.out.print("Your choice : ");
            int choice = sc.nextInt() - 1;
            sc.nextLine();

            if (choice < uniqueYearsArray.length) {
                ProfitReport(uniqueYearsArray[choice]);
                break;

            } else if (choice == uniqueYearsArray.length) {
                ProfitReportByAllTime();
                break;

            } else if (choice == uniqueYearsArray.length + 1) {
                break;

            } else {
                System.out.println("Invalid choice!");
                continue;
            }
        }
    }

    // * Manage Users
    static void ManageUsers() {
        boolean managingUser = true;
        while (managingUser == true) {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║        MANAGE USER MENU        ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("[1] View all");
            System.out.println("[2] Create new User");
            System.out.println("[3] Edit User");
            System.out.println("[4] Delete User");
            System.out.println("[5] Back");

            System.out.print("Masukkan pilihan anda : ");
            int manageUserChoice = sc.nextInt();
            sc.nextLine();

            switch (manageUserChoice) {
                case 1:
                    ViewUserList();
                    break;

                case 2:
                    CreateUser();
                    break;

                case 3:
                    EditUser();
                    break;

                case 4:
                    DeleteUser();
                    break;

                case 5:
                    managingUser = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    static void ViewUserList() {
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                            User List                             ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ No  |       Username       |       Password       |     Role     ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < users.length; i++) {
            if (users[i][0] != null) {
                System.out.println(String.format(
                        "║ %3d ║ %20s ║ %20s ║ %12s ║",
                        i + 1,
                        users[i][0],
                        users[i][1],
                        users[i][2]));
            }
        }
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════╝");
    }

    static void CreateUser() {

        GetLatestUsers();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                 Add New User                 ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Insert Username : ");
        users[latestUsers][0] = sc.nextLine();

        System.out.print("Insert Password : ");
        users[latestUsers][1] = sc.nextLine();

        System.out.print("Insert Role : ");
        String inputRole = sc.nextLine();

        while (true) {
            if (inputRole.equals("kasir") || inputRole.equals("manajer") || inputRole.equals("admin")) {
                users[latestUsers][2] = inputRole;
                break;
            } else {
                System.out.println("Invalid role!");
            }
        }

        System.out.println("New user has been successfully added!");
    }

    static void EditUser() {

        String temp;

        ViewUserList();

        System.out.print("Input the number to edit the user : ");
        int editUserChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (editUserChoice < users.length && users[editUserChoice][0] != null) {
            System.out.println("Input (-) to not edit the data!");
            System.out.print("Input new username (" + users[editUserChoice][0] + ") : ");
            temp = sc.nextLine();
            if (!temp.equals("-")) {
                users[editUserChoice][0] = temp;
            }

            System.out.print("Input new password (" + users[editUserChoice][1] + ") : ");
            temp = sc.nextLine();
            if (!temp.equals("-")) {
                users[editUserChoice][1] = temp;
            }

            System.out.print("Input new role (" + users[editUserChoice][0] + ") : ");
            temp = sc.nextLine();
            if (!temp.equals("-") && (temp.equals("kasir") || temp.equals("manajer") || temp.equals("admin"))) {
                users[editUserChoice][2] = temp;
            }

            System.out.println("Item has been successfully edited!");
        }
    }

    static void DeleteUser() {

        ViewUserList();

        System.out.print("Input the number to delete the user : ");
        int deleteUserChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (deleteUserChoice < users.length && users[deleteUserChoice][0] != null) {
            for (int i = 0; i < users[deleteUserChoice].length; i++) {
                users[deleteUserChoice][i] = null;
            }

            for (int i = 0; i < users.length - 1; i++) {
                if (users[i][0] == null & users[i + 1][0] != null) {
                    for (int j = 0; j < users[i].length; j++) {
                        users[i][j] = users[i + 1][j];
                        users[i + 1][j] = null;
                    }
                }
            }

            System.out.println("User successfully deleted!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // * Exit Program
    static void ExitProgram() {
        System.out.println("Thank you for using this program!");
        System.out.println("Goodbye ^-^");
        session = false;
        sc.close();
    }

    // * login
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

    static void Logout() {
        access = false;
        System.out.println("Logout successful!");
    }

    static boolean CheckLevel(String level) {
        if (users[user_id][2].equals(level)) {
            return true;
        } else {
            System.out.println("Invalid choice!");
            return false;
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
            System.out.println("[2] Lihat Riwayat Penjualan");
            if (users[user_id][2].equals("manager") || users[user_id][2].equals("admin")) {
                System.out.println("[3] Lihat Laporan Pendapatan");
                System.out.println("[4] Manajemen Item Menu");
                System.out.println("[5] Manajemen Stok");
                System.out.println("[6] Manajemen Diskon");
            }
            if (users[user_id][2].equals("admin")) {
                System.out.println("[7] Manajemen User");
            }
            System.out.println("[0] Logout");
            System.out.print("Masukkan pilihan Anda: ");
            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                case 1:
                    CreateOrder();
                    continue;

                case 2:
                    PickHistoryYear();
                    break;

                case 3:
                    if (CheckLevel("manager") || CheckLevel("admin")) {
                        PickReportYear();
                    }
                    break;

                case 4:
                    if (CheckLevel("manager") || CheckLevel("admin")) {
                        ManageItems();
                    }
                    break;

                case 5:
                    if (CheckLevel("manager") || CheckLevel("admin")) {
                        ManageStock();
                    }
                    break;

                case 6:
                    if (CheckLevel("manager") || CheckLevel("admin")) {
                        manageDiscount();
                    }
                    break;

                case 7:
                    if (CheckLevel("admin")) {
                        ManageUsers();
                    }
                    break;

                case 0:
                    Logout();
                    break;

                default:
                    System.out.println("Your choice does not exist!");
                    System.out.println("Please try again!");
                    break;
            }
        }
    }
}