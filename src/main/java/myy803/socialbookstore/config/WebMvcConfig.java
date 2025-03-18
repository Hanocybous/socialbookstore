package myy803.socialbookstore.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures the web MVC.
 * @see WebMvcConfigurer
 * @see WebMvcConfigurer#addViewControllers(ViewControllerRegistry)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Maps the root URL to the homepage view.
     * @param registry the registry to add the view controller
     * @see WebMvcConfigurer#addViewControllers(ViewControllerRegistry)
     * @see ViewControllerRegistry#addViewController(String)
     */
    @Override
    public void addViewControllers(@NotNull ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("homepage");
    }

}