public class Pet implements Parcelable {
    int id;
    String name;
    String species;
    String sex;
    String picLocation;
    byte[] picBlob;

    public Pet() {
    }

    public Pet(String name, String species, String sex) {
        this.name = name;
        this.species = species;
        this.sex = sex;
    }

    public Pet(String name, String species, String sex, String picLocation, byte[] picBlob) {
        this.name = name;
        this.species = species;
        this.sex = sex;
        this.picLocation = picLocation;
        this.picBlob = picBlob;
    }


    public Pet(int id, String name, String species, String sex, String picLocation, byte[] picBlob) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.sex = sex;
        this.picLocation = picLocation;
        this.picBlob = picBlob;
    }

    protected Pet(Parcel in) {
        id = in.readInt();
        name = in.readString();
        species = in.readString();
        sex = in.readString();
        picLocation = in.readString();
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPicLocation() {
        return picLocation;
    }

    public void setPicLocation(String picLocation) {
        this.picLocation = picLocation;
    }

    public byte[] getPicBlob() {return picBlob;}

    public void setPicBlob(byte[] picBlob) {this.picBlob = picBlob;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(sex);
        dest.writeString(picLocation);
    }
}
