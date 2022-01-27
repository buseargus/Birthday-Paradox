package DataProje1;

import java.util.Random;

public class BirthdayParadox {

    public static int[][] table = new int[5][16];    //Özet tabloyu tutar.
    public static int[] n = {2, 3, 5, 10, 20};      //Deneyde kullanılacak n değerlerini tutar.
    public static int repeatNum = 15;    //Gerçekleştirilecek deney sayısını tutar.
    public static void main(String[] args) {
        int index = -1; //Tabloya (table) veriler eklenirken kullanılır.
        for (int value : n) {
            System.out.println("n = " + value);
            index += 1;
            for (int i = 0; i < repeatNum; i++) {
                int[] match = generateBirthday(value);
                table[index][i] = findMatchNumber(match);
                System.out.println((i+1) + ". deney sonucu: " + matchToString(match));
            }
            table[index][repeatNum] = findAverage(index);
            System.out.println("Özet Tablo: ");
            summaryTable();
        }
    }

    //Bu metotta verilen kişi sayısı (n) değerine göre rastgele doğum günleri üretilir
    //ve array olarak döndürülür.
    public static int[] generateBirthday(int n){
        int[] match = {0,0,0,0,0,0,0};
        Random rand = new Random();
        //Burada n değeri kadar rastgele doğum günleri üretilip match arrayine atılır.
        for (int i = 0; i < n; i++){
            int rand_int = rand.nextInt(7);
            match[rand_int] += 1;
        }
        //Burada amaç 2 kişinin aynı günde doğması 1 çakışma anlamına geldiğinden;
        //yukarıda üretilip match'e atılmış doğum günlerindeki gerçek çakışma sayısını bulmaktır.
        for (int j = 0; j < match.length; j++){
            if (match[j] != 0) {
                match[j] -= 1;
            }
        }
        return match;
    }

    //çakışma sayılarını içeren array'i parametre olarak alır ve bir deneydeki toplam çakışma
    //sayısını bulur, tabloya(table) yerleştirilmek üzere döndürür.
    public static int findMatchNumber(int[] match) {
        int matchNumber = 0;
        for (int value : match) {
            matchNumber += value;
        }
        return matchNumber;
    }

    //her bir n değeri için deneyler yapıldıktan sonra, bu deneylerin ortalamasını alır ve
    //tablonun sonuna eklemek üzere döndürür.
    public static int findAverage(int index) {
        int average = 0;
        for(int i = 0; i < table[index].length; i++) {
            average += table[index][i];
        }
        return  average/repeatNum;
    }

    //n'in her bir değeri için bulunan çakışmaların ve ortalama çakışma ssayılarının bulunduğu
    //özet tablonun yazdırılmasını sağlar.
    public static void summaryTable(){
        System.out.println("\t2\t3\t5\t10\t20");
        for (int i = 0; i <= repeatNum; i++) {
            System.out.print(i+1 + "\t");
            for (int j = 0; j < n.length; j++) {
                System.out.print(table[j][i] + "\t");
            }
            System.out.println("");
        }
    }

    //generateBirthday metodu ile üretilmiş olan array'in günlerle eşleştirilmiş olarak
    //ekrana yazdırılmasını sağlar.
    public static String matchToString(int[] match) {
        String days[] = {"Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar"};
        String s = "";
        for (int i = 0; i < days.length; i++) {
            s += "\t" + days[i] + " = " + match[i];
        }
        return s;
    }
}
