package app.routes;

import app.controllers.ReceiptController;
import app.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class ReceiptRoutes {
ReceiptController receiptController = new ReceiptController();
    public EndpointGroup getRoutes(){
        return () ->{
        get("/", receiptController::readAll, SecurityRoutes.Role.ADMIN, SecurityRoutes.Role.USER);
        post("/", receiptController::create, SecurityRoutes.Role.ADMIN);
        };
    }
}
