package com.clabs.examples;

import com.clabs.models.Product;
import com.clabs.models.Response;
import com.clabs.stories.Products;
import com.clabs.utils.Connection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

abstract public class ProductsExamples extends Common {

    public static void example(Connection connection) {
        try {

            // 1. Add a new product
            Product sampleProduct = new Product()
                    .setProductRefId("-1")
                    .setName("Test Product")
                    .setDescription("Test product")
                    .setProductType("slot")
                    .setAdjustmentFactor(2f);

            Response insertedProductsResponse = Products.InsertProduct(connection, sampleProduct);
            printErrorsFromResponse(insertedProductsResponse);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the products I have uploaded.
            Response<ArrayList<Product>> products = Products.GetListOfAllMyProducts(connection);
            printErrorsFromResponse(products);

            // 4. Get a list of products based on my external reference id.
            Response<ArrayList<Product>> productsByMyId = Products.GetProductsByExternalRefId(connection,"-1");
            printErrorsFromResponse(productsByMyId);

            // 5. Update and existing records
            for (int i=0; i<productsByMyId.data.size(); i++){
                Response r = Products.UpdateProductById(connection, productsByMyId.data.get(i).setDescription("Updated to " + i));
                printErrorsFromResponse(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushAll(Connection connection) {
        try {
            Response<ArrayList<Product>> products = Products.GetListOfAllMyProducts(connection);
            Products.PermanentlyDeleteListOfProducts(connection, products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateExampleCSV() {

        String ColumnHeader1 = "productRefId";
        String ColumnHeader2 = "productName";
        String ColumnHeader3 = "productType";
        String ColumnHeader4 = "adjustmentFactor";
        int countOfRowsToGenerate = 10000;

        float adjustmentFactor = 1.0f;

        try {
            FileWriter fileWriter = new FileWriter("export-products-sample.csv");

            fileWriter
                    .append(ColumnHeader1).append(',')
                    .append(ColumnHeader2).append(',')
                    .append(ColumnHeader3).append(',')
                    .append(ColumnHeader4).append('\n');

            for (int i = 0; i < countOfRowsToGenerate; i ++) {
                fileWriter
                        .append("my-custom-product-ref-" + i).append(',')
                        .append("product-Name-" + i).append(',')
                        .append("product-Type-" + ((i%2 ==0 )? "Slots" : "Cards") ).append(',')
                        .append(String.valueOf(adjustmentFactor)).append('\n')
                        .flush();
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
