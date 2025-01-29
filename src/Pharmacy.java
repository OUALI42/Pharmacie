import java.util.ArrayList;
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

//    public void inventory_update(){
//        if(la commande est passer) {
//            for (Product p : ProductList) {
//                if(p.Name == p.Name) {
//                    p.Quantity = p.Quantity - 1;
//                }
//            }
//        }
//    }


    public String warning_message(){
        StringBuilder message = new StringBuilder();
        for (Product p : ProductList) {
            if(p.Quantity <= 5) {
                message.append(p.Name).append("is too low!\n");
            }
        }
        return message.toString();
    }


    public void classification() {
        System.out.println("List of medications sorted by quantity:");

        List<Product> sortedList = new ArrayList<>(ProductList);
        for (int i = 1; i < sortedList.size(); i++) {
            Product key = sortedList.get(i); // is the location where Key should be placed
            int j = i - 1; // previous item

            while (j >= 0 && sortedList.get(j).Quantity > key.Quantity) {
                sortedList.set(j + 1, sortedList.get(j)); // place the element to the right
                j--;
            }
            sortedList.set(j + 1, key); // is the location where Key should be placed
        }


        for (Product p : sortedList) {
            System.out.println("- Name: " + p.Name + " | Quantity: " + p.Quantity);
        }

        System.out.println("\n⚠️ Low stock products:");
        System.out.println(warning_message());
    }
}



