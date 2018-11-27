import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String ProductName;
    private int UnitsOnStock;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_FK")
    private Supplier supplierID;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_FK")
    private Category categoryID;

    public Product() {
    }

    Product(String productName, int unitsOnStock) {
        ProductName = productName;
        UnitsOnStock = unitsOnStock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getUnitsOnStock() {
        return UnitsOnStock;
    }

    public void setUnitsOnStock(int unitsOnStock) {
        UnitsOnStock = unitsOnStock;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
        this.categoryID.getProducts().add(this);
    }

    public Supplier getSupplierID() {
        return supplierID;
    }

    void setSupplierID(Supplier supplierID) {
        this.supplierID = supplierID;
        this.supplierID.getProducts().add(this);
    }


}