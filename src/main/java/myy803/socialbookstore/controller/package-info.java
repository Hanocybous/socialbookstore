/**
 * Contains the controllers of the application.
 *
 * <p> The controllers are responsible for handling the user input and updating the model.
 * They are the intermediaries between the view and the model.
 *
 * <p> The controllers are annotated with {@link org.springframework.stereotype.Controller} and
 * {@link org.springframework.web.bind.annotation.RequestMapping} to indicate that they are Spring
 * controllers and to specify the URL path that they handle.
 *
 * <p> The controllers use the {@link org.springframework.web.bind.annotation.ModelAttribute}
 * annotation to bind the model attributes to the request parameters.
 *
 * <ul>
 *     <li> {@link myy803.socialbookstore.controller.BookOfferController} - Handles the book offers
 *     <li> {@link myy803.socialbookstore.controller.BookRequestController} - Handles the book requests
 *     <li> {@link myy803.socialbookstore.controller.RecommendationController} - Handles the recommendations
 *     <li> {@link myy803.socialbookstore.controller.UserController} - Handles the users
 *     <li> {@link myy803.socialbookstore.controller.UserProfileController} - Handles the user profiles
 *     <li> {@link myy803.socialbookstore.controller.SearchController} - Handles the search
 *     <li> {@link myy803.socialbookstore.controller.AuthController} - Handles the authentication
 * </ul>
 *
 * @since 1.0.0
 *
 * @see myy803.socialbookstore.model
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @see org.springframework.web.bind.annotation.ModelAttribute
 * @version 2.0.0
 */
package myy803.socialbookstore.controller;