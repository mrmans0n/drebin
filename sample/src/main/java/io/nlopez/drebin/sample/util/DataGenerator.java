package io.nlopez.drebin.sample.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;

/**
 * Generates random data to fill lists
 */
public class DataGenerator {
  private static final Random RANDOM = new Random();

  private static final List<String> names = Arrays.asList("Maria", "Pablo", "Nacho", "Concha", "Pepe", "Tiburcio", "Estanislao", "Afrodisio", "Acisclo", "Juan", "John", "Paul", "Kevin", "Anacleto", "Farts", "Falos", "Perry", "Tyler", "Stan", "Kyle", "Eric", "Kenny");
  private static final List<String> lastNames = Arrays.asList("Perez", "Lopez", "Fernandez", "Mason", "Lee", "Who", "Doe", "Vergo", "Cifuentes", "Menchu", "Fuertes", "Funke", "MacGyver", "Trump", "McLovin", "McCool");
  private static final List<String> roles = Arrays.asList("User", "Admin", "VIP");

  public static List<User> generateUsers(int n) {
    List<User> result = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      String name = names.get(RANDOM.nextInt(names.size()));
      String last = lastNames.get(RANDOM.nextInt(lastNames.size()));
      String role = roles.get(RANDOM.nextInt(roles.size()));
      User user = new User(name, last, role, "http://loremflickr.com/500/500/person/all?random=" + i);
      result.add(user);
    }
    return result;
  }

  public static List<Place> generatePlaces(int n) {
    List<Place> result = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      int length = 5 + RANDOM.nextInt(10);
      StringBuilder sb = new StringBuilder(length);
      for (int j = 0; j < length; j++) {
        char tmp = (char) ('a' + RANDOM.nextInt('z' - 'a'));
        sb.append(tmp);
      }
      Place place = new Place(sb.toString(), "http://loremflickr.com/600/300/city/all?random=" + i);
      result.add(place);
    }
    return result;
  }

  public static List generateMix(int n) {
    List<User> users = generateUsers(n);
    List<Place> places = generatePlaces(n);
    List result = new ArrayList(n);
    for (int i = 0; i < n; i++) {
      int index = RANDOM.nextInt(n);
      if (RANDOM.nextBoolean()) {
        result.add(users.get(index));
      } else {
        result.add(places.get(index));
      }
    }
    return result;
  }

}
