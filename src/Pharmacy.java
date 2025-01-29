import java.util.List;

public class Pharmacy {
    List<Product> ProductList;
    public Pharmacy(List<Product> ProductList) {
        this.ProductList = ProductList;
    }
    void ShowProducts(){
        for(Product p : ProductList){
            System.out.println("This pharmacy possesses "+p.Quantity +" "+p.Name +"s. They're "+ p.Product +"s and they cost "+p.Price+"$.");
        }
    }

    public List<Product> getProductList() {
        return ProductList;
    }
}
