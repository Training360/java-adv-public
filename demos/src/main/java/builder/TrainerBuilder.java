package builder;

public class TrainerBuilder {

    private String name = "anonymous";

    private int age = -1;

    public TrainerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TrainerBuilder withAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Invalid age :" + age);
        }
        this.age = age;
        return this;
    }

    public TrainerBuilder withAge(String age) {
        withAge(Integer.parseInt(age));
        return this;
    }

    public Trainer build() {
        if (age == -1) {
            throw new IllegalStateException("Missing age");
        }
        return new Trainer(name, age);
    }
}
