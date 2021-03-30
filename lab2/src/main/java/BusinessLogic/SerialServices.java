package BusinessLogic;

import BusinessLogicModels.ISerialServices;
import BusinessLogicModels.Serial;
import DataAccessModels.ISerialRepository;
import DataAccessModels.SerialDto;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SerialServices implements ISerialServices {
    private ISerialRepository serialRepository;

    public SerialServices(ISerialRepository repository){
        this.serialRepository = repository;
    }
    @Override
    public List<Serial> filterSerialByRating(double rating) {
        List<SerialDto> serialDtoList = serialRepository.getSerials();

        List<Serial> serialList = new ArrayList<>();
        for(SerialDto serialDto: serialDtoList){
            if(serialDto.getRating()> rating){
                Serial serial = new Serial(serialDto.getName(), serialDto.getRating(), serialDto.getCount_episodes());
                serialList.add(serial);
            }
        }
        return serialList;
    }

    @Override
    public void editSerial(String name, Serial serial) {
        List<SerialDto> serialDtoList = serialRepository.getSerials();

        for(SerialDto serialDto: serialDtoList){
            if(serialDto.getName().equals(name)){
                if(serial.getName().isEmpty()){
                    serial.setName(serialDto.getName());
                }
                if(serial.getRating()<0){
                    serial.setRating(serialDto.getRating());
                }
                if(serial.getCount_episodes()==0){
                    serial.setCount_episodes(serialDto.getCount_episodes());
                }
            }
        }
        SerialDto serialDto = new SerialDto(serial.getName(), serial.getRating(), serial.getCount_episodes());
        serialRepository.editSerial(name, serialDto);
    }

    public List<String> getSerialsNames(){
        return serialRepository.getSerialsNames();
    }
}
