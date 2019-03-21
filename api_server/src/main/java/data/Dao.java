package data;

import java.util.List;

public interface Dao<T, K> {
  T get(K id);
  List<T> getAll();
  T save(T t);
  T update(T t);
  void delete(T t);
}
