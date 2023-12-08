package demo.reservation.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusEnum {
  SUCCESS(200, "Success"),
  CREATED_SUCCESS(201, "Created"),
  DELETE_SUCCESS(204, "No_Content");
  private final int statusCode;
  private final String message;
}
