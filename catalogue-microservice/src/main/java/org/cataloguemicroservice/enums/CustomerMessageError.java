package org.cataloguemicroservice.enums;

public enum CustomerMessageError {
    PRODUCT_NOT_FOUND("Product not found. "),
    CATEGORY_NOT_FOUND("Category not found. "),
    PRODUCT_NOT_FOUND_LABEL_EQUALS("Product Not found with label =   "),
    PRODUCT_NOT_FOUND_WITH_SLUG_EQUALS("Product Not found with slug =   "),
    CATEGORY_NOT_FOUND_WITH_LABEL_EQUALS("Category Not found with label =   "),
    CATEGORY_NOT_FOUND_SLUG_EQUALS("Category Not found with slug =   "),
    PRODUCT_NOT_FOUND_WITH_ID_EQUALS("Product Not found with id =   "),
    CATEGORY_NOT_FOUND_WITH_ID_EQUALS("Category Not found with id =   ");

    private final String msg;
    CustomerMessageError(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
