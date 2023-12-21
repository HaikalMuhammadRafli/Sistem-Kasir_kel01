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

    // declarations
    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // additional variables
    static String CLEARSCREEN = "\u001b[H\u001b[2J";

    static String BOLD = "\033[1m";
    static String REGULAR = "\033[0m";

    static String RESET = "\u001B[37m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";
    static String YELLOW = "\u001B[33m";

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

    // array for language
    static int selectedLanguage = 0;
    static String notifMsg[][] = { { "Invalid choice!", "Pilihan tidak valid!" }, };

    // other variables
    static boolean session = true, access = false, memberValid;
    static char isMember;
    static String memberName = " ";

    static void Init() {
        // items dummy data
        // makanan
        items = new String[20][6];
        items[0][0] = "Ayam Bakar"; // nama item
        items[0][1] = "15000"; // harga
        items[0][2] = "40"; // stok
        items[0][3] = "10"; // diskon %
        items[0][4] = "Makanan"; // tipe
        items[0][5] = "12000"; // harga beli

        items[2][0] = "Nasi Goreng";
        items[2][1] = "12000";
        items[2][2] = "30";
        items[2][3] = "15";
        items[2][4] = "Makanan";
        items[2][5] = "9000";

        items[3][0] = "Mie Goreng";
        items[3][1] = "11000";
        items[3][2] = "25";
        items[3][3] = "10";
        items[3][4] = "Makanan";
        items[3][5] = "8000";

        items[4][0] = "Sate Ayam";
        items[4][1] = "15000";
        items[4][2] = "20";
        items[4][3] = "10";
        items[4][4] = "Makanan";
        items[4][5] = "13000";

        items[5][0] = "Bakso";
        items[5][1] = "12000";
        items[5][2] = "35";
        items[5][3] = "10";
        items[5][4] = "Makanan";
        items[5][5] = "10000";

        items[6][0] = "Nasi Uduk";
        items[6][1] = "12000";
        items[6][2] = "30";
        items[6][3] = "15";
        items[6][4] = "Makanan";
        items[6][5] = "9000";

        items[7][0] = "Sop Ikan";
        items[7][1] = "16000";
        items[7][2] = "25";
        items[7][3] = "10";
        items[7][4] = "Makanan";
        items[7][5] = "14000";

        items[8][0] = "Rendang";
        items[8][1] = "20000";
        items[8][2] = "20";
        items[8][3] = "10";
        items[8][4] = "Makanan";
        items[8][5] = "18000";

        items[9][0] = "Soto Ayam";
        items[9][1] = "14000";
        items[9][2] = "35";
        items[9][3] = "10";
        items[9][4] = "Makanan";
        items[9][5] = "11000";

        items[15][0] = "Nasi Kuning";
        items[15][1] = "14000";
        items[15][2] = "30";
        items[15][3] = "10";
        items[15][4] = "Makanan";
        items[15][5] = "11000";

        items[16][0] = "Gado-Gado";
        items[16][1] = "16000";
        items[16][2] = "25";
        items[16][3] = "10";
        items[16][4] = "Makanan";
        items[16][5] = "13000";

        items[17][0] = "Mie Ayam";
        items[17][1] = "12000";
        items[17][2] = "35";
        items[17][3] = "10";
        items[17][4] = "Makanan";
        items[17][5] = "10000";

        items[18][0] = "Soto Betawi";
        items[18][1] = "18000";
        items[18][2] = "30";
        items[18][3] = "15";
        items[18][4] = "Makanan";
        items[18][5] = "15000";

        items[19][0] = "Capcay";
        items[19][1] = "13000";
        items[19][2] = "20";
        items[19][3] = "10";
        items[19][4] = "Makanan";
        items[19][5] = "10000";

        // Minuman
        items[1][0] = "Es teh";
        items[1][1] = "3000";
        items[1][2] = "50";
        items[1][3] = "10";
        items[1][4] = "Minuman";
        items[1][5] = "1000";

        items[10][0] = "Jus Jeruk";
        items[10][1] = "8000";
        items[10][2] = "40";
        items[10][3] = "5";
        items[10][4] = "Minuman";
        items[10][5] = "6000";

        items[11][0] = "Soda Gembira";
        items[11][1] = "10000";
        items[11][2] = "35";
        items[11][3] = "10";
        items[11][4] = "Minuman";
        items[11][5] = "8000";

        items[12][0] = "Teh Tarik";
        items[12][1] = "6000";
        items[12][2] = "50";
        items[12][3] = "5";
        items[12][4] = "Minuman";
        items[12][5] = "5000";

        items[13][0] = "Es Jeruk";
        items[13][1] = "7000";
        items[13][2] = "45";
        items[13][3] = "5";
        items[13][4] = "Minuman";
        items[13][5] = "5500";

        // users dummy data
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

        // orders dummy data
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

        orders[1][0] = "YA"; // nama pemesan
        orders[1][1] = "Irsyad"; // nama kasir
        orders[1][2] = "200000"; // subtotal
        orders[1][3] = "20000"; // total diskon
        orders[1][4] = "180000"; // total
        orders[1][5] = "180000"; // jumlah pembayaran
        orders[1][6] = "0"; // jumlah kembalian
        orders[1][7] = "completed"; // status pembayaran
        orders[1][8] = "member"; // status membership
        orders[1][9] = "10"; // diskon membership
        orders[1][10] = "10000"; // total diskon membership
        orders[1][11] = "cash"; // metode pembayaran
        orders[1][12] = "234172000"; // nomer rekening
        orders[1][13] = "BCA"; // bank
        orders[1][14] = "12-12-2022"; // created_at
        orders[1][15] = "150000"; // harga beli stock total

        orders[2][0] = "Budi"; // nama pemesan
        orders[2][1] = "Rina"; // nama kasir
        orders[2][2] = "250000"; // subtotal
        orders[2][3] = "25000"; // total diskon
        orders[2][4] = "225000"; // total
        orders[2][5] = "225000"; // jumlah pembayaran
        orders[2][6] = "0"; // jumlah kembalian
        orders[2][7] = "completed"; // status pembayaran
        orders[2][8] = "non-member"; // status membership
        orders[2][9] = "0"; // diskon membership
        orders[2][10] = "0"; // total diskon membership
        orders[2][11] = "credit card"; // metode pembayaran
        orders[2][12] = "123456789"; // nomor rekening
        orders[2][13] = "BNI"; // bank
        orders[2][14] = "02-12-2021"; // created_at
        orders[2][15] = "200000"; // harga beli stock total

        orders[3][0] = "Citra"; // nama pemesan
        orders[3][1] = "Rahman"; // nama kasir
        orders[3][2] = "300000"; // subtotal
        orders[3][3] = "30000"; // total diskon
        orders[3][4] = "270000"; // total
        orders[3][5] = "270000"; // jumlah pembayaran
        orders[3][6] = "0"; // jumlah kembalian
        orders[3][7] = "completed"; // status pembayaran
        orders[3][8] = "member"; // status membership
        orders[3][9] = "15"; // diskon membership
        orders[3][10] = "45000"; // total diskon membership
        orders[3][11] = "cash"; // metode pembayaran
        orders[3][12] = "987654321"; // nomor rekening
        orders[3][13] = "Mandiri"; // bank
        orders[3][14] = "05-12-2022"; // created_at
        orders[3][15] = "250000"; // harga beli stock total

        // order_detail dummy data
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

        order_details[1][0] = "1"; // id
        order_details[1][1] = "Nasi Goreng"; // nama item
        order_details[1][2] = "5"; // jumlah beli
        order_details[1][3] = "20000"; // harga item
        order_details[1][4] = "100000"; // subtotal per item
        order_details[1][5] = "20000"; // total diskon per item
        order_details[1][6] = "80000"; // total per item
        order_details[1][7] = "20"; // diskon item
        order_details[1][8] = "100000"; // harga beli stock

        order_details[2][0] = "2"; // id
        order_details[2][1] = "Sate Ayam"; // nama item
        order_details[2][2] = "8"; // jumlah beli
        order_details[2][3] = "18000"; // harga item
        order_details[2][4] = "144000"; // subtotal per item
        order_details[2][5] = "16000"; // total diskon per item
        order_details[2][6] = "128000"; // total per item
        order_details[2][7] = "10"; // diskon item
        order_details[2][8] = "144000"; // harga beli stock

        order_details[3][0] = "3"; // id
        order_details[3][1] = "Mie Ayam"; // nama item
        order_details[3][2] = "7"; // jumlah beli
        order_details[3][3] = "15000"; // harga item
        order_details[3][4] = "105000"; // subtotal per item
        order_details[3][5] = "10000"; // total diskon per item
        order_details[3][6] = "95000"; // total per item
        order_details[3][7] = "10"; // diskon item
        order_details[3][8] = "105000"; // harga beli stock

        // membership dummy data
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
    static void ViewMenuList(String keyword) {

        String language[][] = { { "Menu List", "Daftar Menu" },
                { "Name", "Nama" },
                { "Type", "Tipe" },
                { "Price", "Harga" },
                { "Stock", "Stok" },
                { "Discount (%)", "Diskon (%)" },
                { "Other Actions", "Aksi lainnya" },
                { "Search Item", "Cari Item" },
                { "Cancel an item", "Batalkan sebuah item" },
                { "Finish order", "Selesaikan pesanan" },
                { "Cancel and Exit", "Batalkan dan Keluar" }
        };

        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║ " + CenterString(89, language[0][selectedLanguage]) + " ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ " + CenterString(4, "No") + " | " + CenterString(16, language[1][selectedLanguage]) + " | "
                        + CenterString(16, language[2][selectedLanguage]) + " | "
                        + CenterString(12, language[3][selectedLanguage]) + " | "
                        + CenterString(10, language[4][selectedLanguage]) + " | "
                        + CenterString(16, language[5][selectedLanguage]) + " ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < items.length; i++) {
            if (keyword == null) {
                if (items[i][0] != null && items[i][4].equalsIgnoreCase("makanan")) {
                    System.out.println(String.format(
                            "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3]));
                }
            } else {
                if (items[i][0] != null && items[i][0].toLowerCase().contains(keyword.toLowerCase())
                        && items[i][4].equalsIgnoreCase("makanan")) {
                    System.out.println(String.format(
                            "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3]));
                }
            }
        }

        System.out.println(
                "║-------------------------------------------------------------------------------------------║");

        for (int i = 0; i < items.length; i++) {
            if (keyword == null) {
                if (items[i][0] != null && items[i][4].equalsIgnoreCase("minuman")) {
                    System.out.println(String.format(
                            "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3]));
                }
            } else {
                if (items[i][0] != null && items[i][0].toLowerCase().contains(keyword.toLowerCase())
                        && items[i][4].equalsIgnoreCase("minuman")) {
                    System.out.println(String.format(
                            "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %16s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3]));
                }
            }
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ " + CenterString(4, "No") + " | " + PadStringRGT(82, language[6][selectedLanguage]) + " ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(String.format("║ %4d ║ %82s ║", items.length, language[7][selectedLanguage]));
        System.out.println(String.format("║ %4d ║ %82s ║", items.length + 1, language[8][selectedLanguage]));
        System.out.println(String.format("║ %4d ║ %82s ║", items.length + 2, language[9][selectedLanguage]));
        System.out.println(String.format("║ %4d ║ %82s ║", items.length + 3, language[10][selectedLanguage]));
        System.out.println(
                "╚═══════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewOrderDetailList() {

        String language[][] = { { "Order Detail List", "Daftar Detail Pesanan" },
                { "Name", "Nama" },
                { "Price", "Harga" },
                { "Discount (%)", "Diskon (%)" },
                { "Discount Total", "Total Diskon" } };

        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║ " + CenterString(126, language[0][selectedLanguage]) + " ║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ " + CenterString(4, "No")
                        + " | " + CenterString(16, language[1][selectedLanguage]) // name
                        + " | " + CenterString(16, language[2][selectedLanguage]) // price
                        + " | " + CenterString(5, "Qty")
                        + " | " + CenterString(16, "Subtotal")
                        + " | " + CenterString(16, language[3][selectedLanguage]) // discount (%)
                        + " | " + CenterString(16, language[4][selectedLanguage]) // discoutn total
                        + " | " + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(latestOrders))) {
                System.out.println(String.format(
                        "║ %4d ║ %16s ║ %16s ║ %5s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        MoneyFormat(order_details[i][3]),
                        order_details[i][2],
                        MoneyFormat(order_details[i][4]),
                        order_details[i][7],
                        MoneyFormat(order_details[i][5]),
                        MoneyFormat(order_details[i][6])));
            }
        }

        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                       | "
                        + CenterString(16, "Subtotal")
                        + " | " + CenterString(16, language[4][selectedLanguage])
                        + " | " + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "║                                                                       ═════════════════════════════════════════════════════════╣");
        if (orders[latestOrders][2] != null) {
            System.out.println(String.format(
                    "║ %69s ║ %16s ║ %16s ║ %16s ║",
                    " ",
                    MoneyFormat(orders[latestOrders][2]),
                    MoneyFormat(orders[latestOrders][3]),
                    MoneyFormat(orders[latestOrders][4])));
        }
        System.out.println(
                "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static int SetBankDetails() {

        String language[][] = { { "BANK CARD PAYMENT", "PEMBAYARAN KARTU BANK" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Input account number : ", "Masukkan nomor rekening : " },
                { "BANK CARD PAYMENT", "PEMBAYARAN KARTU BANK" } };

        while (true) {
            if (orders[latestOrders][11].equals("card")) {
                Header(language[4][selectedLanguage]);
                UpperBox();
                SideBox();
                TextBox("[1] BCA");
                SideBox();
                TextBox("[2] MANDIRI");
                SideBox();
                TextBox("[3] BRI");
                SideBox();
                TextBox("[4] " + language[1][selectedLanguage]);
                SideBox();
                LowerBox();
                InputBox(language[2][selectedLanguage]); // your choice
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
                        Notification("failure", "Invalid choice!");
                        continue;
                }

                InputBox(language[3][selectedLanguage]);
                orders[latestOrders][12] = sc.nextLine();
                Delay();
                break;
            }
        }

        return 200;
    }

    static int PaymentReceipt() {

        String language[][] = {
                { "Customer", "Pelanggan" },
                { "Cashier", "Kasir" },
                { "Payment Method", "Metode Pembayaran" },
                { "Date", "Tanggal" },
                { "Order Detail List", "Daftar Detail Pesanan" },
                { "Name", "Nama" },
                { "Price", "Harga" },
                { "Discount (%)", "Diskon (%)" },
                { "Discount Total", "Total Diskon" },
                { "Member Discount (%)", "Diskon Member (%)" },
                { "Pay", "Bayar" },
                { "Change", "Kembalian" }
        };

        if (orders[latestOrders][11].equals("card")) {
            switch (SetBankDetails()) {
                case 200:
                    Notification("success", "Bank account verified!");
                    break;

                case 400:
                    return 400;
            }
        }

        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf(
                "║        Cafe The Orange Receipt                                                                             Order - %04d        ║\n",
                latestOrders);
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║        " + PadStringLFT(12, language[0][selectedLanguage])
                        + "      : %-62s"
                        + PadStringLFT(11, language[1][selectedLanguage])
                        + " : %16s        ║\n",
                orders[latestOrders][0], orders[latestOrders][1]);
        System.out.println(
                "║                                                                                                                                ║");
        if (orders[latestOrders][11].equals("card")) {
            System.out.printf(
                    "║        " + PadStringLFT(17, language[2][selectedLanguage])
                            + " : %-62s" + PadStringLFT(10, language[3][selectedLanguage])
                            + "  : %16s        ║\n",
                    orders[latestOrders][13] + " " + orders[latestOrders][11] + " (" + orders[latestOrders][12] + ")",
                    orders[latestOrders][14]);
        } else {
            System.out.printf(
                    "║        " + PadStringLFT(17, language[2][selectedLanguage])
                            + " : %-62s" + PadStringLFT(10, language[3][selectedLanguage])
                            + "  : %16s        ║\n",
                    orders[latestOrders][11],
                    orders[latestOrders][14]);
        }
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        " + PadStringLFT(119, language[4][selectedLanguage]) + " ║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ " + CenterString(4, "No")
                        + " | " + CenterString(16, language[5][selectedLanguage]) // name
                        + " | " + CenterString(16, language[6][selectedLanguage]) // price
                        + " | " + CenterString(5, "Qty")
                        + " | " + CenterString(16, "Subtotal")
                        + " | " + CenterString(16, language[7][selectedLanguage]) // discount (%)
                        + " | " + CenterString(16, language[8][selectedLanguage]) // discoutn total
                        + " | " + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(latestOrders))) {
                System.out.println(String.format(
                        "║ %4d ║ %16s ║ %16s ║ %5s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        MoneyFormat(order_details[i][3]),
                        order_details[i][2],
                        MoneyFormat(order_details[i][4]),
                        order_details[i][7],
                        MoneyFormat(order_details[i][5]),
                        MoneyFormat(order_details[i][6])));
            }
        }

        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                       | "
                        + CenterString(16, "Subtotal") + " | " + CenterString(16, language[8][selectedLanguage]) // discount
                                                                                                                 // total
                        + " | " + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "║                                                                       ═════════════════════════════════════════════════════════╣");
        if (orders[latestOrders][2] != null) {
            System.out.println(String.format(
                    "║ %69s ║ %16s ║ %16s ║ %16s ║",
                    " ",
                    MoneyFormat(orders[latestOrders][2]),
                    MoneyFormat(orders[latestOrders][3]),
                    MoneyFormat(orders[latestOrders][4])));
        }

        System.out.println(
                "║                                                                       ═════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                       | "
                        + CenterString(35, language[9][selectedLanguage]) // discount
                        // total
                        + " | "
                        + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "║                                                                       ═════════════════════════════════════════════════════════╣");

        if (orders[latestOrders][8] != null && orders[latestOrders][8].equals("member")) {
            if (orders[latestOrders][4] != null) {
                orders[latestOrders][10] = Integer
                        .toString(Integer.parseInt(orders[latestOrders][4]) * memberDiskon / 100);
                System.out.println(String.format(
                        "║ %69s ║ %35s ║ %16s ║",
                        " ",
                        orders[latestOrders][9],
                        MoneyFormat(orders[latestOrders][10])));
            }
        } else {
            orders[latestOrders][10] = "0";
            System.out.println(String.format(
                    "║ %69s ║ %35s ║ %16s ║",
                    " ",
                    "0", "0"));
        }

        orders[latestOrders][4] = Integer.toString(
                Integer.parseInt(orders[latestOrders][2]) - Integer.parseInt(orders[latestOrders][3])
                        - Integer.parseInt(orders[latestOrders][10]));

        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║ Total :                                                                                                       %16s ║\n",
                MoneyFormat(orders[latestOrders][4]));
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        if (orders[latestOrders][11].equals("cash")) {
            System.out.print(
                    "║ " + PadStringLFT(5, language[10][selectedLanguage])
                            + " :                                                                                                              ");
            orders[latestOrders][5] = Integer.toString(sc.nextInt());
        } else {
            orders[latestOrders][5] = orders[latestOrders][4];
            System.out.printf(
                    "║ " + PadStringLFT(5, language[10][selectedLanguage])
                            + " :                                                                                                       %16s ║\n",
                    MoneyFormat(orders[latestOrders][4]));
        }

        if (orders[latestOrders][5] != null
                && Integer.parseInt(orders[latestOrders][5]) >= Integer
                        .parseInt(orders[latestOrders][4])) {

            // kembalian
            orders[latestOrders][6] = Integer
                    .toString(Integer.parseInt(orders[latestOrders][5])
                            - Integer.parseInt(orders[latestOrders][4]));
            System.out.println(
                    "╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf(
                    "║ " + PadStringLFT(9, language[11][selectedLanguage])
                            + " :                                                                                                   %16s ║\n",
                    MoneyFormat(orders[latestOrders][6]));
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

            return 200;

        } else {
            orders[latestOrders][5] = "0";
            return 401;
        }
    }

    static void CreateOrder() {

        String language[][] = {
                { "Buyer's name : ", "Nama pembeli : " },
                { "Your choice : ", "Pilihan anda : " },
                { "Create Order", "Buat Pesanan" }
        };

        String keyword = null;

        // mencari baris yang kosong di orders
        if (!(orders[latestOrders][5] == null || orders[latestOrders][5].equals("0"))) {
            GetLatestOrders();
        }

        // nama kasir
        orders[latestOrders][1] = users[user_id][0];

        // status pembayaran
        orders[latestOrders][7] = "incomplete";

        // tanggal order
        orders[latestOrders][14] = LocalDate.now().format(dateFormat);

        while (true) {

            // mencari baris yang kosong di detail order
            GetLatestOrderDetails();
            ClearScreen();

            Header(language[2][selectedLanguage]);
            if (orders[latestOrders][0] == null) {
                InputBox(language[0][selectedLanguage]); // buyer name
                orders[latestOrders][0] = sc.nextLine();
            } else {
                TextBox(language[0][selectedLanguage] + orders[latestOrders][0]); // buyer name
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

            ViewOrderDetailList();

            ViewMenuList(keyword);

            InputBox(language[1][selectedLanguage]); // your choice

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < items.length && items[choice - 1][0] != null) {
                Delay();
                CreateOrderDetail(choice);

            } else if (choice == items.length) {
                ClearScreen();
                keyword = Search(keyword);

            } else if (choice == items.length + 1) {
                Delay();
                CancelOrderDetail();

            } else if (choice == items.length + 2) {
                Delay();
                FinishOrder();
                break;

            } else if (choice == items.length + 3) {
                CancelOrder();
                Notification("warning", "Ordering has been cancelled!");
                Delay();
                break;

            } else {
                Notification("failure", "Menu Invalid!");
                Delay();
            }
        }
    }

    static void CreateOrderDetail(int choice) {

        String language[][] = { { "How many PCS ", "Beli berapa PCS " },
                { "Adding to cart", "Memasukkan ke keranjang" } };

        Header(language[1][selectedLanguage]);

        // id
        order_details[latestOrder_details][0] = Integer.toString(latestOrders);

        // nama
        order_details[latestOrder_details][1] = items[choice - 1][0];

        // jumlah
        SideBox();
        InputBox(language[0][selectedLanguage] + items[choice - 1][0] + " ? : "); // how many pcs
        order_details[latestOrder_details][2] = Integer.toString(sc.nextInt());
        sc.nextLine();

        SideBox();
        LowerBox();
        // harga
        order_details[latestOrder_details][3] = items[choice - 1][1];

        // subtotal
        order_details[latestOrder_details][4] = Integer.toString(Integer
                .parseInt(order_details[latestOrder_details][3])
                * Integer.parseInt(order_details[latestOrder_details][2]));

        // discount %
        order_details[latestOrder_details][7] = items[choice - 1][3];

        // total diskon
        order_details[latestOrder_details][5] = Integer.toString(
                Integer.parseInt(order_details[latestOrder_details][4]) * Integer.parseInt(items[choice - 1][3])
                        / 100);

        // total
        order_details[latestOrder_details][6] = Integer
                .toString(Integer.parseInt(order_details[latestOrder_details][4])
                        - Integer.parseInt(order_details[latestOrder_details][5]));

        // harga beli
        order_details[latestOrder_details][8] = Integer.toString(Integer
                .parseInt(order_details[latestOrder_details][2])
                * Integer.parseInt(items[choice - 1][5]));

        // pengurangan stok
        items[choice - 1][2] = Integer.toString(Integer.parseInt(items[choice - 1][2])
                - Integer.parseInt(order_details[latestOrder_details][2]));
    }

    static void CancelOrderDetail() {

        String language[][] = { { "Select the item you want to cancel!", "Pilih item yang ingin di cancel!" },
                { "Your choice : ", "Pilihan anda : " },
                { "Cancel Item Order", "Batalkan Barang Pesanan" },
                { "Back", "Kembali" } };

        while (true) {
            Header(language[2][selectedLanguage]);
            ViewOrderDetailList();

            UpperBox();
            SideBox();
            TextBox("[" + order_details.length + "] " + language[3][selectedLanguage]);
            SideBox();
            TextBox(language[0][selectedLanguage]); // Select the item you want to cancel!
            SideBox();
            InputBox(language[1][selectedLanguage]); // your choice
            int removeItemChoice = sc.nextInt();
            SideBox();
            LowerBox();

            if (removeItemChoice < order_details.length && order_details[removeItemChoice][0] != null
                    && order_details[removeItemChoice][0]
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
                Delay();
                break;

            } else if (removeItemChoice == order_details.length) {
                Delay();
                break;

            } else {
                Notification("failure", "Item not found!");
                Delay();
            }
        }
    }

    static void CheckMembership() {

        String memberLanguage[][] = {
                { "Have a membership card? (y/t) : ", "Apakah punya kartu member? (y/t) :" },
                { "Input membership code : ", "Masukkan kode membership : " },
                { "Membership Status ", "Status membership" },
                { "member Name : ", "Nama Member : " },
                { "Member discount : ", "Diskon member : " },
                { "Membership status : ", "Status membership : " }

        };

        memberValid = false;

        while (!memberValid) {
            Header("Membership");
            SideBox();
            InputBox(memberLanguage[0][selectedLanguage]); // Apakah punya kartu member? (y/t) :
            isMember = sc.next().charAt(0);

            SideBox();
            LowerBox();

            if (isMember == 'y' || isMember == 'Y') {
                UpperBox();
                SideBox();
                InputBox(memberLanguage[1][selectedLanguage]);
                String memberNumber = sc.next();

                SideBox();
                LowerBox();

                Delay();

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
                    Notification("failure", "Member not found!");
                    break;
                }
            } else if (isMember == 't' || isMember == 'T') {
                orders[latestOrders][8] = "not member";
            } else {
                Notification("failure", "Invalid choice! Try again.");
                Delay();
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
                    "║" + CenterString(96, memberLanguage[2][selectedLanguage]) + "║");
            System.out.println(
                    "╠════════════════════════════════════════════════════════════════════════════════════════════════╣");
            String membershipStatus = orders[latestOrders][8].equals("member") ? "Active Member" : "Non-Member";
            System.out.printf(
                    "║    " + PadStringLFT(16, memberLanguage[3][selectedLanguage]) + "%-40s"
                            + PadStringRGT(16, memberLanguage[5][selectedLanguage]) + "%-16s" + "║\n",
                    memberName,
                    membershipStatus);
            System.out.printf(
                    "║    " + PadStringLFT(16, memberLanguage[4][selectedLanguage]) + "%-76s" + "║\n",
                    orders[latestOrders][8].equals("member") ? orders[latestOrders][9] + "%" : "0%");
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════╝");

            memberValid = true;
        }
    }

    static void FinishOrder() {

        String language[][] = {
                { "Cash", "Uang tunai" },
                { "Bank card", "Kartu bank" },
                { "Back", "Kembali" },
                { "Choose payment method : ", "Pilih metode pembayaran : " },
                { "Payment methods", "Metode pembayaran" }
        };

        CheckMembership();

        while (true) {
            Header(language[4][selectedLanguage]);
            UpperBox();
            SideBox();
            TextBox("[1] " + language[0][selectedLanguage]); // cash
            SideBox();
            TextBox("[2] " + language[1][selectedLanguage]);
            SideBox();
            TextBox("[3] " + language[2][selectedLanguage]);
            SideBox();
            LowerBox();
            InputBox(language[3][selectedLanguage]); // Choose payment method :
            int paymentChoice = sc.nextInt();
            sc.nextLine();

            ClearScreen();

            switch (paymentChoice) {
                case 1:
                    orders[latestOrders][11] = "cash";
                    if (PaymentReceipt() == 200) {
                        AwaitEnter();
                        Notification("success", "Transaction successful!");
                        orders[latestOrders][7] = "completed";
                        Delay();
                        break;

                    } else if (PaymentReceipt() == 401) {
                        Notification("failure", "Uang tidak cukup!");
                        Delay();
                        continue;

                    } else if (PaymentReceipt() == 400) {
                        Notification("failure", "Transaction cancelled!");
                        Delay();
                        break;
                    }

                case 2:
                    orders[latestOrders][11] = "card";
                    if (PaymentReceipt() == 200) {
                        AwaitEnter();
                        Notification("success", "Transaction successful!");
                        orders[latestOrders][7] = "completed";
                        Delay();
                        break;

                    } else if (PaymentReceipt() == 401) {
                        Notification("failure", "Uang tidak cukup!");
                        Delay();
                        continue;

                    } else if (PaymentReceipt() == 400) {
                        Notification("failure", "Transaction cancelled!");
                        Delay();
                        break;
                    }

                case 3:
                    CreateOrder();
                    break;

                default:
                    Notification("failure", "Invalid method!");
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
    }

    // * Manage Item Menu Feature
    static void ManageItems() {
        boolean managingItem = true;

        String[][] itemLanguage = {
                { "MANAGE ITEM MENU", "KELOLA MENU" },
                { "View all", "Lihat Semua" },
                { "Create new Item Menu", "Buat Menu Baru" },
                { "Edit Item Menu", "Edit Menu" },
                { "Delete Item Menu", "Hapus Menu" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Invalid choice!", "Pilihan tidak valid!" }
        };

        while (managingItem == true) {
            Header(itemLanguage[0][selectedLanguage]);
            UpperBox();
            SideBox();
            TextBox("[1] " + itemLanguage[1][selectedLanguage]); // View all
            SideBox();
            TextBox("[2] " + itemLanguage[2][selectedLanguage]); // Create new Item Menu
            SideBox();
            TextBox("[3] " + itemLanguage[3][selectedLanguage]); // Edit Item Menu
            SideBox();
            TextBox("[4] " + itemLanguage[4][selectedLanguage]); // Delete Item Menu
            SideBox();
            TextBox("[5] " + itemLanguage[5][selectedLanguage]); // Back
            SideBox();
            LowerBox();
            InputBox(itemLanguage[6][selectedLanguage]);
            int manageItemChoice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (manageItemChoice) {
                case 1:
                    ViewItemList(null);
                    AwaitEnter();
                    ClearScreen();
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
                    ClearScreen();
                    break;

                default:
                    Notification("failure", "Invalid choice!");
                    break;
            }
        }
    }

    static void ViewItemList(String keyword) {

        String[][] itemLanguage = {
                { "Item Menu List", "Daftar Menu Item" },
                { "Name", "Nama" },
                { "Type", "Tipe" },
                { "Price", "Harga" },
                { "Stock", "Stok" },
                { "Discount (%)", "Diskon (%)" },
                { "Buying Price", "Harga Beli" },
        };
        Title();
        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║ " + CenterString(110, itemLanguage[0][selectedLanguage]) + "║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║" + CenterString(6, "No") +
                        "|" + CenterString(18, itemLanguage[1][selectedLanguage]) + // name
                        "|" + CenterString(18, itemLanguage[2][selectedLanguage]) + // tipe
                        "|" + CenterString(14, itemLanguage[3][selectedLanguage]) + // price
                        "|" + CenterString(12, itemLanguage[4][selectedLanguage]) + // stok
                        "|" + CenterString(19, itemLanguage[5][selectedLanguage]) + // discount (%)
                        "|" + CenterString(18, itemLanguage[6][selectedLanguage]) + // buying price
                        "║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < items.length; i++) {
            if (keyword == null) {
                if (items[i][0] != null) {
                    System.out.println(String.format(
                            "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %17s ║ %16s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3],
                            MoneyFormat(items[i][5])));
                }

            } else {
                if (items[i][0] != null && items[i][0].toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(String.format(
                            "║ %3d ║ %16s ║ %16s ║ %12s ║ %10s ║ %17s ║ %17s ║",
                            i + 1,
                            items[i][0],
                            items[i][4],
                            MoneyFormat(items[i][1]),
                            items[i][2],
                            items[i][3],
                            MoneyFormat(items[i][5])));
                }
            }
        }
        System.out.println(
                "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewSimpleItemList() {

        String[][] itemLanguage = {
                { "Item Menu List", "Daftar Menu Item" },
                { "Name", "Nama" },
                { "Type", "Tipe" },
                { "Price", "Harga" },
                { "Stock", "Stok" },
                { "Discount (%)", "Diskon (%)" },
                { "Buying Price", "Harga Beli" },
        };

        Title();
        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║ " + CenterString(91, itemLanguage[0][selectedLanguage]) + "║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║" + CenterString(6, "No") +
                        "|" + CenterString(18, itemLanguage[1][selectedLanguage]) + // name
                        "|" + CenterString(18, itemLanguage[2][selectedLanguage]) + // tipe
                        "|" + CenterString(14, itemLanguage[3][selectedLanguage]) + // price
                        "|" + CenterString(12, itemLanguage[4][selectedLanguage]) + // stok
                        "|" + CenterString(19, itemLanguage[5][selectedLanguage]) + // discount (%)
                        "║");
        System.out.println(
                "╠════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < items.length; i++) {
            if (items[i][0] != null) {
                System.out.println(String.format(
                        "║ %4d ║ %16s ║ %16s ║ %12s ║ %10s ║ %17s ║",
                        i + 1,
                        items[i][0],
                        items[i][4],
                        MoneyFormat(items[i][1]),
                        items[i][2],
                        items[i][3]));
            }
        }
        System.out.println(
                "╚════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void CreateItem() {

        String[][] createItemLanguage = {
                { "Add New Item Menu", "Tambah Menu Baru" },
                { "Add new food", "Tambah Makanan Baru" },
                { "Add new drink", "Tambah Minuman Baru" },
                { "Back", "Kembali" },
                { "Food", "Makanan" },
                { "Insert food name", "Masukkan nama makanan" },
                { "Insert food price", "Masukkan harga makanan" },
                { "Insert food stock", "Masukkan stok makanan" },
                { "Insert food discount", "Masukkan diskon makanan" },
                { "Insert food buying price", "Masukkan harga beli makanan" },
                { "New food has been successfully added!", "Makanan baru berhasil ditambahkan!" },
                { "Drink", "Minuman" },
                { "Insert drink name", "Masukkan nama minuman" },
                { "Insert drink price", "Masukkan harga minuman" },
                { "Insert drink stock", "Masukkan stok minuman" },
                { "Insert drink discount", "Masukkan diskon minuman" },
                { "Insert drink buying price", "Masukkan harga beli minuman" },
                { "New drink has been successfully added!", "Minuman baru berhasil ditambahkan!" },
                { "Invalid choice!", "Pilihan tidak valid!" },
                { "> Your choice ", "> Pilihan anda " }
        };

        while (true) {
            // getting latest items index
            GetLatestItems();

            Header(createItemLanguage[0][selectedLanguage]);
            UpperBox();
            SideBox();
            TextBox("[1] " + createItemLanguage[1][selectedLanguage]);
            SideBox();
            TextBox("[2] " + createItemLanguage[2][selectedLanguage]);
            SideBox();
            TextBox("[3] " + createItemLanguage[3][selectedLanguage]);
            SideBox();
            LowerBox();
            System.out.print(createItemLanguage[19][selectedLanguage] + ": ");
            int createItemChoice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (createItemChoice) {
                case 1:
                    // tipe menu
                    items[latestItems][4] = "Makanan";

                    Header(createItemLanguage[4][selectedLanguage]);
                    UpperBox();
                    SideBox();
                    InputBox(createItemLanguage[5][selectedLanguage] + " : ");
                    items[latestItems][0] = sc.nextLine();

                    SideBox();
                    InputBox(createItemLanguage[6][selectedLanguage] + ": ");
                    items[latestItems][1] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[7][selectedLanguage] + ": ");
                    items[latestItems][2] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[8][selectedLanguage] + ": ");
                    items[latestItems][3] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[9][selectedLanguage] + ": ");
                    items[latestItems][5] = Integer.toString(sc.nextInt());

                    SideBox();
                    LowerBox();

                    Notification("success", createItemLanguage[10][selectedLanguage]);
                    Delay();
                    break;

                case 2:
                    // tipe menu
                    items[latestItems][4] = "Minuman";

                    Header(createItemLanguage[11][selectedLanguage]);
                    UpperBox();
                    SideBox();
                    InputBox(createItemLanguage[12][selectedLanguage] + ": ");
                    items[latestItems][0] = sc.nextLine();

                    SideBox();
                    InputBox(createItemLanguage[13][selectedLanguage] + ": ");
                    items[latestItems][1] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[14][selectedLanguage] + ": ");
                    items[latestItems][2] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[15][selectedLanguage] + ": ");
                    items[latestItems][3] = Integer.toString(sc.nextInt());

                    SideBox();
                    InputBox(createItemLanguage[16][selectedLanguage] + ": ");
                    items[latestItems][4] = Integer.toString(sc.nextInt());

                    SideBox();
                    LowerBox();

                    Notification("success", createItemLanguage[17][selectedLanguage]);
                    Delay();
                    break;

                case 3:
                    Delay();
                    break;

                default:
                    Notification("failure", createItemLanguage[18][selectedLanguage]);
                    Delay();
                    continue;
            }

            break;
        }
    }

    static void EditItem() {

        String temp, keyword = null;

        String[][] editItemLanguage = {
                { "Search for an item", "Cari item" },
                { "Back", "Kembali" },
                { "Input the number to edit the item!", "Masukkan nomor untuk mengedit item!" },
                { "Input (-) to not edit the data!", "Masukkan (-) untuk tidak mengedit data!" },
                { "Input new name", "Masukkan nama baru" },
                { "Input new type", "Masukkan jenis baru" },
                { "Input new price", "Masukkan harga baru" },
                { "Input new buying price", "Masukkan harga beli baru" },
                { "Item has been successfully edited!", "Item berhasil diedit!" },
                { "Invalid Type!", "Jenis tidak valid!" },
                { "Invalid choice!", "Pilihan tidak valid!" },
                { "Input (0) to not edit the data!", "Masukkan (0) untuk tidak mengedit data!" },
                { "Your choice : ", "Pilihan anda : " },
                { "Edit Item Menu", "Edit Menu Item" }
        };

        while (true) {
            ViewItemList(keyword);
            UpperBox();
            SideBox();
            TextBox("[" + items.length + "] " + editItemLanguage[0][selectedLanguage]);
            SideBox();
            TextBox("[" + (items.length + 1) + "] " + editItemLanguage[1][selectedLanguage]);
            SideBox();
            LowerBox();
            System.out.println(editItemLanguage[2][selectedLanguage]);
            InputBox(editItemLanguage[12][selectedLanguage]);
            int editItemChoice = sc.nextInt() - 1;
            sc.nextLine();
            ClearScreen();

            if (editItemChoice < items.length && items[editItemChoice][0] != null) {
                Header(editItemLanguage[13][selectedLanguage]);
                UpperBox();
                SideBox();
                TextBox(editItemLanguage[3][selectedLanguage]);
                SideBox();
                InputBox(editItemLanguage[4][selectedLanguage] + " (" + items[editItemChoice][0] + ") : ");
                temp = sc.nextLine();
                if (!temp.equals("-")) {
                    items[editItemChoice][0] = temp;
                }

                while (true) {
                    SideBox();
                    InputBox(editItemLanguage[5][selectedLanguage] + " (" + items[editItemChoice][4] + ") : ");
                    temp = sc.nextLine();
                    if (temp.equals("Makanan") || temp.equals("Minuman")) {
                        items[editItemChoice][4] = temp;
                        break;

                    } else if (temp.equals("-")) {
                        break;

                    } else {
                        Notification("failure", editItemLanguage[9][selectedLanguage]);
                        continue;
                    }
                }

                SideBox();
                TextBox(editItemLanguage[11][selectedLanguage]);
                SideBox();
                InputBox(editItemLanguage[6][selectedLanguage] + " (" + items[editItemChoice][1] + ") : ");
                temp = Integer.toString(sc.nextInt());
                if (!temp.equals("0")) {
                    items[editItemChoice][1] = temp;
                }

                SideBox();
                TextBox(editItemLanguage[3][selectedLanguage]);
                SideBox();
                InputBox(editItemLanguage[7][selectedLanguage] + " (" + items[editItemChoice][5] + ") : ");
                temp = Integer.toString(sc.nextInt());
                if (!temp.equals("0")) {
                    items[editItemChoice][5] = temp;
                }

                SideBox();
                LowerBox();

                Notification("success", editItemLanguage[8][selectedLanguage]);
                Delay();
                break;

            } else if (editItemChoice + 1 == items.length) {
                keyword = Search(keyword);

            } else if (editItemChoice + 1 == items.length + 1) {
                break;

            } else {
                Notification("failure", editItemLanguage[10][selectedLanguage]);
            }
        }
    }

    static void DeleteItem() {

        String keyword = null;

        String[][] deleteItemLanguage = {
                { "Search for an item", "Cari item" },
                { "Back", "Kembali" },
                { "Input the number to delete the item!", "Masukkan nomor untuk menghapus item!" },
                { "Item has been successfully deleted!", "Item berhasil dihapus!" },
                { "Invalid choice!", "Pilihan tidak valid!" }
        };

        while (true) {
            ViewItemList(keyword);
            UpperBox();
            SideBox();
            TextBox("[" + items.length + "] " + deleteItemLanguage[0][selectedLanguage]);
            SideBox();
            TextBox("[" + (items.length + 1) + "] " + deleteItemLanguage[1][selectedLanguage]);
            SideBox();
            LowerBox();
            System.out.print(deleteItemLanguage[2][selectedLanguage] + ": ");
            int deleteItemChoice = sc.nextInt() - 1;
            sc.nextLine();

            if (deleteItemChoice < items.length && items[deleteItemChoice][0] != null) {
                for (int i = 0; i < items[deleteItemChoice].length; i++) {
                    items[deleteItemChoice][i] = null;
                }

                for (int i = 0; i < items.length - 1; i++) {
                    if (items[i][0] == null && items[i + 1][0] != null) {
                        for (int j = 0; j < items[i].length; j++) {
                            items[i][j] = items[i + 1][j];
                            items[i + 1][j] = null;
                        }
                    }
                }

                Notification("success", deleteItemLanguage[3][selectedLanguage]);
                Delay();

            } else if (deleteItemChoice + 1 == items.length) {
                keyword = Search(keyword);

            } else if (deleteItemChoice + 1 == items.length) {
                keyword = Search(keyword);

            } else if (deleteItemChoice + 1 == items.length + 1) {
                break;

            } else {
                Notification("failure", deleteItemLanguage[4][selectedLanguage]);
            }
        }
    }

    static void ManageStock() {
        boolean stocking = true;
        boolean exit = false;

        String[][] stockLanguage = {
                { "Manage Stock", "Kelola Stok" },
                { "Add Stock", "Tambah Stok" },
                { "Reduce Stock", "Kurangi Stok" },
                { "Choose item to add stock", "Pilih item untuk menambahkan stok" },
                { "Choose item to reduce stock", "Pilih item untuk mengurangi stok" },
                { "Back", "Kembali" },
                { "Cafe The Orange Stock", "Stok Cafe The Orange" }
        };

        while (stocking) {
            System.out.println();
            Header(stockLanguage[6][selectedLanguage]);

            // Menambahkan pilihan menu
            UpperBox();
            SideBox();
            TextBox("1. " + stockLanguage[1][selectedLanguage]); // Add Stock
            SideBox();
            TextBox("2. " + stockLanguage[2][selectedLanguage]); // Reduce Stock
            SideBox();
            TextBox("3. " + stockLanguage[5][selectedLanguage]); // Back
            SideBox();
            LowerBox();
            System.out.print(stockLanguage[0][selectedLanguage] + ": ");
            int menuChoice = sc.nextInt();
            ClearScreen();

            switch (menuChoice) {
                case 1:
                    exit = false;
                    do {
                        ViewItemList(null);
                        System.out.println("[" + (items.length + 1) + "] " + stockLanguage[5][selectedLanguage]); // Back
                        // Capture user's choice for stock update
                        System.out.print(
                                stockLanguage[3][selectedLanguage] + " : ");
                        int stockChoice = sc.nextInt();
                        // Validate user choice and update stock
                        if (stockChoice == items.length + 1) {
                            exit = true;
                        } else {
                            // Validate user choice and update stock
                            if (stockChoice > 0 && stockChoice <= items.length && items[stockChoice - 1][0] != null) {
                                System.out.print(
                                        stockLanguage[1][selectedLanguage] + " " + items[stockChoice - 1][0] + " : ");
                                int jumlahMasuk = sc.nextInt();
                                items[stockChoice - 1][2] = Integer
                                        .toString(Integer.parseInt(items[stockChoice - 1][2]) + jumlahMasuk);
                            } else {
                                Notification("failure", stockLanguage[0][selectedLanguage]);
                                Delay();
                            }
                        }
                    } while (!exit);
                    break;

                case 2:
                    exit = false;
                    do {
                        ViewItemList(null);
                        System.out.println("[" + (items.length + 1) + "] " + stockLanguage[5][selectedLanguage]); // Back
                        // Capture user's choice for stock update
                        System.out.print(
                                stockLanguage[4][selectedLanguage] + " : ");
                        int stockChoice = sc.nextInt();

                        // Check if user wants to exit
                        if (stockChoice == items.length + 1) {
                            exit = true;

                        } else {
                            // Validate user choice and update stock
                            if (stockChoice > 0 && stockChoice <= items.length && items[stockChoice - 1][0] != null) {
                                System.out.print(
                                        stockLanguage[2][selectedLanguage] + " " + items[stockChoice - 1][0] + " : ");
                                int jumlahMasuk = sc.nextInt();
                                items[stockChoice - 1][2] = Integer
                                        .toString(Integer.parseInt(items[stockChoice - 1][2]) - jumlahMasuk);
                            } else {
                                Notification("failure", stockLanguage[0][selectedLanguage]);
                                Delay();
                            }
                        }
                    } while (!exit);
                    break;

                case 3:
                    System.out.println(stockLanguage[3][selectedLanguage]);
                    stocking = false;
                    break;

                default:
                    Notification("failure", notifMsg[0][selectedLanguage]);
            }
        }
    }

    // * Manage Discount Feature
    static void ManageDiscount() {
        boolean continueManaging = true;

        String[][] discountLanguage = {
                { "Manage Discount", "Kelola Diskon" },
                { "Edit Discount", "Edit Diskon" },
                { "Remove Discount", "Hapus Diskon" },
                { "Choose item to edit discount", "Pilih item untuk mengedit diskon" },
                { "Enter new discount", "Masukkan diskon baru" },
                { "Discount updated!", "Diskon diperbarui!" },
                { "Discount removed!", "Diskon dihapus!" },
                { "Edit member discount", "Edit diskon member" },
                { "Enter new member discount", "Masukkan diskon member baru" },
                { "Member discount updated!", "Diskon member diperbarui!" },
                { "Member discount removed!", "Diskon member dihapus!" },
                { "Invalid choice! Try again.", "Pilihan tidak valid! Coba lagi." },
                { "Back", "Kembali" },
                { "Item Discount", "Diskon Item" },
                { "Member Discount", "Diskon Member" },
                { "Choose the Menu", "Pilih Menu" },
        };

        while (continueManaging) {
            System.out.println();
            Header(discountLanguage[0][selectedLanguage]);
            UpperBox();
            SideBox();
            TextBox("1. " + discountLanguage[13][selectedLanguage]); // Item Discount
            SideBox();
            TextBox("2. " + discountLanguage[14][selectedLanguage] + " (" + memberDiskon + "%)"); // Member Discount
            SideBox();
            TextBox("3. " + discountLanguage[12][selectedLanguage]); // Back
            SideBox();
            LowerBox();
            System.out.print(discountLanguage[15][selectedLanguage] + ": ");
            int choice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (choice) {
                case 1: // Item Discount
                    System.out.println("1. " + discountLanguage[1][selectedLanguage]); // Edit Discount
                    if (choice == 1) { // Edit Discount
                        while (true) {
                            ViewSimpleItemList();
                            System.out.println("[0] " + discountLanguage[12][selectedLanguage]);
                            System.out.print(discountLanguage[3][selectedLanguage] + ": "); // Choose item to edit
                                                                                            // discount
                            int editDiscountChoice = sc.nextInt();

                            if (editDiscountChoice == 0) {
                                break; // Kembali jika pengguna memilih 0
                            }

                            if (editDiscountChoice > 0 && editDiscountChoice <= items.length
                                    && items[editDiscountChoice - 1][0] != null) {
                                System.out.print(
                                        discountLanguage[4][selectedLanguage] + " " + items[editDiscountChoice - 1][0]
                                                + ": "); // Enter new discount
                                int newDiscount = sc.nextInt();
                                items[editDiscountChoice - 1][3] = Integer.toString(newDiscount);
                                System.out.println(items[editDiscountChoice - 1][0] + " "
                                        + discountLanguage[5][selectedLanguage] + " "
                                        + items[editDiscountChoice - 1][3]
                                        + "% " + discountLanguage[6][selectedLanguage]); // Discount updated
                                Delay();
                            }
                        }
                    }
                    break;

                case 2: // Member Discount
                    System.out.println();
                    Header(discountLanguage[0][selectedLanguage]);
                    UpperBox();
                    SideBox();
                    TextBox("1. " + discountLanguage[1][selectedLanguage]); // Edit Discount
                    SideBox();
                    TextBox("2. " + discountLanguage[2][selectedLanguage]); // Remove Discount
                    SideBox();
                    TextBox("3. " + discountLanguage[12][selectedLanguage]); // Back
                    SideBox();
                    LowerBox();
                    System.out.print(discountLanguage[15][selectedLanguage] + ": "); // Enter new member discount
                    int memberDiscountChoice = sc.nextInt();
                    sc.nextLine();
                    ClearScreen();

                    switch (memberDiscountChoice) {
                        case 1: // Edit Discount
                            System.out.println(discountLanguage[7][selectedLanguage]); // Edit member discount
                            System.out.print(discountLanguage[8][selectedLanguage] + ": "); // Enter new member discount
                            int newDiscount = sc.nextInt();
                            memberDiskon = newDiscount;
                            System.out.println(discountLanguage[9][selectedLanguage]); // Member discount updated
                            break;
                        case 2: // Remove Discount
                            memberDiskon = 0;
                            System.out.println(discountLanguage[10][selectedLanguage]); // Member discount removed
                            Delay();
                            break;
                        case 3: // Back
                            break;
                        default:
                            System.out.println(discountLanguage[12][selectedLanguage]); // Invalid choice! Try again.
                    }
                    break;

                case 3: // Back/Exit
                    continueManaging = false;
                    break;

                default:
                    System.out.println(discountLanguage[12][selectedLanguage]); // Invalid choice! Try again.
            }
        }
    }

    // * History Feature
    static void ViewSalesHistoryDetails(int OrderIndex) {

        String language[][] = {
                { "Customer", "Pelanggan" },
                { "Cashier", "Kasir" },
                { "Payment Method", "Metode Pembayaran" },
                { "Date", "Tanggal" },
                { "Order Detail List", "Daftar Detail Pesanan" },
                { "Name", "Nama" },
                { "Price", "Harga" },
                { "Discount (%)", "Diskon (%)" },
                { "Discount Total", "Total Diskon" },
                { "Member Discount (%)", "Diskon Member (%)" },
                { "Pay", "Bayar" },
                { "Change", "Kembalian" }
        };

        Title();
        System.out.println(
                "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf(
                "║        Cafe The Orange Receipt                                                               Order - %04d                     ║\n",
                OrderIndex + 1);
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║        " + PadStringLFT(12, language[0][selectedLanguage])
                        + "      : %-61s"
                        + PadStringLFT(11, language[1][selectedLanguage])
                        + " : %16s        ║\n",
                orders[OrderIndex][0], orders[OrderIndex][1]);
        System.out.println(
                "║                                                                                                                               ║");
        if (orders[OrderIndex][11].equals("card")) {
            System.out.printf(
                    "║        " + PadStringLFT(17, language[2][selectedLanguage])
                            + " : %-61s" + PadStringLFT(10, language[3][selectedLanguage])
                            + "  : %16s        ║\n",
                    orders[OrderIndex][13] + " " + orders[OrderIndex][11] + " (" + orders[OrderIndex][12] + ")",
                    orders[OrderIndex][14]);
        } else {
            System.out.printf(
                    "║        " + PadStringLFT(17, language[2][selectedLanguage])
                            + " : %-61s" + PadStringLFT(10, language[3][selectedLanguage])
                            + "  : %16s        ║\n",
                    orders[OrderIndex][11],
                    orders[OrderIndex][14]);
        }
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        " + PadStringLFT(118, language[4][selectedLanguage]) + " ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║ " + CenterString(4, "No")
                        + " | " + CenterString(16, language[5][selectedLanguage]) // name
                        + " | " + CenterString(16, language[6][selectedLanguage]) // price
                        + " | " + CenterString(6, "Qty")
                        + " | " + CenterString(16, "Subtotal")
                        + " | " + CenterString(16, language[7][selectedLanguage]) // discount (%)
                        + " | " + CenterString(16, language[8][selectedLanguage]) // discoutn total
                        + " | " + CenterString(16, "Total")
                        + " ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < order_details.length; i++) {
            if (order_details[i][0] != null && order_details[i][0].equals(Integer.toString(OrderIndex))) {
                System.out.println(String.format(
                        "║ %4d ║ %16s ║ %16s ║ %6s ║ %16s ║ %16s ║ %16s ║ %16s ║",
                        i,
                        order_details[i][1],
                        MoneyFormat(order_details[i][3]),
                        order_details[i][2],
                        MoneyFormat(order_details[i][4]),
                        order_details[i][7],
                        MoneyFormat(order_details[i][5]),
                        MoneyFormat(order_details[i][6])));
            }
        }

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                      | "
                        + CenterString(16, "Subtotal") + " | " + CenterString(16, language[8][selectedLanguage]) // discount
                                                                                                                 // total
                        + " | " + CenterString(16, "Total")
                        + " ║");
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
                "║                                                                      | "
                        + CenterString(35, language[9][selectedLanguage]) // discount
                        // total
                        + " | "
                        + CenterString(16, "Total")
                        + " ║");
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
                "║ " + PadStringLFT(5, language[10][selectedLanguage])
                        + " :                                                                                                      %16s ║\n",
                orders[OrderIndex][5]);

        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf(
                "║ " + PadStringLFT(9, language[11][selectedLanguage])
                        + " :                                                                                                  %16s ║\n",
                orders[OrderIndex][6]);
        System.out.println(
                "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void ViewSalesHistory(int day, int month, int year, String type) {

        String language[][] = { { "Sales History Today", "Riwayat Penjualan Hari Ini" },
                { "Sales History", "Riwayat Penjualan" },
                { "Cashier", "Kasir" },
                { "Customer", "Pelanggan" },
                { "Payment Status", "Status Pembayaran" },
                { "Payment Method", "Metode Pembayaran" },
                { "Created At", "Dibuat Pada" },
                { "Input number to view history details!", "Masukkan nomor untuk melihat detail riwayat!" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " } };

        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        while (true) {
            Title();
            System.out.println(
                    "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");

            if (type.equals("today")) {
                System.out.println(
                        "║ " + CenterString(119, language[0][selectedLanguage]) + " ║");
            } else {
                System.out.printf(
                        "║                       " + PadStringRGT(37, language[1][selectedLanguage])
                                + " %-8s %-4s                                               ║\n",
                        monthName, year);
            }

            System.out.println(
                    "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println(
                    "║ " + CenterString(4, "No")
                            + " | " + CenterString(11, language[2][selectedLanguage])
                            + " | " + CenterString(12, language[3][selectedLanguage])
                            + " | " + CenterString(12, "Membership")
                            + " | " + CenterString(15, "Total")
                            + " | " + CenterString(16, language[4][selectedLanguage])
                            + " | " + CenterString(16, language[5][selectedLanguage])
                            + " | " + CenterString(12, language[6][selectedLanguage])
                            + " ║");
            System.out.println(
                    "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

            for (int i = 0; i < orders.length; i++) {
                if (type.equals("today") || type.equals("day")) {
                    if (orders[i][0] != null && (LocalDate.parse(orders[i][14], dateFormat).getDayOfMonth() == day)
                            && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)
                            && (LocalDate.parse(orders[i][14], dateFormat).getYear() == year)) {
                        System.out.println(String.format(
                                "║ %4d ║ %11s ║ %12s ║ %12s ║ %15s ║ %16s ║ %16s ║ %12s ║",
                                i + 1,
                                orders[i][1],
                                orders[i][0],
                                orders[i][8],
                                MoneyFormat(orders[i][4]),
                                orders[i][7],
                                orders[i][11],
                                orders[i][14]));
                    }
                } else {
                    if (orders[i][0] != null && (LocalDate.parse(orders[i][14], dateFormat).getMonthValue() == month)
                            && (LocalDate.parse(orders[i][14], dateFormat).getYear() == year)) {
                        System.out.println(String.format(
                                "║ %4d ║ %11s ║ %12s ║ %12s ║ %15s ║ %16s ║ %16s ║ %12s ║",
                                i + 1,
                                orders[i][1],
                                orders[i][0],
                                orders[i][8],
                                MoneyFormat(orders[i][4]),
                                orders[i][7],
                                orders[i][11],
                                orders[i][14]));
                    }
                }
            }

            System.out.println(
                    "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

            UpperBox();
            SideBox();

            TextBox(language[7][selectedLanguage]); // Input number to view history details!
            SideBox();
            TextBox("[" + orders.length + "] " + language[8][selectedLanguage]); // Back
            SideBox();
            LowerBox();

            InputBox(language[9][selectedLanguage]); // Your choice :
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < orders.length && orders[choice - 1][0] != null) {
                ClearScreen();
                ViewSalesHistoryDetails(choice - 1);
                AwaitEnter();
                Delay();
                continue;

            } else if (choice == orders.length) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                break;
            }
        }
    }

    static void SalesHistoryByToday() {
        ViewSalesHistory(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear(), "today");
    }

    static String[] GetMonths() {
        if (selectedLanguage == 0) {
            String[] months = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };
            return months;

        } else {
            String[] bulan = {
                    "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                    "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            };
            return bulan;
        }
    }

    static void SalesHistoryByMonth(int year) {

        String language[][] = { { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Sales History", "Riwayat Penjualan" }
        };

        String months[] = GetMonths();

        while (true) {
            System.out.println();
            Header(language[2][selectedLanguage]);
            UpperBox();
            SideBox();
            for (int i = 0; i < months.length; i++) {
                TextBox("[" + (i + 1) + "] " + months[i]);
                SideBox();
            }

            TextBox("[" + (months.length + 1) + "] " + language[0][selectedLanguage]); // back
            SideBox();
            LowerBox();
            InputBox(language[1][selectedLanguage]); // your choice
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {
                Delay();
                ViewSalesHistory(1, month, year, "month");
                break;

            } else if (month == months.length) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
                break;
            }
        }
    }

    static void SalesHistoryByDay(int year) {

        String language[][] = { { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Sales History", "Riwayat Penjualan" } };

        String months[] = GetMonths();

        while (true) {
            System.out.println();
            Header(language[2][selectedLanguage]);
            SideBox();
            for (int i = 0; i < months.length; i++) {
                TextBox("[" + (i + 1) + "] " + months[i]);
                SideBox();
            }
            TextBox("[" + (months.length + 1) + "] " + language[0][selectedLanguage]); // back
            SideBox();
            LowerBox();

            InputBox(language[1][selectedLanguage]); // your choice
            int month = sc.nextInt();
            sc.nextLine();

            ClearScreen();

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

                InputBox(language[1][selectedLanguage]); // your choice

                int day = sc.nextInt();
                sc.nextLine();

                Delay();
                ViewSalesHistory(day, month, year, "day");
                break;

            } else if (month == months.length) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                break;
            }
        }
    }

    static void SalesHistory(int selectedYear) {

        String language[][] = { { "Today", "Hari ini" },
                { "By month", "Berdasarkan bulan" },
                { "By day", "Berdasarkan hari" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Sales History", "Riwayat Penjualan" } };

        while (true) {
            System.out.println();
            Header(language[5][selectedLanguage]);
            UpperBox();
            SideBox();
            if (selectedYear == LocalDate.now().getYear()) {
                TextBox("[0] " + language[0][selectedLanguage]); // today
                SideBox();
            }
            TextBox("[1] " + language[1][selectedLanguage]); // by month
            SideBox();
            TextBox("[2] " + language[2][selectedLanguage]); // by day
            SideBox();
            TextBox("[3] " + language[3][selectedLanguage]); // back
            SideBox();
            LowerBox();
            InputBox(language[4][selectedLanguage]); // your choice
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    Delay();
                    SalesHistoryByToday();
                    break;

                case 1:
                    Delay();
                    SalesHistoryByMonth(selectedYear);
                    break;

                case 2:
                    Delay();
                    SalesHistoryByDay(selectedYear);
                    break;

                case 3:
                    Delay();
                    PickHistoryYear();
                    break;

                default:
                    Notification("failure", "Invalid choice!");
                    Delay();
                    break;
            }

            break;
        }
    }

    static void PickHistoryYear() {

        String language[][] = { { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Sales History", "Riwayat Penjualan" } };

        while (true) {

            Set<Integer> uniqueYears = new HashSet<>();

            System.out.println();
            Header(language[2][selectedLanguage]);

            for (int i = 0; i < orders.length; i++) {
                if (orders[i][0] != null) {
                    LocalDate date = LocalDate.parse(orders[i][14], dateFormat);
                    int year = date.getYear();
                    uniqueYears.add(year);
                }
            }

            int uniqueYearsArray[] = uniqueYears.stream().mapToInt(Integer::intValue).toArray();

            UpperBox();
            SideBox();
            for (int i = 0; i < uniqueYears.size(); i++) {
                TextBox("[" + (i + 1) + "] " + uniqueYearsArray[i]);
                SideBox();
            }

            TextBox("[" + (uniqueYearsArray.length + 1) + "] " + language[0][selectedLanguage]); // back
            SideBox();
            LowerBox();
            InputBox(language[1][selectedLanguage]); // your choice
            int choice = sc.nextInt() - 1;

            if (choice < uniqueYearsArray.length) {
                Delay();
                SalesHistory(uniqueYearsArray[choice]);
                break;

            } else if (choice == uniqueYearsArray.length) {
                Delay();
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
                break;
            }
        }
    }

    // * Profit Report Feature
    static void ViewProfitReport(int day, int month, int year, String type) {

        String language[][] = { { "Profit Report", "Laporan Pendapatan" },
                { "All Time", "Semua Waktu" },
                { "Income", "Pendapatan" },
                { "Sales", "Penjualan" },
                { "Expenses", "Beban" },
                { "Cost of Purchase", "Harga Pokok Pembelian" },
                { "Discount Expense", "Beban Diskon" },
                { "Member Discount Expense", "Beban Diskon Membership" },
                { "TOTAL PROFIT", "TOTAL KEUNTUNGAN" } };

        int totalPenjualan = 0, totalDiskon = 0, totalDiskonMember = 0, totalPembelian = 0, totalKeuntungan,
                totalKerugian;

        for (int i = 0; i < orders.length; i++) {
            if (orders[i][0] != null && orders[i][7].equals("completed")) {
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

        Title();
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                                                                          ║");

        if (type.equals("all time")) {
            System.out.println(
                    "║        " + PadStringLFT(18, language[0][selectedLanguage]) // profit report
                            + "                                              "
                            + PadStringRGT(11, language[1][selectedLanguage]) // all time
                            + "       ║");

        } else if (type.equals("year")) {
            System.out.printf(
                    "║        " + PadStringLFT(18, language[0][selectedLanguage]) // profit report
                            + "                                                     %4d       ║\n",
                    year);

        } else if (type.equals("month")) {
            String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            System.out.printf(
                    "║        " + PadStringLFT(18, language[0][selectedLanguage]) // profit report
                            + "                                            %8s %4d       ║\n",
                    monthName, year);

        } else {
            String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            System.out.printf(
                    "║        " + PadStringLFT(18, language[0][selectedLanguage]) // profit report
                            + "                                         %2d %8s %4d       ║\n",
                    day, monthName, year);

        }

        System.out.println(
                "║                                                                                          ║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║        " + PadStringLFT(11, language[2][selectedLanguage]) // income
                        + " :                                                                     ║");
        System.out.printf(
                "║              " + PadStringLFT(9, language[3][selectedLanguage]) // sales
                        + "                %44s       ║\n",
                MoneyFormatInt(totalPenjualan));
        System.out.println(
                "║                                                                                          ║");

        System.out.println(
                "║        " + PadStringLFT(8, language[4][selectedLanguage]) // expensess
                        + "   :                                                                      ║");
        System.out.printf(
                "║              " + PadStringLFT(21, language[5][selectedLanguage]) // cost of purchase
                        + "    %-44s       ║\n",
                MoneyFormatInt(totalPembelian));
        System.out.printf(
                "║              " + PadStringLFT(23, language[6][selectedLanguage]) // discount expense
                        + "  %-44s       ║\n",
                MoneyFormatInt(totalDiskon));
        System.out.printf(
                "║              " + PadStringLFT(23, language[7][selectedLanguage]) // discount expense
                        + "  %-44s       ║\n",
                MoneyFormatInt(totalDiskonMember));
        System.out.printf(
                "║                                       %44s       ║\n", MoneyFormatInt(totalKerugian));
        System.out.println(
                "║        ---------------------------------------------------------------------------       ║");
        System.out.println(
                "║                                                                                          ║");
        System.out.printf(
                "║        " + PadStringLFT(16, language[8][selectedLanguage]) // discount expense
                        + "               %44s       ║\n",
                MoneyFormatInt(totalKeuntungan));
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════╝");

        AwaitEnter();
        ClearScreen();
    }

    static void ProfitReportByAllTime() {
        ViewProfitReport(0, 0, 0, "all time");
    }

    static void ProfitReportByMonth(int year) {

        String language[][] = { { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Income Reports", "Laporan Pendapatan" }, };

        String[] months = GetMonths();

        while (true) {
            System.out.println();
            Header(language[2][selectedLanguage]);
            UpperBox();
            SideBox();
            for (int i = 0; i < months.length; i++) {
                TextBox("[" + (i + 1) + "] " + months[i]);
                SideBox();
            }

            TextBox("[" + (months.length + 1) + "] " + language[0][selectedLanguage]);
            SideBox();
            LowerBox();
            InputBox(language[1][selectedLanguage]); // your choice
            int month = sc.nextInt();
            sc.nextLine();

            if (month <= 12) {
                Delay();
                ViewProfitReport(0, month, year, "month");
                break;

            } else if (month == months.length) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
                break;
            }
        }
    }

    static void ProfitReportByDay(int year) {

        String language[][] = { { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Income Reports", "Laporan Pendapatan" }
        };

        String[] months = GetMonths();

        while (true) {
            System.out.println();
            Header(language[2][selectedLanguage]);
            UpperBox();
            SideBox();
            for (int i = 0; i < months.length; i++) {
                TextBox("[" + (i + 1) + "] " + months[i]);
                SideBox();
            }

            TextBox("[" + (months.length + 1) + "] " + language[0][selectedLanguage]);
            SideBox();
            LowerBox();
            InputBox(language[1][selectedLanguage]); // your choice
            int month = sc.nextInt();
            sc.nextLine();

            ClearScreen();

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

                InputBox(language[1][selectedLanguage]); // your choice

                int day = sc.nextInt();
                sc.nextLine();

                Delay();
                ViewProfitReport(day, month, year, "day");
                break;

            } else if (month == months.length) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
                break;
            }
        }
    }

    static void ProfitReport(int year) {

        String language[][] = { { "This year", "Tahun ini" },
                { "By month", "Berdasarkan bulan" },
                { "By day", "Berdasarkan hari" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Income Reports", "Laporan Pendapatan" } };

        while (true) {
            System.out.println();
            Header(language[0][selectedLanguage]); // this year
            UpperBox();
            SideBox();
            TextBox("1. " + language[1][selectedLanguage]); // by month
            SideBox();
            TextBox("2. " + language[2][selectedLanguage]); // by day
            SideBox();
            TextBox("3. " + language[3][selectedLanguage]); // back
            SideBox();
            LowerBox();
            System.out.print(language[4][selectedLanguage] + ": "); // your choice
            int choice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (choice) {
                case 1:
                    Delay();
                    ViewProfitReport(0, 0, year, "year");
                    break;

                case 2:
                    Delay();
                    ProfitReportByMonth(year);
                    break;

                case 3:
                    Delay();
                    ProfitReportByDay(year);
                    break;

                case 4:
                    break;

                default:
                    Notification("failure", "Invalid choice!");
                    Delay();
                    break;
            }

            break;
        }
    }

    static void PickReportYear() {

        String language[][] = { { "All time", "Semua waktu" },
                { "Back", "Kembali" },
                { "Your choice : ", "Pilihan anda : " },
                { "Income Reports", "Laporan Pendapatan" } };

        while (true) {
            System.out.println();
            Header(language[3][selectedLanguage]);

            Set<Integer> uniqueYears = new HashSet<>();

            for (int i = 0; i < orders.length; i++) {
                if (orders[i][0] != null) {
                    LocalDate date = LocalDate.parse(orders[i][14], dateFormat);
                    int year = date.getYear();
                    uniqueYears.add(year);
                }
            }

            int uniqueYearsArray[] = uniqueYears.stream().mapToInt(Integer::intValue).toArray();
            UpperBox();
            SideBox();
            for (int i = 0; i < uniqueYears.size(); i++) {
                TextBox("[" + (i + 1) + "] " + uniqueYearsArray[i]);
                SideBox();
            }
            TextBox("[" + (uniqueYearsArray.length + 1) + "] " + language[0][selectedLanguage]); // all time
            SideBox();
            TextBox("[" + (uniqueYearsArray.length + 2) + "] " + language[1][selectedLanguage]);
            SideBox();
            LowerBox();
            InputBox(language[2][selectedLanguage]); // your choice
            int choice = sc.nextInt() - 1;
            sc.nextLine();

            if (choice < uniqueYearsArray.length) {
                Delay();
                ProfitReport(uniqueYearsArray[choice]);
                break;

            } else if (choice == uniqueYearsArray.length) {
                Delay();
                ProfitReportByAllTime();
                break;

            } else if (choice == uniqueYearsArray.length + 1) {
                Delay();
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
                break;
            }
        }
    }

    // * Manage Users
    static void ManageUsers() {
        boolean managingUser = true;

        String[][] userLanguage = {
                { "MANAGE USER MENU", " KELOLA PENGGUNA" },
                { "View all", "Lihat Semua" },
                { "Create new User", "Buat Pengguna Baru" },
                { "Edit User", "Edit Pengguna" },
                { "Delete User", "Hapus Pengguna" },
                { "Back", "Kembali" },
                { "Masukkan pilihan anda : ", "Masukkan pilihan anda: " },
                { "Invalid choice!", "Pilihan tidak valid!" },
                { "Add New User", "Tambah Pengguna Baru" },
                { "Input (-) to not edit the data!", "Masukkan (-) untuk tidak mengedit data!" },
                { "Input new username", "Masukkan username baru" },
                { "Input new password", "Masukkan password baru" },
                { "Input new role", "Masukkan peran baru" },
                { "User successfully edited!", "Pengguna berhasil diedit!" },
                { "Search for a user", "Cari pengguna" },
                { "Input the number to edit the user!", "Masukkan nomor untuk mengedit user" },
                { "> Your choice", "Pilihan anda" }
        };

        while (managingUser == true) {
            Header(userLanguage[0][selectedLanguage]);
            UpperBox();
            SideBox();
            TextBox("[1] " + userLanguage[1][selectedLanguage]); // View all
            SideBox();
            TextBox("[2] " + userLanguage[2][selectedLanguage]); // Create new User
            SideBox();
            TextBox("[3] " + userLanguage[3][selectedLanguage]); // Edit User
            SideBox();
            TextBox("[4] " + userLanguage[4][selectedLanguage]); // Delete User
            SideBox();
            TextBox("[5] " + userLanguage[5][selectedLanguage]); // Back
            SideBox();
            LowerBox();

            System.out.print(userLanguage[6][selectedLanguage]);
            int manageUserChoice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (manageUserChoice) {
                case 1:
                    ViewUserList(null);
                    AwaitEnter();
                    ClearScreen();
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
                    Notification("failure", "Invalid choice!");
                    Delay();
                    break;
            }
        }
    }

    static void ViewUserList(String keyword) {
        String[][] userLanguage = {
                { "User List", "Daftar Pengguna" },
                { "UserName", "Nama Pengguna" },
                { "Password", "Kata Sandi" },
                { "Role", "Peran" }
        };

        Title();
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║" + CenterString(66, userLanguage[0][selectedLanguage]) + "║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + CenterString(5, "No") +
                "|" + CenterString(22, userLanguage[1][selectedLanguage]) +
                "|" + CenterString(22, userLanguage[2][selectedLanguage]) +
                "|" + CenterString(14, userLanguage[3][selectedLanguage]) + "║");
        System.out.println(
                "╠══════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < users.length; i++) {
            if (keyword == null) {
                if (users[i][0] != null) {
                    System.out.println(String.format(
                            "║ %3d ║ %20s ║ %20s ║ %12s ║",
                            i + 1,
                            users[i][0],
                            users[i][1],
                            users[i][2]));
                }

            } else {
                if (users[i][0] != null && users[i][0].toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(String.format(
                            "║ %3d ║ %20s ║ %20s ║ %12s ║",
                            i + 1,
                            users[i][0],
                            users[i][1],
                            users[i][2]));
                }
            }
        }
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════╝");

    }

    static void CreateUser() {

        String[][] userLanguage = {
                { "Add New User", "Tambah Pengguna Baru" },
                { "Insert Username : ", "Masukkan Nama Pengguna : " },
                { "Insert Password : ", "Masukkan Kata Sandi : " },
                { "Insert Role : ", "Masukkan Peran : " },
                { "New user has been successfully added!", "Pengguna baru berhasil ditambahkan!" },
                { "Invalid Role!", "Peran tidak valid!" },
        };

        GetLatestUsers();
        Header(userLanguage[0][selectedLanguage]);
        SideBox();
        InputBox(userLanguage[1][selectedLanguage]);
        users[latestUsers][0] = sc.nextLine();

        SideBox();
        InputBox(userLanguage[2][selectedLanguage]);
        users[latestUsers][1] = sc.nextLine();

        while (true) {
            SideBox();
            InputBox(userLanguage[3][selectedLanguage]);
            String inputRole = sc.nextLine();

            if (inputRole.equals("kasir") || inputRole.equals("manajer") || inputRole.equals("admin")) {
                users[latestUsers][2] = inputRole;
                break;
            } else {
                Notification("failure", userLanguage[5][selectedLanguage]);
            }
        }

        SideBox();
        LowerBox();

        Notification("success", userLanguage[4][selectedLanguage]);
        Delay();
    }

    static void EditUser() {

        String[][] userLanguage = {
                { "Input (-) to not edit the data!", "Masukkan (-) untuk tidak mengedit data!" },
                { "Input new username", "Masukkan nama pengguna baru" },
                { "Input new password", "Masukkan kata sandi baru" },
                { "Input new role", "Masukkan peran baru" },
                { "User successfully edited!", "Pengguna berhasil diedit!" },
                { "Invalid Role!", "Peran tidak valid!" },
                { "Input the number to edit the user!", "Masukkan nomor untuk mengedit user" },
                { "Your choice : ", "Pilihan anda : " },
                { "Search for a user", "Cari pengguna" },
                { "Back", "Kembali" },
                { "Edit User", "Edit Pengguna" }
        };

        String temp, keyword = null;

        while (true) {

            ViewUserList(keyword); // Menampilkan daftar pengguna berdasarkan pencarian

            UpperBox();
            SideBox();
            TextBox("[" + users.length + "] " + userLanguage[8][selectedLanguage]);
            SideBox();
            TextBox("[" + (users.length + 1) + "] " + userLanguage[9][selectedLanguage]);
            SideBox();
            LowerBox();
            System.out.println(userLanguage[6][selectedLanguage]);
            InputBox(userLanguage[7][selectedLanguage]);
            int editUserChoice = sc.nextInt() - 1;
            sc.nextLine();
            ClearScreen();

            if (editUserChoice < users.length && users[editUserChoice][0] != null) {
                // Logika untuk mengedit user
                Header(userLanguage[10][selectedLanguage]);
                SideBox();
                TextBox(userLanguage[0][selectedLanguage]);
                SideBox();
                InputBox(userLanguage[1][selectedLanguage] + " (" + users[editUserChoice][0] + ") : ");
                temp = sc.nextLine();
                if (!temp.equals("-")) {
                    users[editUserChoice][0] = temp;
                }

                SideBox();
                InputBox(userLanguage[2][selectedLanguage] + " (" + users[editUserChoice][1] + ") : ");
                temp = sc.nextLine();
                if (!temp.equals("-")) {
                    users[editUserChoice][1] = temp;
                }

                while (true) {
                    SideBox();
                    InputBox(userLanguage[3][selectedLanguage] + " (" + users[editUserChoice][2] + ") : ");
                    temp = sc.nextLine();
                    if (!temp.equals("-") && (temp.equals("kasir") || temp.equals("manajer") || temp.equals("admin"))) {
                        users[editUserChoice][2] = temp;
                        break;

                    } else if (temp.equals("-")) {
                        break;

                    } else {
                        Notification("failure", userLanguage[5][selectedLanguage]);
                        continue;
                    }
                }

                SideBox();
                LowerBox();
                Notification("success", userLanguage[4][selectedLanguage]);
                Delay();
                break;

            } else if (editUserChoice + 1 == users.length) {
                keyword = Search(keyword); // Metode untuk mencari user berdasarkan keyword

            } else if (editUserChoice + 1 == users.length + 1) {
                break;

            } else {
                Notification("failure", "Invalid choice!");
                Delay();
            }
        }
    }

    static void DeleteUser() {

        String[][] userLanguage = {
                { "User successfully deleted!", "Pengguna berhasil dihapus!" },
                { "Invalid Choice", "Pilihan tidak valid" },
                { "Back", "Kembali" },
                { "Search for user", "Mencari pengguna" },
                { "Input the number to edit the user!", "Masukkan nomor untuk mengedit user" },
                { "Your choice : ", "Pilihan anda : " }
        };

        String keyword = null;

        while (true) {
            ViewUserList(keyword); // Menampilkan daftar pengguna berdasarkan pencarian

            UpperBox();
            SideBox();
            TextBox("[" + users.length + "] " + userLanguage[3][selectedLanguage]);
            SideBox();
            TextBox("[" + (users.length + 1) + "] " + userLanguage[2][selectedLanguage]);
            SideBox();
            LowerBox();
            System.out.println(userLanguage[4][selectedLanguage]);
            InputBox(userLanguage[5][selectedLanguage]);
            int deleteUserChoice = sc.nextInt() - 1;
            sc.nextLine();
            ClearScreen();

            if (deleteUserChoice < users.length && users[deleteUserChoice][0] != null) {
                // Logika untuk menghapus user
                for (int i = 0; i < users[deleteUserChoice].length; i++) {
                    users[deleteUserChoice][i] = null;
                }

                // Mengkompres array untuk mengisi ruang kosong setelah penghapusan
                for (int i = deleteUserChoice; i < users.length - 1; i++) {
                    if (users[i][0] == null && users[i + 1][0] != null) {
                        for (int j = 0; j < users[i].length; j++) {
                            users[i][j] = users[i + 1][j];
                            users[i + 1][j] = null;
                        }
                    }
                }

                Notification("success", userLanguage[0][selectedLanguage]);
                Delay();

            } else if (deleteUserChoice + 1 == users.length) {
                keyword = Search(keyword); // Metode untuk mencari user berdasarkan keyword

            } else if (deleteUserChoice + 1 == users.length + 1) {
                break;

            } else {
                Notification("failure", userLanguage[1][selectedLanguage]);
                Delay();
                return;
            }
        }
    }

    // * Swtich Language
    static void SwitchLanguage() {
        Header("SELECT LANGUAGE");
        UpperBox();
        SideBox();
        TextBox("[1] English");
        SideBox();
        TextBox("[2] Bahasa Indonesia");
        SideBox();
        LowerBox();
        System.out.print("> Your choice : ");
        int languageChoice = sc.nextInt();
        sc.nextLine();

        switch (languageChoice) {
            case 1:
                selectedLanguage = 0; // English
                break;
            case 2:
                selectedLanguage = 1; // Bahasa Indonesia
                break;
            default:
                Notification("failure", "Invalid choice!");
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

    static void Start() {

        String language[][] = {
                { "Login", "Masuk" },
                { "Switch language", "Ganti bahasa" },
                { "Exit program", "Keluar program" },
                { "Your choice : ", "Pilihan anda : " }
        };

        while (true) {

            Title();

            UpperBox();
            SideBox();
            TextBox("[1] " + language[0][selectedLanguage]);
            SideBox();
            TextBox("[2] " + language[1][selectedLanguage]);
            SideBox();
            TextBox("[3] " + language[2][selectedLanguage]);
            SideBox();
            LowerBox();
            InputBox(language[3][selectedLanguage]);
            int choice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (choice) {
                case 1:
                    Login();
                    break;

                case 2:
                    SwitchLanguage();
                    continue;

                case 3:
                    ExitProgram();
                    break;

                default:
                    Notification("failure", "Invalid choice!");
                    break;
            }

            break;
        }
    }

    static String CenterString(int width, String text) {
        int padSize = (width - text.length()) / 2;
        return String.format("%" + padSize + "s%s%" + (padSize + (width - text.length()) % 2) + "s", "", text, "");
    }

    static String PadStringLFT(int width, String text) {
        return String.format("%-" + width + "s", text);
    }

    static String PadStringRGT(int width, String text) {
        return String.format("%" + width + "s", text);
    }

    // * login
    static void Login() {

        String[][] loginLanguage = {
                { "PLEASE LOGIN", "SILAHKAN LOGIN" },
                { "Input username : ", "Masukkan username : " },
                { "Input password : ", "Masukkan password : " },
                { "Login successful!", "Login Berhasil!" },
                { "Username and password are incorrect!", "Username dan password salah!" }
        };

        while (!access) {

            Header(loginLanguage[0][selectedLanguage]);

            SideBox();
            InputBox(loginLanguage[1][selectedLanguage]);
            String inputUsername = sc.nextLine();
            SideBox();
            InputBox(loginLanguage[2][selectedLanguage]);
            String inputPassword = sc.nextLine();
            SideBox();
            LowerBox();

            for (int i = 0; i < users.length; i++) {
                if (inputUsername.equals(users[i][0]) && inputPassword.equals(users[i][1])) {
                    user_id = i;
                    Notification("success", loginLanguage[3][selectedLanguage]);
                    access = true;

                    Delay();
                    break;
                }
            }

            if (!access) {
                Notification("failure", loginLanguage[4][selectedLanguage]);

                Delay();
            }

        }
    }

    static void Logout() {

        String[][] logoutLanguage = {
                { "Logout successful!", "Logout berhasil!" },
                { "Logout successful!", "Logout berhasil!" }
        };

        access = false;
        Notification("success", logoutLanguage[0][selectedLanguage]);
    }

    static boolean CheckLevel(String level) {
        if (users[user_id][2].equals(level)) {
            return true;

        } else {
            return false;
        }

    }

    static String Search(String keyword) {

        String language[][] = {
                { "To reset search keyword input (-)!", "Untuk mereset keyword pencarian masukkan (-)!" },
                { "Input keyword", "Masukkan keyword" },
                { "Search Something", "Cari Sesuatu" } };

        Header(language[2][selectedLanguage]);
        UpperBox();
        SideBox();
        TextBox(language[0][selectedLanguage]);
        SideBox();
        InputBox(language[1][selectedLanguage] + " (" + keyword + ") : ");
        keyword = sc.nextLine();
        SideBox();
        LowerBox();
        Delay();

        if (!keyword.equals("-")) {
            return keyword;

        } else {
            return null;
        }
    }

    static void Delay() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ClearScreen();
    }

    static void AwaitEnter() {

        String language[][] = { { "Press Enter to continue... ", "Tekan Enter untuk lanjut..." } };
        InputBox(language[0][selectedLanguage]);
        sc.nextLine();
    }

    static void ClearScreen() {
        System.out.println(CLEARSCREEN);
    }

    static void Notification(String type, String msg) {
        if (type.equals("success")) {
            System.out.println(
                    "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + GREEN + CenterString(128, msg) + RESET + "║");
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        } else if (type.equals("failure")) {
            System.out.println(
                    "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + RED + CenterString(128, msg) + RESET + "║");
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        } else if (type.equals("warning")) {

            System.out.println(
                    "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + YELLOW + CenterString(128, msg) + RESET + "║");
            System.out.println(
                    "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
    }

    static void Header(String msg) {
        Title();
        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + BOLD + CenterString(128, msg) + REGULAR + "║");
        System.out.println(
                "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void UpperBox() {
        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
    }

    static void TextBox(String text) {
        System.out.println("║ " + PadStringLFT(126, text) + " ║");
    }

    static void SideBox() {
        System.out.println(
                "║                                                                                                                                ║");
    }

    static void LowerBox() {
        System.out.println(
                "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    static void InputBox(String msg) {
        System.out.print("║ > " + msg);
    }

    static String MoneyFormat(String money) {
        return String.format("%,d", Integer.parseInt(money));
    }

    static String MoneyFormatInt(int money) {
        return String.format("%,d", money);
    }

    static void Title() {
        System.out.println(
                "╔╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╗");
        System.out.println(
                "╠╬╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╬╣");
        System.out.println(
                "╠╣------------------------------------------------------------------------------------------------------------------------------╠╣");
        System.out.println(
                "╠╣------------------------------------------------██████╗-█████╗-███████╗███████╗-----------------------------------------------╠╣");
        System.out.println(
                "╠╣-----------------------------------------------██╔════╝██╔══██╗██╔════╝██╔════╝-----------------------------------------------╠╣");
        System.out.println(
                "╠╣-----------------------------------------------██║-----███████║███████╗███████╗-----------------------------------------------╠╣");
        System.out.println(
                "╠╣-----------------------------------------------██║-----██╔══██║╚════██║╚════██║-----------------------------------------------╠╣");
        System.out.println(
                "╠╣-----------------------------------------------╚██████╗██║--██║███████║███████║-----------------------------------------------╠╣");
        System.out.println(
                "╠╣-----------------------------------------------╚═════╝╚═╝--╚═╝╚══════╝╚══════╝------------------------------------------------╠╣");
        System.out.println(
                "╠╣------------------------------------------------------------------------------------------------------------------------------╠╣");
        System.out.println(
                "╠╬╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╦╬╣");
        System.out.println(
                "╚╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╩╝");
    }

    public static void main(String[] args) {

        String language[][] = { { "Make Order", "Buat Pesanan" },
                { "Sales History", "Riwayat Penjualan" },
                { "Profit Report", "Laporan Pendapatan" },
                { "Manage Menu Item", "Manajemen Item Menu" },
                { "Manage Stock", "Manajemen Stok" },
                { "Manage Discount", "Manajemen Diskon" },
                { "Manage User", "Manajemen User" },
                { "Your choice : ", "Pilihan anda : " },
                { "Welcome to cafe the orange", "Selamat datang di cafe the orange" }
        };

        Init();

        while (session)

        {
            if (!access) {
                Start();

                if (!session) {
                    break;
                }
            }

            // menu
            if (selectedLanguage == 0) {
                Header("Welcome " + users[user_id][0] + " to CASS");
            } else {
                Header("Selamat datang " + users[user_id][0] + " to CASS");
            }
            UpperBox();
            SideBox();
            TextBox("[1] " + language[0][selectedLanguage]); // make order
            SideBox();
            TextBox("[2] " + language[1][selectedLanguage]); // sales history
            SideBox();
            if (users[user_id][2].equals("manager") || users[user_id][2].equals("admin")) {
                TextBox("[3] " + language[2][selectedLanguage]); // profit report
                SideBox();
                TextBox("[4] " + language[3][selectedLanguage]); // manage menu item
                SideBox();
                TextBox("[5] " + language[4][selectedLanguage]); // manage stock
                SideBox();
                TextBox("[6] " + language[5][selectedLanguage]); // manage discount
            }
            if (users[user_id][2].equals("admin")) {
                SideBox();
                TextBox("[7] " + language[6][selectedLanguage]); // manage user
            }
            SideBox();
            TextBox("[0] Logout");
            SideBox();
            LowerBox();
            System.out.print(language[7][selectedLanguage]); // your choice :
            int choice = sc.nextInt();
            sc.nextLine();
            ClearScreen();

            switch (choice) {

                case 1:
                    CreateOrder();
                    continue;

                case 2:
                    PickHistoryYear();
                    break;

                case 3:
                    if (CheckLevel("manajer") || CheckLevel("admin")) {
                        PickReportYear();
                    }
                    break;

                case 4:
                    if (CheckLevel("manajer") || CheckLevel("admin")) {
                        ManageItems();
                    }
                    break;

                case 5:
                    if (CheckLevel("manajer") || CheckLevel("admin")) {
                        ManageStock();
                    }
                    break;

                case 6:
                    if (CheckLevel("manajer") || CheckLevel("admin")) {
                        ManageDiscount();
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
                    Notification("failure", "Your choice does not exist!");
                    break;
            }
        }

    }
}