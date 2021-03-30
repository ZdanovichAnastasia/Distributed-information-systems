package DataAccessModels;

import java.io.IOException;
import java.util.List;

public interface IMessageRepository {
    void saveMessages(List<String> messages)throws IOException;
}
