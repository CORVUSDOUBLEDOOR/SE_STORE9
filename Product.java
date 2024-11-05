public class Product {
    String nameProduct;
    String id;
    Double cost;
    int quantity;
    String idCategory;

    public Product(String id, String nameProduct , Double cost, int quantity, String idCategory) {
        this.nameProduct = nameProduct;
        this.id = id;
        this.cost = cost;
        this.quantity = quantity;
        this.idCategory = idCategory;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
}
