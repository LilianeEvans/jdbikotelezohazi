package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            User user = User.builder()
                    .id(Long.valueOf(1))
                    .username("Halp")
                    .password("halpmeplease")
                    .name("Halp halp")
                    .email("halp@halp.com")
                    .gender(User.Gender.FEMALE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .enabled(true)
                    .build();
            dao.createTable();
            dao.insert(user);
            dao.findById(user.getId());
            dao.findByUsername(user.getUsername());
            System.out.println(user);
            dao.delete(user);
            dao.list().stream().forEach(System.out::println);
        }

    }
}

    //forma:
    //User user = User.builder()
    //        .name("James Bond")
    //        .username("007")
            // ...
    //        .dob(LocalDate.parse("1920-11-11"))
    //        .build();