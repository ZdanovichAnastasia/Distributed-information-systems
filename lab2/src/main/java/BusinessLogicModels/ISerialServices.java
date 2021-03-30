package BusinessLogicModels;

import java.util.List;

public interface ISerialServices {
    List<Serial> filterSerialByRating(double rating);
    void editSerial(String name, Serial serial);
    List<String> getSerialsNames();
}
