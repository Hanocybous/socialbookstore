/**
 * Provides classes to map entities to Data Transfer Objects (DTOs) and vice versa.
 *
 * <p>This package contains mappers that handle the conversion between entities
 * (e.g., Book, User) and their respective DTO representations (e.g., BookDto, UserDto).
 * These mappers facilitate separation between the data layer and the presentation layer
 * by allowing only the necessary data to be transferred between them.</p>
 *
 * <p>Each mapper class typically includes methods for converting from entity
 * to DTO and from DTO to entity, ensuring consistency and reusability across
 * the application.</p>
 *
 * @since 1.0.0
 * @see myy803.socialbookstore.dto
 * @see myy803.socialbookstore.model
 * @see myy803.socialbookstore.mapper
 * @see myy803.socialbookstore.service
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @implNote Before implementing a new mapper, consider the following:
 * <ul>
 *     <li>Ensure that the entity and DTO classes are correctly defined.</li>
 *     <li>Implement the conversion methods for the entity and DTO classes.</li>
 *     <li>Use the mapper in the service layer to convert between entities and DTOs.</li>
 *     <li>Test the mapper to ensure that the conversion is correct.</li>
 * </ul>
 *
 * @apiNote The mappers in this package are used to convert between entities and DTOs.
 * They are used in the service layer to convert between the data layer and the presentation layer.
 * The mappers are used to ensure that only the necessary data is transferred between the layers.
 *
 * @version 2.0.0
 */
package myy803.socialbookstore.mapper;