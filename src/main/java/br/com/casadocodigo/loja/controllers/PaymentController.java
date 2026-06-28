package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String checkout() {
        BigDecimal total = shoppingCart.getTotal();

        String uriToPay = "http://book-payment.herokuapp.com/payment";

        try {
            String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
            return "redirect:/paument/success";
        } catch (HttpClientErrorException exception) {
            return "redirect:/paument/error";
        }
    }
}
