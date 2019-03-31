package data;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InstantSerializer implements JsonSerializer<Instant> {
  public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone( ZoneId.of("UTC"));
    return new JsonPrimitive(timeFormatter.format(src));
  }
}
