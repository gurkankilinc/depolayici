import java.util.HashMap;
import java.util.Scanner;

public class StokTakip {

    private HashMap<String, Integer> urunStok;


    public StokTakip() {
        urunStok = new HashMap<>();
    }


    public void urunEkle(String urunAdi, int stokMiktari) {
        if (urunAdi == null || urunAdi.isEmpty() || stokMiktari <= 0) {
            System.out.println("Geçersiz giriş! Ürün adı boş olamaz ve stok miktarı sıfırdan büyük olmalıdır.");
            return;
        }
        if (urunStok.containsKey(urunAdi)) {

            int mevcutStok = urunStok.get(urunAdi);
            urunStok.put(urunAdi, mevcutStok + stokMiktari);
            System.out.println(urunAdi + " stoğuna " + stokMiktari + " eklendi. Yeni stok: " + (mevcutStok + stokMiktari));
        } else {

            urunStok.put(urunAdi, stokMiktari);
            System.out.println(urunAdi + " stoğu eklendi. Mevcut stok: " + stokMiktari);
        }
    }


    public void stokSorgula(String urunAdi) {
        if (urunAdi == null || urunAdi.isEmpty()) {
            System.out.println("Geçersiz ürün adı girdiniz!");
            return;
        }
        if (urunStok.containsKey(urunAdi)) {
            int stokMiktari = urunStok.get(urunAdi);
            if (stokMiktari > 0) {
                System.out.println(urunAdi + " için mevcut stok: " + stokMiktari);
            } else {
                System.out.println(urunAdi + " stoğu tükenmiştir.");
            }
        } else {
            System.out.println(urunAdi + " stoğu bulunamadı.");
        }
    }


    public void alisverisYap(String urunAdi, int miktar) {
        if (urunAdi == null || urunAdi.isEmpty() || miktar <= 0) {
            System.out.println("Geçersiz giriş! Ürün adı boş olamaz ve miktar sıfırdan büyük olmalıdır.");
            return;
        }
        if (urunStok.containsKey(urunAdi)) {
            int mevcutStok = urunStok.get(urunAdi);
            if (miktar <= mevcutStok) {
                urunStok.put(urunAdi, mevcutStok - miktar);
                if (mevcutStok - miktar == 0) {
                    System.out.println("Alışveriş başarılı. " + urunAdi + " stoğu tükenmiştir.");
                } else {
                    System.out.println("Alışveriş başarılı. " + urunAdi + " için kalan stok: " + (mevcutStok - miktar));
                }
            } else {
                System.out.println("Yeterli stok yok! " + urunAdi + " için mevcut stok: " + mevcutStok);
            }
        } else {
            System.out.println(urunAdi + " stoğu bulunamadı.");
        }
    }

    public void anaMenu() {
        System.out.println("\n=== STOK TAKİP SİSTEMİ ===");
        System.out.println("1. Ürün Ekle/Güncelle");
        System.out.println("2. Stok Sorgula");
        System.out.println("3. Alışveriş Yap");
        System.out.println("4. Çıkış");
        System.out.print("Bir seçenek seçin: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StokTakip stokTakip = new StokTakip();

        System.out.println("<=== STOK TAKİP SİSTEMİNE HOŞ GELDİNİZ ===>");
        System.out.println("Bu sistem, ürünlerinizin stok durumunu yönetmenize yardımcı olur.");
        System.out.println("---------------------------------------------------------");

        while (true) {
            stokTakip.anaMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Geçersiz seçim! Lütfen bir sayı girin.");
                scanner.next();
                stokTakip.anaMenu();
            }
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.print("Ürün adını girin: ");
                    String urunAdi = scanner.nextLine();
                    System.out.print(urunAdi + " için eklenecek stok miktarını girin: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Geçersiz stok miktarı! Lütfen bir sayı girin.");
                        scanner.next();
                    }
                    int stokMiktari = scanner.nextInt();
                    stokTakip.urunEkle(urunAdi, stokMiktari);
                    break;
                case 2:
                    System.out.print("Stok sorgulamak istediğiniz ürün adını girin: ");
                    urunAdi = scanner.nextLine();
                    stokTakip.stokSorgula(urunAdi);
                    break;
                case 3:
                    System.out.print("Alışveriş yapmak istediğiniz ürün adını girin: ");
                    urunAdi = scanner.nextLine();
                    System.out.print("Almak istediğiniz miktarı girin: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Geçersiz miktar! Lütfen bir sayı girin.");
                        scanner.next();
                    }
                    int miktar = scanner.nextInt();
                    stokTakip.alisverisYap(urunAdi, miktar);
                    break;
                case 4:
                    System.out.println("Programdan çıkılıyor. İyi günler!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }
}

