package BehavioralDesignPatterns.UseCase1;

public class EmailSubscriber implements Subscriber {
    private String name;

    public EmailSubscriber(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println("Email to " + name + ": New article published - " + article);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
