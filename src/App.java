import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Sale> saleList = new ArrayList<>();

        System.out.print("Enter the file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String readLine = br.readLine();

            while (readLine != null) {
                String[] fields = readLine.split(",");
                Integer month = Integer.parseInt(fields[0]);
                Integer year = Integer.parseInt(fields[1]);
                String seller = fields[2];
                Integer items = Integer.parseInt(fields[3]);
                Double total = Double.parseDouble(fields[4]);

                saleList.add(new Sale(month, year, seller, items, total));

                readLine = br.readLine();
            }

            Comparator<Sale> comp = (s1, s2) -> s1.averagePrice().compareTo(s2.averagePrice());

            List<Sale> streamList = saleList.stream()
                    .filter(s -> s.getYear() == 2016)
                    .sorted(comp.reversed()).limit(5)
                    .collect(Collectors.toList());

            System.out.println();
            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");
            streamList.forEach(System.out::println);
            
            Double sum = saleList.stream()
                    .filter(s -> s.getSeller().equals("Logan"))
                    .filter(s -> s.getMonth() == 1 || s.getMonth() == 7)
                    .map(s -> s.getTotal())
                    .reduce(0.0, (x,y) -> x + y);

            System.out.println();
            System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = R$ " + String.format("%.2f", sum));
            System.out.println();
        } 
        catch (IOException IOE) {
            System.out.println("Error: " + path + " (O sistema não pode encontrar o arquivo especificado)");
        }

        sc.close();
    }
}
