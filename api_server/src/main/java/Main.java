import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dog.DogController;
import io.javalin.Javalin;
import io.javalin.json.JavalinJson;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder().create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

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
