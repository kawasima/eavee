package net.unit8.eavee.provider;

import net.unit8.eavee.EavField;
import net.unit8.eavee.entity.EavFieldEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JpaEavAttributeProvider implements EavAttributeProvider {
    private final Map<String, List<EavFieldEntity>> eavFields;

    public JpaEavAttributeProvider() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EavFieldEntity> query = cb.createQuery(EavFieldEntity.class);
            query.from(EavFieldEntity.class);
            eavFields = em.createQuery(query).getResultStream()
                    .collect(Collectors.groupingBy(EavField::getType));
        } finally {
            em.close();
            factory.close();
        }
    }

    @Override
    public List<? extends EavField> get(String type) {
        return eavFields.get(type);
    }
}
