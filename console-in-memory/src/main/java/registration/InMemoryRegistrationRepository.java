package registration;

import common.AggregateId;
import common.Username;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

class InMemoryRegistrationRepository implements RegistrationRepository {

    private Map<AggregateId, Registration> repo = HashMap.empty();

    @Override
    public Registration save(final Registration registration) {
        repo = repo.put(registration.getId(), registration);
        return registration;
    }

    @Override
    public Option<Registration> findById(final AggregateId aggregateId) {
        return repo.get(aggregateId);
    }

    @Override
    public boolean existByName(final Username username) {
        return repo.exists(it -> it._2
                .toResult()
                .getUsername()
                .equals(username.getUsername()));
    }
}
