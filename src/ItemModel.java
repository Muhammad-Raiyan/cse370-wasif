import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ItemModel {
    private int id; // Auto set
    private String name;
    private int quantity;
    private double price;
    private Date purchaseDate;
    private Date timestamp; // Auto set
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd-yyyy");
    private SimpleDateFormat timestampFormat = new SimpleDateFormat("mm-dd-yyyy hh.mm.ss");

    public ItemModel() {
    }

    public ItemModel(String input) {
        // Split input string
        String[] values = input.split("\\|");

        // Trim spaces
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
        }

        this.name = values[0];
        this.quantity = Integer.parseInt(values[1]);
        this.price = Double.parseDouble(values[2]);
        try {
            this.purchaseDate = simpleDateFormat.parse(values[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ItemModel(String name, int quantity, double price, Date purchaseDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return id + "|" +
                name + "|" +
                quantity + "|" +
                price + "|" +
                simpleDateFormat.format(purchaseDate) + "|" +
                timestampFormat.format(timestamp) + "\n";
    }

    public String getItemDescriptor() {
        return "Inventory Number|Item Description|Quantity|Price|Date Purchased|Timestamp\n";
    }
}
