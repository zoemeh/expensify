package io.bootify.expensify.repos;

import io.bootify.expensify.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
