import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dog.DogController;
import io.javalin.ForbiddenResponse;
import io.javalin.Javalin;
import io.javalin.NotFoundResponse;
import io.javalin.json.JavalinJson;
import java.util.HashMap;
import java.util.Map;
import user.UserController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder().create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    Javalin app = Javalin.create().enableCorsForAllOrigins().start(7000);

    app.exception(NumberFormatException.class, ((e, ctx) -> {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", e.getMessage());
      ctx.status(404).json(message);
    }));

    app.exception(JsonSyntaxException.class, ((e, ctx) -> {
      Map<String, String> message = new HashMap<>();
      message.put("status", "400");
      message.put("details", e.getMessage());
      ctx.status(400).json(message);
    }));

    app.routes(() -> {
      path("dogs", () -> {
        get(DogController.getAll);
        post(DogController.create);
        path(":id", () -> {
          get(DogController.get);
          patch(DogController.update);
        });
      });
      path("users", () -> {
        get(UserController.getAll);
        post(UserController.create);
        path(":username", () -> {
          get(UserController.get);
//          delete(UserController.delete);
        });
      });
      path("auctions", () -> {

      });
      path("account", () -> {
        path(":myDogs", () -> {

        });
        path(":myAuctions", () -> {

        });
      });

    });
  }
}
