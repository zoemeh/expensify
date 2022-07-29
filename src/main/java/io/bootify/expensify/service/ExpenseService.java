package io.bootify.expensify.service;

import io.bootify.expensify.domain.Expense;
import io.bootify.expensify.model.ExpenseDTO;
import io.bootify.expensify.repos.ExpenseRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseDTO> findAll() {
        var excel = new ExcelOperation("C:\\dev\\expenses.xlsx");
        var list = new ArrayList<ExpenseDTO>();
        for (int i = 1; i < 10; i++) {
            var row = excel.getDataFromExcel(i);
            var dto = new ExpenseDTO();
            if (row.isEmpty() || row.get("Date").isEmpty()) {
                continue;
            }
            // TODO get date (parse etc etc)
            // TODO do something when a cell is empty
            dto.setId(Long.valueOf(i));
            dto.setAccount(row.get("Account"));
            dto.setDescription(row.get("Description"));
            dto.setHotel(Double.parseDouble(row.get("Hotel")));
            dto.setTransport(Double.parseDouble(row.get("Transport")));
            dto.setFuel(Double.parseDouble(row.get("Fuel")));
            dto.setMeals(Double.parseDouble(row.get("Meals")));
            dto.setPhone(Double.parseDouble(row.get("Phone")));
            dto.setEntertainment(Double.parseDouble(row.get("Entertainment")));
            // revisar por que esto falla
            //  dto.setMisc(Double.parseDouble(row.get("Misc")));
            list.add(dto);
        }
        return list;
    }

    public ExpenseDTO get(final Long id) {
        var excel = new ExcelOperation("C:\\dev\\expenses.xlsx");

        return expenseRepository.findById(id)
                .map(expense -> mapToDTO(expense, new ExpenseDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ExpenseDTO expenseDTO) {
        final Expense expense = new Expense();
        mapToEntity(expenseDTO, expense);
        var excel = new ExcelOperation("C:\\dev\\expenses.xlsx");
        return excel.CreateRecord(expenseDTO).getId();
    }

    public void update(final Long id, final ExpenseDTO expenseDTO) {
        final Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(expenseDTO, expense);
        expenseRepository.save(expense);
    }

    public void delete(final Long id) {
        expenseRepository.deleteById(id);
    }

    private ExpenseDTO mapToDTO(final Expense expense, final ExpenseDTO expenseDTO) {
        expenseDTO.setId(expense.getId());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setDate(expense.getDate());
        expenseDTO.setAccount(expense.getAccount());
        expenseDTO.setHotel(expense.getHotel());
        expenseDTO.setTransport(expense.getTransport());
        expenseDTO.setFuel(expense.getFuel());
        expenseDTO.setMeals(expense.getMeals());
        expenseDTO.setPhone(expense.getPhone());
        expenseDTO.setEntertainment(expense.getEntertainment());
        expenseDTO.setMisc(expense.getMisc());
        return expenseDTO;
    }

    private Expense mapToEntity(final ExpenseDTO expenseDTO, final Expense expense) {
        expense.setDescription(expenseDTO.getDescription());
        expense.setDate(expenseDTO.getDate());
        expense.setAccount(expenseDTO.getAccount());
        expense.setHotel(expenseDTO.getHotel());
        expense.setTransport(expenseDTO.getTransport());
        expense.setFuel(expenseDTO.getFuel());
        expense.setMeals(expenseDTO.getMeals());
        expense.setPhone(expenseDTO.getPhone());
        expense.setEntertainment(expenseDTO.getEntertainment());
        expense.setMisc(expenseDTO.getMisc());
        return expense;
    }

}
