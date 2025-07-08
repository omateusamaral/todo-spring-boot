package io.github.com.omateusamaral.todo_spring_boot.specifications;

import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class TodoSpecifications {

    public static Specification<Todo> hasCompleted(Boolean completed) {
        return (root, query, criteriaBuilder) -> {
            if (completed == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("completed"), completed);
        };
    }

    public static Specification<Todo> titleOrDescriptionContains(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isBlank()) return criteriaBuilder.conjunction();

            String likePattern = "%" + search.toLowerCase() + "%";

            Predicate titleLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likePattern);
            Predicate descriptionLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likePattern);

            return criteriaBuilder.or(titleLike, descriptionLike);
        };
    }
}
