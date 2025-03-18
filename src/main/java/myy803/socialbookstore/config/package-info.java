/**
 * Contains configuration classes for the application.
 *
 * <p> These classes are used to configure the application, such as setting up the database connection, configuring the
 * Spring Security, etc.
 *
 * <p> The classes in this package are annotated with {@link org.springframework.context.annotation.Configuration}
 * and {@link org.springframework.context.annotation.Bean} annotations.
 * The {@link org.springframework.context.annotation.Configuration} annotation indicates that the class is a
 * configuration class, and the {@link org.springframework.context.annotation.Bean} annotation indicates that the
 * method returns a bean that will be managed by the Spring container.
 *
 * <ul>
 *     <li>{@link myy803.socialbookstore.config.CustomSecuritySuccessHandler} - Customizes the success handler for the Spring Security.</li>
 *     <li>{@link myy803.socialbookstore.config.WebMvcConfig} - Configures the Spring MVC.</li>
 *     <li>{@link myy803.socialbookstore.config.WebSecurityConfig} - Configures the Spring Security.</li>
 * </ul>
 *
 * @since 1.0.0
 *
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Bean
 *
 * @version 2.0.0
 *
 * @apiNote The classes in this package are used to configure the application.
 */
package myy803.socialbookstore.config;