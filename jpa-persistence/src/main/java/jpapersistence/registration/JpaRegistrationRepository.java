package jpapersistence.registration;

import domain.common.AggregateId;
import domain.common.Username;
import io.vavr.control.Option;
import domain.registration.Registration;
import domain.registration.RegistrationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaRegistrationRepository implements RegistrationRepository {

    private final JpaDao jpaDao;

    @Override
    public Registration save(Registration registration) {
        final RegistrationJpaEntity saved = jpaDao.save(RegistrationJpaEntity.fromSimpleData(registration.toSimpleData()));
        return saved.toDomainObject();
    }

    @Override
    public Option<Registration> findById(AggregateId aggregateId) {
        return Option.ofOptional(jpaDao.findById(aggregateId.getId()))
                .map(RegistrationJpaEntity::toDomainObject);
    }

    @Override
    public boolean existByName(Username username) {
        return jpaDao.existsByUsername(username.getUsername());
    }
}
