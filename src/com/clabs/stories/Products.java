package com.clabs.stories;

import com.clabs.models.Product;
import com.clabs.models.Response;
import com.clabs.utils.Connection;
import com.clabs.utils.ConnectionResultWrapper;
import com.clabs.utils.Json;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Products {

    public  final static String RESOURCE_PATH = "products";
    private final static Type responseTypeProduct = new TypeToken<Response<Product>>(){}.getType();
    private final static Type responseTypeListProducts = new TypeToken<Response<ArrayList<Product>>>(){}.getType();
    private final static Type responseTypeBoolean = new TypeToken<Response<Boolean>>(){}.getType();

    /**
     * This shows how you retrieve all the products you have
     * We need to get a count of all the records without returning results.
     * The default result set is defaulted to skip=0, limit=20. We will override this so we can get all
     * the records in one go. The MAX to get in one request is 10,000
     * @param connection The http connection handler
     * @return Response object with a list of Product objects
     * @throws Exception
     */
    public static Response<ArrayList<Product>> GetListOfAllMyProducts(Connection connection) throws Exception {

        Response<ArrayList<Product>> count = GetACountOfAllProducts(connection);
        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, count.getTotalRecordsFound());

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeListProducts);
    }

    /**
     * This gets a count of all the products you have uploaded and does not return any product objects as part of the response.
     * @param connection The http connection handler
     * @return Response object with an empty list of Product objects and metadata with total record count
     * @throws Exception
     */
    public static Response<ArrayList<Product>> GetACountOfAllProducts(Connection connection) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, 0);

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeListProducts);
    }

    /**
     * This shows how a filter can be used to get records matching the filter criteria.
     * @param connection The http connection handler
     * @param externalProductRefId The parameter to filter the resultset by.
     * @return Response object with a list of Product objects matching the filter criteria
     * @throws Exception
     */
    public static Response<ArrayList<Product>> GetProductsByExternalRefId(Connection connection, String externalProductRefId) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, "productRefId="+externalProductRefId);

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeListProducts);
    }

    /**
     * Shows how a single product object can be added to your space.
     * @param connection The http connection handler
     * @param product The product to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertProduct(Connection connection, Product product) throws Exception {

        ArrayList<Product> newProduct = new ArrayList<Product>();
        newProduct.add(product);

        return InsertListOfProducts(connection, newProduct);
    }

    /**
     * Shows how a list of new product records can be added to your space.
     * @param connection The http connection handler
     * @param products The products to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertListOfProducts(Connection connection, List<Product> products) throws Exception {

        String body = Json.GSON.toJson(products);
        ConnectionResultWrapper out = connection.sendPost(RESOURCE_PATH, body);

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeBoolean);
    }

    /**
     * Shows how a product records can be modified.
     * @param connection connection The http connection handler
     * @param product
     * @return
     * @throws Exception
     */
    public static Response<Product> UpdateProductById(Connection connection, Product product) throws Exception {

        String json = Json.GSON.toJson(product);
        String resourcePath = RESOURCE_PATH + "/" + product.id;

        ConnectionResultWrapper out = connection.sendPut(resourcePath, json);

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeProduct);
    }

    /**
     * Shows how a product records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param products
     * @return
     * @throws Exception
     */
    public static List<ConnectionResultWrapper> PermanentlyDeleteListOfProducts(Connection connection, Response<ArrayList<Product>> products) throws Exception {

        List responses = new ArrayList<ConnectionResultWrapper>();

        for(Product product: products.data) {
            if(!(product.productRefId.equalsIgnoreCase("fruits")))
                responses.add( PermanentlyDeleteAProductById(connection, product.id) );
        }

        return responses;
    }

    /**
     * Shows how a product records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param id
     * @return
     * @throws Exception
     */
    public static ConnectionResultWrapper PermanentlyDeleteAProductById(Connection connection, String id) throws Exception {
        String resourcePath = RESOURCE_PATH + "/" + id;

        return connection.sendDelete(resourcePath, "");
    }
}
