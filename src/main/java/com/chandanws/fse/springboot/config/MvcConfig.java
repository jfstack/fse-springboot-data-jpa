package com.chandanws.fse.springboot.config;

import com.chandanws.fse.springboot.persistence.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/assets/");
    }

    /**
     * Formatter to format String to LocalDate conversion and vise versa for publishDate field
     *
     * Formatter to format String to Book conversion and vise versa for books multi-select field
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);

        /** Formatter for binding LocalDate property of the model to date form control*/
        registry.addFormatterForFieldType(LocalDate.class, new Formatter<LocalDate>() {

            @Override
            public String print(LocalDate localDate, Locale locale) {
                return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }

            @Override
            public LocalDate parse(String s, Locale locale) throws ParseException {
                return LocalDate.parse(s);
            }
        });

        /** Formatter for binding books property of the model to multi-select form control*/
        registry.addFormatter(new Formatter<Book>() {

            @Override
            public String print(Book book, Locale locale) {
                return String.valueOf(book.getBookId());
            }

            @Override
            public Book parse(String text, Locale locale) throws ParseException {
                return new Book(Long.parseLong(text));
            }
        });
    }


}
