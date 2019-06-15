package com.chandanws.fse.springboot.controller;

import com.chandanws.fse.springboot.dto.BookDto;
import com.chandanws.fse.springboot.dto.SubjectDto;
import com.chandanws.fse.springboot.service.BookService;
import com.chandanws.fse.springboot.service.SubjectService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static com.chandanws.fse.springboot.constants.AppConstants.*;

@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, String> menuBasedViewName = null;

    private Validator validator;
    private SubjectService subjectService;
    private BookService bookService;

    @Autowired
    public IndexController(SubjectService subjectService,
                           BookService bookService) {
        this.subjectService = subjectService;
        this.bookService = bookService;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @PostConstruct
    private void init() {
        menuBasedViewName = new HashMap<>();
        menuBasedViewName.put(KEY_AB, VIEW_AB);
        menuBasedViewName.put(KEY_AS, VIEW_AS);
        menuBasedViewName.put(KEY_DS, VIEW_DS);
        menuBasedViewName.put(KEY_DB, VIEW_DB);
        menuBasedViewName.put(KEY_SB, VIEW_SB);
        menuBasedViewName.put(KEY_SS, VIEW_SS);
    }

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.registerCustomEditor(Set.class, "references", new CustomCollectionEditor(Set.class){
//
//            @Override
//            protected Object convertElement(Object element) {
//                logger.debug("===============> here we are type of {}", element.getClass());
//                if(element instanceof Long) {
//                    logger.debug("===============> element: {}", element);
//                    return new Book((Long)element);
//                }
//                return element;
//            }
//        });
//    }

    @ModelAttribute("books")
    public List<BookDto> books() {
        return bookService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return VIEW_IDX;
    }

    @GetMapping("form")
    public String form(@RequestParam("selected") String selectedMenu) {
        if(menuBasedViewName.containsKey(selectedMenu))
            return menuBasedViewName.get(selectedMenu);
        else
            return VIEW_ERR;
    }

    @PostMapping(value = "/addbook", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addBook(BookDto bookDto, BindingResult result, Model model) {

        logger.debug("{}", bookDto);

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        List<String> errors = null;

        if(!violations.isEmpty()) {
            errors = new ArrayList<>();
        }

        for (ConstraintViolation<BookDto> violation : violations ) {

            String propertypath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            logger.error("validation error at {} message: {}", propertypath, message);
            errors.add(message);
        }

        if(Objects.nonNull(errors) && !errors.isEmpty()) {
            model.addAttribute(ERRORS, errors);
            return menuBasedViewName.get(VIEW_AB);
        }

        bookService.save(bookDto);

        model.addAttribute(STATUS, "Book is added successfully");

        return menuBasedViewName.get(VIEW_AB);
    }

    @PostMapping(value = "/addsubject", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addSubject(SubjectDto subjectDto,
                             BindingResult result,
                             Model model) {

        logger.debug("{}", subjectDto);
        logger.debug("binding result {}", result);

        Set<ConstraintViolation<SubjectDto>> violations = validator.validate(subjectDto);

        List<String> errors = null;

        if(!violations.isEmpty()) {
            errors = new ArrayList<>();
        }

        for (ConstraintViolation<SubjectDto> violation : violations) {

            String propertyPath = violation.getPropertyPath().toString();

            String message = violation.getMessage();

            logger.error("Validation error at {}, message: {}", propertyPath, message);

            errors.add(message);
        }

        if(result.hasErrors()) {
            List<String> bindingErrors = result.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            errors.addAll(bindingErrors);
        }

        if(Objects.nonNull(errors) && !errors.isEmpty()) {
            model.addAttribute(ERRORS, errors);
            return menuBasedViewName.get(KEY_AS);
        }

        subjectService.save(subjectDto);

        model.addAttribute(STATUS, "Subject is added successfully");

        return menuBasedViewName.get(KEY_AS);
    }

    @PostMapping(value = "/deletesubject")
    public String deleteSubject(@RequestParam("id") String id, Model model) {

        logger.debug("{}", id);

        subjectService.deleteById(Long.parseLong(id));

        model.addAttribute(STATUS, "Subject is deleted successfully");

        return menuBasedViewName.get(KEY_DS);
    }

    @PostMapping(value = "/deletebook")
    public String deleteBook(@RequestParam("id") String id, Model model) {

        logger.debug("{}", id);

        bookService.deleteById(Long.parseLong(id));

        model.addAttribute(STATUS, "Book is deleted successfully");

        return menuBasedViewName.get(KEY_DB);
    }

    @PostMapping(value = "/searchbook")
    public String searchBook(@RequestParam("id") Long id, Model model) {

        logger.debug("{}", id);
        try {
            Optional<BookDto> found = bookService.findById(id);

            BookDto bookDto = found.orElseGet(null);

            model.addAttribute(RESULT, bookDto);

            logger.debug("{}", bookDto);

        } catch (Exception ex) {
            logger.error("Book not found with id {}", id);
        }

        model.addAttribute(IS_SRCH_INITIATED, true);

        return menuBasedViewName.get(KEY_SB);
    }

    @PostMapping(value = "/searchsubject")
    public String searchSubject(@RequestParam("id") String id, Model model) {

        logger.debug("{}", id);

        try {
            SubjectDto subjectDto = subjectService.findById(Long.parseLong(id)).orElseGet(null);

            model.addAttribute(RESULT, subjectDto);

            logger.debug("{}", subjectDto);

        } catch (Exception ex) {
            logger.error("No subject found with given id {}", id);
        }

        model.addAttribute(IS_SRCH_INITIATED, true);

        return menuBasedViewName.get(KEY_SS);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDataIntegrityException(Exception ex, Model model) {

        List<String> errors = new ArrayList<>();

        errors.add(ExceptionUtils.getRootCauseMessage(ex));

        model.addAttribute(ERRORS, errors);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException(Exception ex, Model model) {

        List<String> errors = new ArrayList<>();

        errors.add(ExceptionUtils.getRootCauseMessage(ex));

        model.addAttribute(ERRORS, errors);

    }

    @ExceptionHandler(Exception.class)
    public void handleOtherException(Exception ex, Model model) {
        ex.printStackTrace();
        List<String> errors = new ArrayList<>();

        errors.add(ExceptionUtils.getRootCauseMessage(ex));

        model.addAttribute(ERRORS, errors);

    }
}
