package myy803.socialbookstore.controller;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.RecommendationsDto;
import myy803.socialbookstore.service.impl.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for recommending books to users.
 * The user can recommend books based on his preferences.
 *
 * @see RecommendationService
 * @see RecommendationsDto
 * @see BookDto
 */
@Controller
@RequestMapping("/user")
@Transactional
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @RequestMapping("/recom")
    public String showRecommendationsForm(Model model) {
        model.addAttribute("recomDto", new RecommendationsDto());
        return "user/recommendation_form";
    }

    @RequestMapping("/recommend_offers")
    public String recommend(@ModelAttribute("recomDto") RecommendationsDto recomDto, Model model) {
        String username = getCurrentUsername();
        List<BookDto> bookDtos = recommendationService.recommendBooks(username, recomDto);
        if (bookDtos == null) {
            return "redirect:/user/recom";
        }
        model.addAttribute("books", bookDtos);
        return "user/search_results";
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
