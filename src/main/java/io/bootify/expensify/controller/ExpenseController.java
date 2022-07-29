package io.bootify.expensify.controller;

import io.bootify.expensify.model.ExpenseDTO;
import io.bootify.expensify.service.ExpenseService;
import io.bootify.expensify.util.WebUtils;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String list(final Model model) {

        model.addAttribute("expenses", expenseService.findAll());
        return "expense/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("expense") final ExpenseDTO expenseDTO) {
        return "expense/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("expense") @Valid final ExpenseDTO expenseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "expense/add";
        }
        expenseService.create(expenseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("expense.create.success"));
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("expense", expenseService.get(id));
        return "expense/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("expense") @Valid final ExpenseDTO expenseDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "expense/edit";
        }
        expenseService.update(id, expenseDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("expense.update.success"));
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        expenseService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("expense.delete.success"));
        return "redirect:/expenses";
    }

}
