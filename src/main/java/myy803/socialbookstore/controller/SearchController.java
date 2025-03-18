package myy803.socialbookstore.controller;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.service.impl.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for searching books. It handles requests for showing the search form and for searching books.
 *
 * @see SearchService
 * @see SearchDto
 * @see BookDto
 * @see Model
 */
@Controller
@RequestMapping("/user")
@Transactional
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("searchDto", new SearchDto());
        return "user/search_form";
    }

    @RequestMapping("/search_offer")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto, Model model) {
        List<BookDto> bookDtos = searchService.searchBooks(searchDto);
        model.addAttribute("books", bookDtos);
        return "user/search_results";
    }
}