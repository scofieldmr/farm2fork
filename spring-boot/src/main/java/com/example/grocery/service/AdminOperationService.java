package com.example.grocery.service;


import com.example.grocery.beans.Admin;
import com.example.grocery.beans.Product;
import com.example.grocery.dto.ProductDto;
import com.example.grocery.repo.AdminOperationProductRepository;
import com.example.grocery.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminOperationService {

    @Autowired
    AdminOperationProductRepository adminOperationProductRepository;

    @Autowired
    AdminRepository adminRepository;

    public void addProductService(ProductDto product) {

        Product insertProduct = new Product();
        insertProduct.setProductName(product.getProductName());
        insertProduct.setProductCategory(product.getProductCategory());
        insertProduct.setQuantity(product.getQuantity());
        insertProduct.setPrice(product.getPrice());

        adminOperationProductRepository.save(insertProduct);
    }

    public void deleteProductById(Long id){
        adminOperationProductRepository.deleteById(id);
    }

    public void updateAdminDetails(Long adminId, Admin admin) {
        Admin adminTemp = adminRepository.findByAdminId(adminId);
        adminTemp.setAdminName(admin.getAdminName());
        adminTemp.setEmail(admin.getEmail());
        adminTemp.setAddress(admin.getAddress());
        adminTemp.setContactNumber(admin.getContactNumber());
        adminTemp.setEmailDomain(admin.getEmailDomain());
        adminTemp.setPassword(admin.getPassword());
        adminRepository.save(adminTemp);
    }

    public Admin findByAdminId(Long adminId) {
        return adminRepository.findByAdminId(adminId);
    }

    public Admin deactivateUserService(Long adminId) {
        Admin admin = adminRepository.findByAdminId(adminId);
        admin.setActiveUser("No");
        return adminRepository.save(admin);
    }

    public void updateProductById(long id, Product product) {
        Product findProduct = adminOperationProductRepository.findByProductId(id);
        if(findProduct != null){
            if(product.getPrice() > 0){
                findProduct.setPrice(product.getPrice());
            }
            if(product.getQuantity() > 0){
                findProduct.setQuantity(product.getQuantity());
            }
            adminOperationProductRepository.save(findProduct);
        }
    }
}
