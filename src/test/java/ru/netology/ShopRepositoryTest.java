package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    private ShopRepository shopRepository;

    @BeforeEach
    public void setUp() {
        shopRepository = new ShopRepository();
    }

    @Test
    public void shouldRemoveExistingElement() {

        Product product1 = new Product(1, "Product 1", 1222);
        Product product2 = new Product(2, "Product 2", 3000);
        shopRepository.add(product1);
        shopRepository.add(product2);


        shopRepository.removeById(1);


        Product[] expected = {product2};
        Product[] actual = shopRepository.findAll();
        Assertions.assertArrayEquals(expected, actual, "Не удалось удалить существующий элемент");
    }

    @Test
    public void shouldThrowNotFoundExceptionForNonExistingElement() {

        Product product1 = new Product(1, "Product 1", 1000);
        shopRepository.add(product1);


        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(2),
                "Не удалось вызвать исключение NotFoundException для несуществующего элемента");
    }
}
