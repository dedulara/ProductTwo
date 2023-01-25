import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Product> linesTwo = new ArrayList<>();

        final int FILEDS_LENGTH = 4;

        String id, productName, productDesc;
        double productCost;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] fields;
                    fields = rec.split(",");
                    if(fields.length == FILEDS_LENGTH)
                    {
                        id          = fields[0].trim();
                        productName = fields[1].trim();
                        productDesc = fields[2].trim();
                        productCost = Double.parseDouble(fields[3].trim());
                        Product productRec = new Product(id, productName, productDesc, productCost);
                        linesTwo.add(productRec);
                        line++;
                    }
                    else
                    {
                        System.out.println("Four a record that may be corrupt: ");
                        System.out.println(rec);
                    }
                }
                reader.close();

                String[] fields;
                System.out.printf("\n%-8s%-20s%-35s%-6s\n", "ID", "PRODUCT NAME", "DESCRIPTION", "COST");
                int counterInt = 0;
                while(counterInt <= 75)
                {
                    System.out.print("=");
                    counterInt++;
                }
                for(Product l:linesTwo)
                {
                    id = l.getIdString();
                    productName = l.getNameString();
                    productDesc = l.getDescriptString();
                    productCost = l.getCostDouble();
                    System.out.printf("\n%-8s%-20s%-35s%.2f", id, productName, productDesc, productCost);
                }
            }
            else
            {
                System.out.println("Faled to choose a file to process.");
                System.out.println("Run the program again.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}