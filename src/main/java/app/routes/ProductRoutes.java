package app.routes;

import app.controllers.ReceiptController;
import app.controllers.ProductController;
import app.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ProductRoutes {

    public EndpointGroup getRoutes(){
        ProductController productController = new ProductController();
        ReceiptController receiptController = new ReceiptController();
        return () -> {
          get("/", productController::readAll, SecurityRoutes.Role.ADMIN, SecurityRoutes.Role.USER); // this can read all trips and search with a given category
          get("/{id}", productController::read, SecurityRoutes.Role.ADMIN, SecurityRoutes.Role.USER); // this can read with given id
          post("/", productController::create, SecurityRoutes.Role.ADMIN); // This create a trip
          put("/{id}", productController::update, SecurityRoutes.Role.ADMIN); // this updates a trip
          delete("/{id}", productController::delete, SecurityRoutes.Role.ADMIN); // this deletes a trip

        };
    }
}
