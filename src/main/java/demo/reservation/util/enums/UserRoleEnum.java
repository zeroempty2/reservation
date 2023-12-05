package demo.reservation.util.enums;

public enum UserRoleEnum {
  CUSTOMER(Authority.CUSTOMER),
  MANAGER(Authority.MANAGER);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String MANAGER = "ROLE_MANAGER";
  }
}
