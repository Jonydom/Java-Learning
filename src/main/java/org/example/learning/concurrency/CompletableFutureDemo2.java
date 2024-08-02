package org.example.learning.concurrency;

import org.example.learning.concurrency.dto.Product;
import org.example.learning.concurrency.service.ProductDetailService;
import org.example.learning.concurrency.service.ProductService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 真实场景测试CompletableFuture
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args){
        try {
            // test1();
            // test2();
            test3();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    /**
     * 填充 product 的 name 和 detail
     * 使用方法：supplyAsync 和 thenApply
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws ExecutionException, InterruptedException {
        ProductService productService = new ProductService();
        ProductDetailService productDetailService = new ProductDetailService();
        // 第一步异步，第二步异步
        CompletableFuture<CompletableFuture<Product>> future = CompletableFuture.supplyAsync(() -> {
            Product product = Product.builder().id(1).build();
            String title = productService.getById(product.getId());
            product.setTitle(title);
            return product;
        }).thenApply(product -> CompletableFuture.supplyAsync(() -> {
            String detail = productDetailService.getById(product.getId());
            product.setDetail(detail);
            return product;
        }));
        System.out.println("future = " + future.get().get());
    }

    /**
     * 填充 product 的 name 和 detail
     * 使用方法：supplyAsync 和 thenCompose
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        ProductService productService = new ProductService();
        ProductDetailService productDetailService = new ProductDetailService();
        // 第一步异步，第二步异步
        CompletableFuture<Product> future = CompletableFuture.supplyAsync(() -> {
            Product product = Product.builder().id(1).build();
            String title = productService.getById(product.getId());
            product.setTitle(title);
            return product;
        }).thenCompose(product -> CompletableFuture.supplyAsync(() -> {
            String detail = productDetailService.getById(product.getId());
            product.setDetail(detail);
            return product;
        }));
        System.out.println("future = " + future.get());
    }

    /**
     * 填充 product 的 name 和 detail
     * 请求两个接口，将返回的两个CompletableFuture合并，返回一个新的CompletableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test3() throws ExecutionException, InterruptedException {
        ProductService productService = new ProductService();
        ProductDetailService detailService = new ProductDetailService();
        int id = 1;
        //第1个任务
        CompletableFuture<Product> baseProductFuture = CompletableFuture.supplyAsync(() -> {
            String title = productService.getById(id);
            Product product = new Product();
            product.setTitle(title);
            product.setId(id);
            return product;
        });

        //第2个任务
        CompletableFuture<Product> detailProductFuture = CompletableFuture.supplyAsync(() -> {
            String detail = detailService.getById(id);
            Product product = new Product();
            product.setDetail(detail);
            product.setId(id);
            return product;
        });

        CompletableFuture<Product> future = baseProductFuture.thenCombine(detailProductFuture,
                (baseProduct, detailProduct) -> {
                    baseProduct.setDetail(detailProduct.getDetail());
                    return baseProduct;
                });
        System.out.println("future = " + future.get());
    }
}
