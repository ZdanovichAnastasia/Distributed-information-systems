package DataAccessModels;

public class SerialDto {
    public String name;
    public double rating;
    public int count_episodes;

    public SerialDto(){}

    public SerialDto(String name, double rating, int count_episodes){
        this.name = name;
        this.rating =rating;
        this.count_episodes = count_episodes;
    }

    public String getName() {
        return name;
    }
    public double getRating() {
        return rating;
    }
    public int getCount_episodes() {
        return count_episodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCount_episodes(int count_episodes) {
        this.count_episodes = count_episodes;
    }
}
