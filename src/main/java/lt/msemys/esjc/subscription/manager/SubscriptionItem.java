package lt.msemys.esjc.subscription.manager;

import lt.msemys.esjc.subscription.SubscriptionOperation;
import lt.msemys.esjc.tcp.ChannelId;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static lt.msemys.esjc.util.Preconditions.checkNotNull;

public class SubscriptionItem {
    public final SubscriptionOperation operation;
    public final int maxRetries;
    public final Duration timeout;
    public final Instant createdTime;

    public ChannelId connectionId;
    public UUID correlationId;
    public boolean isSubscribed;
    public int retryCount;
    public Instant lastUpdated;

    public SubscriptionItem(SubscriptionOperation operation, int maxRetries, Duration timeout) {
        checkNotNull(operation, "operation");

        this.operation = operation;
        this.maxRetries = maxRetries;
        this.timeout = timeout;
        this.createdTime = Instant.now();

        correlationId = UUID.randomUUID();
        retryCount = 0;
        lastUpdated = Instant.now();
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Subscription ").append(operation.getClass().getSimpleName())
            .append(" (").append(correlationId).append("): ").append(operation)
            .append(", is subscribed: ").append(isSubscribed)
            .append(", retry count: ").append(retryCount)
            .append(", created: ").append(createdTime)
            .append(", last updated: ").append(lastUpdated)
            .toString();
    }
}
