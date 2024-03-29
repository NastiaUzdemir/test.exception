package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == id) {
                return i;
            }
        }
        return -1;  // Если товар не найден, возвращаем -1
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;  // Если товар не найден, возвращаем null
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Element with id " + id + " not found");
        }

        int index = findIndexById(id);
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (int i = 0; i < products.length; i++) {
            if (i != index) {
                tmp[copyToIndex] = products[i];
                copyToIndex++;
            }
        }
        products = tmp;
    }


}