import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        ArrayList<Product> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        File workingdirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingdirectory.getPath() + "\\src\\productData.txt");

        String productRecord = "";
        String idString = "";
        String nameString = "";
        String descriptString = "";
        double costDouble = 0;
        boolean done = false;

        do
        {
            idString = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]" );
            nameString = SafeInput.getNonZeroLenString(in, "Enter product name" );
            descriptString = SafeInput.getNonZeroLenString(in, "Enter product description" );
            costDouble = SafeInput.getDouble(in, "Enter the price");

            Product productInput = new Product(idString, nameString, descriptString, costDouble);
            products.add(productInput);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }
        while(!done);

        for (Product p: products)
            System.out.println(p);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for (Product rec : products) {
                String recString = rec.toCSVDataRecord();
                writer.write(recString, 0, recString.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}