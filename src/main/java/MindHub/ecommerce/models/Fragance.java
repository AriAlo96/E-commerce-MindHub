package MindHub.ecommerce.models;

public class Fragance {
    private Long id;
    private String name;
    private String description;
    private Gender gender;
    private byte[] image;

    public Fragance() {
    }

    public Fragance(String name, String description, Gender gender, byte[] image) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.image = image;
    }

}
