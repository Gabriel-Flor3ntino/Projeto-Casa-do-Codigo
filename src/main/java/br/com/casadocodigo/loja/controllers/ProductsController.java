package br.com.casadocodigo.loja.controllers;

import java.io.IOException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
//	private FileSaver

	// por enquanto não precisamos desse método

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ProductValidator());
//	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile sumary, @Valid @ModelAttribute("produto") Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws IOException {
		System.out.println(sumary.getName() + ";" + sumary.getOriginalFilename());
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}
}