package course2.lesson7.server;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Сервис аутентификации
 */
public interface AuthService {
    /**
     * Запустить сервис.
     */
    void start() throws SQLException;

    /**
     * Отключить сервис.
     */
    void stop();

    /**
     * Получить никнейм по логину/паролю
     *
     * @param login
     * @param pass
     * @return никнейм если найден или null, если такого нет
     */
    String getNickByLoginAndPass(String login, String pass);

    String changeNick(String nick, String nick1);
}
