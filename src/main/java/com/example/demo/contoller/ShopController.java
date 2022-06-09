package com.example.demo.contoller;

import com.example.demo.domain.model.MProduct;
import com.example.demo.form.CartListForm;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController
{
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("shop/shop_list")
//    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK, method = RequestMethod.POST)
    public String postProductAddCheckBack(Model model, @ModelAttribute ProductListForm form)
    {
        List<MProduct>  productList = productService.getProducts();
        System.out.println(productList);
        model.addAttribute("productList",productList);
        return "shop/shop_list";
    }

    @RequestMapping(value = "shop/shop_product/{code:.+}", method = RequestMethod.GET)
//    @GetMapping("user/detailTask/{id:.+}")
    //@GetMapping("/detailTask/9")
    //public ModelAndView getDetailTask(@ModelAttribute TaskForm form, Model model,@PathVariable("id") Integer id,ModelAndView modelAndView)
    public String getDetailTask( Model model,@PathVariable("code") Integer code)
    {

        MProduct product = MProduct.builder().build();
        product = productService.getProduct(code);
        ProductListForm form = new ProductListForm();
        form.setCode(product.getCode());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setGazou(product.getGazou());
        System.out.println("shop_product");
        System.out.println(form);
        System.out.println(product.getCode());
        model.addAttribute(form);
        return "shop/shop_product";
    }


    @RequestMapping(value = "shop/shop_cartin", method = RequestMethod.GET)
    public String getCartIn(Model model, CartListForm cartform)
    {
        System.out.println("ttttttttt");

        return "shop/shop_cartin";
    }


}
