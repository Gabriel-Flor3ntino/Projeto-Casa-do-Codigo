package br.com.casadocodigo.loja.controllers;

import java.io.IOException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO products;
	
	@Autowired
	private FileSaver fileSaver;

	// por enquanto não precisamos desse método

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ProductValidator());
//	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "books", allEntries = true)
	public ModelAndView save(MultipartFile sumary, @Valid @ModelAttribute("produto") Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws IOException {
		System.out.println(sumary.getName() + ";" + sumary.getOriginalFilename());
		
		String webPath = fileSaver.write("upload-images", sumary);
		product.setSummaryPath(webPath);
		products.save(product);
		
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		products.save(product);
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
	@Cacheable(value = "lastProducts")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", products.list());
		return modelAndView;
	}

	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = products.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}

	@RequestMapping("/aprovados")
	public ModelAndView approved(){

		ModelAndView mv = new ModelAndView("products/approved");

		mv.addObject("products",
				products.approvedBooks());

		return mv;

	}

	@RequestMapping("/destaques")
	public ModelAndView featured(){

		ModelAndView mv =
				new ModelAndView("products/featured");

		mv.addObject("products",
				products.featuredBooks());

		return mv;

	}

	@RequestMapping("/categoria/{id}")
	public ModelAndView byCategory(
			@PathVariable Integer id){

		ModelAndView mv =
				new ModelAndView("products/category");

		mv.addObject("products",
				products.findByCategory(id));

		return mv;

	}
	
}