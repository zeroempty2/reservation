package demo.reservation.util;

import demo.reservation.common.dto.StatusResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponse {

  public static final ResponseEntity<StatusResponseDto> RESPONSE_OK = ResponseEntity
      .status(HttpStatus.OK).body(new StatusResponseDto(200,"Success"));

  public static final ResponseEntity<StatusResponseDto> RESPONSE_CREATED = ResponseEntity.status(
      HttpStatus.CREATED).build();

  public static final ResponseEntity<StatusResponseDto> RESPONSE_DELETE = ResponseEntity.status(
      HttpStatus.NO_CONTENT).build();

}
