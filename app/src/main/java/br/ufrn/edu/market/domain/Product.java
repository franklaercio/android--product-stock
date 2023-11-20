package br.ufrn.edu.market.domain;

public class Product {

    private int id;

    private String code;

    private String name;

    private String description;

    private int quantity;

    public Product(int id, String code, String name, String description, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(String code, String name, String description, int quantity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class Builder {
        private int id;
        private String code;
        private String name;
        private String description;
        private int quantity;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(id, code, name, description, quantity);
        }
    }
}
