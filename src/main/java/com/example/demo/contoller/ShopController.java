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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShopController
{
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    List<Integer>  CountNumTmp = new ArrayList<Integer>();
    List<Integer> CountNum = new ArrayList<Integer>();
    List<MProduct> listProduct = new ArrayList<MProduct>();

    List<ProductListForm> productListForm = new ArrayList<ProductListForm>();
    List<ProductListForm> productCartForm = new ArrayList<ProductListForm>();
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


    @RequestMapping(value = "shop/shop_cartin", method = RequestMethod.POST)
    public String getCartIn(Model model, CartListForm cartform, ProductListForm productListForm)
    {

        CountNumTmp.add(productListForm.getCode());
        CountNum= CountNumTmp.stream().distinct().collect(Collectors.toList());


        System.out.println("ttttttttt");

        return "shop/shop_cartin";
    }

    @RequestMapping(value = "shop/shop_cartlook", method = RequestMethod.GET)
    public String getCartlook(Model model, CartListForm cartform)
    {
//        List<MProduct> listProductTmp = new ArrayList<MProduct>();
//        if(productCartForm.get(0).getCode() == null)
//        {
//            tmp
//        }
//
//
        for(int countCode=0; countCode < CountNum.size(); countCode++)
        {


            MProduct tmpProduct = productService.getProduct(countCode+1);
            ProductListForm tmpProductListForm = new ProductListForm();
            tmpProductListForm.setCode(tmpProduct.getCode());
            tmpProductListForm.setName(tmpProduct.getName());
            tmpProductListForm.setPrice(tmpProduct.getPrice());
            productListForm.add(tmpProductListForm);
        }

        List<MProduct> tmpListProduct = new ArrayList<MProduct>();
        tmpListProduct = listProduct.stream().distinct().collect(Collectors.toList());
        listProduct = tmpListProduct;

        model.addAttribute("listProduct",listProduct);

        System.out.println("ttttttttt");
        return "shop/shop_cartlook";
    }

}
