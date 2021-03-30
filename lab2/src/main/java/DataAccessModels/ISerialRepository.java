package DataAccessModels;

import java.util.List;

public interface ISerialRepository {
    List<SerialDto> getSerials();
    List<String> getSerialsNames();
    void editSerial(String name, SerialDto serialDto);
}
