package com.example.grocery.service;

import com.example.grocery.beans.Cart;
import com.example.grocery.beans.Customer;
import com.example.grocery.beans.Wishlist;
import com.example.grocery.dto.CartDto;
import com.example.grocery.dto.WishlistDto;
import com.example.grocery.repo.CustomerRepository;
import com.example.grocery.repo.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerRepository customerRepository;



    public String addProductToWishlist(WishlistDto wishlistDto) {
        Wishlist item = wishlistRepository.existsByCustomerId(wishlistDto.getCustomerId(), wishlistDto.getProductId());
        if(item != null){
            return "Already wishlisted this item";
        }else{
            Wishlist wishlist = new Wishlist();
            wishlist.setCustomer(customerRepository.findByCustomerId(wishlistDto.getCustomerId()));
            wishlist.setProduct(productService.findById(wishlistDto.getProductId()));
            wishlist.setProductName(wishlistDto.getProductName());
            wishlist.setQuantity(wishlistDto.getQuantity());
            wishlist.setPrice(wishlistDto.getPrice());
            wishlistRepository.save(wishlist);
            return "Added to wishlist";
        }
    }


    public List<WishlistDto> fetchAllWishlist(Long customerId) {

        List<Wishlist> wishlistist = wishlistRepository.findByCustomerId(customerId);
        List<WishlistDto> wishlistDtosList = new ArrayList<>();
        WishlistDto wishlistDto = null;

        for(Wishlist wishlist: wishlistist){
            wishlistDto = new WishlistDto(wishlist.getCustomer().getCustomerId(), wishlist.getProduct().getProductId(),
                    wishlist.getProductName(), wishlist.getQuantity(), wishlist.getPrice());
            wishlistDtosList.add(wishlistDto);
        }
        System.out.println(wishlistDtosList);
        return wishlistDtosList;
    }

    public void deleteItemFromWishlist(Long customerId, Long productId) {
        wishlistRepository.deletedByProductId(customerId, productId);
    }

    public List<Map<String, Object>> getProductWishlistDemand() {
        List<Object[]> wishlistData = wishlistRepository.findProductWishlistDemand();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : wishlistData) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productId", row[0]);
            productInfo.put("productName", row[1]);
            productInfo.put("productCategory", row[2]);
            productInfo.put("wishlistCount", row[3]); // Count of customers who added this product to their wishlist
            result.add(productInfo);
        }

        return result;
    }
}
