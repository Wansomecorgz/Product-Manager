package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.data.Book;
import ru.netology.data.Product;
import ru.netology.data.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private final ProductRepository repository = new ProductRepository();
    private final ProductManager manager = new ProductManager(repository);

    Book book1 = new Book(1, "Щегол", 650, "Донна Тартт");
    Book book2 = new Book(2, "Железный Совет", 450, "Чайна Мьёвиль");
    Book book3 = new Book(3, "Немного ненависти", 700, "Джо Аберкромби");
    Smartphone phone1 = new Smartphone(4, "Iphone 11", 54999, "Apple");
    Smartphone phone2 = new Smartphone(5, "Iphone 12", 68499, "Apple");
    Smartphone phone3 = new Smartphone(6, "Iphone 13", 89999, "Apple");


    @Test
    void shouldAddProducts() {
        manager.add(book1);
        manager.add(book2);
        repository.save(phone1);
        repository.save(phone2);
        manager.add(phone3);

        Product[] expected = {book1, book2, phone1, phone2, phone3};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFindById() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product expected = book3;
        Product actual = manager.findById(3);
        assertEquals(expected, actual);
    }


    @Test
    void shouldRemoveById() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        manager.removeById(2);
        manager.removeById(6);

        Product[] expected = {book1, book3, phone1, phone2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAllProducts() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {book1, book2, book3, phone1, phone2, phone3};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByTheName() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Немного ненависти");

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldRemoveFromRepositoryById() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        repository.removeById(6);

        Product[] expected = {book1, book2, book3, phone1, phone2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
        // найти определенную модель
    void shouldFindByName() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {phone3};
        Product[] actual = manager.searchBy("Iphone 13");
        assertArrayEquals(expected, actual);
    }


    @Test
        // найти книгу которой нет в списке
    void shouldFindBookOutOfList() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("book4");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByNonexistentId() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product actual = manager.findById(333);
        assertNull(actual);
    }

    @Test
    void shouldFindAllSmartphones() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {phone1, phone2, phone3};
        Product[] actual = manager.searchBy("phone");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindPhoneById() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product expected = phone3;
        Product actual = repository.findById(6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindNonexistentManufacturer() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }
}