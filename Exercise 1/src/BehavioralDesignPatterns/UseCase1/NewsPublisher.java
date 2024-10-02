package BehavioralDesignPatterns.UseCase1;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<String> articles = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        if (subscriber == null) throw new IllegalArgumentException("Subscriber cannot be null");
        subscribers.add(subscriber);
        System.out.println(subscriber.getName() + " has subscribed.");
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getName() + " has unsubscribed.");
    }

    public void publishArticle(String article) {
        if (article == null || article.isEmpty()) throw new IllegalArgumentException("Article cannot be null or empty");
        articles.add(article);
        notifySubscribers(article);
    }

    private void notifySubscribers(String article) {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.update(article);
            } catch (Exception e) {
                System.err.println("Failed to notify subscriber: " + subscriber.getName());
            }
        }
    }
}
