package app.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {
    ReceiptRoutes receiptRoutes = new ReceiptRoutes();
    ProductRoutes productRoutes = new ProductRoutes();
    public EndpointGroup getRoutes(){
        return () -> {

          get("/", ctx -> ctx.result("Hello World"));
          path("/products", productRoutes.getRoutes());
          path("/receipts", receiptRoutes.getRoutes());
        };
    }
}
