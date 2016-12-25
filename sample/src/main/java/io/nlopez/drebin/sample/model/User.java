package io.nlopez.drebin.sample.model;

public class User {
  private final String firstName;
  private final String lastName;
  private String role;
  private String avatar;

  public User(String firstName, String lastName, String role, String avatar) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.avatar = avatar;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getRole() {
    return role;
  }

  public String getAvatar() {
    return avatar;
  }
}
