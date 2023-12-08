package demo.reservation.store.dto;

import demo.reservation.store.entity.StoreDesignatedDateInfo;
import demo.reservation.util.enums.ReservationPolicy;

public record AddStoreRequestDto(Boolean fixedReservation, Byte openStart, Byte openEnd){

}
