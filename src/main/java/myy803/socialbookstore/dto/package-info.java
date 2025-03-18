/**
 * Data Transfer Objects used to transfer data between the client and the server.
 *
 * <p> DTOs are used to transfer data between the client and the server. They are used to encapsulate
 * the data that is being sent between the client and the server. This is done to prevent the client
 * from having to know the internal structure of the server and vice versa. This allows for the
 * server to change its internal structure without affecting the client and vice versa.
 *
 * <ul>
 *     <li> {@link myy803.socialbookstore.dto.BookDto} - DTO for the {@link myy803.socialbookstore.model.entities.Book} entity.
 *     <li> {@link myy803.socialbookstore.dto.UserProfileDto} - DTO for the {@link myy803.socialbookstore.model.entities.UserProfile} entity.
 * </ul>
 *
 * @since 1.0.0
 *
 * @see myy803.socialbookstore.dto
 * @see myy803.socialbookstore.mapper
 * @see myy803.socialbookstore.model.entities
 * @see org.jetbrains.annotations.NotNull
 * @see org.springframework.stereotype.Component
 * @see lombok.Getter
 * @see lombok.Setter
 *
 * @version 2.0.0
 *
 */
package myy803.socialbookstore.dto;