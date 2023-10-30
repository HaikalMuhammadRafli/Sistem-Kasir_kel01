import java.util.Scanner;

/**
 * main
 */
public class Main {

    public static void main(String[] args) {

        boolean session = true, access = false, ordering, stocking;
        String inputUsername, inputPassword;
        int choice1, choice2, choice3;
        double total = 0, subTotal;
        String item[] = { "Ayam Bakar", "Es Teh" };
        int stok[] = { 40, 50 };
        // String username = "admin", password = "admin123";
        // int hargaAyamBakar = 13000;
        // int hargaEsTeh = 3000;
        // int stokAyamBakar = 40;
        // int stokEsTeh = 50;

        Scanner sc = new Scanner(System.in);

        while (session == true) {
            // login
            while (access == false) {
                System.out.println("LOGIN");
                System.out.print("Input username : ");
                inputUsername = sc.nextLine();
                System.out.print("Input password : ");
                inputPassword = sc.nextLine();

                // TODO: ganti menggunakan array username dan password
                // if (inputUsername.equals(username) && inputPassword.equals(password)) {
                // System.out.println("Login success!");
                // System.out.println("Welcome " + username);
                // access = true;
                // }

            }
            // menu

            System.out.println();
            System.out.println("Cafe The Orange!");
            System.out.println("Choose 1 to make an order");
            System.out.println("Choose 2 to add stok");
            System.out.println("Choose 3 to exit the program");
            System.out.print("Input your answer : ");
            choice1 = sc.nextInt();

            switch (choice1) {
                case 1:

                    ordering = true;
                    while (ordering == true) {
                        System.out.println("Cafe The Orange Menu!");
                        // TODO: Gunakan looping dan array
                        // System.out.println("Pilih 1 untuk pesan Ayam Bakar (" + stokAyamBakar + ")");
                        // System.out.println("Pilih 2 untuk pesan Es Teh (" + stokEsTeh + ")");
                        System.out.println("Pilih 3 untuk selesai");
                        System.out.println("");
                        System.out.println("Total : Rp " + total);
                        System.out.println("");
                        System.out.print("Pilihan : ");

                        choice2 = sc.nextInt();

                        switch (choice2) {
                            case 1:
                                System.out.println("Beli Berapa PCS (Ayam Bakar)?");
                                int jumlahAyamBakar = sc.nextInt();
                                // Todo: Gunakan perhitungan array
                                // subTotal = hargaAyamBakar * jumlahAyamBakar;
                                // total += subTotal;
                                // subTotal = 0;
                                // stokAyamBakar -= jumlahAyamBakar;
                                break;

                            case 2:
                                System.out.println("Beli Berapa PCS (Es Teh)?");
                                int jumlahEsTeh = sc.nextInt();
                                // Todo: Gunakan perhitungan array
                                // subTotal = hargaEsTeh * jumlahEsTeh;
                                // total += subTotal;
                                // subTotal = 0;
                                // stokEsTeh -= jumlahEsTeh;
                                break;

                            case 3:
                                System.out.println("Thank you!");
                                System.out.println("Your total : " + total);
                                ordering = false;
                                break;

                            default:
                                System.out.println("Menu tersebut tidak ada!");
                                System.out.println("Silahkan coba lagi!");
                                break;
                        }
                    }
                    break;

                case 2:
                    stocking = true;

                    while (stocking == true) {
                        System.out.println("Cafe The Orange Stok!");
                        // Todo: Gunakan perhitungan array
                        for (int i = 0; i < item.length; i++) {
                            System.out.println(
                                    "Pilih " + (i + 1) + " untuk tambah stok " + item[i] + " (" + stok[i] + ")");
                        }

                        System.out.println("Pilih 1 untuk tambah stok Ayam Bakar (" + stok[0] + ")");
                        System.out.println("Pilih 2 untuk tambah stok Es Teh (" + stok[1] + ")");
                        System.out.println("Pilih 5 untuk selesai");
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