package myy803.socialbookstore.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Service implementation for handling notifications.
 * <p>
 * This service provides methods to create notifications for users and get notifications for users.
 * </p>
 */
@Service
public class NotificationService {

    private final ConcurrentMap<String, List<String>> notifications = new ConcurrentHashMap<>();

    public void createNotification(String username, String message) {
        notifications.computeIfAbsent(username, k -> new ArrayList<>()).add(message);
    }

    public List<String> getNotificationsForUser(String username) {
        return notifications.getOrDefault(username, new ArrayList<>());
    }

    public int getNotificationCountForUser(String currentUsername) {
        return getNotificationsForUser(currentUsername).size();
    }
}
