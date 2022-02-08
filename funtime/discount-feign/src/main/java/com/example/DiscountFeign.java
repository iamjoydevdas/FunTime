package com.example;
import org.springframework.cloud.openfeign.FeignClient;

import com.example.discount.api.IDiscountAPi;

@FeignClient("discount-service")
public interface DiscountFeign extends IDiscountAPi  {

}
