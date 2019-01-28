import dog.DogController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

  public static void main(String[] args) {
    Javalin app = Javalin.create().start(7000);
    app.routes(() -> {
      path("dogs", () -> {
        get(DogController.getAll);
        post(DogController.create);
        path(":id", () -> {
          get(DogController.get);
//                   patch(DogController.update);
//                   delete(DogController.delete);
        });
      });
    });
  }
}
