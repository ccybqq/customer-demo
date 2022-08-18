package pers.common;

import org.springframework.http.ResponseEntity;

public interface IController<T> {
    ResponseEntity<T> add(T t);
    String get(int id);
    ResponseEntity<T> update(T t);
    ResponseEntity<T> delete(int id);
}
