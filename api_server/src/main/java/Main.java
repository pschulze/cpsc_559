import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import auction.AuctionController;
import dog.DogController;
import io.javalin.Javalin;
import io.javalin.json.JavalinJson;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import user.UserController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

  public static void main(String[] args) {
    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    exec.scheduleAtFixedRate(
      () -> { AuctionController.endAuctions(); },
      1,
      1,
      TimeUnit.SECONDS);
    Gson gson = new GsonBuilder().create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    Javalin app = Javalin.create().enableCorsForAllOrigins().start(7000);

    app.exception(NumberFormatException.class, ((e, ctx) -> {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", e.getMessage());
      ctx.status(404).json(message);
    }));

    app.exception(JsonSyntaxException.class, ((e, ctx) -> {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 400);
      message.put("details", e.getMessage());
      ctx.status(400).json(message);
    }));

    app.routes(() -> {
      path("users", () -> {
        get(UserController.getAll);
        post(UserController.create);
        path(":id", () -> {
          get(UserController.get);
//          delete(UserController.delete);
        });
      });
      path("dogs", () -> {
        get(DogController.getAll);
        post(DogController.create);
        path(":id", () -> {
          get(DogController.get);
          patch(DogController.update);
        });
      });
      path("auctions", () -> {
        get(AuctionController.getAll);
        post(AuctionController.create);
        path(":id", () -> {
          get(AuctionController.get);
          post(AuctionController.placeBid);
        });
      });
    });
  }
}
