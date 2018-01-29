public class Growth {
    int id;
    int pet_id;
    String date;
    double weight;
    double length;

    public Growth() {
    }

    public Growth(double length, double weight) {
        this.length = length;
        this.weight = weight;
    }

    public Growth(int pet_id, double weight, double length) {
        this.pet_id = pet_id;
        this.weight = weight;
        this.length = length;
    }

    public Growth(int pet_id, String date, double input, boolean isWeight) {
        this.pet_id = pet_id;
        this.date = date;
        if(isWeight){
            this.weight = input;
        }else{
            this.length = input;
        }

    }

    public Growth(int pet_id, String date, double weight, double length) {
        this.pet_id = pet_id;
        this.date = date;
        this.weight = weight;
        this.length = length;
    }


    public Growth(String date, double weight, double length) {
        this.date = date;
        this.weight = weight;
        this.length = length;
    }


    public Growth(int id, int pet_id, String date, double weight, double length) {
        this.id = id;
        this.pet_id = pet_id;
        this.date = date;
        this.weight = weight;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPet_id() {return pet_id;}

    public void setPet_id(int pet_id) {this.pet_id = pet_id;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
